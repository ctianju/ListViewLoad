package com.example.thinkpad.listviewload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 列表Adapter
 * @author HeDongDong
 */
public class MainAdapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	private LayoutInflater mLayoutInflater;

	public MainAdapter(Context context, List<String> list) {

		super();
		this.context = context;
		this.list = list;
		mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_live_record, parent, false);
		}

		TextView tvName = ViewHolder.get(convertView, R.id.tv_name);
		tvName.setText(list.get(position));

		return convertView;
	}

}
