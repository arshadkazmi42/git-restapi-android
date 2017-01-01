package com.arshad.boxmetest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.arshad.boxmetest.R;
import com.arshad.boxmetest.global.GlobalFunctions;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.card_view)
    CardView cvCard;

    @Bind(R.id.etUserName)
    EditText etUsername;

    @Bind(R.id.tvGetRepos)
    TextView tvGetRepos;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadViews();
            }
        }, 300);

        tvGetRepos.setOnClickListener(this);
    }

    private void loadViews() {
        cvCard.setVisibility(View.VISIBLE);
        cvCard.setAlpha(0.f);
        cvCard.setScaleX(0.f);
        cvCard.setScaleY(0.f);
        cvCard.animate()
                .alpha(1.f)
                .scaleX(1.f).scaleY(1.f)
                .setDuration(200)
                .start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvGetRepos.setVisibility(View.VISIBLE);
                tvGetRepos.setAlpha(0.f);
                tvGetRepos.setScaleX(0.f);
                tvGetRepos.setScaleY(0.f);
                tvGetRepos.animate()
                        .alpha(1.f)
                        .scaleX(1.f).scaleY(1.f)
                        .setDuration(200)
                        .start();
            }
        }, 200);
    }

    private void startRepoActivity(String username) {
        Intent intent = new Intent(mContext, RepoActivity.class);
        intent.putExtra("username", username);
        Pair<View, String> p1 = Pair.create((View) etUsername, mContext.getResources().getString(R.string.usernameTransition));
        Pair<View, String> p2 = Pair.create((View) tvGetRepos, mContext.getResources().getString(R.string.imageTransition));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1, p2);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            mContext.startActivity(intent, options.toBundle());
        } else {
            mContext.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvGetRepos:
                String username = etUsername.getText().toString();
                if(username.isEmpty()) {
                    GlobalFunctions.toastShort(mContext, "Enter valid username");
                } else {
                    startRepoActivity(username);
                }
                break;

        }
    }
}
