import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class Mario extends Sprite {

    Model model;
    SoundClips jump;
    SoundClips stomp;
    SoundClips fireball;
    SoundClips died;
    SoundClips coin;
    static BufferedImage[] Mario_Forward_Images = null;
    static BufferedImage[] Mario_Backward_Images = null;

    int prev_x;
    int prev_y;
    int lives = 1;
    double vert_vel;
    boolean movingright = true;
    boolean movingleft = false;
    boolean alive = true;
    int pic_num = 0;
    int End_Destination = 8140;
    int Ground = 420;
    int coin_amount;


    boolean istube() { return false;}
    boolean ismario(){ return true;}
    boolean isgoomba(){ return false;}
    boolean isfireball(){ return false;}
    boolean iscoin() { return false;}

    Mario(Model m) {

        model = m;
        x = 200;
        y = 200;
        width = 60;
        height = 95;
        vert_vel = -12.0;
        Mario_Forward_Images = new BufferedImage[5];
        Mario_Backward_Images = new BufferedImage[5];

        if (Mario_Forward_Images[0] == null)
            Mario_Forward_Images[0] = loadImage("images/mario_move_right1.png");
        if (Mario_Forward_Images[1] == null)
            Mario_Forward_Images[1] = loadImage("images/mario_move_right2.png");
        if (Mario_Forward_Images[2] == null)
            Mario_Forward_Images[2] = loadImage("images/mario_move_right3.png");
        if (Mario_Forward_Images[3] == null)
            Mario_Forward_Images[3] = loadImage("images/mario_move_right4.png");
        if (Mario_Forward_Images[4] == null)
            Mario_Forward_Images[4] = loadImage("images/mario_move_right5.png");

        if (Mario_Backward_Images[0] == null)
            Mario_Backward_Images[0] = loadImage("images/mario_move_left1.png");
        if (Mario_Backward_Images[1] == null)
            Mario_Backward_Images[1] = loadImage("images/mario_move_left2.png");
        if (Mario_Backward_Images[2] == null)
            Mario_Backward_Images[2] = loadImage("images/mario_move_left3.png");
        if (Mario_Backward_Images[3] == null)
            Mario_Backward_Images[3] = loadImage("images/mario_move_left4.png");
        if (Mario_Backward_Images[4] == null)
            Mario_Backward_Images[4] = loadImage("images/mario_move_left5.png");

        try{
            jump = new SoundClips("sounds/jump.wav", 2, model);
            stomp = new SoundClips("sounds/stomp.wav", 2, model);
            fireball = new SoundClips("sounds/fireball.wav", 10, model);
            died = new SoundClips("sounds/died.wav", 1, model);
            coin = new SoundClips("sounds/coin.wav", 2, model);
        }
        catch( Exception e){
            throw new RuntimeException("aa", e);
        }
    }

    void draw(Graphics g) {
        if(movingright)
            g.drawImage(Mario_Forward_Images[pic_num], 200, y, null);
        else if(movingleft)
            g.drawImage(Mario_Backward_Images[pic_num], 200, y, null);

    }

    void jump() {
        if(vert_vel == 0) {
            vert_vel = -60;
            jump.play();
        }
    }

    void Keep_Above_Ground() {
        if(y > Ground - height) {
            vert_vel = 0;
            y = Ground - height;
        }
    }

    void Gravity() {
        vert_vel += 6.9;
        y += vert_vel;
    }

    void remembermario() {
        prev_x = x;
        prev_y = y;
    }

    void getoutoftube(Sprite s) {
        if(x + width >= s.x && prev_x + width< s.x)
            x = s.x - width - 1 ;
        else if ( x <= s.x +s.width && prev_x > s.x + s.width)
            x = s.x + s.width + 1;
        else if( y + height >= s.y && prev_y + height < s.y)
            y = s.y - 1 - height;
        else if( y - height <= s.y + s.height && prev_y - height > s.y + s.height) //check this
            y = s.y + s.height + height + 1;
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

    static boolean Intersect (int x1, int y1, int w1,
                              int h1, int x2, int y2, int w2, int h2) {
        if(x1 + w1 < x2)
            return false;
        if(x2 + w2 < x1)
            return false;
        if(y1 + h1 < y2)
            return false;
        if (y1 > y2 + h2)
            return false;
        return true;
    }

    void Check_for_Collision() {

        for(int h = 0; h < model.sprites.size(); h++) {
            Sprite s = model.sprites.get(h);

            if(s.istube()) {
                if(Intersect (x, y, width,height, s.x, s.y, s.width, s.height)) {
                    getoutoftube(s);
                    vert_vel = 0;
                }
            }

            if(s.isgoomba()){
                if(Intersect (x, y, width,height, s.x, s.y, s.width, s.height) && y + height >= s.y){
                    if(y + h < s.y && vert_vel != 0 && x + width >= s.x || x == s.x + s.width){
                        model.sprites.remove(h);
                        vert_vel = -40;
                        stomp.play();
                    }
                    if(y + height == s.y + s.height) {
                        lives--;
                        model.sprites.remove(this);
                        Died();
                    }
                }
            }

            if(s.iscoin()){
                if(Intersect (x, y, width,height, s.x, s.y, s.width, s.height)) {
                    coin.play();
                    model.sprites.remove(h);
                    coin_amount++;
                }
            }
        }
    }

    void Change_pics(){
        if (pic_num > 4) {
            pic_num = 0;
        }
    }

    void Died(){
        if(lives == 0){
            alive = false;
            died.play();
        }
    }

    void Update() {

        Gravity();
        Keep_Above_Ground();
        Check_for_Collision();
        Change_pics();
        Died();

        if (x <= 1)
            x = 1;

        if(x >= End_Destination)
            System.exit(0);
    }
}
