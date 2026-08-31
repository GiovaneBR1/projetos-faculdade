package test;

import config.DBConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class TesteConexaoDB {

    public static void main(String[] args) {
        System.out.println("Iniciando teste de conexão com o banco de dados...");

        try (Connection conexao = DBConfig.obterConexao()) {
            if (conexao != null && !conexao.isClosed()) {
                DatabaseMetaData meta = conexao.getMetaData();
                System.out.println("Conexão estabelecida com sucesso!");
                System.out.println("Driver: " + meta.getDriverName() + " - " + meta.getDriverVersion());
                System.out.println("URL: " + meta.getURL());
                System.out.println("Usuário conectado: " + meta.getUserName());
                System.out.println("Auto-commit: " + conexao.getAutoCommit());
            } else {
                System.out.println("Conexão retornou nula ou estava fechada.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao conectar no banco de dados:");
            System.err.println("Mensagem: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("ErrorCode: " + ex.getErrorCode());
            System.err.println("Verifique: credenciais em config.DBConfig, URL do JDBC e se o MySQL está rodando.");
            // opcional: imprimir stacktrace para debug
            ex.printStackTrace();
        }

        System.out.println("Teste finalizado.");
    }
}
