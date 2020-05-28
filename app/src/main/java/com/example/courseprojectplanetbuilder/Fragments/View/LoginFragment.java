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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.courseprojectplanetbuilder.R;
import com.example.courseprojectplanetbuilder.Utility.GifImageView;
import com.example.courseprojectplanetbuilder.Utility.InvalidInputException;
import com.example.courseprojectplanetbuilder.Fragments.ViewModel.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private LoginViewModel mViewModel;
    private View root;
    private Button loginButton;
    private Button newAccount;
    private TextInputLayout email;
    private TextInputLayout password;
    private TextView errorLabel;

    private FirebaseAuth mAuth;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.login_fragment, container, false);

        GifImageView gifImageView = root.findViewById(R.id.login_gif_image_view);
        email = root.findViewById(R.id.login_email_field);
        password = root.findViewById(R.id.login_password_field);
        loginButton = root.findViewById(R.id.login_login_button);
        newAccount = root.findViewById(R.id.login_new_account_button);
        errorLabel = root.findViewById(R.id.login_error);

        gifImageView.setGifImageResource(R.drawable.rotating_planet);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = false;

                try {
                    isValid = mViewModel.isValidEmail(email.getEditText().getText().toString())
                            && mViewModel.isValidPassword(password.getEditText().getText().toString());
                } catch (InvalidInputException e) {
                    errorLabel.setText(e.getMessage());
                }

                if (isValid) {
                    // Set error label to null just in case there was some error before
                    errorLabel.setText("");

                    // Create new account
                    signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString());
                }
                hideKeyboard();
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceViewWithBackStack(NewAccountFragment.newInstance());
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signInWithEmailAndPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Account identifier does not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null){

        } else {
            AppLayout appLayout = AppLayout.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_layout, appLayout)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    private void replaceViewWithBackStack(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("tag")
                .commit();
    }
}
