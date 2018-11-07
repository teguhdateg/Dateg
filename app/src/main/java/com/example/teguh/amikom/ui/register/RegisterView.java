package com.example.teguh.amikom.ui.register;

import com.example.teguh.amikom.base.BaseView;

public interface RegisterView extends BaseView {
    void onSuccess(String id, String email, String auth);
    void onError(String error);
}
