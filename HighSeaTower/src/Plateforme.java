import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Plateforme extends Entity{

    //Attributs
    protected int largeur = (int)(80+(Math.random() *95));
    protected int epaisseur = 10;
    protected boolean enIntersection;
    Color couleur;
    protected  boolean passable; //E.c.q la plateforme peut se faire passer par en dessous

    public Plateforme(int x, int y){ //Constructeur
        this.x = x;
        this.y = y;
    }

    public Plateforme(){} //Constructeur


    /*
    Méthode update (en fonction du delta temps de HighSeaTower)
     */
    public void update(double dt){
        this.vitesseFenetre += dt*accFenetre;
        this.yFenetre = dt*vitesseFenetre;
        this.y += yFenetre;
    }

    public void draw(GraphicsContext context){ //Pour afficher
        context.setFill(couleur);
        context.fillRect(this.x, this.y, largeur, epaisseur);
    }

    public void debug(boolean debug){
        if(debug){
            this.vitesseFenetre = 0;
            this.accFenetre = 0;
        }

    }


}