import java.util.ArrayList;

class Model {

    Controller controller;
    SoundClips soundclips;
    Mario mario;
    boolean gameover = false;
    ArrayList<Sprite> sprites;

    Model() {

        sprites = new ArrayList<Sprite>();

        //Game Map

        sprites.add(new Cloud(300, 150, this));
        sprites.add(new Tube(400, 380, this));
        sprites.add(new Cloud(400, 50, this));
        sprites.add(new Tube(600, 305, this));
        sprites.add(new Goomba(800, 400, this));
        sprites.add(new Tube(900, 205, this));
        sprites.add(new Goomba(1000, 400, this));
        sprites.add(new Cloud(1100, 50, this));
        sprites.add(new Tube(1200, 305, this));
        sprites.add(new Coin(1210, 200, this));
        sprites.add(new Goomba(1300, 400, this));
        sprites.add(new Cloud(1450, 170, this));
        sprites.add(new Goomba(1600, 400, this));
        sprites.add(new Tube(1900, 205, this));
        sprites.add(new Goomba(2000, 400, this));
        sprites.add(new Cloud(2050, 100, this));
        sprites.add(new Goomba(2100, 400, this));
        sprites.add(new Tube(2300, 275, this));
        sprites.add(new Coin(2305, 220, this));
        sprites.add(new Tube(2800, 340, this));
        sprites.add(new Coin(2805, 290, this));
        sprites.add(new Tube(2950, 340, this));
        sprites.add(new Coin(2955, 290, this));
        sprites.add(new Tube(3100, 340, this));
        sprites.add(new Coin(3105, 290, this));
        sprites.add(new Tube(3400, 300, this));
        sprites.add(new Goomba(3500, 400, this));
        sprites.add(new Cloud(3600, 200, this));
        sprites.add(new Tube(4000, 340, this));
        sprites.add(new Goomba(4200, 400, this));
        sprites.add(new Coin(4200, 200, this));
        sprites.add(new Goomba(4500, 400, this));
        sprites.add(new Coin(4500, 200, this));
        sprites.add(new Goomba(4800, 400, this));
        sprites.add(new Coin(4800, 200, this));
        sprites.add(new Goomba(5100, 400, this));
        sprites.add(new Coin(5100, 200, this));
        sprites.add(new Tube(5300, 360, this));
        sprites.add(new Tube(5500, 310, this));
        sprites.add(new Coin(5605, 380, this));
        sprites.add(new Tube(5700, 260, this));
        sprites.add(new Tube(5900, 210, this));
        sprites.add(new Goomba(6100, 400, this));
        sprites.add(new Tube(6300, 210, this));
        sprites.add(new Tube(6500, 260, this));
        sprites.add(new Tube(6700, 310, this));

        mario = new Mario(this);
        sprites.add(mario);

        try{
            soundclips = new SoundClips("sounds/Overworld_Theme_Music.wav", 1, this);
        }
        catch( Exception e){
            throw new RuntimeException("aa", e);
        }

        soundclips.play();
    }

    public void update() {
        for( int i = 0; i < sprites.size(); i++ ) {
            Sprite s = sprites.get(i);
            s.Update();
        }

        if(this.mario.alive == false)
            gameover = true;
    }

    int CamPos() {
        return mario.x - 200;
    }

    public void setController(Controller c) {
        controller = c;
    }
}
