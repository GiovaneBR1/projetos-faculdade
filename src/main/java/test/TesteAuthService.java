package test;

import service.AuthService;
import model.Usuario;
import config.AppConfig;

import java.time.LocalDate;

public class TesteAuthService {
    public static void main(String[] args) {
        System.out.println("TesteAuthService iniciado");

        AuthService auth = new AuthService();

        String emailTeste = "teste_auth_" + System.currentTimeMillis() + "@exemplo.com";
        String senha = "senhaTeste123";

        try {
            // 1) Registrar usuario
            auth.registrar("Usuário Auth", emailTeste, senha, LocalDate.of(1990, 2, 2));
            System.out.println("Usuário registrado: " + emailTeste);

            // 2) Login com senha correta
            Usuario u = auth.login(emailTeste, senha);
            System.out.println("Login bem-sucedido, id = " + u.getId() + " nome = " + u.getNome());

            // 3) Logout
            auth.logout();
            System.out.println("Logout executado.");

            // 4) Tentar login com senha errada repetidamente até bloqueio
            boolean bloqueado = false;
            for (int i = 1; i <= AppConfig.TENTATIVAS_INICIAIS + 1; i++) {
                try {
                    auth.login(emailTeste, "senhaErrada");
                } catch (Exception ex) {
                    System.out.println("Tentativa " + i + " falhou -> " + ex.getMessage());
                    if (ex.getMessage().toLowerCase().contains("bloqueado")) {
                        bloqueado = true;
                        break;
                    }
                }
            }
            System.out.println("Usuario bloqueado? " + bloqueado);

            // 5) (Opcional) Se tiver um usuário master já com hash correto no BD,
            //    você pode criar um AuthService separado para simular liberação.
            //    Aqui não executamos liberarTentativasParaUsuario explicitamente,
            //    pois requer que exista usuário master id=1 com credenciais conhecidas.

            System.out.println("TesteAuthService finalizado.");
        } catch (Exception e) {
            System.err.println("Erro no teste AuthService: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
