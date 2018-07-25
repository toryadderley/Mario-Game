import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class Fireball extends Sprite {

    Model model;
    SoundClips fireball;

    static BufferedImage Fireball_Image = null;
    double vert_vel;
    int Ground = 420;
    boolean moveright = true;
    int lifespan = 1;
    boolean istube() { return false; }
    boolean ismario() { return false; }
    boolean isgoomba() { return false; }
    boolean isfireball() { return true; }
    boolean iscoin() { return false; }

    Fireball(int xx, int yy, Model m) {

        model = m;
        x = xx;
        y = yy;
        width = 47;
        height = 47;

        if (Fireball_Image == null)
            Fireball_Image = loadImage("images/fireball.png");

        try{
            fireball = new SoundClips("sounds/fireball.wav", 1, model);
        }
        catch( Exception e){
            throw new RuntimeException("aa", e);
        }

        fireball.play();
    }

    void draw(Graphics g) {
        g.drawImage(Fireball_Image, x - model.CamPos(), y, null);
    }

    void Move() {

        vert_vel += 5;
        y += vert_vel;

        if(moveright)
            x += 10;
        else
            x -= 10;

        if(model.mario.movingleft && lifespan == 1){
            moveright = false;
        }
    }

    void Fireball_Disapper(){
        lifespan += 1;
        if(lifespan > 60)
            model.sprites.remove(this);
    }

    void Keep_Above_Ground(){
        if (y > Ground - height) {
            vert_vel = -30;
        }
    }

    static boolean Intersect(int x1, int y1, int w1,
                             int h1, int x2, int y2, int w2, int h2) {
        if (x1 + w1 < x2)
            return false;
        if (x2 + w2 < x1)
            return false;
        if (y1 + h1 <= y2)
            return false;
        if (y1 >= y2 + h2)
            return false;
        return true;
    }

    void Check_for_Collision() {
        for (int h = 0; h < model.sprites.size(); h++) {
            Sprite s = model.sprites.get(h);
            if (s.istube()) {
                if (Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == true) {
                    this.moveright = false;
                } else if (Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == false)
                    this.moveright = true;
            }
        }
    }

        void Update() {

            Check_for_Collision();
            Move();
            Fireball_Disapper();
            Keep_Above_Ground();
        }

        BufferedImage loadImage (String picfile){
            BufferedImage image1 = null;

            try {
                image1 = ImageIO.read(new File(picfile));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
            return image1;
        }
    }