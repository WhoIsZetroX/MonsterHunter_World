package com.example.proyectodani2.start_menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.proyectodani2.R;
import com.example.proyectodani2.monster.Monster;
import com.example.proyectodani2.monster_info.MonsterInfoPagerFragment;
import com.example.proyectodani2.monster_list.MonsterFragment;
import com.example.proyectodani2.monster_list.MonsterListPagerFragment;
import com.example.proyectodani2.others_things.SettingsActivity;
import com.example.proyectodani2.weapon.Weapon;
import com.example.proyectodani2.weapon_info.WeaponInfoPagerFragment;
import com.example.proyectodani2.weapon_list.WeaponAllFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MonsterFragment.MonsterClickedListener, WeaponAllFragment.WeaponClickedListener {

    // Popup
    private LayoutInflater layoutInflater;
    private View popupView;
    private PopupWindow popupWindow;

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.setElevation(0);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        TabLayout tabLayout =  findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) tabLayout.getLayoutParams();
        layoutParams.height = 1;
        tabLayout.setLayoutParams(layoutParams);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Class fragmentClass = null;
        int id = item.getItemId();

        if (id == R.id.nav_monsters) {
            //startActivity(new Intent(this, MonstersActivity.class));
            fragmentClass = MonsterListPagerFragment.class;
        } else if (id == R.id.nav_weapons) {
            fragmentClass = WeaponAllFragment.class;
        } else if (id == R.id.nav_games) {
            Toast.makeText(MainActivity.this, "WIP!", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_music) {
            Toast.makeText(MainActivity.this, "WIP!", Toast.LENGTH_LONG).show();
        }else if (id == R.id.nav_help) {
            abrirPopUp();
        }else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        }

        if(fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public TabLayout getTabLayout(){
        return findViewById(R.id.tabLayout);
    }

    @Override
    public void onMonsterOptionClicked(Monster monster) {

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new MonsterInfoPagerFragment()).commit();
    }
    @Override
    public void onWeaponOptionClicked(Weapon weapon) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new WeaponInfoPagerFragment()).commit();
    }

    void abrirPopUp(){
        layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(popupView, 100, 300, 1);
    }
}
