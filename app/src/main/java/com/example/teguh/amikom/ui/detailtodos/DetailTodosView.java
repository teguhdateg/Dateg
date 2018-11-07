package com.example.teguh.amikom.ui.detailtodos;


import com.example.teguh.amikom.base.BaseView;
import com.example.teguh.amikom.model.Todos;

interface DetailTodosView extends BaseView {
    void onSuccess(Todos todos);
    void onError(String message);
}
