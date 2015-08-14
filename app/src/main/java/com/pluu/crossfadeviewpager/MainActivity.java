package com.pluu.crossfadeviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@Bind(R.id.viewPager)
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		List<CrossFadeItem> list = new ArrayList<>();
		list.add(new CrossFadeItem(R.drawable.emp_friends_01, R.drawable.bg_01));
		list.add(new CrossFadeItem(R.drawable.emp_friends_02, R.drawable.bg_02));
		list.add(new CrossFadeItem(R.drawable.emp_friends_03, R.drawable.bg_03));
		list.add(new CrossFadeItem(R.drawable.emp_friends_04, R.drawable.bg_04));
		viewPager.setAdapter(new CrossFadeAdapter(this, list));
	}

}
