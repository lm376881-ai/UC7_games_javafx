package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.data.repository.EstudioRepository;
import br.senac.sp.gamesfx.model.Estudio;
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
        painelTitulo.setStyle("-fx-background-color:#9a9a9a;");
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


        VBox formulario = new VBox();
        formulario.setPadding(new Insets(20));

        GridPane gridFormulario = new GridPane(5,5);
        gridFormulario.setGridLinesVisible(false);
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setStyle("-fx-border-width: 2; " + "-fx-border-color: #676767; " + "-fx-border-radius: 32");

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
        if (dpAnoFundacao.getValue() == null) {
            dpAnoFundacao.setValue(LocalDate.now());
        }

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
        painelBotoes.setStyle("-fx-background-color: #afafaf");
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

            if (dpAnoFundacao.getValue() != null) {
                estudio.setAnoFundacao(
                        dpAnoFundacao.getValue().getYear()
                );
            }

            estudio.setPaisOrigem(tfPaisOrigem.getText());

            EstudioRepository repository =
                    new EstudioRepository();

            if (tfNomeEstudio.getText().trim().isEmpty()) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro");
                alerta.setHeaderText("Nome do estúdio obrigatório");
                alerta.setContentText(
                        "Informe o nome do estúdio."
                );
                alerta.showAndWait();

                return;
            }

            System.out.println(
                    "Campo Nome Estudio: ["
                            + tfNomeEstudio.getText() + "]"
            );

            System.out.println(
                    "ID = [" + tfId.getText() + "]"
            );

            int resultado;

            // CADASTRO
            if (tfId.getText() == null ||
                    tfId.getText().trim().isEmpty()) {

                resultado = repository.salvar(estudio);

            }
            // EDIÇÃO
            else {

                try {

                    estudio.setId(
                            Integer.parseInt(
                                    tfId.getText().trim()
                            )
                    );

                    repository.editar(estudio);

                    resultado = 1;

                } catch (NumberFormatException e) {

                    Alert erro = new Alert(
                            Alert.AlertType.ERROR
                    );

                    erro.setTitle("Erro");
                    erro.setHeaderText("ID inválido");
                    erro.setContentText(
                            "Não foi possível editar o registro."
                    );

                    erro.showAndWait();

                    return;
                }
            }

            if (resultado > 0) {

                Alert mensagem =
                        new Alert(Alert.AlertType.INFORMATION);

                mensagem.setTitle("Cadastro de Estúdio");

                if (tfId.getText() == null ||
                        tfId.getText().trim().isEmpty()) {

                    mensagem.setHeaderText(
                            "Estúdio cadastrado com sucesso!"
                    );

                } else {

                    mensagem.setHeaderText(
                            "Estúdio atualizado com sucesso!"
                    );
                }

                mensagem.showAndWait();

                stage.close();

            } else {

                Alert erro = new Alert(Alert.AlertType.ERROR);

                erro.setTitle("Erro");
                erro.setHeaderText(
                        "Não foi possível salvar o estúdio."
                );

                erro.showAndWait();
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
//    private void limparCampos() {
//
//        tfNomeEstudio.clear();
//        tfNomeFundador.clear();
//        tfPaisOrigem.clear();
//        dpAnoFundacao.setValue(LocalDate.now());
//        tfNomeFundador.requestFocus();
//    }

}
