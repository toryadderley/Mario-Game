import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener {

    View view;
    Model model;
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    boolean Control;

    Controller(Model m) {
        model = m;
    }

    public void actionPerformed(ActionEvent e){ }
    public void mousePressed(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {   }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: keyRight = true; break;
            case KeyEvent.VK_LEFT: keyLeft = true; break;
            case KeyEvent.VK_UP: keyUp = true; break;
            case KeyEvent.VK_DOWN: keyDown = true; break;
            case KeyEvent.VK_CONTROL: Control = true; break;
        }

        char c = e.getKeyChar();
        if(c == 'f') {
            if(model.mario.movingright)
                model.sprites.add(new Fireball( model.mario.x + 60, model.mario.y, model));
            else if (model.mario.movingleft)
                model.sprites.add(new Fireball(  model.mario.x - 60, model.mario.y, model));

            model.F_Key = true;
        }
        else
            model.F_Key = false;
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: keyRight = false; break;
            case KeyEvent.VK_LEFT: keyLeft = false; break;
            case KeyEvent.VK_UP: keyUp = false; break;
            case KeyEvent.VK_DOWN: keyDown = false; break;
            case KeyEvent.VK_CONTROL: Control = false; break;
        }
    }

    public void keyTyped(KeyEvent e) {  }

    void update() {

        model.mario.marioPastPosition();

        if(keyRight) {
            model.mario.x += 8;
            model.mario.movingright = true;
            model.mario.movingleft = false;
            model.mario.pic_num++;
            model.RightKey = true;
        }
        else if(!keyRight)
            model.RightKey = false;

        if(keyLeft) {
            model.mario.x -= 8;
            model.mario.movingright = false;
            model.mario.movingleft = true;
            model.mario.pic_num++;
            model.LeftKey = true;
        }
        else if (!keyLeft)
            model.LeftKey = false;


        if (keyUp) {
            model.mario.jump();
            model.UpKey = true;
        }
        else if(!keyUp)
            model.UpKey = false;
    }

    void setView(View v) {
        view = v;
    }

}