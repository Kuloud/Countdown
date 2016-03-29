package com.kuloud.android.widget.countdown;

/**
 * 倒计时widget接口，内部实现指定秒数的倒计时逻辑
 *
 * @author kuloud
 */
public interface Countable {
    void initCounter(CharSequence hint, CharSequence format);

    void countDown(int second);

    void cancelCount();

}
