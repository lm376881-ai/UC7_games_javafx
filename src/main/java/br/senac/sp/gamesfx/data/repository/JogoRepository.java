package br.senac.sp.gamesfx.data.repository;

import br.senac.sp.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JogoRepository {

    public ObservableList<Jogo> getJogos() {

        ObservableList<Jogo>  listaJogos = FXCollections.
                observableArrayList(
                new Jogo(1, "Efootbal", "Ps5/Ps4/XOne/Xbox Series"),
                new Jogo( 2,"Ea Fc 26", "Ps5/Ps4/XOne/Xbox Series"),
                new Jogo( 3,"Gran Thef Auto", "Ps5/Ps4/XOne/Xbox Series")
                );


       return listaJogos;
    }
}



