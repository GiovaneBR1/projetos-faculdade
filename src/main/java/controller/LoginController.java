package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Usuario;
import service.AuthService;

import java.io.IOException;

public class LoginController {

    @FXML private TextField campoEmail;
    @FXML private PasswordField campoSenha;
    @FXML private Button botaoLogin;
    @FXML private Button botaoCadastrar;
    @FXML private Label labelMensagem;

    private final AuthService authService = new AuthService();

    @FXML
    private void acaoLogin() {
        labelMensagem.setText("");
        String email = campoEmail.getText();
        String senha = campoSenha.getText();
        try {
            Usuario u = authService.login(email, senha);
            abrirTelaApp(u);
        } catch (Exception ex) {
            labelMensagem.setText(ex.getMessage());
        }
    }

    @FXML
    private void acaoIrCadastro() {
        try {
            Stage palco = (Stage) botaoCadastrar.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cadastro.fxml"));
            palco.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            labelMensagem.setText("Erro ao abrir cadastro: " + e.getMessage());
        }
    }

    private void abrirTelaApp(Usuario usuario) throws IOException {
        Stage palco = (Stage) botaoLogin.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
        palco.setScene(new Scene(loader.load()));
        // passa usuário e authService para o controller da app
        controller.AppController ctrl = loader.getController();
        ctrl.setAuthService(authService);
        ctrl.definirUsuarioAtual(usuario);
    }
}
