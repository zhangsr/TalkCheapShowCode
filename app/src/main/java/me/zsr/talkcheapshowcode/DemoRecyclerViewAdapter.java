package me.zsr.talkcheapshowcode;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: Zhangshaoru
 * @date: 9/24/15
 */
public class DemoRecyclerViewAdapter extends RecyclerView.Adapter<DemoRecyclerViewAdapter.CardTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<DemoCatogery.Demo> mDemoList;

    public DemoRecyclerViewAdapter(Context context, List<DemoCatogery.Demo> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDemoList = list;
    }

    public void setNewDemoList(List<DemoCatogery.Demo> list) {
        mDemoList = list;
        notifyDataSetChanged();
    }

    @Override
    public CardTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardTextViewHolder(mLayoutInflater.inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CardTextViewHolder holder, int position) {
        DemoCatogery.Demo demo = mDemoList.get(position);
        holder.mmTextView.setText(demo.name);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("asset:///" + demo.thumbnail))
                .setAutoPlayAnimations(true)
                .build();
        holder.mmSimpleDraweeView.setController(controller);
    }

    @Override
    public int getItemCount() {
        return mDemoList == null ? 0 : mDemoList.size();
    }

    public class CardTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name_view) TextView mmTextView;
        @Bind(R.id.gif_sdv) SimpleDraweeView mmSimpleDraweeView;

        CardTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CardTextViewHolder", "onClick position=" + getAdapterPosition());
                }
            });
        }

    }
}
