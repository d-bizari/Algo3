package algo3;

import Modelo.Catapulta;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PropiedadUnidad {
    private GridPane grid;

    public PropiedadUnidad(){
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
    }

    public GridPane gridSoldadoInfanteria(){

        Label soldadoInfanteria  = new Label("Soldado Infanteria");
        GridPane.setConstraints(soldadoInfanteria, 1, 1);
        Text text_soldado1 = new Text("Costo: 1");
        Text text_soldado2 = new Text("Atributos: Vida 100 + Daño cuerpo a cuerpo: 10 + Daño a distancia: 0");
        Text text_soldado3 = new Text("Comportamiento: Puede atacar a un enemigo a corta distancia + Si hay más de 3 Soldados contiguos (en cualquier dirección) se comportan como un Batallón y PUEDEN moverse los 3 al mismo tiempo en el mismo turno.");

        GridPane.setConstraints(text_soldado1, 1, 2);
        GridPane.setConstraints(text_soldado2, 1, 3);
        GridPane.setConstraints(text_soldado3, 1, 4);

        grid.getChildren().addAll(soldadoInfanteria, text_soldado1, text_soldado2, text_soldado3);

        return grid;
    }

    public GridPane gridJinete() {

        Label jinete  = new Label("Jinete");
        GridPane.setConstraints(jinete, 1, 1);
        Text text_jinete1 = new Text("Costo: 3");
        Text text_jinete2 = new Text("Atributos: Vida 100 + Daño cuerpo a cuerpo: 15 + Daño a distancia: 15");
        Text text_jinete3 = new Text("Comportamiento: Si hay al menos un Soldado de Infantería aliado cerca o no hay ningún enemigo cerca, su arma de ataque es un Arco y Flecha y únicamente puede atacar a enemigos en distancia media + Si no hay ningún aliado cercano y hay enemigos cercanos , su arma de ataque es una Espada y únicamente puede atacar a enemigos en distancia corta.");
        GridPane.setConstraints(text_jinete1, 1, 2);
        GridPane.setConstraints(text_jinete2, 1, 3);
        GridPane.setConstraints(text_jinete3, 1, 4);

        grid.getChildren().addAll(jinete, text_jinete1, text_jinete2, text_jinete3);

        return grid;

    }

    public GridPane gridCatapulta() {

        Label catapulta  = new Label("Catapulta");
        GridPane.setConstraints(catapulta, 1, 1);
        Text text_catapulta1 = new Text("Costo: 5");
        Text text_catapulta2 = new Text("Atributos: Vida 50 + Daño cuerpo a cuerpo: 0 + Daño a distancia: 20");
        Text text_catapulta3 = new Text("Comportamiento: No puede moverse en toda la partida. + Ataca en una distancia lejana únicamente. + Causa daño a la primera unidad enemiga alcanzada, y a todas las unidades directamente contiguas, y si a su vez la segunda unidad tiene otra unidad contigua, también causa el mismo daño");
        GridPane.setConstraints(text_catapulta1, 1, 2);
        GridPane.setConstraints(text_catapulta2, 1, 3);
        GridPane.setConstraints(text_catapulta3, 1, 4);

        grid.getChildren().addAll(catapulta, text_catapulta1, text_catapulta2, text_catapulta3);

        return grid;
    }

    public GridPane gridCurandero() {

        Label curandero = new Label("Curandero");
        GridPane.setConstraints(curandero, 1, 1);
        Text text_curandero1 = new Text("Costo: 5");
        Text text_curandero2 = new Text("Atributos: Vida 50 + Daño cuerpo a cuerpo: 0 + Daño a distancia: 20");
        Text text_curandero3 = new Text("Comportamiento: No puede moverse en toda la partida. + Ataca en una distancia lejana únicamente. + Causa daño a la primera unidad enemiga alcanzada, y a todas las unidades directamente contiguas, y si a su vez la segunda unidad tiene otra unidad contigua, también causa el mismo daño");
        GridPane.setConstraints(text_curandero1, 1, 2);
        GridPane.setConstraints(text_curandero2, 1, 3);
        GridPane.setConstraints(text_curandero3, 1, 4);

        grid.getChildren().addAll(curandero, text_curandero1, text_curandero2, text_curandero3);

        return grid;
    }

}
