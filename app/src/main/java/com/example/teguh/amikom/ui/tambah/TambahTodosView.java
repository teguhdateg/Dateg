package com.example.teguh.amikom.ui.tambah;


import com.example.teguh.amikom.base.BaseView;

public interface TambahTodosView extends BaseView {
    void onSuccess(String message);
    void onError(String error);

    void showLoading();
}
