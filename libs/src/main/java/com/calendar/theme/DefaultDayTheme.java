package com.calendar.theme;

import android.graphics.Color;

/**
 * Created by Administrator on 2016/7/30.
 */
public class DefaultDayTheme implements IDayTheme {

    // 选择的日期背景色
    @Override
    public int colorSelectBG() {
        return Color.parseColor("#e95345");
    }

    // 选择日期的文字颜色
    @Override
    public int colorSelectDay() {
        return Color.parseColor("#FFFFFF");
    }

    // 选择日期的描述文字颜色
    @Override
    public int colorSelectDesc() {
        return Color.parseColor("#FFFFFF");
    }

    // 今天文字颜色
    @Override
    public int colorToday() {
        return Color.parseColor("#000000");
    }

    @Override
    public int colorMonthView() {
        return Color.parseColor("#FFFFFF");
    }

    @Override
    public int colorWeekday() {
        return Color.parseColor("#000000");
    }

    @Override
    public int colorWeekend() {
        return Color.parseColor("#000000");
    }

    @Override
    public int colorDecor() {
        return Color.parseColor("#e95345");
    }

    // 休息
    @Override
    public int colorRest() {
        return Color.parseColor("#68CB00");
    }

    // 上班
    @Override
    public int colorWork() {
        return Color.parseColor("#FF9B12");
    }

    // 描述文字颜色
    @Override
    public int colorDesc() {
        return Color.parseColor("#000000");
    }

    @Override
    public int sizeDay() {
        return 32;
    }

    @Override
    public int sizeDecor() {
        return 6;
    }

    @Override
    public int sizeDesc() {
        return 18;
    }

    @Override
    public int dateHeight() {
        return 140;
    }

    @Override
    public int colorLine() {
        return Color.parseColor("#cccccc");
    }
}
