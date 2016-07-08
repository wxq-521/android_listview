package com.xie.day10_listviewpage_internet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.xie.day10_listviewpage_internet.adapter.MyAdapter;
import com.xie.day10_listviewpage_internet.internet.MyAsyncTask;
import com.xie.day10_listviewpage_internet.myinterface.OnSuccessListener;

public class MainActivity extends Activity implements OnSuccessListener {

	private ListView mFoodShow;
	private TextView mTips;
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	private MyAdapter adapter;
	private boolean isBottom = false;
	private int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mFoodShow = (ListView) findViewById(R.id.foodshow);
		mTips = (TextView) findViewById(R.id.tips);

		new MyAsyncTask(this).execute(URLs.PATH + page);
		adapter = new MyAdapter(this, data);
		mFoodShow.setAdapter(adapter);
		mFoodShow.setOnScrollListener(onScrollListener);
	}

	OnScrollListener onScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (isBottom && scrollState == SCROLL_STATE_IDLE) {
				mTips.setVisibility(View.VISIBLE);
				mTips.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						page++;
						loadMore();
						mTips.setVisibility(View.GONE);
					}

				});

			} else {
				mTips.setVisibility(View.GONE);
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (firstVisibleItem + visibleItemCount == totalItemCount) {
				isBottom = true;
			} else {
				isBottom = false;
			}

		}
	};

	private void loadMore() {
		new MyAsyncTask(MainActivity.this).execute(URLs.PATH + page);
	}

	@Override
	public void onSuccess(List<Map<String, Object>> result) {
		data.addAll(result);
		adapter.notifyDataSetChanged();
	}

}
