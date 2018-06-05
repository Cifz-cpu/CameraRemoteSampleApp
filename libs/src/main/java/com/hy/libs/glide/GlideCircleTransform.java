package com.hy.libs.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * @包名:com.glide
 * @创建者: 毛化磊、Simple改
 * @创建时间: 2016年9月14日
 * @描述: GlideCircleTransform.java Glide圆形图片转换器
 */
public class GlideCircleTransform extends BitmapTransformation {

	private static Paint mBorderPaint;
	private static float mBorderWidth;

	public GlideCircleTransform(Context context) {
		super(context);
		mBorderPaint = null;
	}

	public GlideCircleTransform(Context context, int borderWidth, int borderColor) {
		super(context);
		mBorderWidth = Resources.getSystem().getDisplayMetrics().density * borderWidth;

		mBorderPaint = new Paint();
		mBorderPaint.setDither(true);
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setColor(borderColor);
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBorderPaint.setStrokeWidth(mBorderWidth);
	}


	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
		return circleCrop(pool, toTransform);
	}

	private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
		if (source == null)
			return null;

		int size = Math.min(source.getWidth(), source.getHeight());
		int x = (source.getWidth() - size) / 2;
		int y = (source.getHeight() - size) / 2;

		// TODO this could be acquired from the pool too
		Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

		Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
		if (result == null) {
			result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		}
		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
		paint.setAntiAlias(true);
		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);
		if (mBorderPaint != null) {
			float borderRadius = r - mBorderWidth / 2;
			canvas.drawCircle(r, r, borderRadius, mBorderPaint);
		}
		return result;
	}

	@Override
	public String getId() {
		return getClass().getName();
	}
}
