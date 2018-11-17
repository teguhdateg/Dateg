package com.example.teguh.amikom.ui.tambah;

import android.support.annotation.NonNull;


import com.example.teguh.amikom.api.APIConfig;
import com.example.teguh.amikom.api.APIInterface;
import com.example.teguh.amikom.api.APIRequest;
import com.example.teguh.amikom.api.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTodosPresenter {
    public TambahTodosView tambahTodosView;

    TambahTodosPresenter(TambahTodosView tambahTodosView) {
        this.tambahTodosView = tambahTodosView;
    }

    void tambahTodos(String token, String text) {
        tambahTodosView.showLoading();

        APIInterface apiInterface = APIConfig.getConfig();
        APIRequest apiRequest = new APIRequest();
        apiRequest.setText(text);
        Call<APIResponse> apiResponseCall = apiInterface.addTodos(token, apiRequest);
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tambahTodosView.onSuccess(response.message());
                    }
                }
                tambahTodosView.hideLoading();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                tambahTodosView.onError(t.getMessage());
                tambahTodosView.hideLoading();
            }
        });
    }
}
