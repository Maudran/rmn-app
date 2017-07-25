package com.maudran.Activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maudran.Model.Coupon;
import com.maudran.Model.Database;
import com.maudran.myapplication.R;


/**
 * Created by maudran on 10/07/2017.
 */

public class FragmentListCoupon extends Fragment {

    private RecyclerView recyclerCoupons;
    private Context context;
    private AdapterCoupons adapterCoupons;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.fragment_coupons_recycler, container, false);
        recyclerCoupons = (RecyclerView) view.findViewById(R.id.recyclerCoupons);
        return view;
    }



    @Override public void onActivityCreated(Bundle savedInstanceState) {
        context = getActivity();

        recyclerCoupons.setLayoutManager(new LinearLayoutManager(context));


        adapterCoupons = new AdapterCoupons(new RecyclerViewClickListener() {
            @Override
            public void onClick(Coupon coupon) {
                Intent appel = new Intent(context,CouponDetailActivity.class);
                appel.putExtra("couponId",coupon.getId());
                startActivity(appel);
            }
        });
        recyclerCoupons.setAdapter(adapterCoupons);

        reloadList();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterCoupons.clearList();
        reloadList();
    }

    private void reloadList(){
        try {
            adapterCoupons.addItemsCoupons(Database.getInstance(getActivity()).getCoupons());
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }
}
