package com.breeze.movieflow.fragments;

import com.breeze.movieflow.R;
import com.breeze.movieflow.utils.BitmapHelper;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HubFragment extends Fragment {
	ImageView avatar;
	ImageView cover;

	public HubFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View layout = inflater.inflate(R.layout.hub_fragment_layout, container,
				false);
		avatar = (ImageView) layout.findViewById(R.id.avatar);
		//处理封面
		cover = (ImageView) layout.findViewById(R.id.cover);
		BitmapHelper imagehelper = new BitmapHelper();
		//获取封面图片
		Drawable cover_img=cover.getDrawable();
		//规格化图片
		Bitmap cover_normalized_img=imagehelper.normalizeCoverImage(cover_img);
		//为规格化后的图片封面创建阴影
		Bitmap cover_normalized_shadow_img =imagehelper.createBitmapShadow(cover_normalized_img);
		cover.setImageBitmap(cover_normalized_shadow_img);

		//处理头像
		Resources res = getResources();
		Drawable image = res.getDrawable(R.drawable.avatar);
		Activity activity=getActivity();

		
		avatar.setImageBitmap(imagehelper.createAvatarBar(imagehelper.makeRectImage(image),activity));
		return layout;
	}
}
