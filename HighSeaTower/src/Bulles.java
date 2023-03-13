import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Bulles extends Entity {

    //Attributs
    protected double vitesseBulle;
    //Opacité VOLONTAIREMENT mise à 0.1 plutot que 0.4
    //pour rendre plus plaisant à voir (à mon gout)
    Color couleurBulle = Color.rgb(0 ,0, 255, 0.1);
    protected double rayon;

    public Bulles(double x){ //Constructeur
        this.x = x;
        this.y = yFenetre + 10;
        this.vitesseBulle = -(350 + (Math.random() * 100));
        this.rayon = 10 +(Math.random()*30);
    }

    public void draw(GraphicsContext context){
        context.setFill(couleurBulle);
        for(int i = 0; i < 5; i ++){
            context.fillOval(this.x, this.y, this.rayon,this.rayon);
        }

    }


    public void update(double dt){
        /*
        * La logique qui permet de faire apparaitre les bulles
        * chaque 3 secondes se trouve dans la methode update
        * de la classe jeu.
        * Elle aurait sans doute dû être coder ici.
         */
        this.y += dt*this.vitesseBulle;
    }

    public void debug(boolean debug){

    }

}
