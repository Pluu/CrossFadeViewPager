package com.pluu.crossfadeviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-13.
 */
public class CrossFadeAdapter extends PagerAdapter {

	private final List<CrossFadeItem> list;

	private LayoutInflater inflater;

	public CrossFadeAdapter(Context context, List<CrossFadeItem> list) {
		inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		CrossFadeItem item = list.get(position);

		View view = inflater.inflate(R.layout.pager_item, container, false);
		ImageView badge1 = ButterKnife.findById(view, R.id.imgBg);
		ImageView badge2 = ButterKnife.findById(view, R.id.img);

		badge1.setImageResource(item.bgResId);
		badge2.setImageResource(item.imgResId);

		ViewCompat.setAlpha(badge1, 0f);
		view.setTag(Const.PAGER_TAG + position);

		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}
