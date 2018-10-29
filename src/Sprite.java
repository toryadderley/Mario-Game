import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.awt.*;
import java.io.File;

abstract class Sprite {

    int x;
    int y;
    int width;
    int height;

    abstract void Update();

    abstract void draw(Graphics g);

    boolean istube() {
        return false;
    }

    boolean isgoomba() {
        return false;
    }

    boolean isfireball() {
        return false;
    }

    boolean iscoin() {
        return false;
    }

    // Any collision is seen from the perspective of mario
    // So No sprite checks to see if it collides with mario
    // Any collision involving mario, mario checks for the collision with another
    // sprite
    boolean ismario() {
        return false;
    }

    // Intersect method used to check for collisions between all Sprites
    boolean Intersect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        if (x1 + w1 < x2)
            return false;
        if (x2 + w2 < x1)
            return false;
        if (y1 + h1 < y2)
            return false;
        if (y1 > y2 + h2)
            return false;
        return true;
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