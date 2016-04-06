package com.sanfriend.ptr;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by shelley on 16/4/5.
 */
public class PullToRefreshHeaderView  extends LinearLayout implements RefreshHeaderTrigger, PullTrigger {

    private ImageView ivArrow;
    private ImageView ivSuccess;
    private ProgressBar pbProgress;
    private TextView tvRefresh;

    private int mHeaderHeight;

    private Animation rotateUp;
    private Animation rotateDown;

    private boolean rotated = false;

    public PullToRefreshHeaderView(Context context) {
        this(context, null);
        init(context);
    }

    public PullToRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public PullToRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        pbProgress = (ProgressBar) findViewById(R.id.pbProgress);

        pbProgress.getIndeterminateDrawable().setColorFilter(Color.WHITE,
                android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        pbProgress.setVisibility(VISIBLE);
        tvRefresh.setText(R.string.ptr_header_refreshing);

    }

    @Override
    public void onPrepare() {
        tvRefresh.setText(R.string.ptr_header_pull);
        if (rotated) {
            ivArrow.clearAnimation();
            ivArrow.startAnimation(rotateDown);
            rotated = false;
        }
    }

    @Override
    public void onPull(int offset, boolean isComplete) {
        if (isComplete) { return; }

        ivArrow.setVisibility(VISIBLE);
        pbProgress.setVisibility(GONE);
        ivSuccess.setVisibility(GONE);
        if (offset <= mHeaderHeight) {
            return;
        }

        tvRefresh.setText(R.string.ptr_header_release);
        if (!rotated) {
            ivArrow.clearAnimation();
            ivArrow.startAnimation(rotateUp);
            rotated = true;
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        pbProgress.setVisibility(GONE);
        tvRefresh.setText(R.string.ptr_header_complete);
    }

    @Override
    public void onReset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        pbProgress.setVisibility(GONE);
        tvRefresh.setText(R.string.ptr_header_pull);
    }
}