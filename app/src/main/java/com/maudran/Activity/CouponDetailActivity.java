package com.maudran.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maudran.Model.Coupon;
import com.maudran.Model.Database;
import com.maudran.myapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by maudran on 03/07/2017.
 */

public class CouponDetailActivity extends AppCompatActivity{

   private Coupon coupon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coupon);

        setTitle("DETAIL COUPON");


        try {
            coupon = Database.getInstance(this).getCoupon(getIntent().getIntExtra("couponId",0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(coupon == null){
            Toast.makeText(this,"Coupon introuvable", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView titleToto = (TextView) findViewById(R.id.titleToto);
        titleToto.setText(coupon.getTitle());

        ImageView logoToto = (ImageView) findViewById(R.id.logoToto);
        Picasso.with(this).load(coupon.getMerchant().getLogo()).into(logoToto);

        TextView codeToto = (TextView) findViewById(R.id.codeToto);
        codeToto.setText(coupon.getCode());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.btnWebsite) {

            try{
                String urlWebsite = coupon.getAffiliationUrl();
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(urlWebsite));
                startActivity(intent);
            } catch(Exception e){
                Toast.makeText(this,"URL non valide",Toast.LENGTH_SHORT).show();
            }


        }

        if(item.getItemId() == R.id.btnDelete) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Voulez-vous vraiment supprimer ce coupon?");
            alert.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Database.getInstance(CouponDetailActivity.this).deleteCoupon(coupon);
                    dialog.dismiss();
                    CouponDetailActivity.this.finish();
                }
            });

            alert.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
