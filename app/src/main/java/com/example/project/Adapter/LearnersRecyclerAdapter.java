package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Model.Learners;
import com.example.project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearnersRecyclerAdapter extends  RecyclerView.Adapter<LearnersRecyclerAdapter.ViewHolder>{
    @NonNull
    Context mContext;
    List<Learners> mLearnersList;


    public LearnersRecyclerAdapter(@NonNull Context pContext, List<Learners> pLearnersList) {
        this.mContext = pContext;
        this.mLearnersList = pLearnersList;

    }




    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.learners_item, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Learners item = mLearnersList.get(position);
        holder.mTextLearners.setText(item.getName());
        Picasso.get().load(item.getBadgeUrl()).into(holder.mLearnersBadge);
        holder.mTextInfo.setText(mContext.getString(R.string.learner_info, item.getHours(), item.getCountry()));
//       Picasso.get().load(mLearnersList.get(position).getBadgeUrl()).into(holder.mLearnersBadge);

    }

    @Override
    public int getItemCount() {
        return mLearnersList == null ? 0 : mLearnersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         TextView mTextLearners;
         TextView mTextInfo;
         ImageView mLearnersBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextLearners = itemView.findViewById(R.id.learners_name);
            mTextInfo = itemView.findViewById(R.id.hour);
            mLearnersBadge = itemView.findViewById(R.id.learnersBadge);

        }
    }
}
