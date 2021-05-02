package com.zeki.football_fixture.ui.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.ui.view.CurrentWeekFragment;
import com.zeki.football_fixture.ui.view.LastWeekFragment;
import com.zeki.football_fixture.ui.view.NextWeekFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.last_week, R.string.this_week, R.string.next_week};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
         Fragment fragment = null;
         switch (position){
             case 0:
                 fragment = new LastWeekFragment();
                 break;
             case 1:
                 fragment = new CurrentWeekFragment();
                 break;
             case 2:
                 fragment = new NextWeekFragment();
                 break;
         }
         return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}