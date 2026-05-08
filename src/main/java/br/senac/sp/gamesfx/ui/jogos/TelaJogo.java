package br.senac.sp.gamesfx.ui.jogos;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaJogo {

    public void criarTela(Stage stagePai) {

        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxWidth(400);
        stage.setMaxHeight(400);

        stage.setTitle("Cadastro de Jogo");

        stage.showAndWait();




    }
}
