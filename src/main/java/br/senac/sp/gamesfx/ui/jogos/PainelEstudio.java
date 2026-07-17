package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.data.repository.EstudioRepository;
import br.senac.sp.gamesfx.model.Estudio;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;


public class PainelEstudio {
    public Stage stage;

    public PainelEstudio(Stage stage){
        this.stage = stage;
    }

    public VBox criarPainelEstudio() {
        VBox painelEstudio = new VBox();
        painelEstudio.setPadding(new Insets(5, 20, 20, 20));
        painelEstudio.setStyle("-fx-background-color: #676767");

        //Titulo painel Estudios

        Label lblTitulo = new Label("Listagem de Estudios");
        lblTitulo.setStyle("-fx-font-size: 23; -fx-text-fill: #ffffff; -fx-font-weight: bold");

        //Linha abaixo do label
        Separator linha = new Separator();

        //Criar uma tabela de listagem de dados
        TableView<Estudio> tabelaEstudios = new TableView<>();

        //Criar colunas na tabela Estudios
        TableColumn<Estudio, Integer> colunaEstudio_Id = new TableColumn<>("ID");
        colunaEstudio_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaEstudio_Id.setPrefWidth(18);

        TableColumn<Estudio, String> colunaNomeFundador = new TableColumn<>("NOME_FUNDADOR");
        colunaNomeFundador.setCellValueFactory(new PropertyValueFactory<>("nomeFundador"));
        colunaNomeFundador.setPrefWidth(230);

        TableColumn<Estudio, String> colunaNomeEstudio = new TableColumn<>("NOME_ESTUDIO");
        colunaNomeEstudio.setCellValueFactory(new PropertyValueFactory<>("nomeEstudio"));
        colunaNomeEstudio.setPrefWidth(230);

        TableColumn<Estudio, LocalDate> colunaAnoFundacao = new TableColumn<>("ANO_FUNDAÇÃO");
        colunaAnoFundacao.setCellValueFactory(new PropertyValueFactory<>("anoFundacao"));
        colunaAnoFundacao.setPrefWidth(95);

        TableColumn<Estudio, String> colunaPaisOrigem = new TableColumn<>("PAÍS_ORIGEM");
        colunaPaisOrigem.setCellValueFactory(new PropertyValueFactory<>("paisOrigem"));
        colunaPaisOrigem.setPrefWidth(95);

        //Obter os dados que serão exibidos

        EstudioRepository Estudio = new EstudioRepository();

        tabelaEstudios.setItems(
                EstudioRepository.getEstudios()
        );

        tabelaEstudios.getColumns().addAll(colunaEstudio_Id, colunaNomeEstudio, colunaNomeFundador,
                colunaAnoFundacao, colunaPaisOrigem);
        // Adicionando a lista de jogos na tabela
        tabelaEstudios.setItems(EstudioRepository.getEstudios());

        // Criar botões de ações
        HBox painelBotoes = new HBox(10);

        Button botaoAdicionar = criarBotao("Adicionar", "/imagens/adicionar.png");

        botaoAdicionar.setOnAction(e -> {
            TelaEstudio telaEstudio = new TelaEstudio();
            telaEstudio.criarTelaEstudio(stage);
            tabelaEstudios.setItems(EstudioRepository.getEstudios());
        });


        Button btnVisualizar = criarBotao("Visualizar", "/imagens/visualizar.png");

        btnVisualizar.setOnAction(event -> {

            Estudio visualizarEstudios = tabelaEstudios.getSelectionModel().getSelectedItem();

            if (visualizarEstudios == null) {
                Alert alertaJogoNaoSelecionado = new Alert(Alert.AlertType.WARNING);
                alertaJogoNaoSelecionado.setTitle("Visualização de Plataforma");
                alertaJogoNaoSelecionado.setHeaderText("Para visualizar um plataforma você deve selecioná-lo na lista.");
                alertaJogoNaoSelecionado.showAndWait();
                return;
            }
            TelaEstudio telaEstudio = new TelaEstudio(visualizarEstudios);
            telaEstudio.criarTelaEstudio(stage);

        });


        Button btnEditar = criarBotao("Editar", "/imagens/editar.png");

        btnEditar.setOnAction(event -> {
            Estudio editarEstudio = tabelaEstudios.getSelectionModel().getSelectedItem();

            //Recuperar plataforma que quero editar
            if (editarEstudio == null) {

                Alert alertaJogoNaoSelecionado = new Alert(Alert.AlertType.WARNING);
                alertaJogoNaoSelecionado.setTitle("Edição de Plataforma");
                alertaJogoNaoSelecionado.setHeaderText("Para editar um plataforma você deve selecioná-lo na lista.");
                alertaJogoNaoSelecionado.showAndWait();
                return;
            }
            TelaEstudio telaEstudio = new TelaEstudio(editarEstudio);
            telaEstudio.criarTelaEstudio(stage);
            tabelaEstudios.setItems(EstudioRepository.getEstudios());

        });

        Button btnExcluir = criarBotao("Excluir", "/imagens/excluir.png");
        btnExcluir.setOnAction(event -> {

            Estudio excluirEstudio =
                    tabelaEstudios.getSelectionModel().getSelectedItem();

            if (excluirEstudio == null) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Excluir Estúdio");
                alerta.setHeaderText("Selecione um estúdio.");
                alerta.showAndWait();

                return;
            }

            Alert confirmacao = new Alert(
                    Alert.AlertType.CONFIRMATION);

            confirmacao.setTitle("Excluir");
            confirmacao.setHeaderText(
                    "Deseja excluir o estúdio selecionado?");

            if (confirmacao.showAndWait().get() == ButtonType.OK) {

                EstudioRepository.excluir(excluirEstudio.getId());

                tabelaEstudios.setItems(
                        EstudioRepository.getEstudios()
                );
            }
        });

        //Adicionar o label no painel
        painelEstudio.getChildren().addAll(lblTitulo, linha, tabelaEstudios, painelBotoes);

        painelBotoes.setAlignment(Pos.BOTTOM_RIGHT);

        painelBotoes.getChildren().addAll(botaoAdicionar, btnEditar, btnVisualizar, btnExcluir);

        return painelEstudio;


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
