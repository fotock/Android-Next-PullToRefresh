package com.sanfriend.ptr;

/**
 * Created by shelley on 16/4/5.
 */
public interface PullTrigger {
    void onPrepare();

    void onPull(int offset, boolean isComplete);

    void onRelease();

    void onComplete();

    void onReset();
}
