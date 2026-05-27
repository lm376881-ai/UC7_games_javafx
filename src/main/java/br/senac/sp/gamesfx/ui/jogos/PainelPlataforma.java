package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.model.Jogo;
import br.senac.sp.gamesfx.model.Plataforma;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PainelPlataforma {

    private Stage stage;

    public PainelPlataforma(Stage stage) {

    }

        public VBox criarPainelPLataforma() {

            VBox painelPlataforma = new VBox();
            painelPlataforma.setStyle("-fx-background-color:#aaaaaa;-fx-font-size: 12");
            painelPlataforma.setPadding(new Insets(10, 10, 10, 10)); // comando utilizado para redimencionar Vbox do painel jogos

            // Titulo Painel de Plataforma

            Label lblTitulo = new Label("Plataforma");
            lblTitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #ffffff; -fx-font-weight: bold");

            // Linha abaixo do label

            Separator linha = new Separator();

            // Tabela com uma lista de jogos

            TableView<Plataforma> tabelaPlataforma = new TableView<>();
            VBox.setVgrow(tabelaPlataforma, Priority.ALWAYS);


            painelPlataforma.getChildren().addAll(lblTitulo, linha);

            return painelPlataforma;
        }


}



