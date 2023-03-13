import javafx.scene.paint.Color;

public class PlateformeOrange extends  Plateforme {

    Color orange = Color.rgb(230, 134, 58);


    public PlateformeOrange(int posX, int posY){
        super(posX, posY);
        passable = true;
        couleur = orange;

    }

    public PlateformeOrange(){}

}
