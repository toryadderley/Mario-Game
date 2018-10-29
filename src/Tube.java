import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.lang.*;

class Tube extends Sprite {

    Model model;
    static BufferedImage tube_image = null;

    boolean istube(){ return true;}

    Tube(int xx, int yy, Model m) {

        model = m;
        x = xx;
        y = yy;
        width = 55;
        height = 400;

        if(tube_image == null)
            tube_image = super.loadImage("images/tube.png");
    }

    void Update() {  }

    void draw(Graphics g) {
        g.drawImage(tube_image, x - model.CamPos(), y, null);
    }

}
