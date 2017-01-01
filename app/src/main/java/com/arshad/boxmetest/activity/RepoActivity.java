package com.arshad.boxmetest.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.arshad.boxmetest.R;
import com.arshad.boxmetest.adapter.RepoAdapter;
import com.arshad.boxmetest.global.CancelableCallback;
import com.arshad.boxmetest.global.GlobalFunctions;
import com.arshad.boxmetest.interfaces.GlobalAPI;
import com.arshad.boxmetest.model.RepoData;
import com.arshad.boxmetest.model.RepoOwner;
import com.arshad.boxmetest.model.RepoResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 1/1/17.
 */
public class RepoActivity extends AppCompatActivity {

    private final static String TAG = RepoActivity.class.getSimpleName();

    @Bind(R.id.ivProfile)
    ImageView ivProfile;

    @Bind(R.id.tvUsername)
    TextView tvUsername;

    @Bind(R.id.tvUsertype)
    TextView tvUsertype;

    @Bind(R.id.rvRepos)
    RecyclerView rvRepos;

    ProgressDialog dialog;
    Context mContext;

    String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        ButterKnife.bind(this);
        mContext = this;
        dialog =  new ProgressDialog(mContext);

        if(getIntent().hasExtra("username")) {
            username = getIntent().getStringExtra("username");
        } else {
            username = "";
        }
        tvUsername.setText(username);

        //Creating Vertical RecyclerView layout
        GlobalFunctions.createVerticalRecyclerView(mContext, rvRepos);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkInternetAndLoad();
            }
        }, 500);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void populateData(RepoOwner data) {
        tvUsername.setText(data.getLogin());
        tvUsertype.setText(data.getType());
        Picasso.with(mContext).load(data.getAvatarUrl()).into(ivProfile);
    }

    private void checkInternetAndLoad() {
        if(GlobalFunctions.isNetworkAvailable(this)) {
            loadRepos();
        } else {
            GlobalFunctions.toastShort(mContext, "Check your internet and try again");
        }
    }

    private void loadRepos() {
        GlobalFunctions.startProgressDialog(dialog, "Loading repos");

        Call<List<RepoData>> call = GlobalFunctions.initializeRetrofit(mContext).create(GlobalAPI.class).getRepos(username);

        call.enqueue(new CancelableCallback<List<RepoData>>() {
            @Override
            public void onResponse(Response<List<RepoData>> response, Retrofit retrofit) {
                try {

                    GlobalFunctions.stopProgressDialog(dialog);
                    Log.e(TAG, response.code() + " ");
                    Log.e(TAG, response.body() + " ");
                    if(response.code() == 200) {
                        List<RepoData> res = response.body();
                        if(res.size() > 0) {
                            populateData(res.get(0).getOwner());
                        }
                        RepoAdapter adapter = new RepoAdapter(mContext, res);
                        rvRepos.setAdapter(adapter);
                        rvRepos.setItemAnimator(new DefaultItemAnimator());
                    } else {
                        GlobalFunctions.handleResponseCode(mContext, response.code());
                    }

                } catch (Exception e) {
                    GlobalFunctions.failedResponse(e, dialog, mContext, "Please check your connectivity and try again");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                GlobalFunctions.failedResponse(t, dialog, mContext, "Please check your connectivity and try again");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
