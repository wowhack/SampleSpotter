package com.wowhack2014.djperro.samplespotter;

/**
 * Created by backman on 2014-08-05.
 */
public class SpotifySongChanged {

    private boolean stateChanged;

    public SpotifySongChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }

    public boolean isStateChanged() {
        return this.stateChanged;
    }

}
