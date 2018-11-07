package com.example.teguh.amikom.ui.main;

import android.support.annotation.NonNull;


import com.example.teguh.amikom.api.APIConfig;
import com.example.teguh.amikom.api.APIInterface;
import com.example.teguh.amikom.api.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainPresenter {
    private MainView mainView;

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void getTodos(String token) {
        mainView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        Call<APIResponse> apiResponseCall = apiInterface.todos(token);
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mainView.onSuccess(response.body().getListTodos());
                    }
                }
                mainView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                mainView.onError(t.getMessage());
                mainView.hideLoading();
            }
        });
    }
}
