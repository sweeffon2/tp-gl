/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.emp.gl.model;

import org.emp.gl.core.lookup.Lookup;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author sweeffon
 */
public class Watch {
    TimerService timerService ;
    int decalageSecondes = 0;
    WatchState state = new WatchState.NormalMode(this) ;

    public Watch() {
        timerService = Lookup.getInstance().getService(TimerService.class) ;
    }
    
    public int getMinutes() {
        return ( timerService.getMinutes() + 
                decalageSecondes/60) % 60 ;
    }

    public int getHeures() {
        return (timerService.getHeures() +
                decalageSecondes/3600) %24;
    }

    public int getSecondes() {
        return (timerService.getSecondes() 
                + decalageSecondes) % 60;
    }

    public int getDixiemeDeSeconde() {
        return timerService.getDixiemeDeSeconde() ;
    }

    public void setState(WatchState state) {
        this.state = state;
    }

    public int getDecalageSecondes() {
        return decalageSecondes;
    }

    public void setDecalageSecondes(int decalageSecondes) {
        this.decalageSecondes = decalageSecondes;
    }

    public void set() {
        state.set();
    }

    public void mode() {
        state.mode();
    }

    public int getValueForFirstPlace() {
        return state.getValueForFirstPlace();
    }

    public int getValueForSecondePlace() {
        return state.getValueForSecondePlace();
    }

    
}
