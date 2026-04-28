module br.senac.sp.gamesfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens br.senac.sp.gamesfx to javafx.fxml;
    exports br.senac.sp.gamesfx;
}