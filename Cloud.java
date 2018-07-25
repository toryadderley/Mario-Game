import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class Cloud extends Sprite{

    Model model;
    static BufferedImage Cloud_Image = null;
    boolean moveright = true;
    int count;
    boolean istube() { return false;}
    boolean ismario(){ return false;}
    boolean isgoomba(){ return false;}
    boolean isfireball(){ return false;}
    boolean iscoin(){ return false;}


    Cloud(int xx, int yy, Model m){

        model = m;
        x = xx;
        y = yy;
        width = 180;
        height = 163;

        if(Cloud_Image == null)
            Cloud_Image = loadImage("images/Big_Cloud.png");
    }

    void Move() {

        count += 1;
        if(moveright && count == 20) {
            x += 5;
            moveright = false;
            count = 0;
        }
        else if(moveright == false && count == 20) {
            x -= 5;
            moveright = true;
            count = 0;
        }
    }

    void Update(){
        Move();
    }

    void draw(Graphics g) {
        g.drawImage(Cloud_Image, x - model.CamPos(), y, null);
    }

    BufferedImage loadImage(String picfile) {
        BufferedImage image1 = null;

        try {
            image1 = ImageIO.read(new File(picfile));
        }

        catch(Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
        return image1;
    }
}
