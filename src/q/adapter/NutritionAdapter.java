package q.adapter;

import java.util.Calendar;
import java.util.List;

import q.util.a.view.QBaseAdapter;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import q.bean.Nutrition;
import q.nutrition.R;

public class NutritionAdapter extends QBaseAdapter<Nutrition> {
	
	private float multi;

	public NutritionAdapter(Context ctx, List<Nutrition> data, int layoutResId) {
		super(ctx, data, layoutResId);
		multi = 450f / ( data.get(0).getDays() );
	}

	@Override
	protected Object getViewHolder(View v) {
		Holder h = new Holder();
		h.tv = (TextView)v.findViewById(R.id.tv);
		h.iv = (ImageView)v.findViewById(R.id.iv);
		h.iv.setBackgroundColor(0xFFFF6600);
		h.tvDays = (TextView)v.findViewById(R.id.list_item_days);
		return h;
	}

	@Override
	protected void onInitItem(Nutrition data, Object viewHolder) {
		Holder h = (Holder)viewHolder;
		h.tv.setText(data.getDes());
		h.iv.setLayoutParams(new RelativeLayout.LayoutParams((int)( data.getDays() * multi ), h.iv.getLayoutParams().height));
		h.tvDays.setText(data.getName() + " " + data.getAmountPerTime() + "粒、" + data.getTimePerDay() + "次 | 粒数：" + data.getAmount() + " 天数：" + data.getDays());
	}
	
	private class Holder {
		TextView tv;
		ImageView iv;
		TextView tvDays;
	}
	
}
