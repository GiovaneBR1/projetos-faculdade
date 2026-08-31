package dao;

import model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    void criar(Usuario usuario) throws SQLException;
    Usuario buscarPorEmail(String email) throws SQLException;
    Usuario buscarPorId(int id) throws SQLException;
    List<Usuario> listarTodos() throws SQLException;
}
