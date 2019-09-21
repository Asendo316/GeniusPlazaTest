package com.cordiscorp.geniustest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.cordiscorp.geniustest.R;
import com.cordiscorp.geniustest.connectvity.response.Datum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 */
public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    List<Datum> data;

    public UserRecyclerAdapter(Context mContext, List<Datum> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @NonNull
    @Override
    public UserRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.MyViewHolder holder, int position) {
        Datum userData = data.get(position);
        holder.name.setText(userData.getFirstName() + "\t"+userData.getLastName());
        Glide.with(mContext)
                .load(""+userData.getAvatar())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.userAvatar);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected @BindView(R.id.name)TextView name;
        protected @BindView(R.id.userAvatar) CircleImageView userAvatar;

        public MyViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
