package com.soluapps.sulaiman.discover;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}



  /* CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(R.drawable.earth1, R.drawable.earth1, R.drawable.earth1)
                .addSubMenu(Color.parseColor("#ccddff"), R.drawable.moon)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        switch (i) {
                            case 0:
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        Intent intent = new Intent(HomeActivity.this, MoonActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 2000);
                                return;

                            default:
                                Toast.makeText(HomeActivity.this, "no selection", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
*/
