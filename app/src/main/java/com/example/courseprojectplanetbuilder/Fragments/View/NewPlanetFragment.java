package com.example.courseprojectplanetbuilder.Fragments.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.Fragments.ViewModel.NewPlanetViewModel;
import com.example.courseprojectplanetbuilder.R;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class NewPlanetFragment extends Fragment {
    private static final String TAG = "NewPlanetFragment";

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
                createPlanetForNameAndSize();
            }
        });

        return root;
    }

    private void createPlanetForNameAndSize() {
        boolean isValid = false;

        String planetName = nameInputLayout.getEditText().getText().toString();
        String planetSize = sizeInputLayout.getEditText().getText().toString();

        try {
            isValid = mViewModel.isValidName(planetName)
                    && mViewModel.isValidSize(planetSize);
        } catch (InvalidInputException e) {
            errorLabel.setText(e.getMessage());
        }

        if (isValid) {
            errorLabel.setText("");
            mViewModel.createPlanet(planetName, planetSize, FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            Toast.makeText(getContext(), "Planet created", Toast.LENGTH_SHORT).show();
        }
        hideKeyboard();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewPlanetViewModel.class);
    }

    private void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e(TAG, "hideKeyboard: error: ", e);
        }
    }

}
