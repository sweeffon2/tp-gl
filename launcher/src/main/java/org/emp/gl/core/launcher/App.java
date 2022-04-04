package org.emp.gl.core.launcher;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.gui.ButtonViewer;
import org.emp.gl.gui.WatchViewer;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Hello world!
 *
 */
public class App {

    static {
        Lookup.getInstance().register(TimerService.class, new DummyTimeServiceImpl());
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WatchViewer w1 = new WatchViewer();
                w1.setVisible(true);
                w1.setLocation(200, 100);
                WatchViewer w2 = new WatchViewer();
                w2.setVisible(true);
                w2.setLocation(600, 100);
                new ButtonViewer().setVisible(true);
            }
        });
    }

}
