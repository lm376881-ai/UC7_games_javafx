package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.data.repository.EstudioRepository;
import br.senac.sp.gamesfx.model.Estudio;
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

import java.time.LocalDate;
import java.util.Optional;

public class TelaEstudio {

    private TextField tfId = new TextField();
    private TextField tfNomeEstudio = new TextField();
    private  TextField tfNomeFundador = new TextField();
    private DatePicker dpAnoFundacao = new DatePicker();
    private TextField tfPaisOrigem = new TextField();

    public TelaEstudio(Estudio estudio){
        tfId.setText(String.valueOf(estudio.getId()));
        tfNomeEstudio.setText(estudio.getNomeEstudio());
        tfNomeFundador.setText(estudio.getNomeFundador());
      //  tfEstudio.setText(estudio.getNomeEstudio());
        dpAnoFundacao.setValue(LocalDate.of(estudio.getAnoFundacao(),1,1));
        tfPaisOrigem.setText(estudio.getPaisOrigem());
    }

    public TelaEstudio (){

    }

    public void criarTelaEstudio(Stage stagePai){
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxWidth(500);
        stage.setMaxHeight(500);
        stage.setTitle("Cadastro de Estudios");

        BorderPane raiz = new BorderPane();
        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFormularioEstudio());
        raiz.setBottom(criarPainelBotoes(stage));

        Scene cena = new Scene(raiz, 400, 600);


        stage.setResizable(false);
        stage.setScene(cena);
        stage.showAndWait();
    }
    public HBox criarPainelTitulo() {

        HBox painelTitulo = new HBox();

        painelTitulo.setPadding(new Insets(20, 0, 20, 20));
        painelTitulo.setStyle("-fx-background-color:#676767; ");
        painelTitulo.setAlignment(Pos.CENTER_LEFT);

        Image image = new Image(getClass().getResourceAsStream("/imagens/exame.png"));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Label lblTitulo = new Label("Cadastro de Estudios");
        painelTitulo.getChildren().addAll(imageView, lblTitulo);
        lblTitulo.setStyle("-fx-font-size: 28; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #ffffff;");

        return painelTitulo;
    }

    private VBox criarFormularioEstudio(){

        EstudioRepository estudioRepository = new EstudioRepository();

        ObservableList<Estudio> estudios = estudioRepository.getEstudios();

        VBox formulario = new VBox();
        formulario.setPadding(new Insets(20));

        GridPane gridFormulario = new GridPane(5,5);
        gridFormulario.setGridLinesVisible(false);
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setStyle("-fx-border-width: 2; -fx-border-color: #2e2b68");

        //Criar os componentes que seram importados no grid
        Label lblEstudioId = new Label("ID:");
        tfId.setDisable(true);

        Label lblFundador = new Label("Fundador: ");
        tfNomeFundador.setPromptText("Ex: Sam Houser e Dan Houser");

        Label lblEstudio = new Label("Estudios: ");
        tfNomeEstudio.setPromptText("Ex: Rockstar Games");

        Label lblPaisOrigem = new Label("País de Origem: ");
        tfPaisOrigem.setPromptText("Ex: Bulgária");

        Label lblAnoFundacao = new Label("Ano de Fundação: ");
        dpAnoFundacao = new DatePicker(LocalDate.now());

        //Adicionar os componentes no GRID

        gridFormulario.add(lblEstudioId,0,0);
        gridFormulario.add(tfId,1,0);

       // gridFormulario.add(lblNomeEstudio,0,1);
       // gridFormulario.add(tfNomeEstudio,1,1);

        gridFormulario.add(lblFundador,0,2);
        gridFormulario.add(tfNomeFundador,1,2);

        gridFormulario.add(lblEstudio,0,3);
        gridFormulario.add(tfNomeEstudio,1,3);

        gridFormulario.add(lblPaisOrigem,0,4);
        gridFormulario.add(tfPaisOrigem,1,4);

        gridFormulario.add(lblAnoFundacao,0,5);
        gridFormulario.add(dpAnoFundacao,1,5);

        formulario.getChildren().add(gridFormulario);

        return formulario;

    }
    private HBox criarPainelBotoes(Stage stage) {
        HBox painelBotoes = new HBox(10);
        painelBotoes.setStyle("-fx-background-color: #676767");
        painelBotoes.setPadding(new Insets(10));
        painelBotoes.setAlignment(Pos.BOTTOM_RIGHT);

        Button btnSalvar = new Button();
        Image imgSalvar = new Image(getClass().getResourceAsStream("/imagens/salve.png"));
        ImageView ivSalvar = new ImageView(imgSalvar);
        btnSalvar.setGraphic(ivSalvar);
        btnSalvar.setTooltip(new Tooltip("Salvar dados do estudio"));
        btnSalvar.setOnAction(event -> {


            Estudio estudio = new Estudio();
            estudio.setNomeEstudio(tfNomeEstudio.getText());
            estudio.setNomeFundador(tfNomeFundador.getText());
            if (dpAnoFundacao.getValue()!= null){
                estudio.setAnoFundacao(dpAnoFundacao.getValue().getYear());
            }
            estudio.setPaisOrigem(tfPaisOrigem.getText());


            //Criar o repositorio para enviar o plataforma
            EstudioRepository repository = new EstudioRepository();

            if (tfId.getText().equals("")) {
                repository.salvar(estudio);

                //Mostra a mensagem do pós-salvar
                Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                mensagemSalvar.setTitle("Cadastro de estúdio");
                mensagemSalvar.setHeaderText("O estúdio gravado com sucesso!");
                mensagemSalvar.setContentText("Deseja cadastrar outro estúdio?");

                Optional<ButtonType> escolha = mensagemSalvar.showAndWait();

                if (escolha.get() == ButtonType.OK) {
                    limparCampos();
                } else {
                    stage.close();
                }


            } else {
                estudio.setId(Integer.parseInt(tfId.getText()));
                repository.editar(estudio);

                //Mostra mensagem a pós-editar
                Alert mensagemEditar = new Alert(Alert.AlertType.CONFIRMATION);
                mensagemEditar.setTitle("Atualização de estudio");
                mensagemEditar.setHeaderText("O estudio foi atualizado com sucesso!");

                mensagemEditar.showAndWait();
                stage.close();


            }
        });

        Button btnCancelar = new Button();
        Image imgCancelar = new Image(getClass().getResourceAsStream("/imagens/cancelar.png"));
        ImageView ivCancelar = new ImageView(imgCancelar);
        btnCancelar.setGraphic(ivCancelar);
        btnCancelar.setTooltip(new Tooltip("Cancelar dados do estúdio"));

        painelBotoes.getChildren().addAll(btnSalvar, btnCancelar);

        return painelBotoes;
    }
    private void limparCampos() {

        tfNomeEstudio.clear();
        tfNomeFundador.clear();
        tfPaisOrigem.clear();
        dpAnoFundacao.setValue(LocalDate.now());
        tfNomeFundador.requestFocus();
    }

}
