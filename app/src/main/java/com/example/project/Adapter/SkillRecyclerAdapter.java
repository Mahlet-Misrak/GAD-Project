package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Model.Learners;
import com.example.project.Model.Skill;
import com.example.project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.ViewHolder> {
    @NonNull
    Context mContext;
    List<Skill> mSkillList;

    public SkillRecyclerAdapter(@NonNull Context pContext, List<Skill> pLearnersList) {
        this.mContext = pContext;
        this.mSkillList= pLearnersList;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.skill_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill item = mSkillList.get(position);
        holder.mTextSkillName.setText(item.getName());
        holder.mTextScore.setText(mContext.getString(R.string.skill_info, item.getScore(), item.getCountry()));
        Picasso.get().load(item.getBadgeUrl()).into(holder.mSkillBadge);

    }

    @Override
    public int getItemCount()
    {
        return mSkillList == null ? 0 : mSkillList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       TextView mTextSkillName;
       TextView mTextScore;
       ImageView mSkillBadge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSkillName = (TextView)itemView.findViewById(R.id.skill_name);
            mTextScore = (TextView)itemView.findViewById(R.id.score);
            mSkillBadge= itemView.findViewById(R.id.skillBadge);
        }
    }
}
