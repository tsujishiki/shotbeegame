/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudy;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrator
 */
public class Bee extends Sprite implements Runnable {

    private BeeShotGameCanvas canvas;
    private boolean isLiving;

    public Bee(Image image, BeeShotGameCanvas canvas, int x, int y) {
        super(image);
        isLiving = true;
        this.canvas = canvas;
        this.setPosition(x, y);
        canvas.getBeeVector().addElement(this);
        canvas.getLayerManager().append(this);
//        new Thread(this).start();
    }

    public void setLiving(boolean isLiving){
        this.isLiving = isLiving;
    }

    public void run() {
        while (isLiving) {
            this.move(0, 2);
            if (this.getY() > canvas.getHeight()) {
                canvas.getLayerManager().remove(this);
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
