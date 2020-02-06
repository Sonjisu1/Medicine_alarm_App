package com.example.medicine_alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TabFragment extends Fragment {

    //Tablayout 구현하는 큰 틀
    //그 안에서 Frag1,Frag2 가 replace됨
    private FragmentPagerAdapter fragmentPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tabfragment,container,false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        fragmentPagerAdapter = new ViewPagerAdapter(getChildFragmentManager()); //프레그먼트안에서 프레그먼트들을 불러올때
        TabLayout tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}
