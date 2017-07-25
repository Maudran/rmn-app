package com.maudran.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.maudran.Model.Coupon;
import com.maudran.Model.Database;
import com.maudran.Model.ExistingCouponException;
import com.maudran.Model.Merchant;
import com.maudran.myapplication.R;

/**
 * Created by maudran on 03/07/2017.
 */

public class CouponAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        setTitle("ADD COUPON");

        FloatingActionButton actionSaveCoupon = (FloatingActionButton) findViewById(R.id.actionSaveCoupon);

        actionSaveCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editCouponId = (EditText) findViewById(R.id.editCouponId);

                Integer id = null;

                try{
                    id = Integer.valueOf(editCouponId.getText().toString());
                } catch (NumberFormatException e){
                    Toast.makeText(CouponAddActivity.this,"ID non valide",Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText editCouponMerchId = (EditText) findViewById(R.id.editCouponMerchId);

                try{
                    id = Integer.valueOf(editCouponMerchId.getText().toString());
                } catch (NumberFormatException e){
                    Toast.makeText(CouponAddActivity.this,"ID Merchant non valide",Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText editCouponTitle = (EditText) findViewById(R.id.editCouponTitle);
                String title = editCouponTitle.getText().toString();


                EditText editCouponCode = (EditText) findViewById(R.id.editCouponCode);
                String code = editCouponCode.getText().toString();

                EditText editCouponUrl = (EditText) findViewById(R.id.editCouponUrl);
                String url = editCouponUrl.getText().toString();

                Coupon coupon;
                Merchant merchant;

                try {
                    merchant = Database.getInstance(CouponAddActivity.this).getMerchant(id);
                } catch (Exception e) {
                    throw new NullPointerException();
                }

                try {
                    coupon = new Coupon(id,title,code,url,merchant);
                } catch (Exception e) {
                    Toast.makeText(CouponAddActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Database.getInstance(CouponAddActivity.this).addCoupon(coupon);
                } catch (NullPointerException e) {
                    Toast.makeText(CouponAddActivity.this,"Coupon null",Toast.LENGTH_SHORT).show();
                    return;
                } catch (ExistingCouponException e) {
                    Toast.makeText(CouponAddActivity.this,"Ce coupon existe déjà",Toast.LENGTH_SHORT).show();
                    return;
                }

                CouponAddActivity.this.finish();

            }
        });

    }
}
