/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * @author tina
 */

public class DummyTimeServiceImpl
        extends TimerTask
        implements TimerService {

    PropertyChangeSupport pcs = new PropertyChangeSupport(this) ;

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;

    /**
     * Constructeur du DummyTimeServiceImpl Ici, nnous avons hérité de la classe
     * TimerTask, et nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tocs à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 100, 100);
        setTimeValues();
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

    @Override
    public void run() {
        timeChanged();
    }

    List<TimerChangeListener> listeners = new LinkedList<>();

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        pcs.addPropertyChangeListener(pl);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl, String prop) {
        pcs.addPropertyChangeListener(prop,pl );
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        pcs.removePropertyChangeListener(pl);
    }

    private void timeChanged() {
        setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;

        // informer les listeners !
        dixiemeDeSecondesChanged(oldValue, dixiemeDeSeconde);
    }

    private void dixiemeDeSecondesChanged(int oldValue, int newValue) {
//        for (TimerChangeListener l : listeners) {
//            l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
//                    oldValue, dixiemeDeSeconde);
//        }
        pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, oldValue, newValue);
    }


    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;

        secondesChanged(oldValue, secondes);
    }

    private void secondesChanged(int oldValue, int secondes) {

//        for (TimerChangeListener l : listeners) {
//            l.propertyChange(TimerChangeListener.SECONDE_PROP,
//                    oldValue, secondes);
//        }
        pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, oldValue, secondes);
    }


    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;

        minutesChanged (oldValue, minutes) ;
    }

    private void minutesChanged(int oldValue, int minutes) {
        pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, oldValue, minutes);
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;

        heuresChanged (oldValue, heures) ;
    }

    private void heuresChanged(int oldValue, int heures) {
        pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, oldValue, heures);
    }


    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}
