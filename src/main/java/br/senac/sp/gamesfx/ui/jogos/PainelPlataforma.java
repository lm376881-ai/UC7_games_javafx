package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.model.Plataforma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.LocalDate;

public class PainelPlataforma {

    private Stage stage;

    public PainelPlataforma(Stage stage) {
        this.stage = stage;
    }

    public VBox criarPainelPLataforma() {


        VBox painelPlataforma = new VBox();
        painelPlataforma.setStyle("-fx-background-color:#aaaaaa;-fx-font-size: 12");
        painelPlataforma.setPadding(new Insets(10, 10, 10, 10)); // comando utilizado para redimencionar Vbox do painel jogos

        // Titulo Painel de Plataforma

        Label lblTitulo = new Label("Plataformas");
        lblTitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #ffffff; -fx-font-weight: bold");

        // Linha abaixo do label

        Separator linha = new Separator();

        // Tabela com uma lista de jogos

        TableView<Plataforma> tabelaPlataforma = new TableView<>();


        // dados mocados

        tabelaPlataforma.getItems().addAll(
                new Plataforma(1, "PS5", "Sony", LocalDate.now(), 4999.99),
                new Plataforma(2, "Xbox", "Microsoft", LocalDate.now(), 4500.00)
        );


        // Ajustar tamanho de tabela para ocupar todo o espaço

        VBox.setVgrow(tabelaPlataforma, Priority.ALWAYS);


        // Criar Colunas da tabela

        TableColumn<Plataforma, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setPrefWidth(40);

        TableColumn<Plataforma, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaNome.setPrefWidth(235);

        TableColumn<Plataforma, String> colunaFabricante = new TableColumn<>("FABRICANTE");
        colunaFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));

        TableColumn<Plataforma, LocalDate> colunaDataLancamento = new TableColumn<>("LANÇAMENTO");
        colunaDataLancamento.setCellValueFactory(new PropertyValueFactory<>("dataLancamento"));
        colunaDataLancamento.setPrefWidth(100);

        TableColumn<Plataforma, Double> colunaValor = new TableColumn<>("VALOR");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setPrefWidth(70);

        // Adicionar Colunas a tabela

        tabelaPlataforma.getColumns().addAll(colunaId, colunaNome, colunaFabricante, colunaDataLancamento, colunaValor);

        // Criar botões de ações


        HBox painelBotoes = new HBox(10);

        Button btnAdicionar = criarBotao("Adicionar", "/imagens/adicionar.png");



        Button btnEditar = criarBotao("Editar", "/imagens/editar.png");
        Button btnExibir = criarBotao("Exibir", "/imagens/visualizar.png");
        Button bntExcluir = criarBotao("Excluir","/imagens/excluir.png");

        painelBotoes.getChildren().addAll(btnAdicionar, btnEditar, btnExibir,bntExcluir);


        painelPlataforma.getChildren().addAll(lblTitulo, linha, tabelaPlataforma, painelBotoes);


        painelBotoes.setAlignment(Pos.CENTER_RIGHT);


        return painelPlataforma;
    }


    private Button criarBotao(String textoBotao, String urlImagem) {
        Image image = new Image(getClass().getResourceAsStream(urlImagem));
        ImageView imageView = new ImageView(image);



        imageView.setFitHeight(16);
        imageView.setFitWidth(16);

        Button botao = new Button(textoBotao, imageView);

        return botao;


    }
}



