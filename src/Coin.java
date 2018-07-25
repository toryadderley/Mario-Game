import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.lang.*;

class Coin extends Sprite {

    Model model;
    static BufferedImage coin_image = null;
    boolean istube(){ return false;}
    boolean ismario(){ return false;}
    boolean isgoomba(){ return false;}
    boolean isfireball(){ return false;}
    boolean iscoin() { return true;}

    Coin (int xx, int yy, Model m){

        model = m;
        x = xx;
        y = yy;
        width = 40;
        height = 40;

        if(coin_image == null)
            coin_image = loadImage("images/coin.png");
    }

    void Update() {  }

    void draw(Graphics g) {
        g.drawImage(coin_image, x - model.CamPos(), y, null);
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

