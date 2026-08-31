package controller;

import java.util.List;

import config.AppConfig;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Usuario;
import service.AuthService;

public class AppController {

    @FXML private Label labelUsuario;
    @FXML private StackPane centroConteudo;

    private AuthService authService;
    private Usuario usuarioAtual;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void definirUsuarioAtual(Usuario usuario) {
        this.usuarioAtual = usuario;
        this.authService = this.authService == null ? new AuthService() : this.authService;
        this.authService.getUsuarioAtual(); 
        inicializarTela();
    }

    private void inicializarTela() {
    	labelUsuario.setText("Usuário: " + usuarioAtual.getNome());
        centroConteudo.getChildren().clear();
    	if (usuarioAtual.getId() == 1) {
    		labelUsuario.setStyle("-fx-text-fill:red;");
    	    TableView<UsuarioLinha> tabela = criarTabelaUsuarios();
    	    Button botaoReset = new Button("Resetar tentativas (Master)");
    	    botaoReset.setOnAction(evt -> {
    	        UsuarioLinha selecionado = tabela.getSelectionModel().getSelectedItem();
    	        if (selecionado == null) {
    	            showAlert("Selecione um usuário na tabela para resetar as tentativas.");
    	            return;
    	        }
    	        try {
    	            authService.liberarTentativasParaUsuario(selecionado.getId());
    	            selecionado.setTentativas(AppConfig.TENTATIVAS_INICIAIS);
    	            tabela.refresh();
    	            showAlert("Tentativas resetadas com sucesso para o usuário ID " + selecionado.getId());
    	        } catch (Exception ex) {
    	            showAlert("Erro ao resetar: " + ex.getMessage());
    	        }
    	    });

    	    VBox box = new VBox(8, tabela, botaoReset);
    	    centroConteudo.getChildren().add(box);
    	}else {
            // usuário normal: mostrar apenas mensagem
    		labelUsuario.setStyle("-fx-text-fill:green;");
            Label bemvindo = new Label("Bem-vindo, " + usuarioAtual.getNome());
            centroConteudo.getChildren().add(bemvindo);
            centroConteudo.setStyle("-fx-font-size:24;");
        }
    	
    }

    @FXML
    private void acaoLogout() {
        authService.logout();
        // volta ao login
        try {
            javafx.stage.Stage palco = (javafx.stage.Stage) labelUsuario.getScene().getWindow();
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            palco.setScene(new javafx.scene.Scene(loader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TableView<UsuarioLinha> criarTabelaUsuarios() {
        TableView<UsuarioLinha> tabela = new TableView<>();

        TableColumn<UsuarioLinha, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());

        TableColumn<UsuarioLinha, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(cell -> cell.getValue().nomeProperty());

        TableColumn<UsuarioLinha, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(cell -> cell.getValue().emailProperty());

        TableColumn<UsuarioLinha, Integer> colTentativas = new TableColumn<>("Tentativas");
        colTentativas.setCellValueFactory(cell -> cell.getValue().tentativasProperty().asObject());

        TableColumn<UsuarioLinha, Boolean> colAtivo = new TableColumn<>("Ativo");
        colAtivo.setCellValueFactory(cell -> cell.getValue().ativoProperty());

        tabela.getColumns().addAll(colId, colNome, colEmail, colTentativas, colAtivo);

        try {
            List<Usuario> usuarios = authService.listarUsuarios();
            // Converte para UsuarioLinha (que exibe tentativas/ativo via consulta adicional)
            java.util.List<UsuarioLinha> linhas = new java.util.ArrayList<>();
            dao.AcessoDAO acessoDAO = new dao.impl.AcessoDAOImpl();
            for (Usuario u : usuarios) {
                model.Acesso a = acessoDAO.buscarPorUsuarioId(u.getId());
                int tent = a != null ? a.getTentativasRest() : 0;
                boolean ativo = a != null && a.isAtivo();
                linhas.add(new UsuarioLinha(u.getId(), u.getNome(), u.getEmail(), tent, ativo));
            }
            tabela.setItems(FXCollections.observableArrayList(linhas));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabela.setRowFactory(tv -> {
            TableRow<UsuarioLinha> row = new TableRow<>();
            row.setOnMouseClicked(evt -> {
                if (evt.getClickCount() == 2 && !row.isEmpty()) {
                    UsuarioLinha linha = row.getItem();
                    // Ao duplo-clique, chama liberação de tentativas
                    try {
                        authService.liberarTentativasParaUsuario(linha.getId());
                        // atualizar visual
                        linha.setTentativas(AppConfig.TENTATIVAS_INICIAIS);
                        tabela.refresh();
                        showAlert("Tentativas resetadas com sucesso para o usuário ID " + linha.getId());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            return row;
        });

        return tabela;
    }
    private void showAlert(String mensagem) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(mensagem);
        a.showAndWait();
    }
}