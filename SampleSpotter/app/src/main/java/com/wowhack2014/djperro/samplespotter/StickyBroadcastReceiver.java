package com.wowhack2014.djperro.samplespotter;

/**
 * Created by backman on 2014-08-05.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.SampledFloatingWindow;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.SampledPlayerFloatingView;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.SimpleWindow;
import com.wowhack2014.djperro.samplespotter.FloatingSampledPlayer.standout.StandOutWindow;
import com.wowhack2014.djperro.samplespotter.SampledTools.EchoNest;
import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by perthoresson on 05/08/14.
 */
public class StickyBroadcastReceiver extends BroadcastReceiver {
    static final class BroadcastTypes {
        static final String SPOTIFY_PACKAGE = "com.spotify.music";
        static final String PLAYBACK_STATE_CHANGED = SPOTIFY_PACKAGE + ".playbackstatechanged";
        static final String QUEUE_CHANGED = SPOTIFY_PACKAGE + ".queuechanged";
        static final String METADATA_CHANGED = SPOTIFY_PACKAGE + ".metadatachanged";
    }

    public static boolean isPlaying = false;
    public static String trackId;
    public static String track;
    public static String artist;

    public static String sampledArtist;
    public static String sampledSong;

    private static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        // This is sent with all broadcasts, regardless of type. The value is taken from
        // System.currentTimeMillis(), which you can compare to in order to determine how
        // old the event is.
        long timeSentInMs = intent.getLongExtra("timeSent", 0);
        System.out.println(intent);

        this.context = context;

        if(intent.getExtras() != null && intent.getExtras().getString("id") != null && intent.getExtras().getString("artist") != null) {
            System.out.println(intent.getExtras());

            trackId = intent.getExtras().getString("id");
            track = intent.getExtras().getString("track");
            artist = intent.getExtras().getString("artist");

            StandOutWindow.closeAll(context, SampledFloatingWindow.class);
            StandOutWindow.closeAll(context, SampledPlayerFloatingView.class);
            StandOutWindow.show(context, SampledFloatingWindow.class, StandOutWindow.DEFAULT_ID);

            String tempArtist = artist.replaceAll("\\s", "%20");
            String tempTrack = track.replaceAll("\\s", "%20");

            String asdf = "WA8XCMX5SD37HII8Y&format=json&results=1&artist=" + tempArtist + "&title=" + tempTrack + "&bucket=id:whosampled&limit=true&bucket=tracks";

            new CheckIfSongIsSampled().execute(asdf);

            System.out.println(trackId);
            System.out.println(track);
            System.out.println(artist);
        }

        if(intent.getExtras() != null && (intent.getExtras().getBoolean("playing") || !intent.getExtras().getBoolean("playing"))){
            isPlaying = intent.getExtras().getBoolean("playing");
            System.out.println(isPlaying);

            if (isPlaying == false) {
                //StandOutWindow.closeAll(context, SampledFloatingWindow.class);
            }
        }

        //if(intent.getExtras() != null && intent.getExtras().getString(""))

        /*if (intent.hasExtra(BroadcastTypes.METADATA_CHANGED)) {
            String trackId = intent.getStringExtra("id");
            String artistName = intent.getStringExtra("artist");

            System.out.println(trackId);

            String albumName = intent.getStringExtra("album");
            String trackName = intent.getStringExtra("track");
            long trackLengthInSec = intent.getLongExtra("length", 0);
            // Do something with extracted information...
        } else if (intent.hasExtra(BroadcastTypes.PLAYBACK_STATE_CHANGED)) {
            boolean playing = intent.getBooleanExtra("playing", false);
            int positionInMs = intent.getIntExtra("playbackPosition", 0);
            // Do something with extracted information
        } else if (intent.hasExtra(BroadcastTypes.QUEUE_CHANGED)) {
            // Sent only as a notification, your app may want to respond accordingly.
        }
        System.out.println("KÖRT SKITEN");*/
    }

    public static void openPlayer() {
        StandOutWindow.closeAll(context, SampledPlayerFloatingView.class);
        StandOutWindow.closeAll(context, SampledFloatingWindow.class);
        StandOutWindow.show(context, SampledPlayerFloatingView.class, StandOutWindow.DEFAULT_ID);

        System.out.println("OPPPEN PLAYYER");
    }


    private class CheckIfSongIsSampled extends AsyncTask<String,Elements, Elements> {
        protected Elements doInBackground(String... strings) {

            EchoNest echoNest = new EchoNest();

            //System.out.println(strings[0]);
           // System.out.println(strings[1]);

            Response response = echoNest.getService().whosampledSongId(strings[0]).getResponse();

            if (response.getSongs().size() == 0) {
                return null;
            }
            String song = response.getSongs().get(0).getTracks().get(0).getForeign_id();


            song = song.substring(song.lastIndexOf(":")+1);

            System.out.println("http://www.whosampled.com/track/view/" + song);

            System.out.println("LOOKING FOR WEBSITE");
            Document doc = null;
            try {
                doc = Jsoup.connect("http://www.whosampled.com/track/view/" + song).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements sampledEntry = doc.select(".sectionHeader");

            System.out.println("FINISHED LOOKING FOR WEBSITE");

            if (sampledEntry.first().toString().contains("Contains samples")) {
                System.out.println("-----> SAMPLED <----");
                sampledEntry = doc.select(".trackDetails a");
                System.out.println("SAMPLED TRACK: " + sampledEntry.first());
            } else {
                return null;
            }

            return sampledEntry;
        }


        protected void onPostExecute(Elements result) {

            System.out.println("OUTPUT:");


            if (result == null) {
                System.out.println("---- NOT SAMPLED ----");
                SampledFloatingWindow.setIcon(false);
                return;
            }

            String resultingString = result.first().toString();

            resultingString = resultingString.substring(resultingString.indexOf("title=\"") + 7, resultingString.indexOf("</a>"));

            String song = resultingString.substring(resultingString.indexOf("\">")+2);
            String artist = resultingString.substring(0, resultingString.indexOf("\">"));
            artist = artist.substring(0, artist.length() - (song.length()+1));


            System.out.println("ARTIST: " + artist + " SONG: " + song);

            sampledArtist = artist;
            sampledSong = song;

            SampledFloatingWindow.setIcon(true);

            // SHOW SAMPLED BUTTON - GUI
        }

    }


}