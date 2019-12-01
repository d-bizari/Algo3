package Controlador;


/*public class Controlador {

    private Stage stage;
    private AlgoChess juego;
    private TableroGridPane tablero;
    private TextField jugador1;
    private TextField jugador2;
    private int sectorJugador1;
    private int sectorJugador2;
    private HashMap<String, Integer> hashUnidadesJugador1;
    private HashMap<String, Integer> hashUnidadesJugador2;
    private GridPane cantidadUnidadesJugador1;
    private GridPane cantidadUnidadesJugador2;
    private ArrayList<Integer> coordenadas;



    public Controlador(Stage escena, AlgoChess juego, TextField NombreJugador1, TextField NombreJugador2, int sector1, int sector2){
        this.stage = escena;
        this.juego = juego;
        this.tablero = new TableroGridPane(juego, 20, 20);
        this.jugador1 = NombreJugador1;
        this.jugador2 = NombreJugador2;
        this.sectorJugador1 = sector1;
        this.sectorJugador2 = sector2;

    }

    private GridPane crearGridCantidadUnidades(String jugador, HashMap<String, Integer> hash, Button continuar) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //crea texto que se muestra al lado de cada choice box.
        String titulo = String.format("Jugador %s posee estas unidades, ubique sus fichas", jugador);
        Text text_jugador = new Text(titulo);
        Text text_soldadoCantidad = new Text("Soldado infanteria:");
        Text text_jineteCantidad = new Text("Jinete:");
        Text text_catapultaCantidad = new Text("Catapulta:");
        Text text_curanderoCantidad = new Text("Curandero:");

        String soldado = hash.get("Soldado Infanteria").toString();
        String jinete = hash.get("Jinete").toString();
        String catapulta = hash.get("Catapulta").toString();
        String curandero = hash.get("Curandero").toString();
        Text _soldado = new Text(soldado);
        Text _jinete = new Text(jinete);
        Text _catapulta = new Text(catapulta);
        Text _curandero = new Text(curandero);


        GridPane.setConstraints(text_jugador, 1, 0);

        GridPane.setConstraints(text_soldadoCantidad, 1, 1);
        GridPane.setConstraints(_soldado, 2, 1);

        GridPane.setConstraints(text_jineteCantidad, 1, 3);
        GridPane.setConstraints(_jinete, 2, 3);

        GridPane.setConstraints(text_catapultaCantidad, 1, 4);
        GridPane.setConstraints(_catapulta, 2, 4);

        GridPane.setConstraints(text_curanderoCantidad, 1, 5);
        GridPane.setConstraints(_curandero, 2, 5);

        GridPane.setConstraints(continuar, 2,6);

        grid.getChildren().addAll(text_jugador, text_soldadoCantidad, _soldado, text_jineteCantidad, _jinete, text_catapultaCantidad, _catapulta, text_curanderoCantidad, _curandero, continuar);

        return grid;
    }

    public void mostrar(Stage stage) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada{
        Scene escenaJuego = new Scene(tablero.getVisual(), 640, 480);
        stage.setScene(escenaJuego);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);

        hashUnidadesJugador1 = SelectorUnidades.display(jugador1);
        hashUnidadesJugador2 = SelectorUnidades.display(jugador2);

        irAUbicarUnidades(stage, escenaJuego);

        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                Coordenada coordenada = tablero.clickGrid(event);
                if(QuedanUnidades(hashUnidadesJugador1)){
                    abrirColocadorUnidades("Turno jugador 1", "Elija unidad para colocar", jugador1.getText(), coordenada.getCoordenadaX(), coordenada.getCoordenadaY(), hashUnidadesJugador1);
                } else if (QuedanUnidades(hashUnidadesJugador2)){
                    abrirColocadorUnidades("Turno jugador 2", "Elija unidad para colocar", jugador2.getText(), coordenada.getCoordenadaX(), coordenada.getCoordenadaY(), hashUnidadesJugador2);
                }
                else {
                    System.out.println("Ataque");
                }
                event.consume();
            }
        });
    }

    private void irAUbicarUnidades(Stage stage, Scene scene) throws PuntosInsuficientesException, CoordenadaFueraDeRango, CeldaDeTerritorioEnemigo, CeldaOcupada  {

        //BotonUbicacionJugador1 botonUbicacion1 = new BotonUbicacionJugador1(hashUnidadesJugador1, juego, jugador1, tablero);
        //botonUbicacion1.setDisable(false);

        Button botonUbicacion1 = new Button();
        botonUbicacion1.setDisable(false);

        Button botonUbicacion2 = new Button();
        botonUbicacion2.setDisable(true);

        cantidadUnidadesJugador1 = crearGridCantidadUnidades(jugador1.getText() , hashUnidadesJugador1, botonUbicacion1);
        cantidadUnidadesJugador2 = crearGridCantidadUnidades(jugador2.getText() , hashUnidadesJugador2, botonUbicacion2);

        HBox caja = new HBox(cantidadUnidadesJugador1, tablero.getVisual(), cantidadUnidadesJugador2);
        Scene escenaUbicarUnidades = new Scene(caja, 640, 480);
        stage.setScene(escenaUbicarUnidades);
    }

    private void abrirColocadorUnidades(String title, String message, String jugador, int x, int y, HashMap<String, Integer> unidadesJugador) {

        final Integer cantRestante1;
        final Integer cantRestante2;
        final Integer cantRestante3;
        final Integer cantRestante4;

        Stage colocadorUnidades = new Stage();

        //Block events to other windows
        colocadorUnidades.initModality(Modality.APPLICATION_MODAL);
        colocadorUnidades.setTitle(title);
        colocadorUnidades.setMinWidth(250);

        //Eleccion de sector
        ComboBox<String> unidades = new ComboBox<String>();
        unidades.getItems().addAll(
                "Soldado Infanteria", "Jinete", "Curandero", "Catapulta"
        );

        unidades.setPromptText("Selecciona unidad");

        Label label = new Label();
        label.setText(message);
        Button selectionButton = new Button("OK!");
        selectionButton.setOnAction(e -> {
            try {
                switch (unidades.getValue()) {

                    case "Jinete":
                        if(unidadesJugador.get("Jinete") == 0){
                        AlertBox.display("Error", "No tiene mas jinetes");
                        break;
                    }
                        juego.colocarJinetePara(jugador, x, y);
                        unidadesJugador.put("Jinete", unidadesJugador.get("Jinete") - 1);
                        tablero.agregarContenidoCeldaJinete(x,y);
                        break;

                    case "Soldado Infanteria":
                        if(unidadesJugador.get("Soldado Infanteria") == 0){
                        AlertBox.display("Error", "No tiene mas soldados");
                        break;
                        }
                        juego.colocarSoldadoInfanteriaPara(jugador,x, y);
                        unidadesJugador.put("Soldado Infanteria",  unidadesJugador.get("Soldado Infanteria") - 1);

                        tablero.agregarContenidoCeldaSoldado(x,y);
                        break;

                    case "Catapulta": if(unidadesJugador.get("Catapulta") == 0){
                        AlertBox.display("Error", "No tiene mas catapultas");
                        break;
                    }
                        juego.colocarCatapultaPara(jugador, x, y);
                        unidadesJugador.put("Catapulta", unidadesJugador.get("Catapulta") -1);
                        tablero.agregarContenidoCeldaCatapulta(x,y);
                        break;

                    case "Curandero": if(unidadesJugador.get("Curandero") == 0){
                        AlertBox.display("Error", "No tiene mas curanderos");
                        break;
                    }
                        juego.colocarCuranderoPara(jugador, x, y);
                        unidadesJugador.put("Curandero", unidadesJugador.get("Curandero") -1);
                        tablero.agregarContenidoCeldaCurandero(x,y);
                        break;
                }
                //TODO DESPUES DE CADA LLAMADA A JUEGO.COLOCAR HACER QUE APAREZCA LA IMAGEN EN EL TABLERO
            }
            catch (Excepciones.CeldaDeTerritorioEnemigo enem){
                enem.printStackTrace();
                AlertBox.display("Error - No se puede colocar", "Territorio enemigo"); //TODO MEJORAR TRATAMIENTO EXCEPCIONES PARICUALRES
            }
            catch (CeldaOcupada | CoordenadaFueraDeRango ocup) {
                ocup.printStackTrace();
                AlertBox.display("Error - No se puede colocar", "Celda ocupada");
            }
            catch(PuntosInsuficientesException exc) {
                exc.printStackTrace();
                AlertBox.display("Error","Puntos Insuficientes");
            }
            finally {
                colocadorUnidades.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, selectionButton, unidades);
        layout.setAlignment(Pos.CENTER);

        //Mostrar ventana y esperar a que se cierre para continuar
        Scene scene = new Scene(layout);
        colocadorUnidades.setScene(scene);
        colocadorUnidades.showAndWait();
    }

    private boolean QuedanUnidades(HashMap<String, Integer> unidadesDisponibles){
        if(unidadesDisponibles.get("Soldado Infanteria") + unidadesDisponibles.get("Jinete") + unidadesDisponibles.get("Catapulta") + unidadesDisponibles.get("Curandero") == 0){
            return false;
        }
        return true;
    }

}


/*scene.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent event) {

        tablero.clickGrid(event);
        Integer col = tablero.colIndex;
        AlertBoxEleccionSector.display("Atencion", "hola");
        event.consume();
        };
        });*/
