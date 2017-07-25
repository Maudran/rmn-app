package com.maudran;


import android.app.Application;
import android.database.SQLException;
import android.provider.ContactsContract;

import com.maudran.Model.Coupon;
import com.maudran.Model.Database;
import com.maudran.Model.Merchant;

/**
 * Created by maudran on 03/07/2017.
 */

public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public Merchant saveMerchant(Merchant merchant) throws Exception {

        Database.getInstance(this).addMerchant(merchant);
        return merchant;
    }

    public Coupon saveCoupon(Coupon coupon) throws Exception{

        Database.getInstance(this).addCoupon(coupon);
        return coupon;
    }

}
