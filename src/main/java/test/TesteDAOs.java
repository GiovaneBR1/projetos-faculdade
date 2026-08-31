package test;

import dao.impl.UsuarioDAOImpl;
import dao.impl.AcessoDAOImpl;
import model.Usuario;
import model.Acesso;
import java.time.LocalDate;
import java.sql.SQLException;
import config.AppConfig;

public class TesteDAOs {
    public static void main(String[] args) {
        System.out.println("TesteDAOs iniciado");

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        AcessoDAOImpl acessoDAO = new AcessoDAOImpl();

        try {
            // 1) Criar usuário de teste (use e-mail único para evitar conflito)
            String emailTeste = "teste_dao_" + System.currentTimeMillis() + "@exemplo.com";
            Usuario novo = new Usuario(emailTeste, "hash-teste", "Usuário DAO", LocalDate.of(2000,1,1));
            usuarioDAO.criar(novo);
            System.out.println("Criado usuario id = " + novo.getId() + " email = " + novo.getEmail());

            // 2) Recuperar por email
            Usuario lido = usuarioDAO.buscarPorEmail(emailTeste);
            System.out.println("Buscar por email -> id = " + (lido != null ? lido.getId() : "nulo"));

            // 3) Criar acesso para o usuario
            Acesso acesso = new Acesso(true, AppConfig.TENTATIVAS_INICIAIS, novo.getId());
            acessoDAO.criar(acesso);
            System.out.println("Criado acesso id = " + acesso.getId() + " para id_usr = " + acesso.getIdUsr());

            // 4) Buscar acesso por usuario id
            Acesso acessoLido = acessoDAO.buscarPorUsuarioId(novo.getId());
            System.out.println("Acesso lido -> tentativas = " + (acessoLido != null ? acessoLido.getTentativasRest() : "nulo"));

            // 5) Atualizar acesso
            acessoLido.setTentativasRest(2);
            acessoLido.setAtivo(false);
            acessoDAO.atualizar(acessoLido);
            Acesso acessoAtual = acessoDAO.buscarPorUsuarioId(novo.getId());
            System.out.println("Acesso apos update -> ativo = " + acessoAtual.isAtivo() + " tentativas = " + acessoAtual.getTentativasRest());

            // 6) Listar todos usuarios (apenas mostra contagem)
            System.out.println("Total de usuarios (listarTodos): " + usuarioDAO.listarTodos().size());

            System.out.println("TesteDAOs finalizado com sucesso.");
        } catch (SQLException ex) {
            System.err.println("Erro SQL: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
