import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

class View extends JPanel {

    Model model;
    static BufferedImage Background_Image = null;
    static BufferedImage Ground_Image = null;
    static BufferedImage Game_Over_Image = null;
    static BufferedImage Castle_Image = null;
    static BufferedImage Ctrl_Key = null;

    View(Model m) {

        model = m;

        if(Background_Image == null)
            Background_Image = loadImage("images/Background.png");
        if(Ground_Image == null)
            Ground_Image = loadImage("images/Ground.png");
        if(Game_Over_Image == null)
            Game_Over_Image = loadImage("images/GameOverScreen.png");
        if(Castle_Image == null)
            Castle_Image = loadImage("images/Castle.png");
    }

    public void paintComponent(Graphics g) {

        g.drawImage(Background_Image, 0, 0, null);
        g.drawImage(Castle_Image, 8000 - model.CamPos(), -77, null);

        for(int i = 0; i < model.sprites.size(); i++) {
            Sprite s = model.sprites.get(i);//
            s.draw(g);
        }

        g.drawImage(Ground_Image, 0, 420, null);

        if(model.mario.alive == false) {
            g.drawImage(Game_Over_Image, 0, 0, null);
        }
        g.drawString("Coins:" + model.mario.coin_amount, 20, 20);
        g.drawString("Lives: 1", 20, 30);
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
