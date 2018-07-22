package com.soluapps.sulaiman.discover;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;
import com.soluapps.sulaiman.discover.login.LoginAuth;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;


public class MainActivity extends AppCompatActivity {
    public LinearLayout up;
    public RelativeLayout down;
    public Button btnStart;
    public Animation uptodown, downtoup;
    public Animation exitdowntoup, exituptodown;
    public CircleMenu circleMenu;

    private void animEnter() {
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        up.setAnimation(uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        down.setAnimation(downtoup);


    }


    @Override
    protected void onStart() {
        //screen delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                circleMenu.open(true);
            }

        }, 5000); // 5 seconds  delay

        super.onStart();
        Handler handler_ = new Handler();
        handler_.postDelayed(new Runnable() {

            @Override
            public void run() {
                circleMenu.close(true);
            }

        }, 10000); // 10 seconds  delay
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        circleMenu = (CircleMenu) findViewById(R.id.circleMenu);


        btnStart = (Button) findViewById(R.id.start);

        // blinking the button for a duration of 1.5 seconds
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(1500); // duration - one and a half second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        btnStart.startAnimation(animation);
        up = (LinearLayout) findViewById(R.id.up);
        down = (RelativeLayout) findViewById(R.id.down);

        animEnter();

        circleMenu.setOnItemClickListener(new CircleMenu.OnItemClickListener() {
            @Override
            public void onItemClick(CircleMenuButton menuButton) {
                switch (menuButton.getId()) {

                    case R.id.settings:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slidein, R.anim.slideout);

                                finish();

                            }
                        }, 1000);

                    case R.id.power:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();

                            }
                        }, 1000);

                    default:

                }
            }

        });

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isConnectingToInternet(MainActivity.this)) {
                    view.clearAnimation();

                    Intent intent = new Intent(MainActivity.this, LoginAuth.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slidein, R.anim.slideout);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            public boolean isConnectingToInternet(Context context) {
                ConnectivityManager connectivity =
                        (ConnectivityManager) context.getSystemService(
                                Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    NetworkInfo[] info = connectivity.getAllNetworkInfo();
                    if (info != null)
                        for (int i = 0; i < info.length; i++)
                            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                                return true;
                            }
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();

    }


}
