import java.awt.image.BufferedImage;
import java.awt.*;

abstract class Sprite{

    int x;
    int y;
    int width;
    int height;

    abstract void Update();
    abstract BufferedImage loadImage(String picfile);

    abstract void draw(Graphics g);

    abstract boolean istube();
    abstract boolean ismario();
    abstract boolean isgoomba();
    abstract boolean isfireball();
    abstract boolean iscoin();

}