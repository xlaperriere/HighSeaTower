import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


import java.util.ArrayList;


public class Meduse extends Entity {

    //Attributs
    protected int gravity = 1200; //Accélération vers le bas en y
    protected double frameRate = 8;
    final int dimension = 50;
    protected boolean parterre;
    protected double vx, vy; //Vitesses en x et y
    protected double ax, ay; //Accélérations en x et y
    protected boolean debugMeduse;
    Image image;

    Image[] imgDroite = new Image[]{ //Images pour déplacement vers la droite
            new Image("/images/jellyfish1.png", 50, 50, false, true),
            new Image("/images/jellyfish2.png", 50, 50, false, true),
            new Image("/images/jellyfish3.png", 50, 50, false, true),
            new Image("/images/jellyfish4.png", 50, 50, false, true),
            new Image("/images/jellyfish5.png", 50, 50, false, true),
            new Image("/images/jellyfish6.png", 50, 50, false, true),
    };
    Image[] imgGauche = new Image[]{ //Images pour déplacement vers la gauche
            new Image("/images/jellyfish1g.png", 50, 50, false, true),
            new Image("/images/jellyfish2g.png", 50, 50, false, true),
            new Image("/images/jellyfish3g.png", 50, 50, false, true),
            new Image("/images/jellyfish4g.png", 50, 50, false, true),
            new Image("/images/jellyfish5g.png", 50, 50, false, true),
            new Image("/images/jellyfish6g.png", 50, 50, false, true),
    };


    //Constructeur
    public Meduse(double x, double y){
        this.x = x;
        this.y = y;
        this.largeur = dimension;
        this.hauteur = dimension;
        this.ay = gravity;
    }

    @Override //Méthode draw
    public void draw(GraphicsContext context){
        context.drawImage(image, x, y , largeur, hauteur);

    }


    double tempsTotal = 0;
    @Override
    public void update(double dt){ //Update en fonction des temps d'appel
        //Update des attributs
        this.vx += dt*this.ax;
        this.vy += dt*this.ay;
        this.x += dt*this.vx;
        this.vitesseFenetre += accFenetre*dt;
        this.yFenetre += dt*vitesseFenetre;
        this.y += dt*(this.vy+vitesseFenetre);

        if(this.y + hauteur > 480){ //Pour le début de la partie.
            this.parterre = true;
        }

        if (x + largeur > HighSeaTower.WIDTH || x < 0){ //Si la méduse frappe un mur
            vx *= -1;
        }

        /*
        Pour choisir la bonne image en fonction du framerate
         */
        tempsTotal += dt;
        int frame = (int) (tempsTotal*frameRate);
        if(vx > 0){ //animation si la méduse se deplace vers la droite
            image = imgDroite[frame % imgDroite.length];
        }
        if(vx < 0){ //animation si la méduse se deplace vers la gauche
            image = imgGauche[frame % imgGauche.length];
        }
        if(vx == 0){
            image = imgDroite[0];
        }
        if(tempsTotal == 0){
            image = imgDroite[0];
        }
    }


    /*
    Logique derriere une 'intersections'
     */
    public boolean intersects(Plateforme other){ //Détecter s'il y a intersection
        return !(x + largeur < other.x
                || other.x + other.largeur < this.x
                || y + hauteur < other.y
                || other.y + other.epaisseur < this.y);
    }


    /*
    Méthode qui teste les collisions avec la logique
    de intersects(Plateforme other)
     */
    public void testCollision(Plateforme other, ArrayList<Plateforme> plateformes){
        //Pour plateformes qu'on peut passer par en dessous
        if(intersects(other) && Math.abs(this.y + hauteur - other.y) < 15
            && this.vy > 0){
            this.parterre = true; //Intersection? -> méduse est parterre

            if(other instanceof  PlateformeOrange){ //implémente effets plateforme orange
                pushOut(other);
                this.vy = 0;
            }
            if(other instanceof PlateformeVerte){ //implémente effet plateforme verte
                this.vy = -600; //rebondissement
            }
            if(other instanceof PlateformeJaune){ //implémente effets plateforme jaune
                pushOut(other);
                this.vy = 0;
            }
            if( other instanceof PlateformeRouge){ //Implémente effet plateforme rouge
                pushOut(other);
                this.vy = 0;
            }

        }
        //Pour plateforme solide
        if(other.passable == false && Math.abs(this.y - other.y) < 10
            && vy < 0 && !(x + largeur < other.x || other.x + other.largeur < this.x)){
            this.vy = 0;
        }


        //Pour le mode DÉBUG (Modification de la couleur)
        if(debugMeduse && parterre && (intersects(other))){
            other.couleur = Color.YELLOW;
        } else  {
            if(other instanceof PlateformeRouge){
                other.couleur = Color.rgb(184, 15, 36);
            }
            if(other instanceof PlateformeVerte){
                other.couleur = Color.LIGHTGREEN;
            }
            if(other instanceof PlateformeOrange){
                other.couleur = Color.rgb(230, 134, 58);
            }
        }
    }


    public void pushOut(Plateforme other){ //Stabiliser la méduse sur une plateforme
        double deltaY = this.y + this.hauteur - other.y;
        this.y -= deltaY;
    }


    /*
    Méthode débug
     */
    public void debug(boolean debug){
        if(debug) {
            this.vitesseFenetre = 0;
            this.accFenetre = 0;
        }
        debugMeduse = true;
    }


    /*
    Les fonctions suivantes sont celles qui définissent les
    dplacements de la méduse
     */
    public void jump(){ //KeyPressed UP ou SPACE
        if(parterre){
            this.vy = -600;
        }
    }

    public void moveDroite(){ //KeyPressed RIGHT
        this.vx = 300;
    }

    public void moveGauche(){ //KeyPressed LEFT
        this.vx = -300;
    }

    public void ralentissementDroite(){ //Activé onKeyReleased RIGHT
        while(vx > 0){
            vx -= 150;
        }
    }

    public void ralentissementGauche(){ //Activé onKeyReleased LEFT
        while(vx < 0){
            vx += 150;
        }
    }
}
