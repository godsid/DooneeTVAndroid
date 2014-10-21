package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.brightcove.player.media.Catalog;
import com.brightcove.player.media.PlaylistListener;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.view.BrightcovePlayer;
import com.brightcove.player.view.BrightcoveVideoView;
import com.brightcove.player.view.SeamlessVideoView;

/**
 * Created by Banpot.S on 10/21/14 AD.
 */
public class PlayerActivity extends BrightcovePlayer{


    private BrightcoveVideoView videoView;
    private BrightcovePlayer videoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        brightcoveVideoView = (SeamlessVideoView) findViewById(R.id.videoView);

        Catalog catalog = new Catalog("ErQk9zUeDVLIp8Dc7aiHKq8hDMgkv5BFU7WGshTc-hpziB3BuYh28A..");
        catalog.findPlaylistByReferenceID("stitch", new PlaylistListener() {
            public void onPlaylist(Playlist playlist) {
                brightcoveVideoView.addAll(playlist.getVideos());
            }

            public void onError(String error) {
                Log.e(TAG, error);
            }
        });

        // Log whether or not instance state in non-null.
        if (savedInstanceState != null) {
            Log.v(TAG, "Restoring saved position");
        } else {
            Log.v(TAG, "No saved state");
        }



    }


}
