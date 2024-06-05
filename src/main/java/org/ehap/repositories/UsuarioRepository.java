package org.ehap.repositories;

import org.ehap.infrastructure.OracleDbConfiguration;
import org.ehap.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public OracleDbConfiguration dbConfig;

    public UsuarioRepository() {
        this.dbConfig = new OracleDbConfiguration();
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList<>();

        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT * FROM usuario ");
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                mapResultSetToUsuario(rs, usuario);
                lista.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuario getUsuarioByUsername(String username) {
        Usuario usuario = null;
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st =
                        connection.prepareStatement("SELECT * FROM usuario WHERE nome_usuario = ?")
        ) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                mapResultSetToUsuario(rs, usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public int createUsuario(Usuario usuario) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("INSERT INTO usuario (" +
                        "nome, senha)" +
                        " VALUES " +
                        "(?, ?)")
        ) {
            prepareStatementForUsuario(usuario, st);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateUsuario(Usuario usuario) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE usuario " +
                                "SET nome = ?, senha = ?" +
                                "WHERE nome_usuario = ?")
        ) {
            prepareStatementForUsuario(usuario, st);
            st.setString(3, usuario.getNomeUsuario());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByUsername(String username) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "DELETE FROM usuario WHERE nome_usuario = ?")
        ) {
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatementForUsuario(Usuario usuario, PreparedStatement st) throws SQLException {
        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getSenha());
    }


    private void mapResultSetToUsuario(ResultSet rs, Usuario usuario) throws SQLException {
        usuario.setNomeUsuario("nome_usuario");
        usuario.setNome("nome");
        usuario.setSenha("senha");
    }
}
