package com.arshad.boxmetest.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arshad.boxmetest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Arshad on 01-01-2017.
 */
public class ReposViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.card_view)
    public CardView cvCard;

    @Bind(R.id.tvRepoTitle)
    public TextView tvRepoTitle;

    @Bind(R.id.tvRepoFullName)
    public TextView tvRepoFullName;

    @Bind(R.id.tvRepoDesc)
    public TextView tvRepoDesc;

    @Bind(R.id.tvLanguage)
    public TextView tvLanguage;

    @Bind(R.id.tvBranch)
    public TextView tvBranch;

    public ReposViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
