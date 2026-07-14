package br.senac.sp.gamesfx.data.repository;

import br.senac.sp.gamesfx.data.ConexaoSQLite;
import br.senac.sp.gamesfx.model.Estudio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EstudioRepository {
    public static ObservableList<Estudio> getEstudios() {

        String sql = "SELECT * FROM  tb_estudios";

        ObservableList<Estudio> listaEstudios = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Estudio estudio = new Estudio();
                int id = rs.getInt("id");
                String nomeFundador = rs.getString("nomeFundador");
                int anoFundacao = rs.getInt("anoFundacao");
                String paisOrigem = rs.getString("paisOrigem");

                // popular dados estudio

                estudio.setId(id);
                estudio.setNomeFundador(nomeFundador);
                estudio.setAnoFundacao(anoFundacao);
                estudio.setPaisOrigem(paisOrigem);

                listaEstudios.add(estudio);

            }

            ConexaoSQLite.fecharConexao();
            return listaEstudios;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados.");
            e.printStackTrace();
            return null;
        }
    }

    public int salvar(Estudio estudio) {
        // instrução SQL para cadastrar uma plataforma no banco de dados

        String sql = "INSERT INTO tb_estudios (nomeFundador, anoFundacao, paisOrigem) " +
                "VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = ConexaoSQLite.getConexao().prepareStatement(sql);

            stmt.setString(1, estudio.getNomeFundador());
            stmt.setInt(2, estudio.getAnoFundacao());
            stmt.setString(3, estudio.getPaisOrigem());


            int resultado = stmt.executeUpdate();
            ConexaoSQLite.fecharConexao();
            return resultado;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar estudio");
            e.printStackTrace();
            return 0;
        }
    }


    public int getTotalEstudios() {
        String sql = "SELECT COUNT(id) as total_estudios FROM tb_estudios";
        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int total = rs.getInt("total_estudios");
            ConexaoSQLite.fecharConexao();
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static int excluir(int id) {
        String sql = "DELETE FROM tb_estudios WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoSQLite
                    .getConexao().
                    prepareStatement(sql);
            stm.setInt(1, id);
            int resultado = stm.executeUpdate();

            ConexaoSQLite.fecharConexao();

            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void editar(Estudio estudio) {
        String sql = "UPDATE tb_estudios SET " +
                "nomeFundador= ?, " +
                "anoFundacao= ?," +
                "paisOrigem = ? "+
                "WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, estudio.getNomeFundador());
            stm.setInt(2, estudio.getAnoFundacao());
            stm.setString(3, estudio.getPaisOrigem());
            stm.setInt(4, estudio.getId());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();

        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }
    }
}




