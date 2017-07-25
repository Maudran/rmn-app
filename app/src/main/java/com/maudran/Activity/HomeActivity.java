package com.maudran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maudran.myapplication.R;

public class HomeActivity extends AppCompatActivity {


    com.github.clans.fab.FloatingActionButton actionAddCoupon;
    com.github.clans.fab.FloatingActionButton actionAddMerchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("HOME");

        actionAddCoupon = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.actionAddCoupon);

        actionAddCoupon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent appel = new Intent(HomeActivity.this, CouponAddActivity.class);
                startActivity(appel);

            }
        });

        actionAddMerchant = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.actionAddMerchant);

        actionAddMerchant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, MerchantAddActivity.class);
                startActivity(intent);

            }
        });
    }

}