package com.soluapps.sulaiman.discover;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.soluapps.sulaiman.discover.login.LoginAuth;

public class Internet extends AppCompatActivity {
    Button btnTry;
    private LinearLayout up_auth;
    private RelativeLayout down_auth;
    private Animation uptodown, downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        btnTry = (Button) findViewById(R.id.try_again);

        up_auth = (LinearLayout) findViewById(R.id.up_auth);
        down_auth = (RelativeLayout) findViewById(R.id.down_auth);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        up_auth.setAnimation(uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        down_auth.setAnimation(downtoup);
        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnectingToInternet(Internet.this)) {

                    Intent intent = new Intent(Internet.this, LoginAuth.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Internet.this, Internet.class);
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
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Internet.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
