import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
    int End_Destination = 8130;
    int Ground = 420;
    int coin_amount;


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
            Mario_Forward_Images[0] = super.loadImage("images/mario_move_right1.png");
        if (Mario_Forward_Images[1] == null)
            Mario_Forward_Images[1] = super.loadImage("images/mario_move_right2.png");
        if (Mario_Forward_Images[2] == null)
            Mario_Forward_Images[2] = super.loadImage("images/mario_move_right3.png");
        if (Mario_Forward_Images[3] == null)
            Mario_Forward_Images[3] = super.loadImage("images/mario_move_right4.png");
        if (Mario_Forward_Images[4] == null)
            Mario_Forward_Images[4] = super.loadImage("images/mario_move_right5.png");

        if (Mario_Backward_Images[0] == null)
            Mario_Backward_Images[0] = super.loadImage("images/mario_move_left1.png");
        if (Mario_Backward_Images[1] == null)
            Mario_Backward_Images[1] = super.loadImage("images/mario_move_left2.png");
        if (Mario_Backward_Images[2] == null)
            Mario_Backward_Images[2] = super.loadImage("images/mario_move_left3.png");
        if (Mario_Backward_Images[3] == null)
            Mario_Backward_Images[3] = super.loadImage("images/mario_move_left4.png");
        if (Mario_Backward_Images[4] == null)
            Mario_Backward_Images[4] = super.loadImage("images/mario_move_left5.png");

        try{
            jump = new SoundClips("sounds/jump.wav", 2, model);
            stomp = new SoundClips("sounds/stomp.wav", 2, model);
            fireball = new SoundClips("sounds/fireball.wav", 10, model);
            died = new SoundClips("sounds/died.wav", 1, model);
            coin = new SoundClips("sounds/coin.wav", 2, model);
        }
        catch( Exception e){
            throw new RuntimeException("Could not play audio", e);
        }
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

    void marioPastPosition() {
        prev_x = x;
        prev_y = y;
    }


    void draw(Graphics g) {
        if(movingright)
            g.drawImage(Mario_Forward_Images[pic_num], 200, y, null);
        else if(movingleft)
            g.drawImage(Mario_Backward_Images[pic_num], 200, y, null);

    }

    // Keeps mario from walking through a tube
    void getOutOfTube(Sprite s) {
        if(x + width >= s.x && prev_x + width< s.x)
            x = s.x - width - 1 ;
        else if ( x <= s.x +s.width && prev_x > s.x + s.width)
            x = s.x + s.width + 1;
        else if( y + height >= s.y && prev_y + height < s.y)
            y = s.y - 1 - height;
        else if( y - height <= s.y + s.height && prev_y - height > s.y + s.height) //check this
            y = s.y + s.height + height + 1;
    }

    // Checks if mario has collided with any of the other sprites
    void Check_for_Collision() {

        for(int h = 0; h < model.sprites.size(); h++) {
            Sprite s = model.sprites.get(h);

            // When mario collides with a tube
            if(s.istube()) {
                if(super.Intersect (x, y, width,height, s.x, s.y, s.width, s.height)) {
                    getOutOfTube(s);
                    vert_vel = 0;
                }
            }

            // When mario collides with a goomba
            if(s.isgoomba()){
                if(super.Intersect (x, y, width,height, s.x, s.y, s.width, s.height) && y + height >= s.y){
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

            // When mario collides with a coin
            if(s.iscoin()){
                if(super.Intersect (x, y, width,height, s.x, s.y, s.width, s.height)) {
                    coin.play();
                    model.sprites.remove(h);
                    coin_amount++;
                }
            }
        }
    }

    void changePics(){
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
        changePics();
        Died();

        // Ensure Mario can't go further back from start position
        if (x <= 1)
            x = 1;

        // Ends Game
        if(x >= End_Destination)
            System.exit(0);
    }
}
