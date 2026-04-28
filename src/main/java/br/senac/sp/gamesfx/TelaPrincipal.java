package br.senac.sp.gamesfx;

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


    private static final String COR_PADRÃO = "#FFFF10";
    private static final String COR_HOVER= "#594e07";
    private static final String TEXT0_BRANCO = "-fx-text-fill: #ffffff";



    @Override
    public void start(Stage stage) throws Exception {

        Image iconeTela = new Image(getClass().getResourceAsStream("/imagens/ico.png"));

        BorderPane raiz = new BorderPane();

        VBox painelLateral = new VBox();
        painelLateral.setSpacing(5);
        painelLateral.setPrefWidth(150);
        painelLateral.setStyle("-fx-background-color: #594e07");
        painelLateral.setPadding(new Insets(30));

        Button btnJogos = criarBotaoMenu("Jogos");
        Button btnPlataformas = criarBotaoMenu("Plataforma");
        Button btnEstudios = criarBotaoMenu("Estúdios");
        Button btnHome = criarBotaoMenu("Home");

        aplicarEfeitoHover(btnJogos, btnHome, btnEstudios, btnPlataformas);

        painelLateral.getChildren().addAll(
                btnHome,
                btnJogos,
                btnPlataformas,
                btnEstudios
        );
//        painelLateral.getChildren().add(btnJogos);
//        painelLateral.getChildren().add(btnPlataformas);
//        painelLateral.getChildren().add(btnEstudios);

        raiz.setLeft(painelLateral);
        raiz.setStyle("-fx-background-color: #ffffff");


        Scene cena = new Scene(raiz, 900, 600);
        stage.setScene(cena);
       // stage.setResizable(false);
        stage.setTitle("Sistema de gestão de jogos V1.0");
        stage.setMaximized(true);
        stage.getIcons().add(iconeTela);
        stage.show();



    }

    private Button criarBotaoMenu(String textoDoBotao) {
        Button button = new Button(textoDoBotao);
        button.setPadding (new Insets( 10));
        button.setPrefWidth(Double.MAX_VALUE);
        return  button;

    }
    private void aplicarEfeitoHover (Button... botoes){

        for(Button button : botoes){

              button .setStyle("-fx-background-color: " + COR_PADRÃO );

              button.setOnMouseEntered(e-> {

                          button.setStyle("-fx-background-color: " + COR_HOVER + ";" +  TEXT0_BRANCO + ";" + "-fx-alignment: center;" + "-fx-cursor: hand;" );

                      }
              );

              button.setOnMouseExited( e ->{
                button.setStyle("-fx-background-color: "+ COR_PADRÃO);
        }
        );
        }
    }
}

