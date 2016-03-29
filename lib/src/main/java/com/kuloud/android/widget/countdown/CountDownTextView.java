package com.kuloud.android.widget.countdown;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by kuloud on 3/29/16.
 */
public class CountDownTextView extends TextView implements Countable {
    private CountDownCallback mCallback;
    private final CountDownImpl mCounter = new CountDownImpl();

    public CountDownTextView(Context context) {
        super(context);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initCounter(getHint(), getText());
        setText(null);
    }

    @Override
    protected void onDetachedFromWindow() {
        mCounter.onDestory();
        super.onDetachedFromWindow();
    }

    @Override
    public void initCounter(CharSequence hint, CharSequence format) {
        mCounter.setFormat(hint, format);
        mCounter.setCallback(new CountDownCallback() {
            @Override
            public void onCountDown(int second) {
                setText(String.format(Locale.US, mCounter.mFormat.toString(), second));
                if (mCallback != null) {
                    mCallback.onCountDown(second);
                }
            }
        });
    }

    @Override
    public void countDown(int second) {
        mCounter.countDown(second);
    }

    @Override
    public void cancelCount() {
        mCounter.onDestory();
        setText(mCounter.mHint);
    }

    public void setCallback(CountDownCallback callback) {
        this.mCallback = callback;
    }

    public void setFormat(String hint, String format) {
        mCounter.setFormat(hint, format);
    }
}
