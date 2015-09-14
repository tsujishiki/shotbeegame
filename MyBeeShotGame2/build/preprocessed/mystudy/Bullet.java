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
public class Bullet extends Sprite implements Runnable{

    public Bullet(Image image){
        super(image);
        new Thread(this).start();
    }

    public void run() {
        while(true){
            this.move(0, -2);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
