/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudy;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Administrator
 */
public class BeeShotGameCanvas extends GameCanvas implements Runnable {

    private Sprite plane;
    private Graphics g;
    private LayerManager lm;
    private Image imgBullet;
    private Vector beeVector;

    public BeeShotGameCanvas() {
        super(true);
        g = this.getGraphics();
        lm = new LayerManager();
        beeVector = new Vector();
        try {
            imgBullet = Image.createImage("/bullet.png");
            Image imgBee = Image.createImage("/bee.png");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; i < 7; i++) {
                    Bee bee = new Bee(imgBee, this, j * 18 + 15, i * 7 + 30);
                }
            }
            Image imgPlane = Image.createImage("/plane.png");
            plane = new Sprite(imgPlane);
            plane.setPosition((this.getWidth() - plane.getWidth()) / 2, this.getHeight() - plane.getHeight());
            lm.append(plane);
            lm.paint(g, 0, 0);
            this.flushGraphics();
            new Thread(this).start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            if ((this.getKeyStates() & GameCanvas.LEFT_PRESSED) != 0) {
                plane.move(-2, 0);
                if (plane.getX() < 0) {
                    plane.setPosition(0, plane.getY());
                }
            }
            if ((this.getKeyStates() & GameCanvas.RIGHT_PRESSED) != 0) {
                plane.move(2, 0);
                if (plane.getX() > this.getWidth() - plane.getWidth()) {
                    plane.setPosition(this.getWidth() - plane.getWidth(), plane.getY());
                }
            }
            if ((this.getKeyStates() & GameCanvas.UP_PRESSED) != 0) {
                plane.move(0, -2);
                if (plane.getY() < 0) {
                    plane.setPosition(plane.getX(), 0);
                }
            }
            if ((this.getKeyStates() & GameCanvas.DOWN_PRESSED) != 0) {
                plane.move(0, 2);
                if (plane.getY() > this.getHeight() - plane.getHeight()) {
                    plane.setPosition(plane.getX(), this.getHeight() - plane.getHeight());
                }
            }
            if ((this.getKeyStates() & GameCanvas.FIRE_PRESSED) != 0) {
                Bullet bullet = new Bullet(imgBullet, this);
            }
            paint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void paint() {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        lm.paint(g, 0, 0);
        this.flushGraphics();
    }

    public Sprite getPlane() {
        return plane;
    }

    public LayerManager getLayerManager() {
        return lm;
    }

    public Vector getBeeVector(){
        return beeVector;
    }
}