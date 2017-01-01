package com.arshad.boxmetest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.arshad.boxmetest.R;
import com.arshad.boxmetest.global.GlobalFunctions;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 1/1/17.
 */
public class RepoDetailsActivity extends AppCompatActivity {

    private static final String TAG = RepoDetailsActivity.class.getSimpleName();

    @Bind(R.id.tvRepoTitle)
    TextView tvRepoTitle;

    @Bind(R.id.tvRepoFullName)
    TextView tvRepoFullName;

    @Bind(R.id.tvRepoDesc)
    TextView tvRepoDesc;

    @Bind(R.id.tvLanguage)
            TextView tvLanguage;

    @Bind(R.id.tvBranch)
            TextView tvBranch;

    @Bind(R.id.tvSize)
            TextView tvSize;

    @Bind(R.id.tvPrivate)
            TextView tvPrivate;

    @Bind(R.id.tvForkCount)
            TextView tvForkCount;

    @Bind(R.id.tvOpenIssues)
            TextView tvOpenIssues;

    @Bind(R.id.tvUrl)
            TextView tvUrl;

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        ButterKnife.bind(this);
        mContext = this;

        try {
            if(getIntent().hasExtra("title")) {
                Intent intent = getIntent();
                tvRepoTitle.setText(intent.getStringExtra("title"));
                tvRepoFullName.setText(intent.getStringExtra("fullName"));
                tvRepoDesc.setText(intent.getStringExtra("desc"));
                tvLanguage.setText("Language: " + intent.getStringExtra("language"));
                tvBranch.setText("Branch: " + intent.getStringExtra("branch"));
                tvPrivate.setText("Private: " + String.valueOf(intent.getBooleanExtra("private", false)));
                tvSize.setText("Size: " + String.valueOf(intent.getIntExtra("size", 0)));
                tvForkCount.setText("Forks Count: " + String.valueOf(intent.getIntExtra("fork", 0)));
                tvOpenIssues.setText("Open Issues: " + String.valueOf(intent.getIntExtra("issue", 0)));
                tvUrl.setText(intent.getStringExtra("url"));
            } else {
                GlobalFunctions.toastShort(mContext, "Unable to fetch repo details");
                finish();
            }
        } catch (Exception e) {
            GlobalFunctions.toastShort(mContext, "Unable to fetch repo details");
            finish();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

}
