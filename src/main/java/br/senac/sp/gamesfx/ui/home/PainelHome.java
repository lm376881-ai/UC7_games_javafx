package br.senac.sp.gamesfx.ui.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PainelHome {

    public VBox criarPainelHome() {

        // Painel Principal, representa a tela toda
        VBox painelPrincipal = new VBox();
        painelPrincipal.setAlignment(Pos.TOP_CENTER);
        painelPrincipal.setStyle("-fx-background-color: #8a8a8a");
        painelPrincipal.setPadding(new Insets(5, 20, 20, 20));

        // Painel de titulo
        VBox painelTitulo = new VBox();
        painelTitulo.setStyle("-fx-background-color: #aaaaaa");
        Label lblTitulo = new Label("Seja Bem-Vindo!");
        lblTitulo.setStyle("-fx-font-size:  24; -fx-font-weight:  bold");

        painelTitulo.getChildren().addAll(lblTitulo, new Separator());

        VBox painelLogo = new VBox();
        painelLogo.setAlignment(Pos.CENTER);
        painelLogo.setStyle("-fx-background-color: #c8c6c6");
        // Imagem da Aplicação
        Image imgLogo = new Image(getClass().getResourceAsStream("/imagens/controle.png"));
        ImageView ivLogo = new ImageView(imgLogo);
        VBox.setVgrow(painelLogo, Priority.ALWAYS);

        // Textos com o nome e descrição da aplicação
        Label lblNomeApp = new Label("Portifolio Game Pro");
        lblNomeApp.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-text-fill: #ffffff");

        Label lblDescApp = new Label("Software para gestão de jogos | Versão 1.0");
        lblDescApp.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #ffffff");

        // Criar painel de contatos
        VBox painelContatos = new VBox(4);
        painelContatos.setAlignment(Pos.CENTER);
        painelContatos.setStyle("-fx-background-color: #7a7979; -fx-border-width: 2; -fx-border-color: #040404; -fx-border-radius: 32; -fx-background-radius: 32");
        painelContatos.setMaxWidth(600);
        painelContatos.setPadding(new Insets(20));
        VBox.setMargin(painelContatos, new Insets( 30,20,30,20));

        Label lblTituloEmail = new Label("E-mail para suporte: ");
        Label lblEmail = new Label("lm376881@gmail.com");
        Label lblTituloTelefone = new Label("Telefone para suporte: ");
        Label lblTelefone = new Label("1195162-8604");

        lblTituloEmail.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #040303");
        lblEmail.setStyle("-fx-font-size: 14; -fx-font-weight: white; -fx-text-fill: white");
        lblTituloTelefone.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #0e0e0e");
        lblTelefone.setStyle("-fx-font-size: 14; -fx-font-weight: white; -fx-text-fill: white");


        painelContatos.getChildren().addAll(
                lblTituloEmail,
                lblEmail,
                lblTituloTelefone,
                lblTelefone
        );

        Label lblDesenvolvidoPor = new Label("Desenvolvido Por Lucas - 2026");
        lblDesenvolvidoPor.setStyle("-fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: #ffffff");

        painelLogo.getChildren().addAll(ivLogo, lblNomeApp, lblDescApp,painelContatos, lblDesenvolvidoPor);

        painelPrincipal.getChildren().addAll(painelTitulo, painelLogo);


        return painelPrincipal;
    }
}



