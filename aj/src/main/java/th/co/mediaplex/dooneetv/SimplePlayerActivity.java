package th.co.mediaplex.dooneetv;

import android.app.Activity;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;

import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.upstream.HttpDataSource;

/**
 * Created by demo on 17/10/2557.
 */
public class SimplePlayerActivity extends Activity implements SurfaceHolder.Callback,
        ExoPlayer.Listener, MediaCodecVideoTrackRenderer.EventListener{

    private View rootView;
    private SurfaceView surfaceView;
    private MediaController mediaController;
    private ExoPlayer player;
    private Handler mainHandler;
    private Surface surface;
    private FrameworkSampleSource sampleSource;
    private MediaCodecVideoTrackRenderer videoRenderer;
    private MediaCodecAudioTrackRenderer audioRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        /*
        rootView = (View)findViewById(R.id.rootView);
        surfaceView = (SurfaceView)findViewById(R.id.videoSurfaceView);

        mainHandler = new Handler(getMainLooper());

        mediaController = new MediaController(this);
        mediaController.setAnchorView(rootView);

        surfaceView.getHolder().addCallback(this);

        sampleSource = new FrameworkSampleSource(getApplicationContext(), Uri.parse(Config.sampleVideoWeb), null, 2);

         videoRenderer = new MediaCodecVideoTrackRenderer(sampleSource,
                MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT, 0, mainHandler,this,50);
         audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource);

        player = ExoPlayer.Factory.newInstance(2);
        player.prepare(videoRenderer,audioRenderer);



        player.setPlayWhenReady(true);*/
        /*
        // Invoke the callback.
        //callback.onRenderers(videoRenderer, audioRenderer);

        */

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("tui", "surfaceCreated:" );
        surface = holder.getSurface();
        player.sendMessage(videoRenderer,MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,surface);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("tui", "surfaceChanged:" );
        //surface = holder.getSurface();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onDroppedFrames(int i, long l) {
        Log.d("tui", "onDroppedFrames:" );
    }

    @Override
    public void onVideoSizeChanged(int i, int i2) {
        Log.d("tui", "onVideoSizeChanged:" );
    }

    @Override
    public void onDrawnToSurface(Surface surface) {
        Log.d("tui", "onDrawnToSurface:" );
    }

    @Override
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException e) {
        Log.d("tui", "onDecoderInitializationError:" + e.getMessage());
    }

    @Override
    public void onCryptoError(MediaCodec.CryptoException e) {
        Log.d("tui", "onCryptoError:" + e.getMessage());
    }

    @Override
    public void onPlayerStateChanged(boolean b, int i) {
        Log.d("tui", "onPlayerStateChanged:" + String.valueOf(i));

    }

    @Override
    public void onPlayWhenReadyCommitted() {
        Log.d("tui", "onPlayWhenReadyCommitted:");
    }

    @Override
    public void onPlayerError(ExoPlaybackException e) {
        Log.d("tui", "onPlayerError:"+e.getMessage());
    }
}
