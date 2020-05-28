package com.example.courseprojectplanetbuilder.Fragments.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.Fragments.ViewModel.CurrentPlanetViewModel;
import com.example.courseprojectplanetbuilder.Model.Planet;
import com.example.courseprojectplanetbuilder.R;

public class CurrentPlanetFragment extends Fragment {
    private static final String TAG = "CurrentPlanetFragment";

    private CurrentPlanetViewModel mViewModel;

    private Button buildButton;
    private ProgressBar planetProgressBar;
    private TextView planetProgressText;
    private TextView planetName;

    public static CurrentPlanetFragment newInstance() {
        return new CurrentPlanetFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.current_planet_fragment, container, false);

        buildButton = root.findViewById(R.id.current_planet_build_button);
        planetProgressBar = root.findViewById(R.id.current_planet_progress_bar);
        planetProgressText = root.findViewById(R.id.current_planet_text_progress);
        planetName = root.findViewById(R.id.current_planet_name);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CurrentPlanetViewModel.class);


        mViewModel.getCurrentPlanet().observe(getActivity(), new Observer<Planet>() {
            @Override
            public void onChanged(Planet planet) {
                planetName.setText(planet.getName());
                planetProgressBar.setMax(planet.getMaxSize());
                planetProgressBar.setProgress(planet.getCurrentSize());
                String progressString = (planet.getCurrentSize() + " / " + planet.getMaxSize());
                planetProgressText.setText(progressString);

            }
        });

        buildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateCurrentPlanetProgress(1);
            }
        });
    }
}
