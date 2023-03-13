import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Score {

    //Attributs
    Text score = new Text();
    protected int x, y;
    protected double distanceParcourue;

    public Score(int x, int y){ //Constructeur
        this.x = x;
        this.y = y;
    }

    public void update(double dt, Meduse meduse){
        distanceParcourue = meduse.yFenetre - 480;
        score.setText(((int)distanceParcourue) + "m");
    }

    public void draw(GraphicsContext context){ //Pour afficher le score
        context.setFill(Color.WHITE);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(Font.font(25));
        context.fillText(score.getText(), x, y);

    }

}
