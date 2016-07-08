package com.xie.day10_listviewpage_internet.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xie.day10_listviewpage_internet.R;
import com.xie.day10_listviewpage_internet.utils.NormalLoadPictrue;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> datas;

	public MyAdapter(Context context, List<Map<String, Object>> datas) {
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_layout, parent, false);
			mHolder.mImageView = (ImageView) convertView.findViewById(R.id.pic);
			mHolder.mTextView1 = (TextView) convertView
					.findViewById(R.id.title);
			mHolder.mTextView2 = (TextView) convertView
					.findViewById(R.id.food_str);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.mTextView1.setText((CharSequence) datas.get(position).get(
				"title"));
		mHolder.mTextView2.setText((CharSequence) datas.get(position).get(
				"food_str"));
		String url = (String) datas.get(position).get("pic");
		new NormalLoadPictrue().getPicture(url, mHolder.mImageView);

		return convertView;
	}

	class ViewHolder {

		ImageView mImageView;
		TextView mTextView1;
		TextView mTextView2;

	}
}
