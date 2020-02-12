package com.example.medicine_alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TabFragment extends Fragment {

    //Tablayout 구현하는 큰 틀
    //그 안에서 Frag1,Frag2 가 replace됨
    private FragmentPagerAdapter fragmentPagerAdapter;
    public String MediName;

    public  static TabFragment neInstance(String value){
        TabFragment tabFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString("name2",value);
        tabFragment.setArguments(args);
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediName = getArguments().getString("name2");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tabfragment,container,false);

        Toast.makeText(getContext(), MediName, Toast.LENGTH_SHORT).show();

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        fragmentPagerAdapter = new ViewPagerAdapter(getChildFragmentManager()); //프레그먼트안에서 프레그먼트들을 불러올때
        TabLayout tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {



        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Frag1.newInstance("데이터 보내기");
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
}
