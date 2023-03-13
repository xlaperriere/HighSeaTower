import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlateformeJaune extends Plateforme {

    Color jaune = Color.rgb(230, 221, 58);

    public PlateformeJaune(int posX, int posY){
        super(posX, posY);
        passable = true;
        couleur = jaune;
    }

    public PlateformeJaune(){}

    public void accelerer(ArrayList<Plateforme> plateformes){
        for(int i = 0; i < plateformes.size(); i++){
            plateformes.get(i).vitesseFenetre *= 3;

        }
    }
}
