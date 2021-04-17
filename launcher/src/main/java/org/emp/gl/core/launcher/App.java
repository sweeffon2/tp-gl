package org.emp.gl.core.launcher;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Hello world!
 *
 */
public class App {

    // ce code nous permettra d'enregistrer les service que notre application utilsiera 
    // lors de l'execution
    static {        
        Lookup.getInstance().register(TimerService.class, new DummyTimeServiceImpl());
    }

    public static void main(String[] args) {

        testDuTimeService();
    }

    private static void testDuTimeService() {
        TimerService ts = Lookup.getInstance().getService(TimerService.class);

        ts.addTimeChangeListener(new TimerChangeListener() {
            @Override
            public void uniteChangee(String prop, Object oldValue, Object newValue) {
                clearScreen();
                System.out.println("" + ts.getHeures()+":" +ts.getMinutes() + ":"
                        +ts.getSecondes()+","+ts.getDixiemeDeSeconde());
            }
        });
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
