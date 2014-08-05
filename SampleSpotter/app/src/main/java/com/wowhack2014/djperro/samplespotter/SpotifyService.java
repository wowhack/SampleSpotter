package com.wowhack2014.djperro.samplespotter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SpotifyService extends Service {
    public SpotifyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
