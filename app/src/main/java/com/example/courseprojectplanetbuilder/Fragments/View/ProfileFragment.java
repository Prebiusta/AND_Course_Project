package com.example.courseprojectplanetbuilder.Fragments.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.Fragments.ViewModel.ProfileViewModel;
import com.example.courseprojectplanetbuilder.Model.LocalStorage.UserData;
import com.example.courseprojectplanetbuilder.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    private TextView totalClicksTV;
    private TextView finishedPlanetsTV;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_fragment, container, false);

        totalClicksTV = root.findViewById(R.id.profile_total_clicks);
        finishedPlanetsTV = root.findViewById(R.id.profile_finished_planets);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        mViewModel.getCurrentUserData().observe(getActivity(), new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                totalClicksTV.setText("Total Clicks: " + userData.getTotalClicks());
                finishedPlanetsTV.setText("Finished planets: " + userData.getFinishedPlanets());
            }
        });
    }

}
