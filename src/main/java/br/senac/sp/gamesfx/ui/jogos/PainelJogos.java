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
import javafx.stage.Stage;

import javax.swing.*;
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
        lblTitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #ffffff; -fx-font-weight: bold");

        // Linha abaixo do label

        Separator linha = new Separator();

        // Tabela com uma lista de jogos

        TableView<Jogo>  tabelaJogos = new TableView<>();


        // Ajustar tamanho de tabela para ocupar todo o espaço

        VBox.setVgrow(tabelaJogos, Priority.ALWAYS);



        // Criar Colunas da tabela

        TableColumn<Jogo, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setPrefWidth(40);

        TableColumn<Jogo, String> colunaTitulo = new TableColumn<>("TÍTULO");
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaTitulo.setPrefWidth(235);

        TableColumn<Jogo, String> colunaPlataforma = new TableColumn<>("PLATAFORMA");
        colunaPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        colunaPlataforma.setPrefWidth(100);

        TableColumn<Jogo, String> colunaCategoria = new TableColumn<>("CATEGORIA");
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaCategoria.setPrefWidth(100);

        TableColumn<Jogo, String> colunaEstudio = new TableColumn<>("ESTÚDIO");
        colunaEstudio.setCellValueFactory(new PropertyValueFactory<>("estudio"));
        colunaEstudio.setPrefWidth(100);

        TableColumn<Jogo, Double> colunaPreco = new TableColumn<>("PREÇO");
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaPreco.setPrefWidth(70);

        TableColumn<Jogo, LocalDate> colunaDataLancamento = new TableColumn<>("LANÇAMENTO");
        colunaDataLancamento.setCellValueFactory(new PropertyValueFactory<>("dataLancamento"));
        colunaDataLancamento.setPrefWidth(100);

        TableColumn<Jogo, Boolean> colunaFinalizado = new TableColumn<>("FINALIZADO");
        colunaFinalizado.setCellValueFactory(new PropertyValueFactory<>("finalizado"));
        colunaFinalizado.setPrefWidth(80);




        // Adicionar Colunas a tabela

        tabelaJogos.getColumns().addAll(colunaId, colunaTitulo, colunaPlataforma, colunaCategoria, colunaEstudio, colunaPreco, colunaDataLancamento, colunaFinalizado);


        // Obter os dados que serão exibidos

        JogoRepository repository = new JogoRepository();


        // Adiciona a lista de jogos na tabela

        tabelaJogos.setItems(repository.getJogos());

        // Criar painel de botões de ação

        // Criar botões de ações
        HBox painelBotoes = new HBox(10);

        Button botaoAdicionar = criarBotao("Adicionar", "/imagens/cancelar.png");
        botaoAdicionar.setOnAction(e -> {
            TelaJogo telaJogo = new TelaJogo();
            telaJogo.criarTela(stage);
            tabelaJogos.setItems(repository.getJogos());

        });

        Button btnExcluir = criarBotao("Excluir", "/imagens/excluir.png");
        btnExcluir.setOnAction(event -> {
            Jogo jogoExcluir = tabelaJogos.getSelectionModel().getSelectedItem();
            int resultado = repository.excluir(jogoExcluir.getId());

            if (resultado > 0) {

                JOptionPane.showMessageDialog(null, "Jogo excluído com sucesso!");
                tabelaJogos.setItems(repository.getJogos());

            }

        });
        Button btnExibir = criarBotao("Visualizar", "/imagens/visualizar.png");

        Button bntEditar = criarBotao("Editar", "/imagens/editar.png");
         bntEditar.setOnAction(e ->{
             Jogo jogoEditar = tabelaJogos.getSelectionModel().getSelectedItem();
             TelaJogo telaJogo = new TelaJogo(jogoEditar);
             telaJogo.criarTela(stage);
         });


        painelBotoes.setAlignment(Pos.BOTTOM_RIGHT);


        //Criar os botões

        Button bntAdicionar = criarBotao("Adicionar", "/imagens/adicionar.png");
        bntAdicionar.setOnAction(e -> {
            TelaJogo telaJogo = new TelaJogo();
            telaJogo.criarTela(new Stage ());
            tabelaJogos.setItems(repository.getJogos());
        });







        painelBotoes.getChildren().addAll(bntAdicionar, bntEditar, btnExibir, btnExcluir); // chamar os botôes criados



        // Adicionar o label no painel

        painelJogos.getChildren().addAll(lblTitulo, linha, tabelaJogos, painelBotoes);



        return painelJogos;
    }

    private Button criarBotao(String textoBotao, String urlImagem) {
        Image image = new Image(getClass().getResourceAsStream(urlImagem));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Button button = new Button();
        button.setPrefWidth(110);
        button.setPrefHeight(50);
        button.setText(textoBotao);
        button.setGraphic(imageView);

        return button;


    }
}