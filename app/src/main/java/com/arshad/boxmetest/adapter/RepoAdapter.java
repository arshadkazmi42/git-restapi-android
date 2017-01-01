package com.arshad.boxmetest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arshad.boxmetest.R;
import com.arshad.boxmetest.activity.MainActivity;
import com.arshad.boxmetest.activity.RepoActivity;
import com.arshad.boxmetest.activity.RepoDetailsActivity;
import com.arshad.boxmetest.model.RepoData;
import com.arshad.boxmetest.viewholder.ReposViewHolder;

import java.util.List;

/**
 * Created by Arshad on 01-01-2017.
 */
public class RepoAdapter extends RecyclerView.Adapter<ReposViewHolder> {

    private static final String TAG = RepoAdapter.class.getSimpleName();

    private Context mContext;
    private List<RepoData> repos;

    public RepoAdapter(Context mContext, List<RepoData> repos) {
        this.mContext = mContext;
        this.repos = repos;
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    @Override
    public void onBindViewHolder(final ReposViewHolder holder, final int position) {
        final RepoData data = repos.get(position);
        holder.tvRepoTitle.setText(data.getName());
        holder.tvRepoFullName.setText(data.getFullName());
        holder.tvRepoDesc.setText(data.getDescription());
        holder.tvLanguage.setText("Language: " + data.getLanguage());
        holder.tvBranch.setText("Branch: " + data.getDefaultBranch());

        holder.cvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, RepoDetailsActivity.class);
                i.putExtra("title", data.getName());
                i.putExtra("fullName", data.getFullName());
                i.putExtra("desc", data.getDescription());
                i.putExtra("language", data.getLanguage());
                i.putExtra("branch", data.getDefaultBranch());
                i.putExtra("private", data.getPrivate());
                i.putExtra("size", data.getSize());
                i.putExtra("fork", data.getForks());
                i.putExtra("issue", data.getOpenIssues());
                i.putExtra("url", data.getUrl());
//                mContext.startActivity(i);

                Pair<View, String> p1 = Pair.create((View) holder.tvRepoTitle, mContext.getResources().getString(R.string.repoTitleTransition));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((RepoActivity)mContext, p1);
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    mContext.startActivity(i, options.toBundle());
                } else {
                    mContext.startActivity(i);
                }
            }
        });
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from (mContext).inflate(R.layout.single_row_repos, viewGroup, false);
        return new ReposViewHolder(v);
    }
}
