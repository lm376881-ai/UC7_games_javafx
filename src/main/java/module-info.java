module br.senac.sp.gamesfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
<<<<<<< HEAD
    requires java.desktop;
    requires java.management;
=======
    requires java.management;
    requires java.desktop;
>>>>>>> a62e25edc39768ec657550d5496abcf39667a9f3

    opens br.senac.sp.gamesfx to javafx.fxml;
    opens br.senac.sp.gamesfx.model to javafx.base;
    exports br.senac.sp.gamesfx;
}