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
    static BufferedImage UpKey_Green = null;
    static BufferedImage UpKey_Red = null;
    static BufferedImage RightKey_Green = null;
    static BufferedImage RightKey_Red = null;
    static BufferedImage LeftKey_Green = null;
    static BufferedImage LeftKey_Red = null;
    static BufferedImage F_Key_Green = null;
    static BufferedImage F_Key_Red = null;

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
        if(UpKey_Green == null)
            UpKey_Green = loadImage("images/UpArrow_Key_Green.png");
        if(UpKey_Red == null)
            UpKey_Red = loadImage("images/UpArrow_Key_Red.png");
        if(RightKey_Green == null)
            RightKey_Green = loadImage("images/RightArrow_Key_Green.png");
        if(RightKey_Red == null)
            RightKey_Red = loadImage("images/RightArrow_Key_Red.png");
        if(LeftKey_Green == null)
            LeftKey_Green = loadImage("images/LeftArrow_Key_Green.png");
        if(LeftKey_Red == null)
            LeftKey_Red = loadImage("images/LeftArrow_Key_Red.png");
        if(F_Key_Green == null)
            F_Key_Green = loadImage("images/F_Key_Green.png");
        if(F_Key_Red == null)
            F_Key_Red = loadImage("images/F_Key_Red.png");

    }

    void DrawButtons(Graphics g){
        if(!model.LeftKey)
            g.drawImage(LeftKey_Green,80, 10, null);
        else if (model.LeftKey)
            g.drawImage(LeftKey_Red,80, 10, null);

        if(!model.UpKey)
            g.drawImage(UpKey_Green,140, 10, null);
        else if (model.UpKey)
            g.drawImage(UpKey_Red,140, 10, null);

        if(!model.RightKey)
            g.drawImage(RightKey_Green,200, 10, null);
        else if (model.RightKey)
            g.drawImage(RightKey_Red,200, 10, null);

        if(!model.F_Key)
            g.drawImage(F_Key_Green,260, 10, null);
        else if (model.F_Key)
            g.drawImage(F_Key_Red,260, 10, null);

    }

    public void paintComponent(Graphics g) {

        g.drawImage(Background_Image, 0, 0, null);
        g.drawImage(Castle_Image, 8000 - model.CamPos(), -77, null);

        DrawButtons(g);

        for(int i = 0; i < model.sprites.size(); i++) {
            Sprite s = model.sprites.get(i);//
            s.draw(g);
        }

        g.drawImage(Ground_Image, 0, 420, null);

        if(!model.mario.alive ) {
            g.drawImage(Game_Over_Image, 0, 0, null);
        }
        g.drawString("Coins: " + model.mario.coin_amount, 20, 20);
        g.drawString("Lives: " + model.mario.lives, 20, 40);
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


