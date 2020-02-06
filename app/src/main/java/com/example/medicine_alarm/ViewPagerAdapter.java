package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Frag1.newInstance();
            case 1:
                return Frag2.newInstance();
            case 2:
                return Frag2.newInstance();

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    //탭 레이아웃에 이름을 선언
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Monday";
            case 1:
                return "Tuseday";
            case 2:
                return "Wedsday";

            default:
                return null;

        }
    }
}
