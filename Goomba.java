import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class Goomba extends Sprite {

    Model model;
    static BufferedImage[] Goomba_Images = null;
    int vert_vel = 0;
    int burn;
    final int burnt = 3;
    boolean moveright = true;
    boolean i_am_hit = false;
    int count = 0;
    int Ground = 420;
    int pic_num;

    boolean istube() { return false;}
    boolean ismario(){ return false;}
    boolean isgoomba(){ return true;}
    boolean isfireball(){ return false;}
    boolean iscoin() { return false;}


    Goomba(int xx, int yy, Model m){

        model = m;
        x = xx;
        y = yy;
        width = 50;
        height = 60;

        Goomba_Images = new BufferedImage[3];
        if(Goomba_Images[0] == null)
            Goomba_Images[0] = loadImage("images/goomba_1.png");
        if(Goomba_Images[1] == null)
            Goomba_Images[1] = loadImage("images/goomba_2.png");
        if(Goomba_Images[2] == null)
            Goomba_Images[2] = loadImage("images/goomba_on_fire.png");
    }

    void Move() {
        if(moveright)
            x = x + 7;
        else
            x = x - 7;
    }

    void Gravity(){
        vert_vel += 6.9;
        y += vert_vel;
    }

    void Keep_Above_Ground(){
        if(y > Ground - height) {
            vert_vel = 0;
            y = Ground - height;
        }
    }

    void Change_pics(){
        count += 1;

        if (count == 7 && pic_num == 0) {
            pic_num = 1;
            count = 0;
        }
        else if(count == 7 && pic_num == 1){
            pic_num = 0;
            count = 0;
        }
    }

    void Check_for_Collision() {
        for(int h = 0; h < model.sprites.size(); h++) {
            Sprite s = model.sprites.get(h);

            if(s.istube()) {
                if(Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == true) {
                    this.moveright = false;
                }
                else if (Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == false) {
                    this.moveright = true;
                }
            }

            if(s.isfireball()) {
                if(Intersect(x, y, width, height, s.x, s.y, s.width, s.height)) {
                    pic_num = 2;
                    model.sprites.remove(h);
                    i_am_hit = true;
                }
            }
        }
    }

    static boolean Intersect (int x1, int y1, int w1,
                              int h1, int x2, int y2, int w2, int h2) {
        if(x1 + w1 < x2)
            return false;
        if(x2 + w2 < x1)
            return false;
        if(y1 + h1 <= y2)
            return false;
        if (y1 >= y2 + h2)
            return false;
          return true;
    }

    void Update(){
        Change_pics();
        Move();
        Gravity();
        Keep_Above_Ground();
        Check_for_Collision();

        if(i_am_hit) {
            burn++;
            if (burn > burnt)
                model.sprites.remove(this);
        }
    }

    void draw(Graphics g) {
        g.drawImage(Goomba_Images[pic_num], x - model.CamPos(), y, null);
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
