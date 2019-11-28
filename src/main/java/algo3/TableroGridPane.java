package algo3;

import Modelo.AlgoChess;
import Modelo.Tablero;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;

import java.io.File;
import java.util.ArrayList;

public class TableroGridPane{


    private GridPane tableroGridPane;

    private AlgoChess algoChess;

    private double altoCelda = 40;
    private double anchoCelda = 40;

    private int n;
    private int m;

    Integer colIndex;
    Integer rowIndex;

    public TableroGridPane(AlgoChess algoChess, double ancho, double alto){

        this.algoChess = algoChess;

        n = algoChess.getCantColumnasTablero();
        m = algoChess.getCantFilasTablero();

        tableroGridPane = new GridPane();
        tableroGridPane.setAlignment(Pos.CENTER);
        tableroGridPane.setPrefSize(ancho, alto);

        setColoresEnTablero();

    }


    private void setColoresEnTablero(){
        Label label;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                label = new Label();

                label.setPrefWidth(anchoCelda);
                label.setPrefHeight(altoCelda);

                Image image = new Image("file:img/sector" + getNumeroSector(y) + ".png",anchoCelda,altoCelda,false,false);
                label.setGraphic(new ImageView(image));

                label.setPadding(new Insets(0, 0, 0, 0));
                tableroGridPane.add(label, x, y);
            }
        }

    }

    private String getNumeroSector(double y) {
        if (y<m/2) {return "1";}
        return "2";
    }

    public GridPane getVisual() {
        return tableroGridPane;
    }

    public void clickGrid(javafx.scene.input.MouseEvent event){

        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != tableroGridPane) {
            colIndex = GridPane.getColumnIndex(clickedNode);
            rowIndex = GridPane.getRowIndex(clickedNode);
        }
    }

    public Integer getColIndex(){
        return colIndex;
    }

    public Integer getRowIndex(){
        return rowIndex;
    }

}