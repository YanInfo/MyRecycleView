package com.yaninfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yaninfo.adapter.GirlAdapter;
import com.yaninfo.adapter.StaggeredGridLayoutAdapter;
import com.yaninfo.entity.Girl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 瀑布流Activity
 */
public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private List<Girl> girlList = new ArrayList<>();
    private StaggeredGridLayoutAdapter adapter;
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
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        StaggeredGridLayoutAdapter adapter = new StaggeredGridLayoutAdapter(girlList);
        mRecyclerView.setAdapter(adapter);
    }

    /**GirlAdapter
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
        }
        return true;
    }

    private void initGirl() {
        girlList.clear();
        for (int i = 0; i < 50; i++) {
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
            new Girl("Mango", R.drawable.meizi9)};


}
