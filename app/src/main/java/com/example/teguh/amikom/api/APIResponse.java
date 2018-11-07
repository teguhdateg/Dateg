package com.example.teguh.amikom.api;

import com.example.teguh.amikom.model.Todos;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIResponse {
    @SerializedName("_id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("todos")
    private ArrayList<Todos> listTodos;

    public Todos getTodos() {
        return todos;
    }

    public void setTodos(Todos todos) {
        this.todos = todos;
    }

    @SerializedName("todo")
    private Todos todos;

    public ArrayList<Todos> getListTodos() {
        return listTodos;
    }

    public void setListTodos(ArrayList<Todos> listTodos) {
        this.listTodos = listTodos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
