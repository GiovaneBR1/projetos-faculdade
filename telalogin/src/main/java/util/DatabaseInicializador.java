package util;

import java.sql.SQLException;
import java.time.LocalDate;

import config.AppConfig;
import dao.impl.AcessoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import model.Acesso;
import model.Usuario;

public class DatabaseInicializador {

   
    private static final String EMAIL_MASTER = "MASTER";
    private static final String SENHA_MASTER = "ADSIS"; 
    private static final String NOME_MASTER = "Master";
    private static final LocalDate DATA_NASC_MASTER = LocalDate.of(2000, 1, 1);

    public static void garantirUsuarioMaster() throws Exception {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        AcessoDAOImpl acessoDAO = new AcessoDAOImpl();

        try {
            // 1) Verifica existência de usuário com id = 1
            Usuario usuarioId1 = usuarioDAO.buscarPorId(1);
            if (usuarioId1 != null) {
                //System.out.println("Usuário master já existe: id=1, email=" + usuarioId1.getEmail());
                // garante que exista registro de acesso para este usuário
                if (acessoDAO.buscarPorUsuarioId(1) == null) {
                    Acesso acesso = new Acesso(true, AppConfig.TENTATIVAS_INICIAIS, 1);
                    acessoDAO.criar(acesso);
                    System.out.println("Registro de acesso criado para master id=1.");
                }
                return;
            }

            // 2) Se não existe id=1, cria um novo usuário master
            String hashSenha = HashUtil.gerarHash(SENHA_MASTER);
            Usuario novoMaster = new Usuario(EMAIL_MASTER, hashSenha, NOME_MASTER, DATA_NASC_MASTER);
            usuarioDAO.criar(novoMaster);

            // Após criação, obtém o id gerado (novoMaster.getId())
            int idGerado = novoMaster.getId();
            if (idGerado <= 0) {
                // tentativa de recuperação simples: buscar pelo email do master
                Usuario rec = usuarioDAO.buscarPorEmail(EMAIL_MASTER);
                if (rec != null) idGerado = rec.getId();
            }

            if (idGerado <= 0) {
                throw new Exception("Erro: não foi possível obter id do usuário master após inserção.");
            }

            // 3) Cria registro de acesso para o usuário criado
            Acesso acessoMaster = new Acesso(true, AppConfig.TENTATIVAS_INICIAIS, idGerado);
            acessoDAO.criar(acessoMaster);

            System.out.println("Usuário master criado: id=" + idGerado + ", email=" + EMAIL_MASTER);
        } catch (SQLException ex) {
            throw new Exception("Falha ao garantir usuário master: " + ex.getMessage(), ex);
        }
    }
}
