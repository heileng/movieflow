package com.breeze.movieflow.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;

@SuppressWarnings("deprecation")
public class BitmapHelper {
	private static final Xfermode MASK_XFERMODE;
	private int shadowwidth = 30;
	static {
		PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
		MASK_XFERMODE = new PorterDuffXfermode(localMode);
	}

	// 创建圆角矩形头像
	public Bitmap makeRoundedCornerImage(Drawable image) {

		Bitmap bmp = drawableToBitmap(image);
		Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = 10;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bmp, rect, rect, paint);
		return output;
	}

	// 创建圆形头像
	public Bitmap makeCircularImage(Drawable image) {
		Bitmap bmp = drawableToBitmap(image);
		Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = Color.WHITE;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		float f1 = bmp.getWidth();
		float f2 = bmp.getHeight();
		float radius;
		if (f1 < f2)
			radius = f1;
		else
			radius = f2;
		RectF localRectF = new RectF(0.0F + 7, 0.0F + 7, radius - 7, radius - 7);
		canvas.drawOval(localRectF, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bmp, rect, rect, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.DST_OVER));
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		RectF localRectF2 = new RectF(0.0F, 0.0F, f1, f2);
		canvas.drawOval(localRectF2, paint);
		return output;
	}

	// 创建方形头像
	public Bitmap makeRectImage(Drawable image) {

		Bitmap bmp = drawableToBitmap(image);
		Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRect(rectF, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bmp, rect, rect, paint);
		return output;
	}

	// 将drawable资源转换为Bitmap
	public Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	// 为图片添加阴影效果
	public Bitmap createBitmapShadow(Drawable image) {
		Bitmap bmp = drawableToBitmap(image);
		Bitmap output = Bitmap.createBitmap(bmp.getWidth() + shadowwidth,
				bmp.getHeight() + shadowwidth, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShadowLayer(15, -5, 0, Color.argb(200, 10, 10, 10));
		final Rect rect_bmp = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		final Rect rect_can = new Rect(shadowwidth, 0, bmp.getWidth()
				+ shadowwidth, bmp.getHeight());
		canvas.drawBitmap(bmp, rect_bmp, rect_can, paint);

		return output;
	}

	// 创建头像bar
	public Bitmap createAvatarBar(Bitmap avatar, Context context) {
		Bitmap output = Bitmap.createBitmap(
				4 * avatar.getWidth() + shadowwidth, avatar.getHeight()
						+ shadowwidth, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		// 画背景
		final Rect rectbc = new Rect(shadowwidth, 0, 4 * avatar.getWidth()
				+ shadowwidth, avatar.getHeight());
		final RectF rectbcF = new RectF(rectbc);
		paint.setShadowLayer(15, -5, 0, Color.argb(200, 10, 10, 10));
		paint.setColor(Color.argb(100, 50, 50, 50));
		canvas.drawRect(rectbcF, paint);

		paint.setColor(Color.argb(255, 0, 0, 0));
		paint.setShadowLayer(0, 10, 10, Color.argb(100, 10, 10, 10));
		// 头像范围
		final Rect rect_draw = new Rect(shadowwidth, 0, avatar.getWidth()
				+ shadowwidth, avatar.getHeight());
		final RectF rect_draw_F = new RectF(rect_draw);
		// 画布范围
		final Rect rect_avatar = new Rect(0, 0, avatar.getWidth(),
				avatar.getHeight());
		// canvas.drawRect(rectF, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
		canvas.drawBitmap(avatar, rect_avatar, rect_draw_F, paint);
		paint.setColor(Color.WHITE);
		Typeface font = Typeface.createFromAsset(context.getAssets(),
				"fonts/berlin.ttf");
		paint.setTypeface(font);
		paint.setTextSize(70);
		canvas.drawText("HEILENG", avatar.getWidth() + 30 + shadowwidth,
				avatar.getHeight() / 2 + 70, paint);
		return output;
	}

}
