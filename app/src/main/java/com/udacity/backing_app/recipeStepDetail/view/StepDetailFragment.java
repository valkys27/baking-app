package com.udacity.backing_app.recipeStepDetail.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.View;
import android.widget.TextView;
import butterknife.BindBool;
import butterknife.BindView;
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

public class StepDetailFragment extends BaseFragment<StepDetailPresenter> implements StepDetailView {

    public static final String TAG = StepDetailFragment.class.getSimpleName();

    @Nullable
    @BindView(R.id.instructions_text_tv)
    TextView text;

    @BindView(R.id.step_video_player)
    PlayerView mPlayerView;

    @BindBool(R.bool.fullscreen_video)
    boolean fullScreenVideo;

    private SimpleExoPlayer mPlayer;

    @Override
    public void init(Step step) {
        if (fullScreenVideo)
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
            mPlayer.prepare(videoSource);
            mPlayer.setPlayWhenReady(true);
        } else if (!fullScreenVideo)
            mPlayerView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
