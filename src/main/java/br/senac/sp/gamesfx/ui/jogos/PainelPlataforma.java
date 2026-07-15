package br.senac.sp.gamesfx.ui.jogos;

import br.senac.sp.gamesfx.data.repository.PlataformaRepository;
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

    private PlataformaRepository repository =
            new PlataformaRepository();

    public PainelPlataforma(Stage stage) {
        this.stage = stage;
    }

    public VBox criarPainelPLataforma() {

        VBox painelPlataforma = new VBox();

        painelPlataforma.setStyle(
                "-fx-background-color:#aaaaaa;-fx-font-size:12"
        );

        painelPlataforma.setPadding(
                new Insets(10, 10, 10, 10)
        );

        Label lblTitulo = new Label("Plataformas");

        lblTitulo.setStyle(
                "-fx-font-size:24;" +
                        "-fx-text-fill:#ffffff;" +
                        "-fx-font-weight:bold"
        );

        Separator linha = new Separator();

        TableView<Plataforma> tabelaPlataforma =
                new TableView<>();

        VBox.setVgrow(
                tabelaPlataforma,
                Priority.ALWAYS
        );

        // Dados do banco
        tabelaPlataforma.setItems(
                repository.getPlataforma()
        );

        // Colunas

        TableColumn<Plataforma, Integer> colunaId =
                new TableColumn<>("ID");

        colunaId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colunaId.setPrefWidth(50);

        TableColumn<Plataforma, String> colunaNome =
                new TableColumn<>("Nome");

        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colunaNome.setPrefWidth(220);

        TableColumn<Plataforma, String> colunaFabricante =
                new TableColumn<>("Fabricante");

        colunaFabricante.setCellValueFactory(
                new PropertyValueFactory<>("fabricante")
        );

        colunaFabricante.setPrefWidth(180);

        TableColumn<Plataforma, LocalDate> colunaDataLancamento =
                new TableColumn<>("Lançamento");

        colunaDataLancamento.setCellValueFactory(
                new PropertyValueFactory<>("dataLancamento")
        );

        colunaDataLancamento.setPrefWidth(120);

        TableColumn<Plataforma, Double> colunaValor =
                new TableColumn<>("Valor");

        colunaValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );

        colunaValor.setPrefWidth(100);

        tabelaPlataforma.getColumns().addAll(
                colunaId,
                colunaNome,
                colunaFabricante,
                colunaDataLancamento,
                colunaValor
        );

        HBox painelBotoes = new HBox(10);

        // ADICIONAR

        Button btnAdicionar =
                criarBotao(
                        "Adicionar",
                        "/imagens/adicionar.png"
                );

        btnAdicionar.setOnAction(e -> {

            TelaPlataforma telaPlataforma =
                    new TelaPlataforma();

            telaPlataforma.criarTela(new Stage());

            tabelaPlataforma.setItems(
                    repository.getPlataforma()
            );
        });

        // EDITAR

        Button btnEditar =
                criarBotao(
                        "Editar",
                        "/imagens/editar.png"
                );

        btnEditar.setOnAction(e -> {

            Plataforma plataforma =
                    tabelaPlataforma
                            .getSelectionModel()
                            .getSelectedItem();

            if (plataforma == null) {

                Alert alerta =
                        new Alert(Alert.AlertType.WARNING);

                alerta.setTitle("Editar");
                alerta.setHeaderText(
                        "Selecione uma plataforma para editar."
                );

                alerta.showAndWait();

                return;
            }

            TelaPlataforma tela =
                    new TelaPlataforma(plataforma);

            tela.criarTela(new Stage());

            tabelaPlataforma.setItems(
                    repository.getPlataforma()
            );
        });

        // VISUALIZAR

        Button btnExibir =
                criarBotao(
                        "Visualizar",
                        "/imagens/visualizar.png"
                );

        btnExibir.setOnAction(e -> {

            Plataforma plataforma =
                    tabelaPlataforma
                            .getSelectionModel()
                            .getSelectedItem();

            if (plataforma == null) {

                Alert alerta =
                        new Alert(Alert.AlertType.WARNING);

                alerta.setTitle("Visualização");

                alerta.setHeaderText(
                        "Selecione uma plataforma para visualizar."
                );

                alerta.showAndWait();

                return;
            }

            TelaPlataforma telaPlataforma =
                    new TelaPlataforma(plataforma);

            telaPlataforma.criarTela(stage);
        });

        // EXCLUIR

        Button btnExcluir =
                criarBotao(
                        "Excluir",
                        "/imagens/excluir.png"
                );

        btnExcluir.setOnAction(e -> {

            Plataforma plataformaExcluir =
                    tabelaPlataforma
                            .getSelectionModel()
                            .getSelectedItem();

            if (plataformaExcluir == null) {

                Alert alerta =
                        new Alert(Alert.AlertType.WARNING);

                alerta.setTitle("Excluir Plataforma");

                alerta.setHeaderText(
                        "Selecione uma plataforma para excluir."
                );

                alerta.showAndWait();

                return;
            }

            int resultado =
                    repository.excluir(
                            plataformaExcluir.getId()
                    );

            if (resultado > 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Plataforma excluída com sucesso!"
                );

                tabelaPlataforma.setItems(
                        repository.getPlataforma()
                );
            }
        });

        painelBotoes.getChildren().addAll(
                btnAdicionar,
                btnEditar,
                btnExibir,
                btnExcluir
        );

        painelBotoes.setAlignment(
                Pos.CENTER_RIGHT
        );

        painelPlataforma.getChildren().addAll(
                lblTitulo,
                linha,
                tabelaPlataforma,
                painelBotoes
        );

        return painelPlataforma;
    }

    private Button criarBotao(
            String textoBotao,
            String urlImagem) {

        Image image = new Image(getClass().getResourceAsStream(urlImagem));

        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(16);
        imageView.setFitWidth(16);

        return new Button(
                textoBotao,
                imageView
        );
    }
}