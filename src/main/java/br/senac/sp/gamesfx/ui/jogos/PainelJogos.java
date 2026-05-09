package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.data.repository.JogoRepository;
import br.senac.sp.gamesfx.model.Jogo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.time.LocalDate;

public class PainelJogos {

    private Stage stage;

    public PainelJogos(Stage stage ){

    }

    public VBox criarPainelJogos() {

        VBox painelJogos = new VBox();
       painelJogos.setStyle("-fx-background-color:#aaaaaa;-fx-font-size: 12");
       painelJogos.setPadding(new Insets(10,10,10,10)); // comando utilizado para redimencionar Vbox do painel jogos

       // Titulo Painel de Jogos

        Label lblTitulo =  new Label("Listagem de jogos!");

        // Linha abaixo do label

        Separator linha = new Separator();

        // Tabela com uma lista de jogos

        TableView<Jogo>  tabelaJogos = new TableView<>();


        // Ajustar tamanho de tabela para ocupar todo o espaço

        VBox.setVgrow(tabelaJogos, Priority.ALWAYS);



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

        // Obter os dados que serão exibidos

        JogoRepository repository = new JogoRepository();


        // Adiciona a lista de jogos na tabela

        tabelaJogos.setItems(repository.getJogos());

        // Criar painel de botões de ação

        HBox painelBotoes = new HBox(15);
        painelBotoes.setPadding( new Insets(20, 0, 0, 0));
        painelBotoes.setAlignment(Pos.BASELINE_RIGHT);



        //Criar os botões

        Button bntAdicionar = criarBotao("Adicionar", "/imagens/adicionar.png");
        bntAdicionar.setOnAction(e -> {
            TelaJogo telaJogo = new TelaJogo();
            telaJogo.criarTela(new Stage ());
        });

        Button bntEditar = criarBotao("Editar", "/imagens/editar.png");
        Button btnExibir = criarBotao("Exibir", "/imagens/visualizar.png");
        Button btnExcluir = criarBotao("Excluir", "/imagens/excluir.png");



        painelBotoes.getChildren().addAll(bntAdicionar, bntEditar, btnExibir, btnExcluir); // chamar os botôes criados



        // Adicionar o label no painel

        painelJogos.getChildren().addAll(lblTitulo, linha, tabelaJogos, painelBotoes);



        return painelJogos;
    }
    // função/método para criar botão e add imagem

    private Button criarBotao(String textoBotao,  String urlImagem) {

        Image image = new Image(getClass().getResourceAsStream(urlImagem));
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(15); // para alterar tamanho da imagem
        imageView.setFitWidth(15);

        Button button = new Button();
        button.setText(textoBotao);
        button.setGraphic(imageView);
        button.setPrefWidth(100);
        button.setPrefHeight(50);

        button.setContentDisplay(ContentDisplay.BOTTOM);

        return button;
    }


}
