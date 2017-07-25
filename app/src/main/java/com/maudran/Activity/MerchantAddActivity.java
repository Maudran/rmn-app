package com.maudran.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.maudran.Model.Database;
import com.maudran.Model.Merchant;
import com.maudran.myapplication.R;

/**
 * Created by maudran on 03/07/2017.
 */

public class MerchantAddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_merchant);


        setTitle("ADD MERCHANT");

        FloatingActionButton actionSaveMerchant = (FloatingActionButton) findViewById(R.id.actionSaveMerchant);

        actionSaveMerchant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText editMerchantId = (EditText) findViewById(R.id.editMerchantId);

                Integer id = null;

                try{
                    id = Integer.valueOf(editMerchantId.getText().toString());
                } catch (NumberFormatException e){
                    Toast.makeText(MerchantAddActivity.this,"ID Merchant non valide",Toast.LENGTH_SHORT).show();

                    return;
                }


                EditText editMerchantName = (EditText) findViewById(R.id.editMerchantName);
                String name = editMerchantName.getText().toString();


                EditText editMerchantLogo = (EditText) findViewById(R.id.editMerchantLogo);
                String logo = editMerchantLogo.getText().toString();


                Merchant merchant = null;

                try {

                    merchant = new Merchant(id,name,logo);

                } catch (Exception e) {

                    Toast.makeText(MerchantAddActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Database.getInstance(MerchantAddActivity.this).addMerchant(merchant);
                } catch (Exception e) {
                    throw new NullPointerException();
                }

                MerchantAddActivity.this.finish();
            }
        });



    }
}
