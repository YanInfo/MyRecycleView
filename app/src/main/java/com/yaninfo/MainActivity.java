package com.yaninfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.yaninfo.adapter.GirlAdapter;
import com.yaninfo.entity.Girl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 主活动Activity
 */
public class MainActivity extends AppCompatActivity {

    private List<Girl> girlList = new ArrayList<>();
    private GirlAdapter adapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 加载ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initGirl();
        mRecyclerView = findViewById(R.id.recycler_view);

        // 默认布局
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new GirlAdapter(girlList);
        mRecyclerView.setAdapter(adapter);

        // 设置增加删除效果，这里是默认的效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 调用自定义的监听
        adapter.setmOnItemClickListener(new GirlAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"这是第"+position+"张图片", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"长按删除第"+position+"张图片", Toast.LENGTH_LONG).show();
                // 设置长按删除对应的item
                adapter.deleteGirl(position);
            }
        });

    }

     /**
     * 加载ToolBar布局
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * ToolBat监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.addGirl(0);
                mRecyclerView.scrollToPosition(0);
                break;
            case R.id.delete:
                adapter.deleteGirl(0);
                break;
            case R.id.LinearLayout:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.StaggeredGrid:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                Toast.makeText(this, "欢迎来到瀑布流", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
        return true;
    }

    private void initGirl() {
        girlList.clear();
        for (int i = 0; i < 51; i++) {
            Random random = new Random();
            int index = random.nextInt(girls.length);
            girlList.add(girls[index]);
        }
    }

    // girls数组
    private Girl[] girls = {
            new Girl("Apple", R.drawable.meizi),
            new Girl("Banana", R.drawable.meizi1),
            new Girl("Orange", R.drawable.meizi2),
            new Girl("Watermelon", R.drawable.meizi3),
            new Girl("Pear", R.drawable.meizi4),
            new Girl("Grape", R.drawable.meizi5),
            new Girl("Pineapple", R.drawable.meizi6),
            new Girl("Strawberry", R.drawable.meizi7),
            new Girl("Cherry", R.drawable.meizi8),
            new Girl("Mango", R.drawable.meizi9)
    };
}
