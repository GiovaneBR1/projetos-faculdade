package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SessionUtil {

    private static final Path ARQUIVO_SESSAO = Path.of(System.getProperty("user.home"), ".sistema_login_session.properties");
    private static final String CHAVE_ID_USUARIO = "id_usuario";

    public static void gravarUsuarioLogado(int idUsuario) {
        try {
            Properties p = new Properties();
            p.setProperty(CHAVE_ID_USUARIO, String.valueOf(idUsuario));
            try (OutputStream out = Files.newOutputStream(ARQUIVO_SESSAO)) {
                p.store(out, "Sessão do Sistema - arquivo local");
            }
        } catch (IOException e) {
            System.err.println("Erro ao gravar sessão: " + e.getMessage());
        }
    }

    public static Integer recuperarUsuarioLogado() {
        try {
            if (!Files.exists(ARQUIVO_SESSAO)) return null;
            Properties p = new Properties();
            try (InputStream in = Files.newInputStream(ARQUIVO_SESSAO)) {
                p.load(in);
            }
            String s = p.getProperty(CHAVE_ID_USUARIO);
            if (s == null || s.isBlank()) return null;
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.err.println("Erro ao ler sessão: " + e.getMessage());
            return null;
        }
    }

    public static void limparSessao() {
        try {
            if (Files.exists(ARQUIVO_SESSAO)) Files.delete(ARQUIVO_SESSAO);
        } catch (IOException e) {
            System.err.println("Erro ao limpar sessão: " + e.getMessage());
        }
    }
}
