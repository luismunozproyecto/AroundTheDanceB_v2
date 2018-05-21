package com.example.paxi.aroundthedanceb.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentEvents;
import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentMaps;
import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentProfile;
import com.example.paxi.aroundthedanceb.R;


public class ActivityInicioTabsDown extends FragmentActivity
{
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    TabFragmentEvents tabFragmentEvents;
    TabFragmentMaps tabFragmentMaps;
    TabFragmentProfile tabFragmentProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tabsdown);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout_bottom);

        tabFragmentEvents = new TabFragmentEvents();
        tabFragmentMaps = new TabFragmentMaps();
        tabFragmentProfile = new TabFragmentProfile();

        setFragment(tabFragmentEvents);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {

                switch (item.getItemId())
                {
                    case R.id.tab_bottom_events:
                        setFragment(tabFragmentEvents);
                        return true;

                    case R.id.tab_bottom_map:
                        setFragment(tabFragmentMaps);
                        return true;

                    case R.id.tab_bottom_profile:
                        setFragment(tabFragmentProfile);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    public void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_bottom, fragment);
        fragmentTransaction.commit();
    }
}
