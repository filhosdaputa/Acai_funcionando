package com.example.verdemusset.acai_9;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends ActionBarActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().setText("Mensajes").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Programas de apoyo").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Derechos y deberes").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Solicitando Refugio").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Preguntas frecuentes").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Hacer pregunta").setTabListener(this);
        actionBar.addTab(tab);


    }


    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int arg0) {
            switch (arg0) {
                case 0:
                    return new Fragment_Mensajes();
                case 1:
                    return new Fragment_PApoyo();
                case 2:
                    return new Fragment_DyD();
                case 3:
                    return new Fragment_SolRef();
                case 4:
                    return new Fragment_PFrecuentes();
                case 5:
                    return new Fragment_HPregunta();
                default:
                    return null;
            }
        }
        public int getCount() {
            return 3;
        }
    }

    //implements on pager selected
    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        getSupportActionBar().setSelectedNavigationItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    //implements tab listener
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
