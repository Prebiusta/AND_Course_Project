package com.example.courseprojectplanetbuilder.Fragments.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseprojectplanetbuilder.Fragments.Adapter.AllPlanetsAdapter;
import com.example.courseprojectplanetbuilder.Fragments.ViewModel.AllPlanetsViewModel;
import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.R;

import java.util.ArrayList;

public class AllPlanetsFragment extends Fragment implements AllPlanetsAdapter.OnListItemClickListener {

    private static final String TAG = "AllPlanetsFragment";
    private AllPlanetsViewModel mViewModel;

    private View root;

    private RecyclerView allPlanetsRecyclerView;
    private AllPlanetsAdapter allPlanetsAdapter;

    public static AllPlanetsFragment newInstance() {
        return new AllPlanetsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.all_planets_fragment, container, false);
        allPlanetsRecyclerView = root.findViewById(R.id.all_planets_recycler_view);

        initRecyclerView();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllPlanetsViewModel.class);

        LiveData<ArrayList<Planet>> allPlanets = mViewModel.getAllPlanetsLiveData();
        allPlanets.observe(getActivity(), new Observer<ArrayList<Planet>>() {
            @Override
            public void onChanged(ArrayList<Planet> planets) {
                ArrayList<Planet> notCompletedPlanets = mViewModel.getNotCompletedPlanets(planets);
                allPlanetsAdapter.setAllPlanets(notCompletedPlanets);
                allPlanetsAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onButtonClicked(Planet planet) {
        mViewModel.setCurrentPlanet(planet);
    }

    private void initRecyclerView() {

        allPlanetsRecyclerView.hasFixedSize();
        allPlanetsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        allPlanetsAdapter = new AllPlanetsAdapter(this);
        allPlanetsRecyclerView.setAdapter(allPlanetsAdapter);
    }
}
