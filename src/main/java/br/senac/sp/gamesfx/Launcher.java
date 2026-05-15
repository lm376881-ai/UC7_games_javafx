package br.senac.sp.gamesfx;

import br.senac.sp.gamesfx.data.repository.JogoRepository;
import br.senac.sp.gamesfx.model.Jogo;
import javafx.application.Application;

import java.time.LocalDate;

public class Launcher {
    public static void main(String[] args) {
        //Application.launch(TelaPrincipal.class, args);

        Jogo jogo = new Jogo(0,"Teste", "Plataforma Teste");
        jogo.setEstudio("Estudio Teste");
        jogo.setCategoria("Categodia Teste");
        jogo.setPreco(9.99);
        jogo.setDataLancamento(LocalDate.now());
        jogo.setFinalizado(true);

        JogoRepository repository = new JogoRepository();
        repository.salvar(jogo);


    }
}
