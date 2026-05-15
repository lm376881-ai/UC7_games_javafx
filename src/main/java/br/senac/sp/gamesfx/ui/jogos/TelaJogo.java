package br.senac.sp.gamesfx.ui.jogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaJogo {

    // para conseguir exibir ser visivél e salvar texsto passado dentro do formulario
    private TextField tfId;
    private  TextField tfTitulo;
    // acima e abaixo variaveis declaradas
    private TextField tfValor;
    private ComboBox <String>  comboPlataforma;
    private ComboBox <String>  comboEstudio;
    private DatePicker dpDataLancamento;
    private CheckBox cbFinalizado;


    public void criarTela(Stage stagePai) {

        // tela quando clico em jogos
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Cadastro de Jogo");

        // Criar um BorderPane = parte de cima onde fica o
        BorderPane raiz = new BorderPane();

        // chamar
        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFormulario());
        raiz.setBottom(criarPainelBotoes());

        // para conseguir chamar todo os metodos criados
        Scene cena = new Scene(raiz, 500, 500);

        stage.setScene(cena);
        stage.setResizable(false);
        stage.showAndWait();

    }

    private  HBox criarPainelTitulo(){
        HBox painelTitulo = new HBox(10);
        painelTitulo.setPadding(new Insets(20,0,20,20));
        painelTitulo.setStyle("-fx-background-color: #9e9e9e");
        painelTitulo.setAlignment(Pos.CENTER_LEFT);

        // Imagem do Titulo
        Image image = new Image(getClass().getResourceAsStream("/imagens/adicionar.png"));
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(45); // para alterar tamanho da imagem
        imageView.setFitWidth(45);


        // Texto do Titulo
        Label lblTitulo = new Label("Cadastro de Jogos");
        lblTitulo.setStyle("-fx-text-fill: #ffffffff; -fx-font-size: 22; -fx-font-weight: bold"); // alterar  cor, tamanho da fonte e deixar em negrito



        // Adicionar os componentes do Hbox
        painelTitulo.getChildren().addAll(imageView, lblTitulo);

        return  painelTitulo;

    }

    //
    private VBox criarFormulario() {

        VBox formulario = new VBox();

        formulario.setPadding(new Insets(50));

        GridPane gridFormulario = new GridPane(6,6);
        gridFormulario.setGridLinesVisible(false); // linhas da tabela
        gridFormulario.setPadding( new Insets(30)); // por borda no formulario
        gridFormulario.setStyle("-fx-border-width: 2; -fx-border-color:  #9e9e9e; -fx-border-radius: 8"); // trocar de cor a borda
        gridFormulario.setAlignment(Pos.CENTER);



        ObservableList<String> plataformas = FXCollections.observableArrayList(
                "Super Nintendo", "Playstation 2", "Xbox 360", "PC ", "Mega Drive", "Polistation"
        );

        ObservableList<String> Estudio = FXCollections.observableArrayList(
                "Nintendo", "Rockstar Games", "BlizzardEntertainment", "Ubisoft", "Naughty Dog"
        );

        // Criar componentes que serão exibidos no grid
        Label lblId = new Label ("ID:");
        tfId = new TextField();
        tfId.setEditable(false);

        Label lblTitulo = new Label ("Titulo:");
        tfTitulo = new TextField();
        tfTitulo.setPromptText("Ex. Pro Evolution Soccer");

        Label lblPlataforma = new Label("Plataforma");
        comboPlataforma = new ComboBox<>(plataformas);

        Label lblEstudio = new Label("Estudio");
        comboEstudio = new ComboBox<>(Estudio);

        Label lblValor = new Label("Valor");
        tfValor = new TextField();
        tfValor.setPromptText("Valor");


        Label lblDataLancamento = new Label("Data de lançamento");
        dpDataLancamento = new DatePicker();

        cbFinalizado = new CheckBox("Finalizado");



        // Adicionar os componentes no Grid

        gridFormulario.add(lblId, 0, 0);
        gridFormulario.add(tfId, 1, 0);

        gridFormulario.add(lblTitulo,0,1);
        gridFormulario.add(tfTitulo,1,1);

        gridFormulario.add(lblPlataforma,0,2);
        gridFormulario.add(comboPlataforma,1,2 );

        gridFormulario.add(comboEstudio, 1,3);
        gridFormulario.add(lblEstudio, 0,3);

        gridFormulario.add(lblValor, 0,4);
        gridFormulario.add(tfValor,1,4);

        gridFormulario.add(lblDataLancamento, 0,5);
        gridFormulario.add(dpDataLancamento,1,5);

        gridFormulario.add(cbFinalizado, 1,6);





        formulario.getChildren().addAll(gridFormulario);

        return formulario;
    }

    private HBox criarPainelBotoes(){

        HBox painelBotoes = new HBox(20);


        painelBotoes.setAlignment(Pos.CENTER_RIGHT);
        painelBotoes.setStyle("-fx-background-color: #9e9e9e");
        painelBotoes.setPadding(new Insets(20));

        Button btnSalvar = new Button();
        Image imgSalvar = new Image(getClass().getResourceAsStream("/imagens/disquete.png")); // aqui adiciona imagem ao botão
        ImageView ivSalvar = new ImageView(imgSalvar);
        btnSalvar.setGraphic(ivSalvar);
        btnSalvar.setTooltip(new Tooltip("Salvar Dados do jogo"));




        Button btnCancelar = new Button();
        Image imgCancelar= new Image(getClass().getResourceAsStream("/imagens/cancelar.png"));
        ImageView ivCancelar = new ImageView(imgCancelar);
        btnCancelar.setGraphic(ivCancelar);
        btnCancelar.setTooltip(new Tooltip("Cancelar"));


        painelBotoes.getChildren().addAll(btnSalvar, btnCancelar);

        return painelBotoes;
    }
}
