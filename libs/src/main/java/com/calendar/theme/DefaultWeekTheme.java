package com.calendar.theme;

import android.graphics.Color;

/**
 * Created by Administrator on 2016/7/31.
 */
public class DefaultWeekTheme implements IWeekTheme {
	@Override
	public int colorTopLinen() {
		return Color.parseColor("#4b4b57");
	}

	@Override
	public int colorBottomLine() {
		return Color.parseColor("#4b4b57");
	}

	@Override
	public int colorWeekday() {
		return Color.parseColor("#FFFFFF");
	}

	@Override
	public int colorWeekend() {
		return Color.parseColor("#FFFFFF");
	}

	@Override
	public int colorWeekView() {
		return Color.parseColor("#4b4b57");
	}

	@Override
	public int sizeLine() {
		return 0;
	}

	@Override
	public int sizeText() {
		return 16;
	}
}
