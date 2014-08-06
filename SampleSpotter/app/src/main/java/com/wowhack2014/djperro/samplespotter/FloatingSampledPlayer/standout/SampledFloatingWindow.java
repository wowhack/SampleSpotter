package com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.wowhack2014.djperro.samplespotter.AnimatedGifImageView;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.constants.StandOutFlags;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.ui.Window;
import com.wowhack2014.djperro.samplespotter.R;
import com.wowhack2014.djperro.samplespotter.StickyBroadcastReceiver;

/**
 * Created by backman on 2014-08-06.
 */
public class SampledFloatingWindow extends StandOutWindow {

    private Context context;
    private static boolean isSampled;
    private static AnimatedGifImageView gif;

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
        final View view = inflater.inflate(R.layout.circle_layout, frame, true);

        System.out.println("SampledFloatingWindow");

        context = view.getContext();

        gif = (AnimatedGifImageView) view.findViewById(R.id.animatedGifViewLoadingCircle);
        gif.setAnimatedGif(R.drawable.loading, AnimatedGifImageView.TYPE.STREACH_TO_FIT);

        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("OONNNNNN CLIKKCKCKCKKCKC!!");

                if (isSampled) {
                    StickyBroadcastReceiver.openPlayer();
                } else {
                    StandOutWindow.closeAll(context, SampledPlayerFloatingView.class);
                    StandOutWindow.closeAll(context, SampledFloatingWindow.class);
                }
            }
        });

/*
        AnimatedGifImageView animatedGifImageView = ((AnimatedGifImageView) view.findViewById(R.id.animatedGifView));
        animatedGifImageView.setAnimatedGif(R.drawable.raccoon_cat_food_thief, AnimatedGifImageView.TYPE.STREACH_TO_FIT);

        TextView textView = (TextView) view.findViewById(R.id.textView2);

        textView.setText("SAMPLED FROM: " + StickyBroadcastReceiver.sampledArtist + " " + StickyBroadcastReceiver.sampledSong);

        Button button = (Button) view.findViewById(R.id.playButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StandOutWindow.closeAll(view.getContext(), SampledFloatingWindow.class);
                viewOnSpotify(StickyBroadcastReceiver.sampledArtist, StickyBroadcastReceiver.sampledSong);
            }
        });*/
    }
    // the window will be centered
    @Override
    public StandOutLayoutParams getParams(int id, Window window) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        return new StandOutLayoutParams(id, 220, 220, screenWidth-200,screenHeight/9);
        /*return new StandOutLayoutParams(id, 800, 740,
                StandOutLayoutParams.CENTER, 60); */
    }
/*
    public boolean onTouchBody(final int id, final Window window,
                               final View view, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            event.getButtonState();
        }

        StandOutWindow.closeAll(context, SampledFloatingWindow.class);
        StandOutWindow.closeAll(context, SampledPlayerFloatingView.class);
        StandOutWindow.show(context, SampledPlayerFloatingView.class, StandOutWindow.DEFAULT_ID);

        return true;
    }

*/
    // move the window by dragging the view
    @Override
    public int getFlags(int id) {
        return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
    }

    @Override
    public String getPersistentNotificationMessage(int id) {
        return "Click to close SampleSpotter window";
    }

    @Override
    public Intent getPersistentNotificationIntent(int id) {
        return StandOutWindow.getCloseIntent(this, SampledFloatingWindow.class, id);
    }

    public static void setIcon(boolean sampled) {
        if (sampled) {
            gif.setImageResource(R.drawable.sampled);
            isSampled = true;
        } else {
            gif.setImageResource(R.drawable.not_sampled);
            isSampled = false;
        }
    }
}
