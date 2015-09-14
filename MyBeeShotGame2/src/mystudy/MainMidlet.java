/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mystudy;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Administrator
 */
public class MainMidlet extends MIDlet {

    private Display dis;
    private BeeShotGameCanvas canvas;

    public MainMidlet() {
        dis = Display.getDisplay(this);
        canvas = new BeeShotGameCanvas();
    }

    public void startApp() {
        dis.setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
