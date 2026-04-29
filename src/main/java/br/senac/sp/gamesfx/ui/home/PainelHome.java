package br.senac.sp.gamesfx.ui.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PainelHome {

    public VBox criarPainelHome(){

        // Painel Principal, representa a tela toda
        VBox painelPrincipal = new VBox();
        painelPrincipal.setAlignment(Pos.TOP_CENTER);
        painelPrincipal.setStyle("-fx-background-color: #2e83c1");
        painelPrincipal.setPadding(new Insets(5,20,20,20));

        // Painel de titulo
        VBox painelTitulo = new VBox();
        painelTitulo.setStyle("-fx-background-color: yellow");
        Label lblTitulo = new Label("Seja Bem-Vindo!");
        lblTitulo.setStyle("-fx-font-size:  24; -fx-font-weight:  bold");

        painelTitulo.getChildren().addAll(lblTitulo,  new Separator());

        // Imagem da Aplicação

        Image imgLogo = new Image(getClass().getResourceAsStream("/imagens/cannabis.png"));
        ImageView ivLogo = new ImageView(imgLogo);

        // Textos com o nome e descrição da aplicação
        Label lblNomeApp = new Label("Game Weed Pro");
        lblNomeApp.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-text-fill: #ffffff");

        Label lblDescApp = new Label("Software para gestão de jogos | Versão 1.0");
        lblDescApp.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #ffffff");

        painelPrincipal.getChildren().addAll(painelTitulo, ivLogo, lblNomeApp, lblDescApp);


        return painelPrincipal;
    }
}
