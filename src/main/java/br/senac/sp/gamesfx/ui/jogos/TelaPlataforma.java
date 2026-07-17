package br.senac.sp.gamesfx.ui.jogos;
import br.senac.sp.gamesfx.data.repository.PlataformaRepository;
import br.senac.sp.gamesfx.model.Plataforma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.time.LocalDate;

public class TelaPlataforma {

    private TextField tfId = new TextField();
    private TextField tfNome = new TextField();
    private TextField tfFabricante = new TextField();
    private TextField tfValor = new TextField();
    private DatePicker dpDataLancamento = new DatePicker();


    public TelaPlataforma() {
    }


    public TelaPlataforma(Plataforma plataforma) {
        tfId.setText(String.valueOf(plataforma.getId()));
        tfNome.setText(plataforma.getNome());
        tfFabricante.setText(plataforma.getFabricante());
//        tfValor.setText(String.valueOf(plataforma.getValor()));
        dpDataLancamento.setValue(plataforma.getDataLancamento());
    }

    public void criarTela(Stage stagePai) {

        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Cadastro de Plataforma");

        BorderPane raiz = new BorderPane();
        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFormulario());
        raiz.setBottom(criarPainelBotoes(stage));

        Scene cena = new Scene(raiz, 400, 600);
        stage.setMaxWidth(500);
        stage.setHeight(500);
        stage.setScene(cena);
        stage.setResizable(false);
        stage.showAndWait();


    }

    private HBox criarPainelTitulo() {

        HBox painel = new HBox(10);
        painel.setPadding(new Insets(20));
        painel.setAlignment(Pos.CENTER_LEFT);
        painel.setStyle("-fx-background-color:#9a9a9a;");

        Image img = new Image(getClass().getResourceAsStream("/imagens/exame.png"));
        ImageView iv = new ImageView(img);

        iv.setFitWidth(40);
        iv.setFitHeight(40);

        Label titulo = new Label("Cadastro de Plataforma");
        titulo.setStyle("-fx-font-size: 28; " + "-fx-font-weight: bold; " + "-fx-text-fill: #ffffff;");


        painel.getChildren().addAll(iv, titulo);

        return painel;
    }

    private VBox criarFormulario() {

        VBox formulario = new VBox();
        formulario.setPadding(new Insets(20));
        GridPane gridFormulario = new GridPane(5, 5);
        gridFormulario.setGridLinesVisible(false);
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setStyle("-fx-border-width: 2; -fx-border-color: #676767;-fx-border-radius: 32");


        //  Campos
        Label lblId = new Label("ID:");
        tfId.setDisable(true);

        Label lblNome = new Label("Nome:");
        tfNome.setPromptText("Ex: PlayStation 5");

        Label lblFabricante = new Label("Fabricante:");
        tfFabricante.setPromptText("Ex: Sony");

        Label lblValor = new Label("Valor:");
        tfValor.setPromptText("Ex: 3999.99");

        Label lblData = new Label("Data de Lançamento:");
        dpDataLancamento.setValue(LocalDate.now());

        //  Adicionar no gridFormulario
        gridFormulario.add(lblId, 0, 0);
        gridFormulario.add(tfId, 1, 0);

        gridFormulario.add(lblNome, 0, 1);
        gridFormulario.add(tfNome, 1, 1);

        gridFormulario.add(lblFabricante, 0, 2);
        gridFormulario.add(tfFabricante, 1, 2);

        gridFormulario.add(lblValor, 0, 3);
        gridFormulario.add(tfValor, 1, 3);

        gridFormulario.add(lblData, 0, 4);
        gridFormulario.add(dpDataLancamento, 1, 4);

        formulario.getChildren().add(gridFormulario);

        return formulario;
    }



    private HBox criarPainelBotoes(Stage stage) {

        HBox box = new HBox(10);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER_RIGHT);

        Button btnSalvar = new Button();
        Image imgSalvar = new Image(
                getClass().getResourceAsStream("/imagens/salve.png")
        );
        ImageView ivSalvar = new ImageView(imgSalvar);
        btnSalvar.setGraphic(ivSalvar);

        btnSalvar.setOnAction(e -> {

            Plataforma plataforma = new Plataforma();

            plataforma.setNome(tfNome.getText());
            plataforma.setFabricante(tfFabricante.getText());
            plataforma.setDataLancamento(dpDataLancamento.getValue());

            try {

                double valor =
                        Double.parseDouble(
                                tfValor.getText().replace(",", ".")
                        );

                plataforma.setValor(valor);

                PlataformaRepository repository =
                        new PlataformaRepository();

                // EDITAR
                if (!tfId.getText().isEmpty()) {

                    plataforma.setId(
                            Integer.parseInt(tfId.getText())
                    );

                    repository.editar(plataforma);

                }
                // NOVO CADASTRO
                else {

                    repository.salvar(plataforma);

                }

                Alert ok =
                        new Alert(Alert.AlertType.INFORMATION);

                ok.setTitle("Sucesso");
                ok.setHeaderText("Operação realizada com sucesso!");

                ok.showAndWait();

                stage.close();

            } catch (NumberFormatException erro) {

                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Erro");
                alert.setHeaderText("Valor inválido!");
                alert.setContentText(
                        "Digite apenas números no campo valor."
                );

                alert.showAndWait();
            }
        });

        Button btnCancelar = new Button();
        Image imgCancelar = new Image(
                getClass().getResourceAsStream("/imagens/cancelar.png")
        );
        ImageView ivCancelar = new ImageView(imgCancelar);
        btnCancelar.setGraphic(ivCancelar);
        btnCancelar.setOnAction(e -> stage.close());

        box.getChildren().addAll(
                btnSalvar,
                btnCancelar
        );

        return box;
    }
}
