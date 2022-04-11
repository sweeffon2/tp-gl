/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.emp.gl.model;

/**
 *
 * @author sweeffon
 */
public abstract class WatchState {

    final protected Watch watch;

    public WatchState(Watch watch) {
        this.watch = watch;
    }

    public abstract void set();

    public abstract void mode();

    public int getValueForFirstPlace() {
        return watch.getHeures();
    }

    public int getValueForSecondePlace() {
        return watch.getMinutes();
    }

    public static class NormalMode extends WatchState {

        public NormalMode(Watch watch) {
            super(watch);
        }

        @Override
        public void set() {
            watch.setState(new SecondesMode(watch));
        }

        @Override
        public void mode() {
            watch.setState(new RegMinutesMode(watch));
        }
    }

    public static class SecondesMode extends WatchState {

        public SecondesMode(Watch watch) {
            super(watch);
        }

        @Override
        public void set() {
            watch.setState(new NormalMode(watch));
        }

        @Override
        public void mode() {
            watch.setDecalageSecondes(watch.getDecalageSecondes() - watch.getSecondes());
        }

        @Override
        public int getValueForFirstPlace() {
            return 0;
        }

        @Override
        public int getValueForSecondePlace() {
            return (watch.getSecondes() + 60)%60;
        }

    }

    public static class RegMinutesMode extends WatchState {

        public RegMinutesMode(Watch watch) {
            super(watch);
        }

        @Override
        public void set() {
            watch.setDecalageSecondes(watch.getDecalageSecondes() + 60);
        }

        @Override
        public void mode() {
            watch.setState(new RegHeureMode(watch));
        }

    }

    public static class RegHeureMode extends WatchState {

        public RegHeureMode(Watch watch) {
            super(watch);
        }

        @Override
        public void set() {
            watch.setDecalageSecondes(watch.getDecalageSecondes() + 3600);
        }

        @Override
        public void mode() {
            watch.setState(new NormalMode(watch));
        }

    }

}
