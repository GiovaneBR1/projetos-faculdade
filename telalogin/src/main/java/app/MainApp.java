package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;
import service.AuthService;
import util.DatabaseInicializador;
import util.SessionUtil;

public class MainApp extends Application {

	private final AuthService authService = new AuthService();
	public void start(Stage primaryStage) throws Exception {
		// Garante  usuário master 
		try {
			DatabaseInicializador.garantirUsuarioMaster();
		} catch (Exception ex) {
			System.err.println("Falha ao garantir usuário master: " + ex.getMessage());
			ex.printStackTrace();
		}

		 // Verifica arquivo de sessão local
        Integer idLogado = SessionUtil.recuperarUsuarioLogado();
        if (idLogado != null) {
            try {
                Usuario u = authService.buscarUsuarioPorIdParaSessao(idLogado);
                if (u != null) {
                    // carrega app.fxml direto
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
                    primaryStage.setScene(new Scene(loader.load()));
                    primaryStage.setTitle("Sistema de Login - Aplicação");
                    controller.AppController ctrl = loader.getController();
                    ctrl.setAuthService(authService);
                    ctrl.definirUsuarioAtual(u);
                    primaryStage.show();
                    return;
                }
            } catch (Exception e) {
                System.err.println("Sessão inválida: " + e.getMessage());
                SessionUtil.limparSessao();
            }
        }
        // se não houver sessão válida, abre login
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
		primaryStage.setScene(new Scene(loader.load()));
		primaryStage.setTitle("Sistema de Login");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}