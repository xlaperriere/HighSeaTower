import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;


public class Controleur {

    Background background;
    boolean started;
    Jeu jeu;

    public Controleur(){
        jeu = new Jeu();
    }

    void draw(GraphicsContext context){
        jeu.draw(context);
    }

    void start(){
        jeu.commence = true;
    }


    void update(double deltaTemps){
        if (jeu.commence){
            jeu.update(deltaTemps);
        } if (jeu.fini) {

            jeu = new Jeu();
        }
    }


    //int compteurDebug = 1;
    void debug(boolean debug, GraphicsContext context){
        debug = true;
        jeu.debugJeu = true;
        jeu.debug(debug);

    }

    void jump(){
        jeu.jump();
    }

    void moveDroite(){
        jeu.moveDroite();
    }

    void moveGauche(){
        jeu.moveGauche();
    }

    void ralentissementDroite(){
        jeu.ralentissementDroite();
    }

    public void ralentissementGauche(){
        jeu.ralentissementGauche();
    }
}
