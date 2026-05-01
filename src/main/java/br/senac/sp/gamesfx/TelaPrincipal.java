package br.senac.sp.gamesfx;

import br.senac.sp.gamesfx.ui.home.PainelHome;
import br.senac.sp.gamesfx.ui.jogos.PainelJogos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPrincipal extends Application {

    private static final String COR_PADRAO ="-fx-background-color: #cccccc; -fx-text-fill: #ffffff;-fx-alignment: center;-fx-cursor: hand";
    private static final String COR_HOVER ="-fx-background-color: #070707; -fx-text-fill: #ffffff;-fx-alignment: center;-fx-cursor: hand";


    @Override
    public void start(Stage stage) throws Exception {

        Image iconeTela = new Image(getClass().getResourceAsStream("/imagens/ico.png"));

        BorderPane raiz = new BorderPane();

        VBox painelLateral = new VBox();
        painelLateral.setSpacing(5);
        painelLateral.setPrefWidth(150);
        painelLateral.setStyle("-fx-background-color:#494444");
        painelLateral.setPadding(new Insets(10));

        Button btnJogos = criarBotaoMenu("Jogos");
        btnJogos.setOnAction(clique -> {
            PainelJogos painelJogos = new PainelJogos();
            raiz.setCenter(painelJogos.criarPainelJogos());
        });

        Button btnPlataformas = criarBotaoMenu("Plataformas");
        Button btnEstudios = criarBotaoMenu("Estudios");

        Button btnHome = criarBotaoMenu("Home");
        btnHome.setOnAction(clique->{
            PainelHome painelHome = new PainelHome();
        });


        aplicarEfeitoHover(btnJogos,btnHome,btnEstudios,btnPlataformas);

        painelLateral.getChildren().addAll(
                btnHome,
                btnJogos,
                btnPlataformas,
                btnEstudios
        );

//        paineilLateral.getChildren().add(btnJogos);
//        paineilLateral.getChildren().add(btnPlataformas);
//        paineilLateral.getChildren().add(btnEstudios);
//        paineilLateral.getChildren().add(btnHome);

        raiz.setLeft(painelLateral);
        raiz.setStyle("-fx-background-color: #ffffff");

        PainelHome painelHome = new PainelHome();

        raiz.setCenter(painelHome.criarPainelHome());

        Scene cena = new Scene(raiz, 900,600);
        stage.setScene(cena);
        stage.setTitle("Sistema de Gestão de Jogos V1.0");
        stage.setResizable(true);
        // stage.setMaximized(true);
        stage.getIcons().add(iconeTela);
        stage.show();
    }

    private Button criarBotaoMenu(String textoDoBotao){
        Button button =new Button(textoDoBotao);

        button.setPadding(new Insets(10));

        button.setPrefWidth(Double.MAX_VALUE);

        // button.setStyle("-fx-background-color: #1b3f55; -fx-text-fill: #ffffff;-fx-alignment: center;-fx-cursor: hand");
        return button;
    }

    private void aplicarEfeitoHover(Button... botoes){
        for(Button button: botoes){

            button.setStyle(COR_PADRAO);


            //Ao passar no botão
            button.setOnMouseEntered(event -> {
                button.setStyle(COR_HOVER);

            });
            //Ao sair no botão
            button.setOnMouseExited(event -> {
                button.setStyle(COR_PADRAO );
            });
        }

    }


}