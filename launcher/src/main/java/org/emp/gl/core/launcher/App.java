package org.emp.gl.core.launcher;

import java.beans.PropertyChangeEvent;
import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.impl.delegation.TimeServiceImplWithDelegation;

/**
 * Hello world!
 *
 */
public class App {

    // ce code nous permettra d'enregistrer les service que notre application utilsiera 
    // lors de l'execution
    static {
        //Lookup.getInstance().register(TimerService.class, new DummyTimeServiceImpl());
        Lookup.getInstance().register(TimerService.class, new TimeServiceImplWithDelegation());
    }

    public static void main(String[] args) {

        testDuTimeService();
    }

    private static void testDuTimeService() {
        TimerService ts = Lookup.getInstance().getService(TimerService.class);

        ts.addTimeChangeListener(new TimerChangeListener() {
            @Override
            public void uniteChangee(String prop, Object oldValue, Object newValue) {
                //clearScreen();
            }

            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                System.out.println("" + ts.getHeures() + ":" + ts.getMinutes() + ":"
                        + ts.getSecondes() + "," + ts.getDixiemeDeSeconde());
            }

        });

        // maintenant ici nous allons créer des listeners qui vont se désaboner après un certains temps 
        // premier listener qui se désabonne après 5 secondes !       
        for (int i = 0; i < 50; i++) {
            ts.addTimeChangeListener(new TimerChangeListener() {
                int compteArebours = (int) (Math.random() * 10) + 5;

                @Override
                public void uniteChangee(String prop, Object oldValue, Object newValue) {
                }

                @Override
                public void propertyChange(PropertyChangeEvent pce) {
                    if (pce.getPropertyName().equals(TimerChangeListener.SECONDE_PROP)) {
                        compteArebours--;
                        System.out.println("Il me reste : " + compteArebours);
                    }

                    if (compteArebours == 0) {
                        ts.removeTimeChangeListener(this);
                    }
                }
            });
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
