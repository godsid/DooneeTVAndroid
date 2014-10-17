package th.co.mediaplex.dooneetv.player;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaCodec.CryptoException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.VideoSurfaceView;
import com.google.android.exoplayer.util.PlayerControl;

import th.co.mediaplex.dooneetv.Config;
import th.co.mediaplex.dooneetv.R;


public class PlayerActivity extends Activity implements SurfaceHolder.Callback,
        ExoPlayer.Listener, MediaCodecVideoTrackRenderer.EventListener {

    /**
     * Builds renderers for the player.
     */
    public interface RendererBuilder {

        void buildRenderers(RendererBuilderCallback callback);

    }

    public static final int RENDERER_COUNT = 2;
    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_AUDIO = 1;

    private static final String TAG = "PlayerActivity";

    public static final int TYPE_DASH_VOD = 0;
    public static final int TYPE_SS_VOD = 1;
    public static final int TYPE_OTHER = 2;

    private MediaController mediaController;
    private Handler mainHandler;
    private View shutterView;
    private VideoSurfaceView surfaceView;

    private ExoPlayer player;
    private RendererBuilder builder;
    private RendererBuilderCallback callback;
    private MediaCodecVideoTrackRenderer videoRenderer;

    private boolean autoPlay = true;
    private int playerPosition;

    private Uri contentUri;
    private int contentType;
    private String contentId;

    // Activity lifecycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        contentUri = Uri.parse(Config.sampleVideoRTSP);
        contentType = TYPE_SS_VOD;//intent.getIntExtra(DemoUtil.CONTENT_TYPE_EXTRA, TYPE_OTHER);
        contentId = "12345467890";//intent.getStringExtra(DemoUtil.CONTENT_ID_EXTRA);

        mainHandler = new Handler(getMainLooper());
        builder = getRendererBuilder();

        setContentView(R.layout.activity_package);
        View rootView = findViewById(R.id.rootView);
        /*
        rootView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                    toggleControlsVisibility();
                }
                return true;
            }
        });
        */

        mediaController = new MediaController(this);
        mediaController.setAnchorView(rootView);
        shutterView = findViewById(R.id.shutter);
        surfaceView = (VideoSurfaceView) findViewById(R.id.videoSurfaceView);
        surfaceView.getHolder().addCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Setup the player
        player = ExoPlayer.Factory.newInstance(RENDERER_COUNT, 1000, 5000);
        player.addListener(this);
        player.seekTo(playerPosition);
        // Build the player controls
        mediaController.setMediaPlayer(new PlayerControl(player));
        mediaController.setEnabled(true);
        // Request the renderers
        callback = new RendererBuilderCallback();
        builder.buildRenderers(callback);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Release the player
        if (player != null) {
            playerPosition = player.getCurrentPosition();
            player.release();
            player = null;
        }
        callback = null;
        videoRenderer = null;
        shutterView.setVisibility(View.VISIBLE);
    }

    // Public methods

    public Handler getMainHandler() {
        return mainHandler;
    }

    // Internal methods

    private void toggleControlsVisibility()  {
        if (mediaController.isShowing()) {
            mediaController.hide();
        } else {
            mediaController.show(0);
        }
    }

    private RendererBuilder getRendererBuilder() {
        String userAgent = DemoUtil.getUserAgent(this);
        switch (contentType) {
            case TYPE_SS_VOD:
                return new SmoothStreamingRendererBuilder(this, userAgent, contentUri.toString(),
                        contentId);
            case TYPE_DASH_VOD:
                return new DashVodRendererBuilder(this, userAgent, contentUri.toString(), contentId);
            default:
                return new DefaultRendererBuilder(this, contentUri);
        }
    }

    private void onRenderers(RendererBuilderCallback callback,
                             MediaCodecVideoTrackRenderer videoRenderer, MediaCodecAudioTrackRenderer audioRenderer) {
        if (this.callback != callback) {
            return;
        }
        this.callback = null;
        this.videoRenderer = videoRenderer;
        player.prepare(videoRenderer, audioRenderer);
        maybeStartPlayback();
    }

    private void maybeStartPlayback() {
        Surface surface = surfaceView.getHolder().getSurface();
        if (videoRenderer == null || surface == null || !surface.isValid()) {
            // We're not ready yet.
            return;
        }
        player.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surface);
        if (autoPlay) {
            player.setPlayWhenReady(true);
            autoPlay = false;
        }
    }

    private void onRenderersError(RendererBuilderCallback callback, Exception e) {
        if (this.callback != callback) {
            return;
        }
        this.callback = null;
        onError(e);
    }

    private void onError(Exception e) {
        Log.e(TAG, "Playback failed", e);
        Toast.makeText(this, "Playback failed", Toast.LENGTH_SHORT).show();
        finish();
    }

    // ExoPlayer.Listener implementation

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        // Do nothing.
    }

    @Override
    public void onPlayWhenReadyCommitted() {
        // Do nothing.
    }

    @Override
    public void onPlayerError(ExoPlaybackException e) {
        onError(e);
    }

    // MediaCodecVideoTrackRenderer.Listener

    @Override
    public void onVideoSizeChanged(int width, int height) {
        surfaceView.setVideoWidthHeightRatio(height == 0 ? 1 : (float) width / height);
    }

    @Override
    public void onDrawnToSurface(Surface surface) {
        shutterView.setVisibility(View.GONE);
    }

    @Override
    public void onDroppedFrames(int count, long elapsed) {
        Log.d(TAG, "Dropped frames: " + count);
    }

    @Override
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException e) {
        // This is for informational purposes only. Do nothing.
    }

    @Override
    public void onCryptoError(CryptoException e) {
        // This is for informational purposes only. Do nothing.
    }

    // SurfaceHolder.Callback implementation

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        maybeStartPlayback();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Do nothing.
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (videoRenderer != null) {
            player.blockingSendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, null);
        }
    }

    /* package */
    final class RendererBuilderCallback {

        public void onRenderers(MediaCodecVideoTrackRenderer videoRenderer,
                                MediaCodecAudioTrackRenderer audioRenderer) {
            PlayerActivity.this.onRenderers(this, videoRenderer, audioRenderer);
        }

        public void onRenderersError(Exception e) {
            PlayerActivity.this.onRenderersError(this, e);
        }

    }
}
