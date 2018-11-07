package com.example.teguh.amikom.ui.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.teguh.amikom.R;
import com.example.teguh.amikom.preferences.UserPreferences;
import com.example.teguh.amikom.ui.main.MainActivity;
import com.roger.catloadinglibrary.CatLoadingView;


public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener {

    private RegisterPresenter registerPresenter;
    private EditText etEmail;
    private EditText etPassword;
    private UserPreferences userPreferences;
    private CatLoadingView catLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this);
        userPreferences = new UserPreferences(getBaseContext());
        catLoadingView = new CatLoadingView();

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onSuccess(String id, String email, String auth) {
        userPreferences.setKeyId(id);
        userPreferences.setKeyMail(email);
        userPreferences.setKeyToken(auth);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String error) {
        Log.d("onError", error);
    }

    @Override
    public void showLoading() {
        catLoadingView.show(getSupportFragmentManager(), "Mohon Menunggu");
    }

    @Override
    public void hideLoading() {
        catLoadingView.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register :
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                registerPresenter.register(email, password);
                break;
        }
    }


}
