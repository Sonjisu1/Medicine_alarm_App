package com.example.medicine_alarm;

import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;

public class TabFragment extends Fragment {

    //Tablayout 구현하는 큰 틀
    //그 안에서 Frag1,Frag2 가 replace됨
    private FragmentPagerAdapter fragmentPagerAdapter;
    public ListViewItem MediName;

    //MainActivity에서 받아온 Item 를 받아옴
    public  static TabFragment neInstance(){
        TabFragment tabFragment = new TabFragment();
        /*
        Bundle args = new Bundle();
        args.putParcelable("list",item);//item 은 ListviewItem형태

     //  args.putParcelableArrayList("list",(ArrayList<? extends Parcelable>) list);
        tabFragment.setArguments(args);*/
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  MediName = getArguments().getParcelable("list"); //전달된 데이터 받기
       // MediName = getArguments().getString("name");
    }

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

    public  class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Frag1.newInstance();  // ListViewItem 객체를 Frag1 으로 전달
                case 1:
                    return Frag2.newInstance();
                case 2:
                    return Total.newInstance();

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
