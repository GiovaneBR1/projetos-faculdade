package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DBConfig;
import dao.AcessoDAO;
import model.Acesso;

public class AcessoDAOImpl implements AcessoDAO {

    @Override
    public void criar(Acesso acesso) throws SQLException {
        String sql = "INSERT INTO acesso (ativo, tentativas_rest, id_usr) VALUES (?, ?, ?)";
        try (Connection conn = DBConfig.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, acesso.isAtivo());
            ps.setInt(2, acesso.getTentativasRest());
            ps.setInt(3, acesso.getIdUsr());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) acesso.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public Acesso buscarPorUsuarioId(int idUsr) throws SQLException {
        String sql = "SELECT id, ativo, tentativas_rest, id_usr FROM acesso WHERE id_usr = ?";
        try (Connection conn = DBConfig.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsr);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Acesso a = new Acesso();
                    a.setId(rs.getInt("id"));
                    a.setAtivo(rs.getBoolean("ativo"));
                    a.setTentativasRest(rs.getInt("tentativas_rest"));
                    a.setIdUsr(rs.getInt("id_usr"));
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public void atualizar(Acesso acesso) throws SQLException {
        String sql = "UPDATE acesso SET ativo = ?, tentativas_rest = ? WHERE id = ?";
        try (Connection conn = DBConfig.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, acesso.isAtivo());
            ps.setInt(2, acesso.getTentativasRest());
            ps.setInt(3, acesso.getId());
            ps.executeUpdate();
        }
    }
}
