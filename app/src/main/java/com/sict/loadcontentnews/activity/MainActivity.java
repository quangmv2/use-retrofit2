package com.sict.loadcontentnews.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sict.loadcontentnews.R;
import com.sict.loadcontentnews.activity.home.HomeActivity;
import com.sict.loadcontentnews.activity.news.NewsActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeActivity homeActivity;
    private NewsActivity newsActivity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment active = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        homeActivity = new HomeActivity();

        newsActivity = new NewsActivity();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home_naviagtion);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

        active = homeActivity;
        frameLayout = findViewById(R.id.container);
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.container, newsActivity, "2").hide(newsActivity).commit();
        fragmentManager.beginTransaction().add(R.id.container, homeActivity, "1").commit();

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
//        FirebaseMessaging.getInstance().subscribeToTopic("weather")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = getString(R.string.msg_subscribed);
//                        if (!task.isSuccessful()) {
//                            msg = getString(R.string.msg_subscribe_failed);
//                        }
//                        Log.d(TAG, msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
////        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("TOKEN", "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//
//                        System.out.println(token);
//
//                        // Log and toast
//                        String msg = token;
//                        Log.d("msg", msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home_naviagtion:
                fragmentManager.beginTransaction().hide(active).show(homeActivity).commit();
                active = homeActivity;
                return true;
            case R.id.news_navigation:
                fragmentManager.beginTransaction().hide(active).show(newsActivity).commit();
                active = newsActivity;
                return true;
        }

        return false;
    }
}
