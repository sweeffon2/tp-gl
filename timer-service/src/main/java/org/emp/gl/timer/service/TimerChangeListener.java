/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

/**
 *
 * @author tina
 */
public interface TimerChangeListener 
        // cette ligne est necessaire pour utiliser la delegation
        // vu que le délégué à besoin de cette "capability" ! 
        extends PropertyChangeListener         
        {
    
    final static String DIXEME_DE_SECONDE_PROP = "dixième" ;
    final static String SECONDE_PROP = "seconde" ;
    final static String MINUTE_PROP = "minute" ;
    final static String HEURE_PROP = "heure" ;
    
    // cette méthode est appelé du TimeChangeProvider à chaque 
    // fois qu'il y a un changement sur l'une des propriété de l'heure    
    void uniteChangee (String prop, Object oldValue, Object newValue) ;
                  
}
