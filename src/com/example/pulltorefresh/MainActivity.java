package com.example.pulltorefresh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private ListView listView;
	private SwipeRefreshLayout swipeLayout;
	private Handler uiHandler = new Handler(Looper.getMainLooper());
	private Runnable stopAnimation = new Runnable() {
		public void run() {
			swipeLayout.setRefreshing(false);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.swipe_list_view);
		String[] items = getResources().getStringArray(R.array.items);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, items);
		listView.setAdapter(adapter);

		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				uiHandler.post(stopAnimation);
			}
		});
	}

}
