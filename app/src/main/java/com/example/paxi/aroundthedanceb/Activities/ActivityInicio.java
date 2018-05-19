package com.example.paxi.aroundthedanceb.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.Fragments.TabFragmentAdapterPager;

public class ActivityInicio extends AppCompatActivity
{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TabLayout tabs;


    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        recogerExtras();

        //region MenuLateral
        //Action Bar
        /*drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_gallery);
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        //Menu
        /*NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                Toast.makeText(ActivityInicio.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
        //endregion

        //region Tabs

        tabs = (TabLayout) findViewById(R.id.tablayout);
        tabs.addTab(tabs.newTab().setText("EVENTS"));
        tabs.addTab(tabs.newTab().setText("CALENDAR"));
        tabs.addTab(tabs.newTab().setText("MAPS"));
        tabs.addTab(tabs.newTab().setText("PROFILE"));

        final ViewPager mviewPager = (ViewPager) findViewById(R.id.viewpager);
        final TabFragmentAdapterPager adapter = new TabFragmentAdapterPager(getSupportFragmentManager(), tabs.getTabCount());
        mviewPager.setAdapter(adapter);
        mviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                mviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        //endregion

        //Oculta el teclado al ejecutar
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void recogerExtras()
    {
        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            String email = bundle.getString(ActivityLogin.EXTRA_EMAIL);
            System.out.println("aqui: " + email);
        }
    }



    //region Menu
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    //endregion
}
