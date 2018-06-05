package com.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.calendar.component.GridMonthView;
import com.calendar.component.MonthView;
import com.calendar.component.WeekView;
import com.calendar.entity.CalendarInfo;
import com.calendar.theme.IDayTheme;
import com.calendar.theme.IWeekTheme;

import java.util.List;


/**
 * Created by Administrator on 2016/7/31.
 */
public class GridCalendarView extends LinearLayout {
    private WeekView weekView;
    private GridMonthView gridMonthView;

    public GridCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutParams llParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        weekView = new WeekView(context, null);
        gridMonthView = new GridMonthView(context, null);
        addView(weekView, llParams);
        addView(gridMonthView, llParams);
    }

    /**
     * 设置日历点击事件
     *
     * @param dateClick
     */
    public void setDateClick(MonthView.IDateClick dateClick) {
        gridMonthView.setDateClick(dateClick);
    }

    /**
     * 设置星期的形式
     *
     * @param weekString 默认值 "日","一","二","三","四","五","六"
     */
    public void setWeekString(String[] weekString) {
        weekView.setWeekString(weekString);
    }

    public void setCalendarInfos(List<CalendarInfo> calendarInfos) {
        gridMonthView.setCalendarInfos(calendarInfos);
    }

    public void setDayTheme(IDayTheme theme) {
        gridMonthView.setTheme(theme);
    }

    public void setWeekTheme(IWeekTheme weekTheme) {
        weekView.setWeekTheme(weekTheme);
    }

    public WeekView getWeekView() {
        return weekView;
    }

    public void setWeekView(WeekView weekView) {
        this.weekView = weekView;
    }

    public GridMonthView getGridMonthView() {
        return gridMonthView;
    }

    public void setGridMonthView(GridMonthView gridMonthView) {
        this.gridMonthView = gridMonthView;
    }
}