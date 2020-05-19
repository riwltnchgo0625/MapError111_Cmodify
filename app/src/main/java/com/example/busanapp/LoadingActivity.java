package com.example.busanapp;
// 편의시설 네비 드로워 어플

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;

import com.example.busanapp.Adapter.ViewPagerAdapter;
import com.example.busanapp.Common.Common;
import com.example.busanapp.calendar.CalendarFragment;

import com.example.busanapp.home.TodayWeatherFragment;
import com.example.busanapp.ui.home.BusFragment;
import com.example.busanapp.ui.home.DisabledFragment;
import com.example.busanapp.ui.home.FindFoodFragment;
import com.example.busanapp.ui.home.FindHospitalFragment;
import com.example.busanapp.ui.home.FoodFragment;
import com.example.busanapp.home.HomeFragment;
import com.example.busanapp.ui.home.HosMapFragment;
import com.example.busanapp.ui.home.HospitalFragment;
import com.example.busanapp.ui.home.ParkingFragment;
import com.example.busanapp.ui.home.PublicartFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;


public class LoadingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //HomeFragment fragment1;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ViewPager viewPager;

    private CoordinatorLayout coordinatorLayout;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if(multiplePermissionsReport.areAllPermissionsGranted())
                        {
                            buildLocationRequest();
                            buildLocationCallBack();


                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(LoadingActivity.this);
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,Looper.myLooper());
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        Snackbar.make(coordinatorLayout,"Permisson Denied",Snackbar.LENGTH_LONG)
                                .show();

                    }



                }).check();

    }

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Common.current_location = locationResult.getLastLocation();

                viewPager =(ViewPager)findViewById(R.id.view_pager);
                setupViewpager(viewPager);

                //Log
                Log.d("Location",locationResult.getLastLocation().getLatitude()+"/"+locationResult.getLastLocation().getLongitude());
            }
        };
    }

    private void setupViewpager(ViewPager viewPager) {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TodayWeatherFragment.getInstance(),"Today");

        viewPager.setAdapter(adapter);

    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;

            case R.id.nav_hospital:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HospitalFragment()).commit();
                break;
            case R.id.nav_find_hospital:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FindHospitalFragment()).commit();
                break;

            case R.id.nav_parking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ParkingFragment()).commit();
                break;

            case R.id.nav_bus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BusFragment()).commit();
                break;
            case R.id.nav_disabled_person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisabledFragment()).commit();
                break;

            case R.id.nav_food:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FoodFragment()).commit();
                break;

            case R.id.nav_find_food:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FindFoodFragment()).commit();
                break;


            case R.id.nav_publicart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PublicartFragment()).commit();
                break;

            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalendarFragment()).commit();
                break;

            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HosMapFragment()).commit();
                break;
            default:
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}