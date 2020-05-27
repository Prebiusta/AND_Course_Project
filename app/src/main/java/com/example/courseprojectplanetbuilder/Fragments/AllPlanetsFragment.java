package com.example.courseprojectplanetbuilder.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.R;
import com.example.courseprojectplanetbuilder.ViewModel.AllPlanetsViewModel;

public class AllPlanetsFragment extends Fragment {

    private AllPlanetsViewModel mViewModel;

    public static AllPlanetsFragment newInstance() {
        return new AllPlanetsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_planets_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllPlanetsViewModel.class);
        // TODO: Use the ViewModel
    }

}
