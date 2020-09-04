package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    @NonNull
    int mNoOfTabs;
    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs =NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
       switch (position)
       {
           case 0:
               LearningFragment tab1 = new LearningFragment();
               return tab1;
           case 1:
               SkillFragment tab2 = new SkillFragment();
               return tab2;
           default:
                return null;
       }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
