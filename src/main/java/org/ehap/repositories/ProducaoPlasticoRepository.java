package org.ehap.repositories;

import org.ehap.infrastructure.OracleDbConfiguration;
import org.ehap.models.ProducaoPlastico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducaoPlasticoRepository {

    public OracleDbConfiguration dbConfig;

    public ProducaoPlasticoRepository() {
        this.dbConfig = new OracleDbConfiguration();
    }

    public List<ProducaoPlastico> getProducaoPlasticos() {
        List<ProducaoPlastico> lista = new ArrayList<>();

        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("SELECT * FROM producao_plastico");
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                ProducaoPlastico producaoPlastico = new ProducaoPlastico();
                mapResultSetToProducaoPlastico(rs, producaoPlastico);
                lista.add(producaoPlastico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ProducaoPlastico getProducaoPlasticoByPk(Integer ano, Integer entidadeId) {
        ProducaoPlastico ProducaoPlastico = null;
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st =
                        connection.prepareStatement("SELECT * FROM producao_plastico WHERE ano = ? and " +
                                "ENTIDADE_ID = ?")
        ) {
            st.setInt(1, ano);
            st.setInt(2, entidadeId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ProducaoPlastico = new ProducaoPlastico();
                mapResultSetToProducaoPlastico(rs, ProducaoPlastico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ProducaoPlastico;
    }

    public int createProducaoPlastico(ProducaoPlastico ProducaoPlastico) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement("INSERT INTO producao_plastico (" +
                        "ano, PRODUCAO_ANUAL_PLASTICO, PARTICI_EMISSAO_OCEANOS, " +
                        "PARTICI_RECICLAGEM_REGIONAL, PARTICI_QUEIMA_REGIONAL, " +
                        "PARTICI_LIXO_MAL_GERIDO, PARTICI_ATERROS_REGIONAL, LIXO_MAL_GERIDO_PER_CAPITA, ENTIDADE_ID)" +
                        " VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?)")
        ) {
            prepareStatementForProducaoPlastico(ProducaoPlastico, st);

            return st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateProducaoPlastico(ProducaoPlastico producaoPlastico) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE producao_plastico " +
                                "SET PRODUCAO_ANUAL_PLASTICO = ?, PARTICI_EMISSAO_OCEANOS = ?, " +
                                "PARTICI_RECICLAGEM_REGIONAL = ?, PARTICI_QUEIMA_REGIONAL = ?, " +
                                "PARTICI_LIXO_MAL_GERIDO = ?, PARTICI_ATERROS_REGIONAL = ?, " +
                                "LIXO_MAL_GERIDO_PER_CAPITA = ?" +
                                "WHERE ano = ? and ENTIDADE_ID = ?")
        ) {
            st.setDouble(1, producaoPlastico.getProducaoAnualPlastico());
            st.setDouble(2, producaoPlastico.getParticipacaoEmissaoOceanos());
            st.setDouble(3, producaoPlastico.getParticipacaoReciclagemRegional());
            st.setDouble(4, producaoPlastico.getParticipacaoQueimaRegional());
            st.setDouble(5, producaoPlastico.getParticipacaoLixoMalGerido());
            st.setDouble(6, producaoPlastico.getParticipacaoAterrosRegional());
            st.setDouble(7, producaoPlastico.getLixoMalGeridoPerCapita());
            st.setInt(8, producaoPlastico.getAno());
            st.setInt(9, producaoPlastico.getEntidadeId());


            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByPk(Integer ano, Integer entidadeId) {
        try (
                Connection connection = dbConfig.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "DELETE FROM producao_plastico WHERE ano = ? and ENTIDADE_ID = ?")
        ) {
            st.setInt(1, ano);
            st.setInt(2, entidadeId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prepareStatementForProducaoPlastico(ProducaoPlastico producaoPlastico, PreparedStatement st) throws SQLException {
        st.setInt(1, producaoPlastico.getAno());
        st.setDouble(2, producaoPlastico.getProducaoAnualPlastico());
        st.setDouble(3, producaoPlastico.getParticipacaoEmissaoOceanos());
        st.setDouble(4, producaoPlastico.getParticipacaoReciclagemRegional());
        st.setDouble(5, producaoPlastico.getParticipacaoQueimaRegional());
        st.setDouble(6, producaoPlastico.getParticipacaoLixoMalGerido());
        st.setDouble(7, producaoPlastico.getParticipacaoAterrosRegional());
        st.setDouble(8, producaoPlastico.getParticipacaoLixoMalGerido());
        st.setInt(9, producaoPlastico.getEntidadeId());
    }


    private void mapResultSetToProducaoPlastico(ResultSet rs, ProducaoPlastico producaoPlastico) throws SQLException {
        producaoPlastico.setAno(rs.getInt("ano"));
        producaoPlastico.setProducaoAnualPlastico(rs.getDouble("producao_anual_plastico"));
        producaoPlastico.setParticipacaoEmissaoOceanos(rs.getDouble("partici_emissao_oceanos"));
        producaoPlastico.setParticipacaoReciclagemRegional(rs.getDouble("partici_reciclagem_regional"));
        producaoPlastico.setParticipacaoQueimaRegional(rs.getDouble("partici_queima_regional"));
        producaoPlastico.setParticipacaoLixoMalGerido(rs.getDouble("partici_lixo_mal_gerido"));
        producaoPlastico.setParticipacaoAterrosRegional(rs.getDouble("partici_aterros_regional"));
        producaoPlastico.setLixoMalGeridoPerCapita(rs.getDouble("lixo_mal_gerido_per_capita"));
        producaoPlastico.setEntidadeId(rs.getInt("entidade_id"));
    }
}
