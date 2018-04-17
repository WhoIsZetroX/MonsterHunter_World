package com.example.proyectodani2.start_menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.proyectodani2.weapon.Weapon;
import com.example.proyectodani2.weapon_info.WeaponInfoPagerFragment;
import com.example.proyectodani2.weapon_list.WeaponAllFragment;
import com.example.proyectodani2.monster.Monster;
import com.example.proyectodani2.monster_info.MonsterInfoPagerFragment;
import com.example.proyectodani2.monster_list.MonsterFragment;
import com.example.proyectodani2.monster_list.MonsterListPagerFragment;
import com.example.proyectodani2.R;
import com.example.proyectodani2.others_things.SettingsActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MonsterFragment.MonsterClickedListener, WeaponAllFragment.WeaponClickedListener {

    // Popup
    private LayoutInflater layoutInflater;
    private View popupView;


    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        Fragment fragment = null;
        Class fragmentClass = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_monsters) {
            fragmentClass = MonsterListPagerFragment.class;

        } else if (id == R.id.nav_weapons) {
            fragmentClass = WeaponAllFragment.class;
            //Toast.makeText(MainActivity.this, "WIP!", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_games) {
            Toast.makeText(MainActivity.this, "WIP!", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_music) {
            Toast.makeText(MainActivity.this, "WIP!", Toast.LENGTH_LONG).show();
        }else if (id == R.id.nav_help) {
            abrirPopUp();
        }else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
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

    @Override
    public void onMonsterClicked(Monster monster) {

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new MonsterInfoPagerFragment()).commit();
    }
    @Override
    public void onWeaponClicked(Weapon weapon){
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new WeaponInfoPagerFragment()).commit();
    }

    void abrirPopUp(){
        layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        popupView.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(popupView, 100, 300, 1);
    }
}
