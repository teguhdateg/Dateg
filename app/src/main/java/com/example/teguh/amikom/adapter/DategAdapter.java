package com.example.teguh.amikom.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.teguh.amikom.R;
import com.example.teguh.amikom.model.Todos;
import com.example.teguh.amikom.ui.detailtodos.DetailTodosActivity;

import java.util.ArrayList;


public class DategAdapter extends RecyclerView.Adapter<DategAdapter.ViewHolder> {
    private ArrayList<Todos> listTodos;
    private Context context;

    public DategAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Todos> getTodos() {
        return listTodos;
    }

    public void setTodos(ArrayList<Todos> listTodos) {
        this.listTodos = listTodos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Todos todos = getTodos().get(position);

        holder.tvText.setText(todos.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTodosActivity.class);
                intent.putExtra("id", todos.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTodos().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvText = itemView.findViewById(R.id.tv_text);
        }
    }
}
