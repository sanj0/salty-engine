package testing;

import java.applet.Applet;

public class AppletWrapper extends Applet {

    public void start() {
        new Thread("application main Thread") {
            public void run() {
                runApplication();
            }
        }.start();
    }

    private void runApplication() {
        Tester.main(new String[0]);
    }

}
