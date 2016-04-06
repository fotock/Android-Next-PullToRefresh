package com.sanfriend.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by shelley on 16/4/5.
 */
public class LoadMoreFooterLayout extends FrameLayout implements LoadMoreTrigger, PullTrigger {

    public LoadMoreFooterLayout(Context context) {
        this(context, null);
    }

    public LoadMoreFooterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onPull(int offset, boolean isComplete) {
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
    }
}
