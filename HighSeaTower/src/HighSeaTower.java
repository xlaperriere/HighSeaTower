/*
Xavier Laperriere

 */


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HighSeaTower extends Application {

    public static final int HEIGHT = 480, WIDTH = 350;


    public static void main(String[] args){
        HighSeaTower.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("HighSeaTower"); //Titre
        stage.getIcons().add(new Image("/images/jellyfish1.png")); //Icone
        stage.setResizable(false);

        //Prochaine lignes servent à définir l'interface graphique
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        Controleur controleur = new Controleur();
        root.setBackground(controleur.background); //Couleur background
        controleur.draw(context);


        AnimationTimer timer = new AnimationTimer() {
                long lastTime = 0;
                @Override
                public void handle(long now) {
                    if(lastTime == 0){
                        lastTime = now;
                        return;
                    }
                    double deltaTemps = (now -lastTime) * 1e-9;

                    controleur.update(deltaTemps);
                    controleur.draw(context);

                    lastTime = now;
                }
        };

        timer.start(); //On démarre le timer

        //Actions lorsque l'on appuie sur un touche
        scene.setOnKeyPressed((value) ->{
            controleur.start(); //Attribut commencé dans jeu -> true;

            if(value.getCode() == KeyCode.SPACE
                    || value.getCode() == KeyCode.UP){
                controleur.jump();
            }
            if(value.getCode() == KeyCode.RIGHT){
                controleur.moveDroite();
            }
            if(value.getCode() == KeyCode.LEFT){
                controleur.moveGauche();
            }
            if (value.getCode() == KeyCode.ESCAPE){
                Platform.exit();
            }
            if(value.getCode() == KeyCode.T){
                controleur.debug(true, context);
            }
        });
        //Actions lorsque l'on relache la touche droite ou gauche
        scene.setOnKeyReleased((value) ->{
            if(value.getCode() == KeyCode.RIGHT){
                controleur.ralentissementDroite();
            }
            if(value.getCode() == KeyCode.LEFT){
                controleur.ralentissementGauche();
            }
        });


        stage.setScene(scene);
        stage.show();

    }
}
