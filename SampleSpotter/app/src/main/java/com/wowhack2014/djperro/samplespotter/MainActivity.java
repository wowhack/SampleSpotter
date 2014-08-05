package com.wowhack2014.djperro.samplespotter;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.greenrobot.event.EventBus;


public class    MainActivity extends Activity {

    private static final String CLIENT_ID = "1681de3691974b09a68eaf26ffdb6ede";
    private static final String REDIRECT_URI = "samplespotter://callback ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new DownloadFilesTask().execute();
        EventBus.getDefault().register(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(SpotifySongChanged event) {
        if (event.isStateChanged()) {
            TextView textView = new TextView(this);

            textView = (TextView) findViewById(R.id.hello);

            textView.setText(StickyBroadcastReceiver.artist);
        }
    }

    private void viewOnSpotify(String artist, String track) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
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

    public void playSampledSong(View view) {
        this.viewOnSpotify(StickyBroadcastReceiver.sampledArtist, StickyBroadcastReceiver.sampledSong);
    }
}
