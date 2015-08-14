package com.pluu.crossfadeviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-13.
 */
public class CrossFadeViewPager extends ViewPager {

	private int alpha;
	private boolean ltr = true;
	private int frontIdx, forwardIdx;

	public CrossFadeViewPager(Context context) {
		super(context);
	}

	public CrossFadeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		final Rect rect = canvas.getClipBounds();
		View view;
		if (alpha < 255) {
			view  = findViewWithTag(Const.PAGER_TAG + forwardIdx);
			if (view != null) {
				drawBg(canvas, rect, view, false);
			}
		}
		view = findViewWithTag(Const.PAGER_TAG + frontIdx);
		if (view != null) {
			drawBg(canvas, rect, view, true);
		}
		super.onDraw(canvas);
	}

	private void drawBg(Canvas canvas, Rect rect, View view, boolean isFront) {
		view = view.findViewById(R.id.imgBg);
		if (view != null) {
			Drawable drawable = ((ImageView) view).getDrawable();
			if (drawable != null) {
				drawable.setBounds(rect.left, 0, rect.right, getHeight());
				drawable.setAlpha(isFront ? alpha : 255 - alpha);
				drawable.draw(canvas);
				drawable.setAlpha(0);
			}
		}
	}

	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		super.onPageScrolled(position, offset, offsetPixels);
		PagerAdapter adapter = getAdapter();
		if (adapter == null) {
			return;
		}
		if (offset == 0.0F) {
			alpha = 255;
			frontIdx = position;
			return;
		}

		final int totalCount = adapter.getCount();
		if (ltr) {
			frontIdx = ((position + totalCount + 1) % totalCount);
			forwardIdx = position;
		} else {
			frontIdx = position;
			forwardIdx = ((position + totalCount + 1) % totalCount);
		}

		int offsetAlpha = (int) (255.0F * offset);
		int tempAlpha = offsetAlpha;
		if (!ltr) {
			tempAlpha = 255 - offsetAlpha;
		}
		alpha = tempAlpha;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		ltr = (l - oldl >= 0);
	}
}
