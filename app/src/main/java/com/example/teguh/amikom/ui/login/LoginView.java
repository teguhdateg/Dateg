package com.example.teguh.amikom.ui.login;

import com.example.teguh.amikom.base.BaseView;

interface LoginView extends BaseView {
    void onSuccess(String id, String email, String auth);
    void onError(String error);
}
