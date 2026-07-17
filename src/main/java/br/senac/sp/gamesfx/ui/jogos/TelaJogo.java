package br.senac.sp.gamesfx.ui.jogos;
import br.senac.sp.gamesfx.data.repository.JogoRepository;
import br.senac.sp.gamesfx.model.Jogo;
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
import javafx.scene.control.CheckBox;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Optional;


public class TelaJogo {


    private TextField tfId = new TextField();
    private TextField tfTitulo = new TextField();
    private TextField tfValor = new TextField();
    private ComboBox<String> comboPlataforma = new ComboBox<>();
    private ComboBox<String> comboEstudio = new ComboBox<>();
    private ComboBox <String> comboCategoria = new ComboBox<>();
    private DatePicker dpDataLacamento = new DatePicker();
    private CheckBox cbFinalizado = new CheckBox("Finalizado");


    public TelaJogo(Jogo jogo){
        tfId.setText(String.valueOf(jogo.getId()));
        tfTitulo.setText(jogo.getTitulo());
        tfValor.setText(String.valueOf(jogo.getPreco()));
        comboCategoria.setValue(String.valueOf(jogo.getCategoria()));
        comboPlataforma.setValue(jogo.getPlataforma());
        comboEstudio.setValue(jogo.getEstudio());
        dpDataLacamento.setValue(jogo.getDataLancamento());
        cbFinalizado.setSelected(jogo.isFinalizado());
    }


    public TelaJogo(){}

    public void criarTela(Stage stagePai) {

        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxWidth(500);
        stage.setHeight(500);
        stage.setTitle("Cadastro de Jogo");

        BorderPane raiz = new BorderPane();
        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFormulario());
        raiz.setBottom(criarPainelBotoes(stage));

        Scene cena = new Scene(raiz, 400, 600);

//        stage.setHeight(400);
//        stage.setWidth(500);
        stage.setResizable(false);
        stage.setScene(cena);
        stage.showAndWait();

    }

    public HBox criarPainelTitulo() {

        HBox painelTitulo = new HBox();

        painelTitulo.setPadding(new Insets(20, 0, 20, 20));
        painelTitulo.setStyle("-fx-background-color:#9a9a9a; ");
        painelTitulo.setAlignment(Pos.CENTER_LEFT);

        Image image = new Image(getClass().getResourceAsStream("/imagens/exame.png"));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Label lblTitulo = new Label("Cadastro de Jogos");
        painelTitulo.getChildren().addAll(imageView, lblTitulo);
        lblTitulo.setStyle("-fx-font-size: 28; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #ffffff;");

        return painelTitulo;
    }

    private VBox criarFormulario() {
        ObservableList<String> plataformas = FXCollections.observableArrayList(
                "PC", "PlayStation 1", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "Xbox", "Xbox 360",
                "Xbox One", "Xbox Series X", "Nintendo Switch", "Android", "iOS"
        );

        ObservableList<String> estudios = FXCollections.observableArrayList(
                "Rockstar Games", "Naughty Dog", "Santa Monica Studio", "Insomniac Games", "Ubisoft", "Electronic Arts", "Activision",
                "Bethesda Game Studios", "CD Projekt Red", "Square Enix", "Capcom", "Konami", "Bandai Namco", "343 Industries", "The Coalition",
                "Guerrilla Games", "FromSoftware", "Epic Games", "Valve", "Nintendo"
        );

        ObservableList<String> categorias = FXCollections.observableArrayList(
                "Ação", "Aventura","Esportes", "RPG", "FPS", "TPS", "Estratégia", "Simulação", "Esporte", "Corrida", "Luta",
                "Puzzle", "Terror", "Survival", "Battle Royale", "MMO", "Sandbox", "Plataforma", "Stealth", "Ritmo", "Cartas"
        );

        VBox formulario = new VBox();
        formulario.setPadding(new Insets(20));

        GridPane gridFormulario = new GridPane(5, 5);
        gridFormulario.setGridLinesVisible(false);
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setStyle("-fx-border-width: 2; -fx-border-color: #676767;-fx-border-radius: 32");


        // Criar os componentes que seram importados no grid
        Label lblId = new Label("ID:");
        tfId.setEditable(false);
        tfId.setDisable(true);

        Label lblTitulo = new Label("Título: ");
        tfTitulo.setPromptText("Ex: Mortal Kombat");

        Label lblPlataforma = new Label("Plataformas: ");
        comboPlataforma.setItems(plataformas);

        Label lblEstudio = new Label("Estudios:");
        comboEstudio.setItems(estudios);

        Label lblCategoria = new Label("Categoria:");
        comboCategoria.setItems(categorias);

        Label lblValor = new Label("Valor: ");
        tfValor.setPromptText("Ex.9.99");

        Label lblDatalancamento = new Label("Data de Lançamento: ");


        // Add os componentes GRID

        gridFormulario.add(lblId, 0, 0);
        gridFormulario.add(tfId, 1, 0);

        gridFormulario.add(lblTitulo, 0, 1);
        gridFormulario.add(tfTitulo, 1, 1);

        gridFormulario.add(lblPlataforma, 0, 2);
        gridFormulario.add(comboPlataforma, 1, 2);

        gridFormulario.add(lblEstudio, 0, 3);
        gridFormulario.add(comboEstudio, 1, 3);

        gridFormulario.add(lblCategoria, 0, 4);
        gridFormulario.add(comboCategoria, 1, 4);

        gridFormulario.add(lblValor, 0, 5);
        gridFormulario.add(tfValor, 1, 5);

        gridFormulario.add(lblDatalancamento, 0, 6);
        gridFormulario.add(dpDataLacamento, 1, 6);

        gridFormulario.add(cbFinalizado, 1, 7);



        formulario.getChildren().add(gridFormulario);

        return formulario;
    }

    private HBox criarPainelBotoes(Stage stage) {
        HBox painelBotoes = new HBox(10);
        painelBotoes.setStyle("-fx-background-color: #afafaf");
        painelBotoes.setPadding(new Insets(10));
        painelBotoes.setAlignment(Pos.BOTTOM_RIGHT);

        Button btnSalvar = new Button();
        Image imgSalvar = new Image(getClass().getResourceAsStream("/imagens/salve.png"));
        ImageView ivSalvar = new ImageView(imgSalvar);
        btnSalvar.setGraphic(ivSalvar);
        btnSalvar.setTooltip(new Tooltip("Salvar dados do jogo"));

        btnSalvar.setOnAction(evento -> {
            Jogo jogo = new Jogo();
            jogo.setTitulo(tfTitulo.getText());
            jogo.setPlataforma(comboPlataforma.getValue());
            jogo.setEstudio(comboEstudio.getValue());
            jogo.setDataLancamento(dpDataLacamento.getValue());
            jogo.setCategoria("Testando Categoria");
            jogo.setFinalizado(cbFinalizado.isSelected());

            try {
                double preco = Double.parseDouble(tfValor.getText().replace(",","."));
                jogo.setPreco(preco);
            } catch (NumberFormatException e) {
                Alert valorIncorreto = new Alert(Alert.AlertType.ERROR);
                valorIncorreto.setTitle("Valor Incorreto");
                valorIncorreto.setHeaderText("O valor digitado deve conter apenas números! \n Utilize ponto como separador de decimal");
                valorIncorreto.showAndWait();
                tfValor.clear();
                return;
            }


            //Criar o repositorio para enviar o jogo
            JogoRepository repository = new JogoRepository();
            if (tfId.getText().equals("")) {
                repository.salvar(jogo);


                // Mostrar a Mensagem pos salvar

                Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                mensagemSalvar.setTitle("Cadastro jogo");
                mensagemSalvar.setHeaderText("O jogo foi gravado com sucesso!");
                mensagemSalvar.setContentText("Deseja Cadastro outro jogo?");

                Optional<ButtonType> escolha = mensagemSalvar.showAndWait();// chamar mensagem a cima

                if(escolha.get() == ButtonType.OK) {
                    limparCampos();
                } else{
                    stage.close();
                }


            } else {
                jogo.setId(Integer.parseInt(tfId.getText()));
                repository.editar(jogo);

                Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
                mensagemEditar.setTitle("Atualização de jogo");
                mensagemEditar.setHeaderText("O jogo foi atualizado com sucesso!");

                mensagemEditar.showAndWait();
                stage.close();
            }


            // Mostrar a mensagem pós editar

//            JOptionPane.showMessageDialog(
//                    null,
//                    "Jogo cadastrado com sucesso!",
//                    "Erro",
//                    JOptionPane.ERROR_MESSAGE
//            );


        });

        Button btnCancelar = new Button();
        Image imgCancelar = new Image(getClass().getResourceAsStream("/imagens/cancelar.png"));
        ImageView ivCancelar = new ImageView(imgCancelar);
        btnCancelar.setGraphic(ivCancelar);
        btnCancelar.setTooltip(new Tooltip("Cancelar dados do jogo"));

        painelBotoes.getChildren().addAll(btnSalvar, btnCancelar);

        return painelBotoes;
    }

    private void limparCampos() {

        tfTitulo.clear();
        comboCategoria.setValue("");
        tfValor.clear();
        comboEstudio.setValue("");
        comboPlataforma.setValue("");
        cbFinalizado.setSelected(false);
        dpDataLacamento.setValue(LocalDate.now());
        tfTitulo.requestFocus();
    }

}