package me.zsr.talkcheapshowcode;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.stetho.common.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zsr.library_common.FileUtil;

public class MainActivity extends BaseActivity {
    private static final int INDEX_DEFAULT = 0;
    @Bind(R.id.main_rv) RecyclerView mRecyclerView;
    @Bind(R.id.main_drawer) DrawerLayout mDrawerLayout;
    @Bind(R.id.catogery_list) ListView mNavListView;
    private ActionBarDrawerToggle mActionBarDrawerToggle = null;
    private CatogeryAdapter mCatogeryAdapter;
    private DemoRecyclerViewAdapter mDemoRecyclerViewAdapter;
    private List<DemoCatogery> mCatogeryList;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setListener();
    }

    private void initData() {
        mCatogeryList = DataUtil.parseString2CatogeryList(FileUtil.readAssetFie(this, "content.json"));
        for (DemoCatogery demoCatogery : mCatogeryList) {
            LogUtil.e("initData : " + demoCatogery.toString());
        }
    }

    private void initView() {
        ButterKnife.bind(this);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                setTitle(mCatogeryList.get(mCurrentIndex).name);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDemoRecyclerViewAdapter = new DemoRecyclerViewAdapter(this, mCatogeryList.get(INDEX_DEFAULT).demos);
        mRecyclerView.setAdapter(mDemoRecyclerViewAdapter);
        mActionBarDrawerToggle.syncState();
        mCatogeryAdapter = new CatogeryAdapter(this, mCatogeryList);
        mNavListView.setAdapter(mCatogeryAdapter);
    }

    private void setListener() {
        mNavListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCurrentIndex != position) {
                    mCurrentIndex = position;
                    mDemoRecyclerViewAdapter.setNewDemoList(mCatogeryList.get(mCurrentIndex).demos);
                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }
}
