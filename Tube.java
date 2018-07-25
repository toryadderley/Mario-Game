import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.lang.*;

class Tube extends Sprite {

    Model model;
    static BufferedImage tube_image = null;
    boolean istube(){ return true;}
    boolean ismario(){ return false;}
    boolean isgoomba(){ return false;}
    boolean isfireball(){ return false;}
    boolean iscoin() { return false;}

    Tube(int xx, int yy, Model m) {

        model = m;
        x = xx;
        y = yy;
        width = 55;
        height = 400;

        if(tube_image == null)
            tube_image = loadImage("images/tube.png");
    }

    void Update() {  }

    void draw(Graphics g) {
        g.drawImage(tube_image, x - model.CamPos(), y, null);
    }

    BufferedImage loadImage(String picfile){
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
