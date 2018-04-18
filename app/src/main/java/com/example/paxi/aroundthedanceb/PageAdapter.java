package com.example.paxi.aroundthedanceb;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter
{
    int NumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.NumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                TabFragmentEvents tabFragmentEvents = new TabFragmentEvents();
                return tabFragmentEvents;
            case 1:
                TabFragmentCalendar tabFragmentCalendar = new TabFragmentCalendar();
                return tabFragmentCalendar;
            case 2:
                TabFragmentMaps tabFragmentMaps = new TabFragmentMaps();
                return tabFragmentMaps;
            case 3:
                TabFragmentProfile tabFragmentProfile = new TabFragmentProfile();
                return tabFragmentProfile;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return NumOfTabs;
    }
}
