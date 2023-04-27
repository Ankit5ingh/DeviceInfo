package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.deviceinfo.adapters.ViewPagerAdapter;
import com.example.deviceinfo.fragments.BatteryPage;
import com.example.deviceinfo.fragments.DevicePage;
import com.example.deviceinfo.fragments.SensorPage;
import com.example.deviceinfo.fragments.SystemPage;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);

        // setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new DevicePage(), getString(R.string.device_page));
        viewPagerAdapter.add(new SystemPage(), getString(R.string.system_page));
        viewPagerAdapter.add(new BatteryPage(), getString(R.string.battery_page));
        viewPagerAdapter.add(new SensorPage(), "Sensor Page");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to set the page viewer
        // we use the setupWithViewPager().
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
