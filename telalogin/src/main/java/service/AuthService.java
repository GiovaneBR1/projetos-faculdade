package service;

import java.sql.SQLException;
import java.util.List;

import config.AppConfig;
import dao.AcessoDAO;
import dao.UsuarioDAO;
import dao.impl.AcessoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import model.Acesso;
import model.Usuario;
import util.HashUtil;

public class AuthService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private final AcessoDAO acessoDAO = new AcessoDAOImpl();

    private Usuario usuarioAtual; // usuário logado na sessão em memória

    // Registro de novo usuário
    public void registrar(String nome, String email, String senha, java.time.LocalDate dataNascimento) throws Exception {
        if (nome == null || nome.isBlank()) throw new Exception("Nome obrigatório");
        if (email == null || email.isBlank()) throw new Exception("Email obrigatório");
        if (senha == null || senha.length() < 6) throw new Exception("Senha deve ter ao menos 6 caracteres");

        try {
            Usuario existente = usuarioDAO.buscarPorEmail(email);
            if (existente != null) throw new Exception("Email já cadastrado");

            String hash = HashUtil.gerarHash(senha);
            Usuario novo = new Usuario(email, hash, nome, dataNascimento);
            usuarioDAO.criar(novo);

            Acesso acesso = new Acesso(true, AppConfig.TENTATIVAS_INICIAIS, novo.getId());
            acessoDAO.criar(acesso);
        } catch (SQLException ex) {
            throw new Exception("Erro ao registrar: " + ex.getMessage(), ex);
        }
    }

    public Usuario login(String email, String senha) throws Exception {
        try {
            Usuario u = usuarioDAO.buscarPorEmail(email);
            if (u == null) throw new Exception("Usuário não encontrado");

            model.Acesso a = acessoDAO.buscarPorUsuarioId(u.getId());
            if (a == null) throw new Exception("Registro de acesso não encontrado");

            // checar bloqueio pela contagem de tentativas
            if (a.getTentativasRest() <= 0) {
                throw new Exception("Usuário bloqueado. Solicite liberação ao master.");
            }

            boolean ok = util.HashUtil.verificarSenha(senha, u.getSenhaHash());
            if (!ok) {
                int novas = a.getTentativasRest() - 1;
                a.setTentativasRest(Math.max(0, novas));
                // NÃO alterar 'ativo' aqui: apenas decrementar tentativas
                acessoDAO.atualizar(a);

                if (a.getTentativasRest() <= 0) {
                    throw new Exception("Senha incorreta. Usuário agora bloqueado. Procure o master.");
                } else {
                    throw new Exception("Senha incorreta. Tentativas restantes: " + a.getTentativasRest());
                }
            }

            // login bem-sucedido:
            a.setTentativasRest(config.AppConfig.TENTATIVAS_INICIAIS); // restaura tentativas
            a.setAtivo(true); // marca sessão ativa
            acessoDAO.atualizar(a);

            this.usuarioAtual = u;
            util.SessionUtil.gravarUsuarioLogado(u.getId()); // grava sessão local
            return u;
        } catch (SQLException ex) {
            throw new Exception("Erro no login: " + ex.getMessage(), ex);
        }
    }
    
    public void logout() {
        if (this.usuarioAtual != null) {
            try {
                model.Acesso a = acessoDAO.buscarPorUsuarioId(this.usuarioAtual.getId());
                if (a != null) {
                    a.setAtivo(false); // encerra sessão
                    acessoDAO.atualizar(a);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao atualizar acesso no logout: " + e.getMessage());
            }
        }
        util.SessionUtil.limparSessao();
        this.usuarioAtual = null;
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public List<Usuario> listarUsuarios() throws Exception {
        try {
            return usuarioDAO.listarTodos();
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar usuários: " + ex.getMessage(), ex);
        }
    }

    
    
    public Usuario buscarUsuarioPorIdParaSessao(int id) throws Exception {
        try {
            Usuario u = usuarioDAO.buscarPorId(id);
            if (u == null) return null;
            model.Acesso a = acessoDAO.buscarPorUsuarioId(u.getId());
            // sessão válida apenas se ativo == true e tentativas_rest > 0
            if (a == null) return null;
            if (!a.isAtivo()) return null;
            if (a.getTentativasRest() <= 0) return null; // conta bloqueada
            this.usuarioAtual = u;
            return u;
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar usuário: " + ex.getMessage(), ex);
        }
    }

    
    public void liberarTentativasParaUsuario(int idUsuario) throws Exception {
        try {
            model.Acesso acesso = acessoDAO.buscarPorUsuarioId(idUsuario);
            if (acesso == null) throw new Exception("Acesso não encontrado para o usuário");
            acesso.setTentativasRest(config.AppConfig.TENTATIVAS_INICIAIS);
            // Não alteramos 'ativo' aqui; o admin pode optar por manter sessão ativa ou não.
            acessoDAO.atualizar(acesso);
        } catch (SQLException ex) {
            throw new Exception("Erro ao liberar tentativas: " + ex.getMessage(), ex);
        }
    }


}
