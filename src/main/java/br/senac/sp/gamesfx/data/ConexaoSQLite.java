package br.senac.sp.gamesfx.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

    private static Connection conexao;

    public static Connection getConexao(){

        //String de conexão - URl do Banco de Dados
        String url = "jdbc:sqlite:/C:/Users/lucas.mnascimento25/banco_de_dados/db_games";

        try {
            conexao = DriverManager.getConnection(url);
            System.out.println("Sucesso");
            return conexao;
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro durante a conexão com o banco.");
            erro.printStackTrace();//Imprime toda pilha de erro
            return null;
        }

    }

    public static void fecharConexao(){
        try {
            if (!conexao.isClosed()){
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}