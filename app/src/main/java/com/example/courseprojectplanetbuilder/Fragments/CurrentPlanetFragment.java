package com.example.courseprojectplanetbuilder.Fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.courseprojectplanetbuilder.ViewModel.CurrentPlanetViewModel;
import com.example.courseprojectplanetbuilder.R;

public class CurrentPlanetFragment extends Fragment {

    private CurrentPlanetViewModel mViewModel;

    public static CurrentPlanetFragment newInstance() {
        return new CurrentPlanetFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.current_planet_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CurrentPlanetViewModel.class);
        // TODO: Use the ViewModel
    }

}
