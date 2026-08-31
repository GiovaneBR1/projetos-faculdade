package dao;

import model.Acesso;
import java.sql.SQLException;

public interface AcessoDAO {
    void criar(Acesso acesso) throws SQLException;
    Acesso buscarPorUsuarioId(int idUsr) throws SQLException;
    void atualizar(Acesso acesso) throws SQLException;
}
