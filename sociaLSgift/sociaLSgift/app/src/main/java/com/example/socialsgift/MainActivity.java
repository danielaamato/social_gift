package com.example.socialsgift;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String accessToken = getIntent().getStringExtra("accessToken");

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        viewPager.setAdapter(new ViewPagerAdapter(this, accessToken));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
        {
            switch (position) {
                case 0:
                    tab.setText("Menu");
                    break;
                case 1:
                    tab.setText("Social");
                    break;
                case 2:
                    tab.setText("Listas");
                    break;
                case 3:
                    tab.setText("Perfil");
                    break;
            }
        }).attach();
    }
}
