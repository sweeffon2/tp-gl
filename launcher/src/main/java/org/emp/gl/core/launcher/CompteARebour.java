/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.core.launcher;

import java.beans.PropertyChangeEvent;
import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author tina
 */
public class CompteARebour implements TimerChangeListener {

    private int compteArebours;

    public CompteARebour(int compteARebour) {
        this.compteArebours = compteARebour ;
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (prop.equals(TimerChangeListener.SECONDE_PROP)) {
            compteArebours--;
            System.out.println("Il me reste : " + compteArebours);
        }

        if (compteArebours == 0) { // se désabonner du TimerService ! 
            Lookup.getInstance()
                    .getService(TimerService.class)
                    .removeTimeChangeListener(this);
        }
    }

    // cette méthode provient du PropertyChangeListener 
    // à utiliser plustard ! (Ignorer pour l'instant !
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
