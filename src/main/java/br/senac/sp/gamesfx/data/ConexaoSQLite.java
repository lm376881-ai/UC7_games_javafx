package br.senac.sp.gamesfx.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

    public static Connection getConexao (){

        // String de conexão - URL do banco de dados


        String url = "jdbc:sqlite:/C:/Users/lucas.mnascimento25/banco_de_dados/db_games.db";

        try {
            Connection conexao = DriverManager.getConnection(url);
            return conexao;
        } catch (SQLException e) {
            System.out.println("Ocorreu erro durante a conexão com o banco");
            e.printStackTrace(); // imprimir toda a pilha de erro e a mensagem técnica
            return null;
        }


    }


}
