/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudy;

import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrator
 */
public class Bullet extends Sprite implements Runnable {

    private BeeShotGameCanvas canvas;
    private boolean isLiving;

    public Bullet(Image image, BeeShotGameCanvas canvas) {
        super(image);
        isLiving = true;
        this.canvas = canvas;
        Sprite plane = canvas.getPlane();
        this.setPosition(plane.getX() + (plane.getWidth() - this.getHeight()) / 2, plane.getY());
        canvas.getLayerManager().append(this);
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            this.move(0, -2);
            if (this.getY() < 10) {
                canvas.getLayerManager().remove(this);
                break;
            }
            Vector allBee = canvas.getBeeVector();
            for (int i = 0; i < allBee.size(); i++) {
                Bee aBee = (Bee) allBee.elementAt(i);
                if (this.collidesWith(aBee, true)) {
                    canvas.getLayerManager().remove(this);
                    canvas.getLayerManager().remove(aBee);
                    canvas.getBeeVector().removeElementAt(i);
                    aBee.setLiving(false);
                    isLiving = false;
                    break;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
