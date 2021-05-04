package com.zeki.football_fixture.ui.view;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.TaskStackBuilder;
import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zeki.football_fixture.R;
import com.zeki.football_fixture.ui.adapter.SectionsPagerAdapter;
import com.zeki.football_fixture.ui.view_model.WeekCalculator;

public class BaseActivity extends AppCompatActivity {

    private static WeekCalculator t = new WeekCalculator();
    public static String [] currentWeek = t.getCurrentWeek();
    public static String [] nextWeek = t.getNextWeek();
    public static String [] lastWeek = t.getPreviousWeek();
    public static SpannableString lastWeekTitle=  new SpannableString("GEÇEN HAFTA");
    public static SpannableString currentWeekTitle=  new SpannableString("BU HAFTA");
    public static SpannableString nextWeekTitle=  new SpannableString("GELECEK HAFTA");

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch=null;

     LottieAnimationView loadingAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        loadingAnimation = (LottieAnimationView) findViewById(R.id.loading_animation);
        aSwitch = (Switch) findViewById(R.id.switchTheme);
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);

        startAnimation();

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkMood);
        } else {setTheme(R.style.BaseTheme); }


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                 }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    reset();
                 }
            }

            private void reset() {
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(intent);
                finish();
            }

        });

        loadingAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                loadingAnimation.setVisibility(View.INVISIBLE);
                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
                viewPager.setAdapter(sectionsPagerAdapter);
                tabs.setupWithViewPager(viewPager);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    public void startAnimation() {

        loadingAnimation.setAnimation(R.raw.loading);
        loadingAnimation.animate();
    }
}