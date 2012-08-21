package q.nutrition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import q.adapter.NutritionAdapter;
import q.bean.Nutrition;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainA extends Activity implements OnItemClickListener {
	
	private List<Nutrition> list;
	private SharedPreferences sp;
	private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        sp = getSharedPreferences("sp", Context.MODE_PRIVATE);  
        //
        lv = (ListView)findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
    }
    
    @Override
    protected void onResume() {
    	System.out.println("onResume");
    	super.onResume();
        initList();
        lv.setAdapter(new NutritionAdapter(this, list, R.layout.list_item));
    }

    @Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	final Nutrition item = list.get(position);
    	View v = getLayoutInflater().inflate(R.layout.list_item_more, null);
    	final EditText etResult = (EditText)v.findViewById(R.id.list_item_more_result);
    	etResult.setText(String.valueOf(item.getAmount()));
    	v.findViewById(R.id.list_item_more_sub).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				etResult.setText(String.valueOf( Integer.parseInt(etResult.getText().toString()) - item.getAmountPerTime() * item.getTimePerDay() ));
				v.setEnabled(false);
			}
		});
		new AlertDialog.Builder(this)
		.setView(v)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sp.edit().putInt(item.getName(), Integer.parseInt(etResult.getText().toString())).commit();
				onResume();
			}
		})
		.setNegativeButton("取消", null)
		.show();
	}
    
    private void initList(){
    	list = new ArrayList<Nutrition>();
        Nutrition item;
        //
        item = new Nutrition();
        item.setName("多种维");
        item.setDes("9:00、18:00 | 饭后吞服 | vc50, vd100, ve10");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(1);
        item.setTimePerDay(2);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("钙铁锌");
        item.setDes("9:00、18:00 | 咀嚼 | 钙113、铁1.6、锌1.6");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(2);
        item.setTimePerDay(2);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("牛初乳");
        item.setDes("09:30、22:00 | 咀嚼 | 蛋白质113、免疫球蛋白1.6");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(2);
        item.setTimePerDay(2);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("纤维素");
        item.setDes("13:30、20:00 | 饭后吞服 | 纤维素160");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(1);
        item.setTimePerDay(2);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("液体钙");
        item.setDes("14:30、22:00 | 温水吞服 | 钙257、vd34");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(1);
        item.setTimePerDay(2);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("维生C");
        item.setDes("14:30 | 泡腾 | vc100、纳412");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(1);
        item.setTimePerDay(1);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("维生E");
        item.setDes("14:30 | 吞服 | ve109");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(1);
        item.setTimePerDay(1);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("蓝莓素");
        item.setDes("8月20日 | 温水吞服 | 叶黄素2、花青素5");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(2);
        item.setTimePerDay(2);
        list.add(item);
        //
        item = new Nutrition();
        item.setName("生血剂");
        item.setDes("8月20日 | 口服早晚各一次 | 铁1.3、锌1.1");
        item.setAmount(sp.getInt(item.getName(), 0));
        item.setAmountPerTime(1);
        item.setTimePerDay(2);
        list.add(item);
        //
        Collections.sort(list, new Comparator<Nutrition>() {
			@Override
			public int compare(Nutrition lhs, Nutrition rhs) {
				/*if(lhs.getType() < rhs.getType()){
					return 1;
				}*/
				if(lhs.getDays() > rhs.getDays()){
					return -1;
				}
				return 0;
			}
		});
    }

	
    
}

