package Cliente.Controladores;

import Cliente.Respuestas.modelos.TopRanking;
import Config.APIREQUEST;
import Util.Funciones;
import Util.JsonUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.json.JSONObject;

import java.util.List;

public class RankingController extends Controller {

    @FXML
    private Circle imagenJ1;
    @FXML
    private Label nombreJ1;
    @FXML
    private Label puntosJ1;

    @FXML
    private Circle imagenJ2;
    @FXML
    private Label nombreJ2;
    @FXML
    private Label puntosJ2;

    @FXML
    private Circle imagenJ3;
    @FXML
    private Label nombreJ3;
    @FXML
    private Label puntosJ3;

    @FXML
    private Circle imagenJ4;
    @FXML
    private Label nombreJ4;
    @FXML
    private Label puntosJ4;

    @FXML
    private Circle imagenJ5;
    @FXML
    private Label nombreJ5;
    @FXML
    private Label puntosJ5;

    private List<TopRanking> rankingList;


    @FXML
    public void initialize() {
        recuperarRanking();
        mostrarRanking();
    }

    public void recuperarRanking() {
        JSONObject json = getApi(APIREQUEST.RANKING_JUGADORES + "/Pong");
        if (json.getBoolean("success")) {
            rankingList = JsonUtils.fromJsonListRanking(json.getJSONArray("ranking").toString(), TopRanking.class);
        } else {
            showError("Error al recuperar el ranking", json.getString("message"));
        }
    }

    public void mostrarRanking() {
        if (rankingList != null && !rankingList.isEmpty()) {
            for (TopRanking ranking: rankingList){
                switch (ranking.getPosicionRanking()){
                    case 1 -> {
                        if (ranking.getImagen() != null){
                            imagenJ1.setFill(new ImagePattern(Funciones.fromByteArrayToImage(ranking.getImagen())));
                        }
                        nombreJ1.setText(ranking.getNick());
                        puntosJ1.setText(String.valueOf(ranking.getPuntos()));
                    }
                    case 2 -> {
                        if (ranking.getImagen() != null){
                            imagenJ2.setFill(new ImagePattern(Funciones.fromByteArrayToImage(ranking.getImagen())));
                        }
                        nombreJ2.setText(ranking.getNick());
                        puntosJ2.setText(String.valueOf(ranking.getPuntos()));
                    }
                    case 3 -> {
                        if (ranking.getImagen() != null){
                            imagenJ3.setFill(new ImagePattern(Funciones.fromByteArrayToImage(ranking.getImagen())));
                        }
                        nombreJ3.setText(ranking.getNick());
                        puntosJ3.setText(String.valueOf(ranking.getPuntos()));
                    }
                    case 4 -> {
                        if (ranking.getImagen() != null){
                            imagenJ4.setFill(new ImagePattern(Funciones.fromByteArrayToImage(ranking.getImagen())));
                        }
                        nombreJ4.setText(ranking.getNick());
                        puntosJ4.setText(String.valueOf(ranking.getPuntos()));
                    }
                    case 5 -> {
                        if (ranking.getImagen() != null){
                            imagenJ5.setFill(new ImagePattern(Funciones.fromByteArrayToImage(ranking.getImagen())));
                        }
                        nombreJ5.setText(ranking.getNick());
                        puntosJ5.setText(String.valueOf(ranking.getPuntos()));
                    }
                }
            }
        } else {
            showError("Ranking vacío", "No hay jugadores en el ranking.");

        }
    }
}