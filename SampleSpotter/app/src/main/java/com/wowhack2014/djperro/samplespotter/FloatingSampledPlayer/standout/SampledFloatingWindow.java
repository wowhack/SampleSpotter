package com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.constants.StandOutFlags;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.ui.Window;
import com.wowhack2014.djperro.samplespotter.R;

/**
 * Created by backman on 2014-08-06.
 */
public class SampledFloatingWindow extends StandOutWindow {
    @Override
    public String getAppName() {
        return "SampleSpotter";
    }

    @Override
    public int getAppIcon() {
        return android.R.drawable.ic_menu_close_clear_cancel;
    }

    @Override
    public void createAndAttachView(int id, FrameLayout frame) {
        // create a new layout from body.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.simple, frame, true);
    }

    // the window will be centered
    @Override
    public StandOutLayoutParams getParams(int id, Window window) {
        return new StandOutLayoutParams(id, 250, 300,
                StandOutLayoutParams.CENTER, StandOutLayoutParams.CENTER);
    }

    // move the window by dragging the view
    @Override
    public int getFlags(int id) {
        return super.getFlags(id) | StandOutFlags.FLAG_BODY_MOVE_ENABLE
                | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
    }

    @Override
    public String getPersistentNotificationMessage(int id) {
        return "Click to close the SimpleWindow";
    }

    @Override
    public Intent getPersistentNotificationIntent(int id) {
        return StandOutWindow.getCloseIntent(this, SampledFloatingWindow.class, id);
    }
}
