package com.maudran.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maudran.Model.Coupon;
import com.maudran.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by maudran on 06/07/2017.
 */

public class AdapterCoupons extends RecyclerView.Adapter<AdapterCoupons.CouponViewHolder> {

    private List<Coupon> coupons = new ArrayList<>();
    private RecyclerViewClickListener listener;

    AdapterCoupons(RecyclerViewClickListener listener){
        this.listener = listener;
    }


    public void addItemCoupon(Coupon coupon) {

        coupons.add(coupon);
        notifyDataSetChanged();
    }

    public void addItemsCoupons(Collection<Coupon> collection){
        coupons.addAll(collection);
        notifyDataSetChanged();
    }

    public void clearList(){
        coupons.clear();
        notifyDataSetChanged();
    }

    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_coupons, parent, false);
        return new CouponViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(CouponViewHolder holder, int position) {
            Coupon coupon = coupons.get(position);
            holder.display(coupon);
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }






    public class CouponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        private RecyclerViewClickListener mListener;
        private Coupon coupon;

        private TextView textViewTitle = null;
        private ImageView imageViewLogo = null;

        public CouponViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);

            this.textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.imageViewLogo = (ImageView) itemView.findViewById(R.id.imageViewLogo);
            context = itemView.getContext();
        }

        public void display(Coupon coupon) {

            this.coupon = coupon;

            this.textViewTitle.setText(coupon.getTitle());
            Picasso.with(context).load(coupon.getMerchant().getLogo()).into(imageViewLogo);

        }

        @Override
        public void onClick(View v) {

            if(coupon == null) {
                Toast.makeText(context,"Coupon non valide",Toast.LENGTH_SHORT).show();
                return;
            }

            mListener.onClick(coupon);

        }
    }

}
