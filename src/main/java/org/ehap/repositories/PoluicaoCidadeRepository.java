package org.ehap.repositories;

import org.ehap.infrastructure.OracleDbConfiguration;
import org.ehap.models.PoluicaoCidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoluicaoCidadeRepository {

    public OracleDbConfiguration dbConfig;

    public PoluicaoCidadeRepository() {
        this.dbConfig = new OracleDbConfiguration();
    }

    public List<PoluicaoCidade> getPoluicaoCidades() {
        List<PoluicaoCidade> lista = new ArrayList<>();

        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT * FROM poluicao_cidade ");
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                PoluicaoCidade pc = new PoluicaoCidade();
                mapResultSetToPoluicaoCidade(rs, pc);
                lista.add(pc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public PoluicaoCidade getPoluicaoCidadeByPk(String cidade, String regiao, Integer ano) {
        PoluicaoCidade PoluicaoCidade = null;
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st =
                        connection.prepareStatement("SELECT * FROM poluicao_cidade WHERE cidade = ? and regiao = ? and ano = ?")
        ) {
            st.setString(1, cidade);
            st.setString(2, regiao);
            st.setInt(3, ano);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                PoluicaoCidade = new PoluicaoCidade();
                mapResultSetToPoluicaoCidade(rs, PoluicaoCidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PoluicaoCidade;
    }

    public int createPoluicaoCidade(PoluicaoCidade poluicaoCidade) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("INSERT INTO poluicao_cidade (" +
                        "cidade, regiao, ano, qualidadeAr, poluicaoAgua, entidadeId)" +
                        " VALUES " +
                        "(?, ?, ?, ?, ?, ?)")
        ) {
            prepareStatementForPoluicaoCidade(poluicaoCidade, st);

            return st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updatePoluicaoCidade(PoluicaoCidade poluicaoCidade) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE poluicao_cidade " +
                                "SET qualidadeAr = ?, poluicaoAgua = ?, entidadeId = ?" +
                                "WHERE cidade = ? and regiao = ? and ano = ?")
        ) {
            st.setDouble(1, poluicaoCidade.getQualidadeAr());
            st.setDouble(2, poluicaoCidade.getPoluicaoAgua());
            st.setInt(3, poluicaoCidade.getEntidadeId());
            st.setString(4, poluicaoCidade.getCidade());
            st.setString(5, poluicaoCidade.getRegiao());
            st.setInt(6, poluicaoCidade.getAno());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPk(String cidade, String regiao, Integer ano) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "DELETE FROM poluicao_cidade WHERE cidade = ? and regiao = ? and ano = ?")
        ) {
            st.setString(1, cidade);
            st.setString(2, regiao);
            st.setInt(3, ano);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatementForPoluicaoCidade(PoluicaoCidade poluicaoCidade, PreparedStatement st) throws SQLException {
        st.setString(1, poluicaoCidade.getCidade());
        st.setString(2, poluicaoCidade.getRegiao());
        st.setInt(3, poluicaoCidade.getAno());
        st.setDouble(4, poluicaoCidade.getQualidadeAr());
        st.setDouble(5, poluicaoCidade.getPoluicaoAgua());
        st.setInt(6, poluicaoCidade.getEntidadeId());
    }


    private void mapResultSetToPoluicaoCidade(ResultSet rs, PoluicaoCidade poluicaoCidade) throws SQLException {
        poluicaoCidade.setCidade(rs.getString("cidade"));
        poluicaoCidade.setRegiao(rs.getString("regiao"));
        poluicaoCidade.setAno(rs.getInt("ano"));
        poluicaoCidade.setQualidadeAr(rs.getDouble("qualidade_ar"));
        poluicaoCidade.setPoluicaoAgua(rs.getDouble("poluicao_agua"));
        poluicaoCidade.setEntidadeId(rs.getInt("entidade_id"));
    }
}
