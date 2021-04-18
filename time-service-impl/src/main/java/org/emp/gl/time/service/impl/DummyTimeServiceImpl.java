/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author tina
 */
public class DummyTimeServiceImpl
        extends TimerTask
        implements TimerService {

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

        LocalTime localTime = LocalTime.now();

        secondes = localTime.getSecond();
        minutes = localTime.getMinute();
        heures = localTime.getHour();
        dixiemeDeSeconde = localTime.getNano() / 100000000;

        timer.scheduleAtFixedRate(this, 100, 100);
    }

    @Override
    public void run() {
        timeChanged();
    }

    List<TimerChangeListener> listeners = new LinkedList<>();

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        listeners.add(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        listeners.remove(pl);
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }

    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    private void timeChanged() {
        updateDixiemeDeSecode();
    }

    private void updateDixiemeDeSecode() {
        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = (dixiemeDeSeconde + 1) % 10;

        // informer les listeners ! 
        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                    oldValue, dixiemeDeSeconde);
        }

        if (dixiemeDeSeconde == 0) {
            updateSecode();
        }
    }

    private void updateSecode() {
        int oldValue = secondes;
        secondes = (secondes + 1) % 60;

        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.SECONDE_PROP,
                    oldValue, secondes);
        }

        if (secondes == 0) {
            updateMinutes();
        }
    }

    private void updateMinutes() {
        int oldValue = minutes;
        minutes = (minutes + 1) % 60;

        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.MINUTE_PROP,
                    oldValue, minutes);
        }

        if (minutes == 0) {
            updateHeures();
        }
    }

    private void updateHeures() {
        int oldValue = heures;
        heures = (heures + 1) % 24;

        for (TimerChangeListener l : listeners) {
            l.propertyChange(TimerChangeListener.HEURE_PROP,
                    oldValue, heures);
        }
    }

}
