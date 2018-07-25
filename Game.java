import java.awt.Toolkit;
import javax.swing.JFrame;

public class Game extends JFrame {

    Model model;
    View view;
    Controller controller;

    public Game() {

        model = new Model();
        controller = new Controller(model);
        view = new View(model);
        controller.setView(view);
        model.setController(controller);
        controller.setView(view);

        view.addMouseListener(controller);
        this.addKeyListener(controller);

        this.setSize(700, 517);
        this.setFocusable(true);
        this.getContentPane().add(view);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.run();
    }

    public void run() {

        while(true) {
            controller.update();
            model.update();
            view.repaint();
            Toolkit.getDefaultToolkit().sync();

            try {
                Thread.sleep(25);
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}