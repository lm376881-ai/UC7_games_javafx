package br.senac.sp.gamesfx.data.repository;

import br.senac.sp.gamesfx.data.ConexaoSQLite;
import br.senac.sp.gamesfx.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlataformaRepository {
    public ObservableList<Plataforma> getPlataforma() {

        String sql = "SELECT * FROM  tb_plataformas";

        ObservableList<Plataforma> listaPlataformas = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Plataforma plataforma = new Plataforma();
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String fabricante = rs.getString("fabricante");
                LocalDate dataLancamento = LocalDate.parse(rs.getString("data_lancamento"));
                double valor = rs.getDouble("valor");

                // popular dados plataforma

                plataforma.setId(id);
                plataforma.setNome(nome);
                plataforma.setFabricante(fabricante);
                plataforma.setDataLancamento(dataLancamento);

                listaPlataformas.add(plataforma);
            }
            ConexaoSQLite.fecharConexao();
            return listaPlataformas;


        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados.");
            e.printStackTrace();
            return null;
        }
    }

    public void salvar(Plataforma plataforma) {
        // instrução SQL para cadastrar uma plataforma no banco de dados

        String sql = "INSERT INTO tb_plataformas (nome, fabricante, data_lancamento, valor) " +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement stmt = ConexaoSQLite.getConexao().prepareStatement(sql);

            stmt.setString(1, plataforma.getNome());
            stmt.setString(2, plataforma.getFabricante());
            stmt.setString(3, plataforma.getDataLancamento().toString());
            stmt.setDouble(4, plataforma.getValor());

            stmt.executeUpdate();
            ConexaoSQLite.fecharConexao();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar plataforma");
            e.printStackTrace();
        }
    }

    public int getTotalPlataformas() {
        String sql = "SELECT COUNT(id) as total_plataformas FROM tb_plataformas";
        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int total = rs.getInt("total_plataformas");
            ConexaoSQLite.fecharConexao();
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int excluir(int id) {
        String sql = "DELETE FROM tb_plataformas WHERE id = ?";
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
    public void editar (Plataforma plataforma){
        String sql = "UPDATE tb_plataformas SET "+
                "nome= ?, "+
                "fabricante= ?,"+
                "data_lancamento = ?, "+
                "valor= ?, "+
                " WHERE  id = ?; ";
        try{
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, plataforma.getNome());
            stm.setString(2, plataforma.getFabricante());
            stm.setString(3, plataforma.getDataLancamento().toString());
            stm.setDouble(4,plataforma.getValor());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }

    }
}
