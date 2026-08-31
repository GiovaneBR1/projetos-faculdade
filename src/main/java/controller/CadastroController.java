package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.AuthService;

import java.io.IOException;
import java.time.LocalDate;

public class CadastroController {

    @FXML private TextField campoNome;
    @FXML private TextField campoEmail;
    @FXML private PasswordField campoSenha;
    @FXML private DatePicker campoDataNasc;
    @FXML private Label labelMensagem;

    private final AuthService authService = new AuthService();

    @FXML
    private void acaoCadastrar() {
        labelMensagem.setText("");
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String senha = campoSenha.getText();
        LocalDate dataNasc = campoDataNasc.getValue();

        try {
            // validações básicas
            if (nome == null || nome.isBlank()) throw new Exception("Nome é obrigatório.");
            if (email == null || email.isBlank()) throw new Exception("Email é obrigatório.");
            if (senha == null || senha.length() < 6) throw new Exception("Senha deve ter ao menos 6 caracteres.");

            authService.registrar(nome, email, senha, dataNasc);

            // mensagem de sucesso e retorno para login
            labelMensagem.setStyle("-fx-text-fill:green;");
            labelMensagem.setText("Usuário cadastrado com sucesso.");

            // opcional: voltar automaticamente para o login após cadastro (descomente se quiser)
            // voltarParaLogin();

        } catch (Exception ex) {
            labelMensagem.setStyle("-fx-text-fill:red;");
            labelMensagem.setText(ex.getMessage());
        }
    }

    @FXML
    private void acaoCancelar() {
        voltarParaLogin();
    }

    private void voltarParaLogin() {
        try {
            Stage palco = (Stage) campoNome.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            palco.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            labelMensagem.setStyle("-fx-text-fill:red;");
            labelMensagem.setText("Erro ao voltar ao login: " + e.getMessage());
        }
    }
}
