package com.example.rplrus06.midsemester12rpl;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class pageAdapter extends FragmentStatePagerAdapter {


        int Tabs;

        public pageAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.Tabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    OneFragment tab1 = new OneFragment();
                    return tab1;
                case 1:
                    twoFragment tab2 = new twoFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return Tabs;
        }
    }

