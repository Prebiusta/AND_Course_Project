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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.R;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;
import com.example.courseprojectplanetbuilder.Fragments.ViewModel.NewAccountViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class NewAccountFragment extends Fragment {
    private static final String TAG = "NewAccountFragment";

    private NewAccountViewModel mViewModel;

    private Button createAccountButton;
    private TextInputLayout usernameInputLayout;
    private TextInputLayout emailInputLayout;
    private TextInputLayout passwordInputLayout;
    private TextInputLayout confirmPasswordInputLayout;
    private TextView errorLabel;

    private FirebaseAuth mAuth;

    public static NewAccountFragment newInstance() {
        return new NewAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.new_account_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();

        // Load elements
        usernameInputLayout = root.findViewById(R.id.register_username);
        emailInputLayout = root.findViewById(R.id.register_email);
        passwordInputLayout = root.findViewById(R.id.register_password);
        confirmPasswordInputLayout = root.findViewById(R.id.register_confirm_password);
        errorLabel = root.findViewById(R.id.register_error);

        createAccountButton = root.findViewById(R.id.register_create_account_button);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = false;

                try {
                    isValid = mViewModel.isValidUsername(usernameInputLayout.getEditText().getText().toString())
                            && mViewModel.isValidEmail(emailInputLayout.getEditText().getText().toString())
                            && mViewModel.isValidPassword(passwordInputLayout.getEditText().getText().toString(), confirmPasswordInputLayout.getEditText().getText().toString());
                } catch (InvalidInputException e) {
                    errorLabel.setText(e.getMessage());
                }

                if (isValid) {
                    // Set error label to null just in case there was some error before
                    errorLabel.setText("");
                    // Create new account
                    createAccount(emailInputLayout.getEditText().getText().toString(), passwordInputLayout.getEditText().getText().toString());
                }
                hideKeyboard();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewAccountViewModel.class);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(usernameInputLayout.getEditText().getText().toString()).build();

                            user.updateProfile(profileUpdates);

                            mViewModel.createUserData(user.getUid());

                            Log.i(TAG, "createUserWithEmail:success", task.getException());
                            updateUI(AppLayout.newInstance());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    private void updateUI(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}
