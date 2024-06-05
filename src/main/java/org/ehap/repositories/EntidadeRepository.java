package org.ehap.repositories;

import org.ehap.infrastructure.OracleDbConfiguration;
import org.ehap.models.Entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntidadeRepository {

    public OracleDbConfiguration dbConfig;

    public EntidadeRepository() {
        this.dbConfig = new OracleDbConfiguration();
    }

    public List<Entidade> getEntidades() {
        List<Entidade> lista = new ArrayList<>();

        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT * FROM entidade");
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                Entidade Entidade = new Entidade();
                mapResultSetToEntidade(rs, Entidade);
                lista.add(Entidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Entidade getEntidadeById(int id) {
        Entidade entidade = null;
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st =
                        connection.prepareStatement("SELECT * FROM entidade WHERE entidade_id = ?")
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                entidade = new Entidade();
                mapResultSetToEntidade(rs, entidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entidade;
    }

    public int createEntidade(Entidade Entidade) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("INSERT INTO entidade (" +
                        "nome, codigo, grupo)" +
                        " VALUES " +
                        "(?, ?, ?)")
        ) {
            prepareStatementForEntidade(Entidade, st);

            return st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateEntidade(Entidade entidade) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE entidade " +
                                "SET nome = ?, codigo = ?, grupo = ?" +
                                "WHERE entidade_id = ?")
        ) {
            prepareStatementForEntidade(entidade, st);
            st.setInt(4, entidade.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "DELETE FROM entidade WHERE entidade_id = ?")
        ) {
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatementForEntidade(Entidade entidade, PreparedStatement st) throws SQLException {
        st.setString(1, entidade.getNome());
        st.setString(2, entidade.getCodigo());
        st.setString(3, String.valueOf(entidade.getGrupo()));
    }


    private void mapResultSetToEntidade(ResultSet rs, Entidade entidade) throws SQLException {
        entidade.setId(rs.getInt("entidade_id"));
        entidade.setNome(rs.getString("nome"));
        entidade.setCodigo(rs.getString("codigo"));
        entidade.setGrupo(rs.getString("grupo").charAt(0));
    }
}
