package com.myandroid.qiye;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberActivity extends AppCompatActivity {
    private GridView memberGridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_member);
        memberGridView = (GridView) findViewById(R.id.member_gridview);
        
        //  实始化Grid数据
        initgrid();
        String[] from={"member_gridimg","member_gridtext"};
        int[] to={R.id.member_gridimg,R.id.member_gridtext};
        adapter=new SimpleAdapter(this, dataList, R.layout.item_member_grid, from, to);
        memberGridView.setAdapter(adapter);

        memberGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MemberActivity.this);
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("member_gridtext").toString()).create().show();
            }
        });

    }

    private void initgrid() {
        //图标
        int icno[] = { R.drawable.ico_cart, R.drawable.ico_fav, R.drawable.ico_kefu,R.drawable.ico_wl, R.drawable.ico_setting};
        //图标下的文字
        String name[]={"购物车","收藏夹","客服","物流","设置"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icno.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("member_gridimg", icno[i]);
            map.put("member_gridtext",name[i]);
            dataList.add(map);
        }

    }
}
