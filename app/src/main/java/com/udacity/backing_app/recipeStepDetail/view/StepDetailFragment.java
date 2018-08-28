package com.udacity.backing_app.recipeStepDetail.view;

import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.*;
import android.view.*;
import android.widget.TextView;
import butterknife.*;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.source.*;
import com.google.android.exoplayer2.trackselection.*;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.util.Util;
import com.udacity.backing_app.R;
import com.udacity.backing_app.core.pojo.Step;
import com.udacity.backing_app.core.utils.VideoUtils;
import com.udacity.backing_app.core.view.BaseFragment;
import com.udacity.backing_app.recipeStepDetail.presenter.StepDetailPresenter;
import org.jetbrains.annotations.NotNull;

public class StepDetailFragment extends BaseFragment<StepDetailPresenter> implements StepDetailView {

    public static final String TAG = StepDetailFragment.class.getSimpleName();
    private static final String VIDEO_POSITION_KEY = "position";
    private static final String VIDEO_PLAYED_WHEN_READY_KEY = "played";

    @Nullable
    @BindView(R.id.instructions_text_tv)
    TextView text;

    @BindView(R.id.step_video_player)
    PlayerView mPlayerView;

    @BindBool(R.bool.fullscreen_video)
    boolean fullScreenVideo;

    private SimpleExoPlayer mPlayer;

    private long videoPosition = -1;
    private boolean videoPlayedWhenReady = true;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (mPlayer != null) {
            outState.putLong(VIDEO_POSITION_KEY, mPlayer.getCurrentPosition());
            outState.putBoolean(VIDEO_PLAYED_WHEN_READY_KEY, mPlayer.getPlayWhenReady());
        }
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            videoPosition = savedInstanceState.getLong(VIDEO_POSITION_KEY, -1);
            videoPlayedWhenReady = savedInstanceState.getBoolean(VIDEO_PLAYED_WHEN_READY_KEY, true);
        }
        return super.onCreateView(inflater, parent, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23)
            releasePlayer();
    }

    @Override
    public void init(Step step) {
        if (!fullScreenVideo)
            text.setText(step.getDescription());
        String videoUrl = (!step.getVideoURL().isEmpty()) ?
                step.getVideoURL() : (!step.getThumbnailURL().isEmpty()) ?
                    step.getThumbnailURL() : "";
        String thumbnailUrl = (videoUrl.equals(step.getThumbnailURL())) ? "" : step.getThumbnailURL();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_highlight_off);
        mPlayerView.setDefaultArtwork(bitmap);
        if (mPlayer == null && !videoUrl.isEmpty()) {
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            DefaultTrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            mPlayerView.setPlayer(mPlayer);
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), "BakingApp"), bandwidthMeter);
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoUrl));
            // Prepare the player with the source.
            if (!thumbnailUrl.isEmpty())
                mPlayerView.setDefaultArtwork(VideoUtils.retrieveVideoFrameFromVideo(thumbnailUrl));
            mPlayer.prepare(videoSource, false, true);
            if (videoPosition != -1)
                mPlayer.seekTo(videoPosition);
            mPlayer.setPlayWhenReady(videoPlayedWhenReady);
        } else if (!fullScreenVideo)
            mPlayerView.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23)
            releasePlayer();
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_step_detail;
    }
}
