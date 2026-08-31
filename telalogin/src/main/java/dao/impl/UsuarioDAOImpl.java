package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void criar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (email, senha_hash, nome, data_nascimento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConfig.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenhaHash());
            ps.setString(3, usuario.getNome());
            if (usuario.getDataNascimento() != null) {
                ps.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            } else {
                ps.setNull(4, Types.DATE);
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT id, email, senha_hash, nome, data_nascimento FROM usuarios WHERE email = ?";
        try (Connection conn = DBConfig.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setSenhaHash(rs.getString("senha_hash"));
                    u.setNome(rs.getString("nome"));
                    Date dt = rs.getDate("data_nascimento");
                    if (dt != null) u.setDataNascimento(dt.toLocalDate());
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, email, senha_hash, nome, data_nascimento FROM usuarios WHERE id = ?";
        try (Connection conn = DBConfig.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setSenhaHash(rs.getString("senha_hash"));
                    u.setNome(rs.getString("nome"));
                    Date dt = rs.getDate("data_nascimento");
                    if (dt != null) u.setDataNascimento(dt.toLocalDate());
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT id, email, senha_hash, nome, data_nascimento FROM usuarios";
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = DBConfig.obterConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setSenhaHash(rs.getString("senha_hash"));
                u.setNome(rs.getString("nome"));
                Date dt = rs.getDate("data_nascimento");
                if (dt != null) u.setDataNascimento(dt.toLocalDate());
                lista.add(u);
            }
        }
        return lista;
    }
}
