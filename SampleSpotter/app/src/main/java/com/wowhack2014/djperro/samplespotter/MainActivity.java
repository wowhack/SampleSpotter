package com.wowhack2014.djperro.samplespotter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wowhack2014.djperro.samplespotter.SampledTools.EchoNest;
import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Artist.Artist;
import com.wowhack2014.djperro.samplespotter.SampledTools.Models.Song.Song;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {

    private static final String CLIENT_ID = "1681de3691974b09a68eaf26ffdb6ede";
    private static final String REDIRECT_URI = "samplespotter://callback ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EchoNest echoNest = new EchoNest();

        new DownloadFilesTask().execute();
        new DownloadFilesTask2().execute();
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

    private class DownloadFilesTask2 extends AsyncTask<Elements,Elements, Elements> {
        protected Elements doInBackground(Elements... strings) {

            EchoNest echoNest = new EchoNest();

            String song = echoNest.getService().whosampledSongId().getResponse().getSongs().get(0).getTracks().get(0).getForeign_id();

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

            return sampledEntry;
        }


        protected void onPostExecute(Elements result) {

            System.out.println("OUTPUT:");

            System.out.println(result.first().toString());

            if (result.first().toString().contains("Contains samples")) {
                System.out.println("JA DET FAKKING GÖR DEN!");
            }
        }
    }

    private class DownloadFilesTask extends AsyncTask<List<Artist>, List<Artist>, List<Artist>> {
        protected List<Artist> doInBackground(List<Artist>... strings) {

            EchoNest echoNest = new EchoNest();

            return echoNest.getWhoSampledArtist().getResponse().getArtists();
        }


        protected void onPostExecute(List<Artist> result) {

            for (Artist artist : result) {
                System.out.println("NAME: " + artist.getName() + " FOREIGN: " + artist.getForeignIds() + " ID: "+artist.getId());
            }
        }
    }

}
