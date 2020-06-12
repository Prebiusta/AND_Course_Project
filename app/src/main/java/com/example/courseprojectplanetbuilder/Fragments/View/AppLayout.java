package com.example.courseprojectplanetbuilder.Fragments.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.Fragments.ViewModel.AppLayoutViewModel;
import com.example.courseprojectplanetbuilder.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppLayout extends Fragment {

    private AppLayoutViewModel mViewModel;

    private NavigationView navDrawer;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private AppCompatActivity activity;
    public static FirebaseUser user;

    public static AppLayout newInstance() {
        return new AppLayout();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        activity = (AppCompatActivity) getActivity();
        user = FirebaseAuth.getInstance().getCurrentUser();
        return inflater.inflate(R.layout.app_layout_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navDrawer = activity.findViewById(R.id.app_layout_navigation);
        drawerLayout = activity.findViewById(R.id.app_layout_drawer_layout);
        toolbar = activity.findViewById(R.id.top_toolbar);

        activity.setSupportActionBar(toolbar);

        // Setting for Drawer Layout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Setting up Navigation View
        setupDrawerContent(navDrawer);

        mViewModel = new ViewModelProvider(this).get(AppLayoutViewModel.class);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.nav_current_planet:
                fragmentClass = CurrentPlanetFragment.class;
                break;
            case R.id.nav_all_planets:
                fragmentClass = AllPlanetsFragment.class;
                break;
            case R.id.nav_new_planet:
                fragmentClass = NewPlanetFragment.class;
                break;
            case R.id.nav_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.nav_corona_virus:
                fragmentClass = CoronaVirusFragment.class;
                break;
            case R.id.nav_logout:
                signOut();
                fragmentClass = LoginFragment.class;
                break;
            default:
                fragmentClass = CurrentPlanetFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing existing one
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.app_layout_fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        // Highlight the selected item has been done by NavigationView
        navDrawer.setCheckedItem(item);

        // Set action bar title
        activity.setTitle(item.getTitle());

        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        mViewModel.resetLiveData();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, LoginFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
