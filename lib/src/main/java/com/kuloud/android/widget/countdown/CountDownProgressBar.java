package com.kuloud.android.widget.countdown;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by kuloud on 3/29/16.
 */
public class CountDownProgressBar extends ProgressBar implements Countable {
    private static final int MAX_PROGRESS = 1000;
    private final CountDownImpl mCounter = new CountDownImpl();

    private int mMaxProgress = MAX_PROGRESS;
    private CountDownCallback mCallback;

    public CountDownProgressBar(Context context) {
        super(context);
    }

    public CountDownProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setMax(MAX_PROGRESS);
        setProgress(MAX_PROGRESS);
        initCounter(null, null);
    }

    @Override
    protected void onDetachedFromWindow() {
        mCounter.onDestory();
        super.onDetachedFromWindow();
    }

    @Override
    public void initCounter(CharSequence hint, CharSequence format) {
        mCounter.setCallback(new CountDownCallback() {
            @Override
            public void onCountDown(int second) {
                int progress = (int) (1.0f * second / mMaxProgress * MAX_PROGRESS);
                setProgress(progress);
                if (mCallback != null) mCallback.onCountDown(second);
            }
        });
    }

    @Override
    public void countDown(int second) {
        mMaxProgress = second;
        mCounter.countDown(second);
    }

    @Override
    public void cancelCount() {
        mCounter.onDestory();
    }

    public void setCallback(CountDownCallback callback) {
        this.mCallback = callback;
    }

}
