package com.breeze.movieflow.fragments;

import com.breeze.movieflow.R;
import com.breeze.movieflow.utils.BitmapHelper;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HubFragment extends Fragment {
	ImageView avatar;

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

		Resources res = getResources();
		Drawable image = res.getDrawable(R.drawable.avatar);
		Activity activity=getActivity();

		BitmapHelper imagehelper = new BitmapHelper();
		avatar.setImageBitmap(imagehelper.createAvatarBar(imagehelper.makeRectImage(image),activity));
		return layout;
	}
}
