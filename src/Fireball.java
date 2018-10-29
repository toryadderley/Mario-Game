import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Fireball extends Sprite {

    Model model;
    SoundClips fireball;

    static BufferedImage Fireball_Image = null;
    double vert_vel;
    int Ground = 420;
    boolean moveright = true;
    int lifespan = 1;

    boolean isfireball() { return true; }


    Fireball(int xx, int yy, Model m) {

        model = m;
        x = xx;
        y = yy;
        width = 47;
        height = 47;

        if (Fireball_Image == null)
            Fireball_Image = super.loadImage("images/fireball.png");

        try{
            fireball = new SoundClips("sounds/fireball.wav", 1, model);
        }
        catch( Exception e){
            throw new RuntimeException("Could not play audio", e);
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

    void fireballDisappear(){
        lifespan += 1;
        if(lifespan > 60)
            model.sprites.remove(this);
    }

    void Keep_Above_Ground(){
        if (y > Ground - height) {
            vert_vel = -30;
        }
    }


    void Check_for_Collision() {
        for (int h = 0; h < model.sprites.size(); h++) {
            Sprite s = model.sprites.get(h);
            if (s.istube()) {
                if (super.Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == true) {
                    this.moveright = false;
                } else if (super.Intersect(x, y, width, height, s.x, s.y, s.width, s.height) && this.moveright == false)
                    this.moveright = true;
            }
        }
    }

        void Update() {

            Check_for_Collision();
            Move();
            fireballDisappear();
            Keep_Above_Ground();
        }

    }