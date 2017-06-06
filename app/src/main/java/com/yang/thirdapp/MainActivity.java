package com.yang.thirdapp;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yang.thirdapp.db.UserContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_00)
    TextView btn00;
    @BindView(R.id.btn_01)
    TextView btn01;
    @BindView(R.id.btn_02)
    TextView btn02;
    @BindView(R.id.btn_03)
    TextView btn03;
    @BindView(R.id.btn_04)
    TextView btn04;
    @BindView(R.id.btn_05)
    TextView btn05;
    @BindView(R.id.btn_06)
    TextView btn06;
    @BindView(R.id.btn_07)
    TextView btn07;
    @BindView(R.id.btn_08)
    TextView btn08;
    @BindView(R.id.btn_09)
    TextView btn09;
    @BindView(R.id.btn_10)
    TextView btn10;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.btn_00, R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04, R.id.btn_05, R.id.btn_06, R.id.btn_07, R.id.btn_08, R.id.btn_09, R.id.btn_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_00:
                Log.d(TAG, "onViewClicked: ");
                try {
//                    Uri uri = UserContract.CONTENT_URI;
                    Uri uri = UserContract.Users.CONTENT_URI;
                    uri = ContentUris.withAppendedId(uri, 2);
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    Log.d(TAG, "onViewClicked: cursor=" + cursor);
                    if (cursor != null && cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            String username = cursor.getString(cursor.getColumnIndex(UserContract.Users.USER_NAME));
                            Log.d(TAG, "onViewClicked: username=" + username);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "onViewClicked: e=" + e.getMessage());
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                break;
            case R.id.btn_01:
                break;
            case R.id.btn_02:
                break;
            case R.id.btn_03:
                break;
            case R.id.btn_04:
                break;
            case R.id.btn_05:
                break;
            case R.id.btn_06:
                break;
            case R.id.btn_07:
                break;
            case R.id.btn_08:
                break;
            case R.id.btn_09:
                break;
            case R.id.btn_10:
                break;
        }
    }
}
