package com.maudran.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.Collection;
import java.util.HashMap;

/**
 * Created by maudran on 03/07/2017.
 */

public final class Database extends SQLiteOpenHelper{

    private static Database instance = null;


    public static Database getInstance(Context context) {
        if(Database.instance == null)
        {
            synchronized(Database.class)
            {
                if(Database.instance == null)
                {
                    Database.instance = new Database(context);
                }
            }
        }
        return Database.instance;
    }





    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "couponsManager";

    private static final String TABLE_COUPONS = "coupons";
    private static final String TABLE_MERCHANTS= "merchants";


    private static final String COUPON_ID = "couponId";
    private static final String COUPON_TITLE = "couponTitle";
    private static final String COUPON_CODE = "couponCode";
    private static final String COUPON_URL = "couponUrl";
    private static final String MERCHANT_ID = "merchantId";
    private static final String MERCHANT_NAME = "merchantName";
    private static final String MERCHANT_LOGO = "merchantLogo";

    public Database(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUPONS_TABLE = "CREATE TABLE " + TABLE_COUPONS + "("
                + COUPON_ID + " INTEGER," + COUPON_TITLE + " TEXT,"
                + COUPON_CODE + " TEXT," + COUPON_URL + " TEXT," + MERCHANT_ID + " INTEGER" + ")";
        db.execSQL(CREATE_COUPONS_TABLE);

        String CREATE_MERCHANTS_TABLE = "CREATE TABLE " + TABLE_MERCHANTS + "("
                + MERCHANT_ID + " INTEGER," + MERCHANT_NAME + " TEXT,"
                + MERCHANT_LOGO + " TEXT" + ")";
        db.execSQL(CREATE_MERCHANTS_TABLE);

    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private HashMap<Integer,Coupon> coupons = new HashMap<>();


    public Coupon getCoupon(int id) throws Exception {

        Merchant merchant;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COUPONS, new String[] { COUPON_ID,
                        COUPON_TITLE, COUPON_CODE, COUPON_URL, MERCHANT_ID }, COUPON_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Coupon coupon = new Coupon(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),getMerchant(cursor.getInt(4)));

        return coupon;
    }

    public Merchant getMerchant(int id) throws Exception {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MERCHANTS, new String[] { MERCHANT_ID,
                        MERCHANT_NAME, MERCHANT_LOGO }, MERCHANT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Merchant merchant = new Merchant(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return merchant;
    }

    public void addMerchant(Merchant merchant) throws Exception
    {
        if(merchant == null)
        {
            throw new Exception("Merchant null");
        }
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MERCHANT_ID, merchant.getId());
        values.put(MERCHANT_NAME, merchant.getName());
        values.put(MERCHANT_LOGO, merchant.getLogo());

        db.insert(TABLE_MERCHANTS, null, values);
        db.close();;
    }

    public boolean hasCoupon(Coupon coupon){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_COUPONS + " WHERE " + COUPON_ID + " = " + coupon.getId(), null);
        if(cursor != null)
            cursor.moveToFirst();

        return (cursor.getInt(0) != 0);
    }

    public void addCoupon(Coupon coupon) throws ExistingCouponException, NullPointerException
    {
        if(coupon == null)
        {
            throw new NullPointerException("Coupon null");
        }
        if(hasCoupon(coupon))
        {
            throw new ExistingCouponException();
        }


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUPON_ID, coupon.getId());
        values.put(COUPON_TITLE, coupon.getTitle());
        values.put(COUPON_CODE, coupon.getCode());
        values.put(COUPON_URL, coupon.getAffiliationUrl());
        values.put(MERCHANT_ID, coupon.getMerchant().getId());

        db.insert(TABLE_COUPONS, null, values);
        db.close();
    }

    public Collection<Coupon> getCoupons() throws Exception {
        HashMap<Integer,Coupon> coupons = new HashMap<>();

        String selectQuery = "SELECT  * FROM " + TABLE_COUPONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Coupon coupon = new Coupon(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),getMerchant(cursor.getInt(4)));

                coupons.put(coupon.getId(), coupon);
            } while (cursor.moveToNext());
        }

        return coupons.values();
    }

    public void deleteCoupon(Coupon coupon){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COUPONS, COUPON_ID + "= ?", new String[]{String.valueOf(coupon.getId())});
        db.close();
    }

}
