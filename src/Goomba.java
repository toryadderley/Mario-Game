import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Goomba extends Sprite {

    Model model;
    static BufferedImage[] Goomba_Images = null;
    double vert_vel = 0;
    int burn;
    final int burnt = 3;
    boolean moveright = true;
    boolean i_am_hit = false;
    int count = 0;
    int Ground = 420;
    int pic_num;

    boolean isgoomba(){ return true;}

    Goomba(int xx, int yy, Model m){

        model = m;
        x = xx;
        y = yy;
        width = 50;
        height = 60;

        Goomba_Images = new BufferedImage[3];
        if(Goomba_Images[0] == null)
            Goomba_Images[0] = super.loadImage("images/goomba_1.png");
        if(Goomba_Images[1] == null)
            Goomba_Images[1] = super.loadImage("images/goomba_2.png");
        if(Goomba_Images[2] == null)
            Goomba_Images[2] = super.loadImage("images/goomba_on_fire.png");
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

    void changePics(){
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
                if(super.Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == true) {
                    this.moveright = false;
                }
                else if (super.Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == false) {
                    this.moveright = true;
                }
            }

            if(s.isfireball()) {
                if(super.Intersect(x, y, width, height, s.x, s.y, s.width, s.height)) {
                    pic_num = 2;
                    model.sprites.remove(h);
                    i_am_hit = true;

                }
            }
        }
    }



    void Update(){
        changePics();
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

}
