import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;

public class Jeu {

    //Attributs du jeu
    ArrayList<Plateforme> plateformes = new ArrayList<>();
    Meduse meduse;
    ArrayList<Bulles> bulles = new ArrayList<>();
    protected boolean commence;
    Score score = new Score(HighSeaTower.WIDTH/2, 20);
    Score scoreDebut = new Score(HighSeaTower.WIDTH/2, 20);
    protected boolean debugJeu;
    protected boolean fini;

    /*
    Constructeur de notre jeu. Initialise une méduse,
    des plateformes, des bulles, ainsi que la logique
    de début et fin de parties.
     */
    public Jeu(){
        commence = false;
        meduse = new Meduse(150, HighSeaTower.HEIGHT -50);
        meduse.parterre = true;

        plateformes.add(choixPlateforme((int)(Math.random() *250), 400));
        for(int i = 1; i < 8; i++){

            plateformes.add(i, choixPlateforme((int)(Math.random() *250),
                        (int)plateformes.get(i-1).y -100));
        }

        for(int i = 0; i < 3; i++){
            bulles.add(new Bulles(Math.random()*340));
            for(int j = 0; j < 5; j++){
                bulles.add(new Bulles((bulles.get(i).x-20)+(Math.random()*40)));
            }
        }
    }


    public void finPartie(){
        if(meduse.y > HighSeaTower.HEIGHT +5){
            fini = true;
        }
    }


    /*
    Méthode qui permet de faire le choix de quelle plateforme
    ajouté selon les probabilités de chacune
    */
    public Plateforme choixPlateforme(int x, int y){
        double choix = Math.random();
        Plateforme p = null;

        if(choix < 0.05){ //Plateforme rouge
            p = new PlateformeRouge(x, y);
        }
        if(choix >= 0.05 && choix < 0.15){ //Plateforme jaune
            p = new PlateformeJaune(x, y);
        }
        if (choix >= 0.15 && choix < 0.35){ //Plateforme verte
            p = new PlateformeVerte(x, y);
        }
        if(choix >= 0.35 && choix <= 1){ //Plateforme orange
            p = new PlateformeOrange(x, y);
        }
        return p;
    }

    public void draw(GraphicsContext context){ //Pour afficher le jeu en entier
        context.setFill(Color.DARKBLUE); //Background
        context.fillRect(0, 0, HighSeaTower.WIDTH, HighSeaTower.HEIGHT);

        meduse.draw(context); //Dessin de la meduse
        for(int i = 0; i < plateformes.size(); i++){ //Dessin des plateformes
            plateformes.get(i).draw(context);
        }

        for(int i = 0; i < bulles.size(); i++){ //Dessin des bulles
            bulles.get(i).draw(context);
        }
        score.draw(context);

        /*
        Les quelques prochaines lignes ne sont que le setup pour
        que l'on voit le score ainsi que la méduse à temps 0
         */
        if(fini == true || commence == false){
            Image imgDefalut =  new Image("/images/jellyfish1.png",
                                        50, 50, false, true);
            context.drawImage(imgDefalut, 150, 430 , 50, 50);

            context.setFill(Color.WHITE);
            context.setTextAlign(TextAlignment.CENTER);
            context.setTextBaseline(VPos.CENTER);
            context.setFont(Font.font(25));
            context.fillText(0+"m", scoreDebut.x, scoreDebut.y);
        }
        if(debugJeu) {
            drawDebug(context);
        }
    }


    double tempsEcoule = 0; //Pour l'apparition des bulles
    public void update(double dt){
        /*
        à chaque tour en revérifie si la méduse est parterre ou non
         */
        meduse.parterre = false;

        /*
        Update des plateformes sur la surface de jeu :
        on supprime les plateformes lorsqu'elle sont rendues assez bas
        et on en rajoute une lorsque qu'une autres est supprimée.
         */
        for(int i = 0; i < plateformes.size(); i++){
            plateformes.get(i).update(dt); //Update
            meduse.testCollision(plateformes.get(i), plateformes); //On test les collisions

            if(plateformes.get(i).y > meduse.y + 400){
                plateformes.remove(plateformes.get(i)); //Supprime lorsque la plateforme est assez basse

                plateformes.add(choixPlateforme((int)(Math.random() *250), //Créé une nouvelle plateforme
                            (int)plateformes.get(plateformes.size()-1).y -100));

                plateformes.get(plateformes.size()-1).vitesseFenetre =
                        meduse.vitesseFenetre; //Plateforme créé prend la bonne vitesse

                plateformes.get(plateformes.size()-1).accFenetre =
                        meduse.accFenetre; //Plateforme créé prend la bonne accélération
            }
        }
        if(meduse.y < 0.25*HighSeaTower.HEIGHT ){//REPOSITIONNEMENT
            meduse.y = 0.25*HighSeaTower.HEIGHT;
            for(int i = 0; i < plateformes.size(); i++){
                plateformes.get(i).y -= meduse.vy*dt;
            }
        }

        for(int i = 0; i < bulles.size(); i++){ //Update des bulles
            bulles.get(i).update(dt);
        }
        tempsEcoule += dt;
        if(tempsEcoule > 3){ //Création de nouvelles bulles chaque 3 secondes
            for(int j = 0; j < bulles.size(); j++){
                bulles.remove(j);
            }
            for(int i = 0; i < 3; i++){
                bulles.add(new Bulles(Math.random()*340));
                for(int k = 0; k < 5; k++){
                    bulles.add(new Bulles((bulles.get(i).x-20)+(Math.random()*40)));
                }
            }
            tempsEcoule = 0;
        }
        meduse.update(dt);
        score.update(dt, meduse);
        finPartie();
    }


    /*
    Méthode qui permet d'afficher les coordonnées, vitesse et accélération
    lorsque le mode débug est activé
     */
    public void drawDebug(GraphicsContext context){
        context.setFill(Color.WHITE);
        context.setTextAlign(TextAlignment.LEFT);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(Font.font(10));
        context.fillText("Position : (" +(int)meduse.x +", " +(int)(meduse.yFenetre-480 ) +")",
                5, 10);

        context.setFill(Color.rgb(255, 0 , 0, 0.3));
        context.fillRect(meduse.x, meduse.y, 50, 50);

        context.setFill(Color.WHITE);
        context.fillText("v : (" +(int)meduse.vx +", " +(int)meduse.vy +")", 5, 20);

        context.setFill(Color.WHITE);
        context.fillText("a : (" +(int)meduse.ax +", " +(int)meduse.ay +")", 5, 30);

        String ouiParterre = "Parterre : oui";
        String nonParterre = "Parterre : non";
        context.setFill(Color.WHITE);
        if(meduse.parterre){
            context.fillText(ouiParterre, 5, 40);
        } else {
            context.fillText(nonParterre, 5, 40);
        }
    }



    public void debug(boolean debug){ //Simple mode débug
        if(debug){
            meduse.debug(true);
            for(int i = 0; i < plateformes.size(); i ++){
                plateformes.get(i).debug(true);
            }
        }
        debugJeu = true;
    }


    /*
    Les fonctions suivantes sont les fonctions de mouvement de la méduse
     */
    public void jump(){
        meduse.jump();
    }

    public void moveDroite(){
        meduse.moveDroite();
    }

    public void moveGauche(){
        meduse.moveGauche();
    }

    public void ralentissementDroite(){
        meduse.ralentissementDroite();
    }

    public void ralentissementGauche(){
        meduse.ralentissementGauche();
    }



}
