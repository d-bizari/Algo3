package Vista;

import Excepciones.CoordenadaFueraDeRango;
import Modelo.AlgoChess;
import Modelo.Coordenada;
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
import javafx.scene.text.Text;


import java.io.File;
import java.util.ArrayList;

public class TableroGridPane{

    private GridPane tableroGridPane;

    private AlgoChess algoChess;

    private double altoCelda = 35;
    private double anchoCelda = 35;

    private double ancho;
    private double alto;

    private int n;
    private int m;

    Integer colIndex;
    Integer rowIndex;

    public TableroGridPane(AlgoChess algoChess, double ancho, double alto){

        this.algoChess = algoChess;

        n = algoChess.getCantColumnasTablero();
        m = algoChess.getCantFilasTablero();
        this.alto = alto;
        this.ancho = ancho;

        tableroGridPane = new GridPane();
        tableroGridPane.setAlignment(Pos.CENTER);
        tableroGridPane.setPrefSize(ancho, alto);

        setColoresEnTablero();

    }


    private void setColoresEnTablero(){
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                setColor(x, y);
            }
        }

    }

    private String getNumeroSector(double x) {
        if (x<m/2) {return "1";}
        return "2";
    }

    public GridPane getVisual() {
        return tableroGridPane;
    }

   public Coordenada clickGrid(javafx.scene.input.MouseEvent event){

        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != tableroGridPane) {
            colIndex = GridPane.getColumnIndex(clickedNode);
            rowIndex = GridPane.getRowIndex(clickedNode);
        }

        return new Coordenada(rowIndex, colIndex);
    }

    public Integer getColIndex(){
        return colIndex;
    }

    public Integer getRowIndex(){
        return rowIndex;
    }


    public void setUnidadEnCelda(String unidad, int x, int y) {
       Label label = new Label();
       label.setPrefWidth(anchoCelda);
       label.setPrefHeight(altoCelda);
       Image image = new Image(String.format("file:img/%s.png",unidad), anchoCelda, altoCelda, false, false);
       label.setGraphic(new ImageView(image));
       label.setPadding(new Insets(0, 0, 0, 0));
       tableroGridPane.add(label, y, x);
    }

    public void setColor(int x, int y) {
        Label label = new Label();

        label.setPrefWidth(anchoCelda);
        label.setPrefHeight(altoCelda);

        Image image = new Image("file:img/sector" + getNumeroSector(x) + ".png",anchoCelda,altoCelda,false,false);
        label.setGraphic(new ImageView(image));

        label.setPadding(new Insets(0, 0, 0, 0));
        tableroGridPane.add(label, y, x);

    }

    public void actualizar() throws CoordenadaFueraDeRango {
        for (int x = 0; x < m; x++) {
            for(int y = 0; y < n; y++) {
                String tipoUnidad = algoChess.getTipoDeUnidadEnPosicion(x, y);
                if (tipoUnidad.equals("NOHAYUNIDAD")) {
                    this.setColor(x, y);
                } else {
                    this.setUnidadEnCelda(tipoUnidad, x, y);
                }
            }
        }
    }

    public double getAncho() { return ancho;
    }
}