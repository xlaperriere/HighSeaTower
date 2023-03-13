import javafx.scene.paint.Color;

public class PlateformeRouge extends Plateforme {

    Color rouge = Color.rgb(184, 15, 36);

    public PlateformeRouge(int posX, int posY){
        super(posX, posY);
        passable = false;
        couleur = rouge;
    }

    public PlateformeRouge(){}

}
