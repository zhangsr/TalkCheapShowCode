package me.zsr.talkcheapshowcode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @description:
 * @author: Zhangshaoru
 * @date: 10/12/15
 */
public class CatogeryAdapter extends BaseAdapter {
    private List<DemoCatogery> mDemoCatogeryList;
    private Context mContext;

    public CatogeryAdapter(Context context, List<DemoCatogery> list) {
        mContext = context;
        mDemoCatogeryList = list;
    }

    @Override
    public int getCount() {
        return mDemoCatogeryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDemoCatogeryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DemoCatogery demoCatogery = mDemoCatogeryList.get(position);
        TextView textView = new TextView(mContext);
        textView.setText(demoCatogery.name);
        return textView;
    }
}
