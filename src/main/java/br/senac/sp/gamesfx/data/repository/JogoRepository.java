package br.senac.sp.gamesfx.data.repository;

import br.senac.sp.gamesfx.data.ConexaoSQLite;
import br.senac.sp.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

// Mock lista  de dados falsos

public class JogoRepository {

    public ObservableList<Jogo> getJogos() {

        String sql = "SELECT * FROM tb_games";

        ObservableList<Jogo> listaJogos = FXCollections.observableArrayList();
        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Jogo jogo = new Jogo();
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String categoria = rs.getString("categoria");
                String plataforma = rs.getString("plataforma");
                String estudio = rs.getString("estudio");
                double preco = rs.getDouble("preco");
                LocalDate dataLancamento = LocalDate.parse(rs.getString("data_lancamento"));
                boolean isFinalizado = rs.getInt("finalizado") == 1 ? true : false;

                //Popular o objeto jogo com os dados
                jogo.setId(id);
                jogo.setTitulo(titulo);
                jogo.setPlataforma(plataforma);
                jogo.setCategoria(categoria);
                jogo.setEstudio(estudio);
                jogo.setPreco(preco);
                jogo.setFinalizado(isFinalizado);
                jogo.setDataLancamento(dataLancamento);

                listaJogos.add(jogo);

            }
            return listaJogos;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados.");
            e.printStackTrace();
            return null;
        }
    }


    public void salvar(Jogo jogo){ // Quando usuario preencher os campos os dados salva no banco de dados

        // Instrução SQL para cadastrar um jogo no banco de dados
        String sql = "INSERT INTO tb_games (titulo, plataforma,"+
        "categoria, estudio, preco, data_lancamento,"+
        "finalizado)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"; // cada interragação é o valor de um campo

        // Preparar a instrução SQL para ser enviada para o banco através de um conexão

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, jogo.getTitulo()); // Adicionar o jogo ao BD
            stm.setString(2, jogo.getPlataforma()); // Adicionar a plataforma ao BD
            stm.setString(3, jogo.getEstudio()); // Adicionar o Estudio ao BD
            stm.setString(4, jogo.getCategoria()); // Adicionar a Catergoria ao BD
            stm.setDouble(5,jogo.getPreco()); // Adicionar o Valor ao BD
            stm.setString(6,jogo.getDataLancamento().toString()); // Adicionar a data ao BD
            stm.setInt(7, jogo.isFinalizado() ? 1 : 0); // Adicionar se o  jogo foi finalizado ou não;
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro de gravação. ");
            e.printStackTrace(); // para me mostrar o erro
        }


    }
}



