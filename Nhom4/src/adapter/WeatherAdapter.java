package adapter;

import java.util.ArrayList;
import Model.Weather;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter {
	private Activity context = null;
	private ArrayList<Weather> myArray;
	int layoutId;
	public WeatherAdapter(Activity context, int layoutId, ArrayList<Weather> arr) {
		this.context = context;
		this.layoutId = layoutId;
		Log.e("texxt", arr.toString());
		this.myArray = arr;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myArray.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;

		if(convertView == null) {
			//convertView = LayoutInflater.from(context).inflate(R.layout.truyen, null);
			holder = new ViewHolder();
			//holder.txtTenthanhpho = (TextView) convertView.findViewById(R.id.Tenthanhpho);
			
			convertView.setTag(holder);
			final Weather emp = myArray.get(position);
			

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtTenthanhpho.setText(myArray.get(position).getTenthanhpho());
		holder.txtNhietdo.setText(myArray.get(position).getNhietdo());
		holder.txtNhietdocaonhat.setText(myArray.get(position).getNhietdocaonhat());
		holder.txtNhietdothapnhat.setText(myArray.get(position).getNhietdothapnhat());
		holder.txtDoam.setText(myArray.get(position).getDoam());
		holder.txtTocdogio.setText(myArray.get(position).getTocdogio());
		holder.txtApSuat.setText(myArray.get(position).getApSuat());
		return convertView;
	}
	class ViewHolder {
		TextView txtTenthanhpho;
		TextView txtNhietdo;
		TextView txtNhietdocaonhat;
		TextView txtNhietdothapnhat;
		TextView txtDoam;
		TextView txtTocdogio;
		TextView txtDate;
		TextView txtApSuat;
	}

}
