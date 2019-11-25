package algo3.Controller;

import Modelo.AlgoChess;
import Modelo.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class tableroGridPane {


    private GridPane tableroGridPane;

    private AlgoChess algoChess;

    private double altoCelda = 40;
    private double anchoCelda = 40;

    private int n;
    private int m;

    public tableroGridPane(AlgoChess algoChess, double ancho, double alto) {

        this.algoChess = algoChess;

        n = algoChess.getCantColumnasTablero();
        m = algoChess.getCantFilasTablero();

        tableroGridPane = new GridPane();
        tableroGridPane.setAlignment(Pos.CENTER);
        tableroGridPane.setPrefSize(ancho, alto);

        setColoresEnTablero();
    }

    private void setColoresEnTablero() {
        Label label;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                label = new Label();

                label.setPrefWidth(anchoCelda);
                label.setPrefHeight(altoCelda);

                Image image = new Image("file:img/sector" + getNumeroSector(i, j) + ".png",anchoCelda,altoCelda,false,false);
                label.setGraphic(new ImageView(image));

                label.setPadding(new Insets(0, 0, 0, 0));
                tableroGridPane.add(label, j, i);
            }
        }

    }

    private String getNumeroSector(double x, double y) {
        if (y<m/2) {return "1";}
        return "2";
    }

    public GridPane getVisual() {
        return tableroGridPane;
    }

    public String clickGrid(javafx.scene.input.MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != tableroGridPane) {
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            return "Clickeo posicion x: " + colIndex + " y: " + rowIndex;
        }
        return "hola";
    }

}