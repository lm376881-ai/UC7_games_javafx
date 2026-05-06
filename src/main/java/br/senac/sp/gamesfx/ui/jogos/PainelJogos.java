package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.model.Jogo;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class PainelJogos {

    public VBox criarPainelJogos() {

        VBox painelJogos = new VBox();
       painelJogos.setStyle("-fx-background-color:#aaaaaa;-fx-font-size: 12");

                   // Titulo Painel de Jogos

        Label lblTitulo =  new Label("Listagem de jogos!");

                    // Linha abaixo do label

        Separator linha = new Separator();

        // Tabela com uma lista de jogos

        TableView<Jogo>  tabelaJogos = new TableView<>();

        // Criar Colunas da tabela

        TableColumn<Jogo, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setPrefWidth(50);

        TableColumn<Jogo, String> colunaTitulo = new TableColumn<>("TITULO");
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaTitulo.setPrefWidth(400);


        TableColumn<Jogo, String> colunaPlataforma = new TableColumn<>("PLATAFORMA");
        colunaPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        colunaPlataforma.setPrefWidth(200);


    // Adicionar Colunas a tabela

        tabelaJogos.getColumns().addAll(colunaId,colunaTitulo,colunaPlataforma);


        /// Adicionar o label no painel

        painelJogos.getChildren().addAll(lblTitulo, linha, tabelaJogos);



        return painelJogos;
    }


}
