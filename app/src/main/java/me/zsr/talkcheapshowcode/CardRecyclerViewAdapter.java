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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: Zhangshaoru
 * @date: 9/24/15
 */
public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.CardTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles = {
            "circular-progress-button",
    };

    public CardRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CardTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardTextViewHolder(mLayoutInflater.inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CardTextViewHolder holder, int position) {
        holder.mmTextView.setText(mTitles[position]);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("asset:///circular_progress_button.gif"))
                .setAutoPlayAnimations(true)
                .build();
        holder.mmSimpleDraweeView.setController(controller);
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
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
