package com.example.viewpagergridview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "甜品饮品",
            "水上乐园", "汽车服务", "美发", "丽人", "景点"};

    private String[] urls={"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1665207864,746409922&fm=27&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=991823527,956610122&fm=27&gp=0.jpg"
    ,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2644124456,1222741308&fm=27&gp=0.jpg"
    ,"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&ie=utf-8&in=24401&cl=2&lm=-1&st=-1&step_word=&rn=1&cs=&ln=1998&fmq=1402900904181_R&ic=0&s=&se=1&sme=0&tab=&width=&height=&face=0&is=&istype=2&ist=&jit=&fr=ala&ala=1&alatpl=others&pos=1&pn=3&word=%E5%9B%BE%E7%89%87%20%E9%A3%8E%E6%99%AF&di=39263243360&os=3381160936,3507749551&pi=0&objurl=http%3A%2F%2Fpic4.nipic.com%2F20091121%2F3764872_215617048242_2.jpg"
    ,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2661055612,1771836516&fm=27&gp=0.jpg"
    ,"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1665207864,746409922&fm=27&gp=0.jpg"
            ,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=991823527,956610122&fm=27&gp=0.jpg"
            ,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2644124456,1222741308&fm=27&gp=0.jpg"
            ,"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&ie=utf-8&in=24401&cl=2&lm=-1&st=-1&step_word=&rn=1&cs=&ln=1998&fmq=1402900904181_R&ic=0&s=&se=1&sme=0&tab=&width=&height=&face=0&is=&istype=2&ist=&jit=&fr=ala&ala=1&alatpl=others&pos=1&pn=3&word=%E5%9B%BE%E7%89%87%20%E9%A3%8E%E6%99%AF&di=39263243360&os=3381160936,3507749551&pi=0&objurl=http%3A%2F%2Fpic4.nipic.com%2F20091121%2F3764872_215617048242_2.jpg"
            ,"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2661055612,1771836516&fm=27&gp=0.jpg"};
    private List<Subject> subjectList;

    private LinearLayout ll_dot;
    //每页展示的主题个数
    private final int pageSize = 10;
    private static final String TAG = "MainActivity";
    //当前页索引
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ll_dot = (LinearLayout) findViewById(R.id.ll_dot);
        subjectList = new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            subjectList.add(new Subject(titles[i],urls[i]));
        }





        //需要的页面数
        int pageCount = (int) Math.ceil(subjectList.size() * 1.0 / pageSize);
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            GridView gridView = (GridView) getLayoutInflater().inflate(R.layout.layout_grid_view, viewPager, false);
            gridView.setAdapter(new GridViewAdapter(this, subjectList, i, pageSize));
            viewList.add(gridView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + currentIndex * pageSize;
                    Toast.makeText(MainActivity.this, subjectList.get(pos).getName(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        viewPager.setAdapter(new ViewPagerAdapter(viewList));
        for (int i = 0; i < pageCount; i++) {
            ll_dot.addView(getLayoutInflater().inflate(R.layout.view_dot, null));
        }
        //使第一个小圆点呈选中状态
        ll_dot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                ll_dot.getChildAt(currentIndex).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_normal);
                ll_dot.getChildAt(position).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
                currentIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

}
