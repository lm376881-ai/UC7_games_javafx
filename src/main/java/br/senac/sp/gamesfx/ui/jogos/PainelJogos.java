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

    public PainelJogos(Stage stage) {
        this.stage = stage;
    }

    public VBox criarPainelJogos() {

        VBox painelJogos = new VBox();
        painelJogos.setStyle("-fx-background-color:#aaaaaa;-fx-font-size: 12");
        painelJogos.setPadding(new Insets(10, 10, 10, 10));

        // Título
        Label lblTitulo = new Label("Listagem de jogos!");
        lblTitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #ffffff; -fx-font-weight: bold");

        Separator linha = new Separator();

        // Tabela
        TableView<Jogo> tabelaJogos = new TableView<>();

        VBox.setVgrow(tabelaJogos, Priority.ALWAYS);

        // Colunas
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

        tabelaJogos.getColumns().addAll(
                colunaId,
                colunaTitulo,
                colunaPlataforma,
                colunaCategoria,
                colunaEstudio,
                colunaPreco,
                colunaDataLancamento,
                colunaFinalizado
        );

        // Repositório
        JogoRepository repository = new JogoRepository();

        tabelaJogos.setItems(repository.getJogos());

        // Painel de botões
        HBox painelBotoes = new HBox(10);
        painelBotoes.setAlignment(Pos.BOTTOM_RIGHT);

        // Botão Adicionar
        Button btnAdicionar = criarBotao("Adicionar", "/imagens/adicionar.png");

        btnAdicionar.setOnAction(e -> {
            TelaJogo telaJogo = new TelaJogo();
            telaJogo.criarTela(new Stage());
            tabelaJogos.setItems(repository.getJogos());
        });

        // Botão Editar
        Button btnEditar = criarBotao("Editar", "/imagens/editar.png");

        btnEditar.setOnAction(e -> {

            Jogo jogoEditar = tabelaJogos.getSelectionModel().getSelectedItem();

            if (jogoEditar == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Editar Jogo");
                alerta.setHeaderText("Selecione um jogo para editar.");
                alerta.showAndWait();
                return;
            }

            TelaJogo telaJogo = new TelaJogo(jogoEditar);
            telaJogo.criarTela(stage);

            tabelaJogos.setItems(repository.getJogos());
        });

        // Botão Visualizar
        Button btnExibir = criarBotao("Visualizar", "/imagens/visualizar.png");

        btnExibir.setOnAction(event -> {

            Jogo visualizarJogo = tabelaJogos.getSelectionModel().getSelectedItem();

            if (visualizarJogo == null) {
                Alert alertaJogoNaoSelecionado =
                        new Alert(Alert.AlertType.WARNING);

                alertaJogoNaoSelecionado.setTitle("Visualização de Jogo");
                alertaJogoNaoSelecionado.setHeaderText(
                        "Para visualizar um jogo você deve selecioná-lo na lista."
                );
                alertaJogoNaoSelecionado.showAndWait();
                return;
            }

            TelaJogo telaJogo = new TelaJogo(visualizarJogo);
            telaJogo.criarTela(stage);
        });

        // Botão Excluir
        Button btnExcluir = criarBotao("Excluir", "/imagens/excluir.png");

        btnExcluir.setOnAction(event -> {

            Jogo jogoExcluir = tabelaJogos.getSelectionModel().getSelectedItem();

            if (jogoExcluir == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Excluir Jogo");
                alerta.setHeaderText("Selecione um jogo para excluir.");
                alerta.showAndWait();
                return;
            }

            int resultado = repository.excluir(jogoExcluir.getId());

            if (resultado > 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Jogo excluído com sucesso!"
                );

                tabelaJogos.setItems(repository.getJogos());
            }
        });

        painelBotoes.getChildren().addAll(
                btnAdicionar,
                btnEditar,
                btnExibir,
                btnExcluir
        );

        painelJogos.getChildren().addAll(
                lblTitulo,
                linha,
                tabelaJogos,
                painelBotoes
        );

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