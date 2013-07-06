package com.breeze.movieflow;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.graphics.Canvas;
import android.os.Bundle;

public class HomeActivity extends SlidingFragmentActivity {
	CanvasTransformer mTransformer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("电影流");
		setContentView(R.layout.content_frame);
		setBehindContentView(R.layout.sliding_frame);
		getSlidingMenu().setSlidingEnabled(true);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSupportActionBar().hide();
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindScrollScale(0.0f);
		sm.setFadeDegree(0.25f);
		mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}
		};
		sm.setBehindCanvasTransformer(mTransformer);
	}

}
