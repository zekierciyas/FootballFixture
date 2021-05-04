package com.zeki.football_fixture.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.ui.view.CurrentWeekFragment;
import com.zeki.football_fixture.ui.view.LastWeekFragment;
import com.zeki.football_fixture.ui.view.NextWeekFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {



    @StringRes
    public static final int[] TAB_TITLES = new int[]{R.string.last_week, R.string.this_week, R.string.next_week};
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

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = mContext.getResources().getString(TAB_TITLES[position]);
        return title;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }


}