import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {

    //Attributs
    protected double yFenetre = 480;
    protected double vitesseFenetre = 50;
    protected double accFenetre = 2;
    protected double largeur, hauteur;
    protected double x, y; //Positions en x et y sur l'interface graphique

    public Entity(){} //Constructeur

    public void update(double dt){
    }

    public abstract void draw(GraphicsContext context);

    public void debug(){

    }



}
