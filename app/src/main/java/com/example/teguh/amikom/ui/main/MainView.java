package com.example.teguh.amikom.ui.main;


import com.example.teguh.amikom.base.BaseView;
import com.example.teguh.amikom.model.Todos;

import java.util.ArrayList;

public interface MainView extends BaseView {
    void onSuccess(ArrayList<Todos> list);
    void onError(String error);
}
