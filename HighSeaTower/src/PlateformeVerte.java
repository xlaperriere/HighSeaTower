import javafx.scene.paint.Color;

public class PlateformeVerte extends Plateforme {

    Color vert = Color.LIGHTGREEN;

    public PlateformeVerte(int posX, int posY){
        super(posX, posY);
        passable = true;
        couleur = vert;
    }

    public PlateformeVerte(){}

}
