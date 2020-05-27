package com.example.courseprojectplanetbuilder.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.R;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;
import com.example.courseprojectplanetbuilder.ViewModel.NewPlanetViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class NewPlanetFragment extends Fragment {

    private NewPlanetViewModel mViewModel;

    private TextInputLayout nameInputLayout;
    private TextInputLayout sizeInputLayout;
    private TextView errorLabel;
    private Button createPlanetButton;


    public static NewPlanetFragment newInstance() {
        return new NewPlanetFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.new_planet_fragment, container, false);

        nameInputLayout = root.findViewById(R.id.new_planet_name);
        sizeInputLayout = root.findViewById(R.id.new_planet_size);
        errorLabel = root.findViewById(R.id.new_planet_error_label);
        createPlanetButton = root.findViewById(R.id.new_planet_create_button);
        createPlanetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = false;

                String planetName = nameInputLayout.getEditText().getText().toString();
                String planetSize = sizeInputLayout.getEditText().getText().toString();

                try {
                    isValid = mViewModel.isValidName(planetName)
                            && mViewModel.isValidSize(planetSize);
                } catch (InvalidInputException e){
                    errorLabel.setText(e.getMessage());
                }

                if (isValid){
                    errorLabel.setText("");
                    mViewModel.createPlanet(planetName, planetSize);
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewPlanetViewModel.class);
    }

}
