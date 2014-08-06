package com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wowhack2014.djperro.samplespotter.AnimatedGifImageView;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.constants.StandOutFlags;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.ui.Window;
import com.wowhack2014.djperro.samplespotter.R;
import com.wowhack2014.djperro.samplespotter.StickyBroadcastReceiver;

/**
 * Created by backman on 2014-08-06.
 */
public class SampledPlayerFloatingView extends StandOutWindow {
    @Override
    public String getAppName() {
        return "SampleSpotter2";
    }

    @Override
    public int getAppIcon() {
        return android.R.drawable.ic_menu_close_clear_cancel;
    }

    @Override
    public void createAndAttachView(int id, FrameLayout frame) {
        // create a new layout from body.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.simple, frame, true);

        System.out.println("PLAAAAAYYYYYYER");

        AnimatedGifImageView animatedGifImageView = ((AnimatedGifImageView) view.findViewById(R.id.animatedGifView));
        animatedGifImageView.setAnimatedGif(R.drawable.raccoon_cat_food_thief, AnimatedGifImageView.TYPE.STREACH_TO_FIT);

        TextView textView = (TextView) view.findViewById(R.id.textView2);

        textView.setText("SAMPLED FROM: " + StickyBroadcastReceiver.sampledArtist + " " + StickyBroadcastReceiver.sampledSong);

        Button button = (Button) view.findViewById(R.id.playButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StandOutWindow.closeAll(view.getContext(), SampledPlayerFloatingView.class);
                viewOnSpotify(StickyBroadcastReceiver.sampledArtist, StickyBroadcastReceiver.sampledSong);
            }
        });
    }
    // the window will be centered
    @Override
    public StandOutLayoutParams getParams(int id, Window window) {
        return new StandOutLayoutParams(id, 1100, 1300,
                StandOutLayoutParams.CENTER, 100);
    }

    // move the window by dragging the view
    @Override
    public int getFlags(int id) {
        return super.getFlags(id) | StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE;
    }

    @Override
    public String getPersistentNotificationMessage(int id) {
        return "Click to close SampleSpotter window.";
    }

    @Override
    public Intent getPersistentNotificationIntent(int id) {
        return StandOutWindow.getCloseIntent(this, SampledPlayerFloatingView.class, id);
    }

    private void viewOnSpotify(String artist, String track) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
            intent.setComponent(new ComponentName(
                    "com.spotify.music",
                    "com.spotify.music.MainActivity"));
            intent.putExtra(SearchManager.QUERY, artist + " - " + track);
            this.startActivity(intent);

        } catch (Exception e) {
            this.viewOnSpotifyFallback(artist, track);
        }
    }

    private void viewOnSpotifyFallback(String artist, String track) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
            intent.setComponent(new ComponentName(
                    "com.spotify.music",
                    "com.spotify.music.MainActivity"));
            intent.putExtra(SearchManager.QUERY, artist + " - " + track);
            this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
