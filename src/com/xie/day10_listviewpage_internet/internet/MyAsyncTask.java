package com.xie.day10_listviewpage_internet.internet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.xie.day10_listviewpage_internet.myinterface.OnSuccessListener;
import com.xie.day10_listviewpage_internet.utils.HttpUtils;

public class MyAsyncTask extends
		AsyncTask<String, Void, List<Map<String, Object>>> {

	private OnSuccessListener onSuccessListener;

	// 这儿传过来的OnSuccessListener其实可以看做是MainActivity
	public MyAsyncTask(OnSuccessListener onSuccessListener) {
		this.onSuccessListener = onSuccessListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected List<Map<String, Object>> doInBackground(String... params) {
		String urlString = params[0];
		String json = HttpUtils.getHttpResult(urlString);
		List<Map<String, Object>> datas = null;
		datas = getJsonToList(json);

		return datas;
	}

	private List<Map<String, Object>> getJsonToList(String json) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", jsonObject2.getString("title"));
				map.put("pic", jsonObject2.getString("pic"));
				map.put("food_str", jsonObject2.getString("food_str"));
				list.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	protected void onPostExecute(List<Map<String, Object>> result) {
		// TODO 结果通过接口传出
		super.onPostExecute(result);
		onSuccessListener.onSuccess(result);
	}

}
