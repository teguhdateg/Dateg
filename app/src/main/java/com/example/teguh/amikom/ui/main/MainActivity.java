package com.example.teguh.amikom.ui.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teguh.amikom.R;
import com.example.teguh.amikom.adapter.DategAdapter;
import com.example.teguh.amikom.model.Todos;
import com.example.teguh.amikom.preferences.UserPreferences;
import com.example.teguh.amikom.ui.login.LoginActivity;
import com.example.teguh.amikom.ui.tambah.TambahTodosActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ArrayList<Todos> listTodos;
    private DategAdapter dategAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String token;
    private MainPresenter mainPresenter;
    UserPreferences userPreferences;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    FloatingActionButton floating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_);



//Settingan Navbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        builder = new AlertDialog.Builder(MainActivity.this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opn_drawer, R.string.cls_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

//Settingan menu
        mainPresenter = new MainPresenter(this);
        UserPreferences userPreferences = new UserPreferences(getBaseContext());
        token = userPreferences.getKeyToken();
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        listTodos = new ArrayList<>();

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        floating = (FloatingActionButton) findViewById(R.id.floating);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tambah:
                Intent intent = new Intent(this, TambahTodosActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSuccess(ArrayList<Todos> list) {
        listTodos.clear();
        listTodos.addAll(list);
        dategAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadData() {
        mainPresenter.getTodos(token);
        dategAdapter = new DategAdapter(getBaseContext());
        dategAdapter.setTodos(listTodos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dategAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        {
            switch (id) {
                case R.id.biodata_id:
                    Toast.makeText(getApplicationContext(), "Inbox", Toast.LENGTH_LONG).show();
                    break;
                case R.id.pendidikan_id:
                    Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_LONG).show();
                    break;
                case R.id.keahlian_id:
                    Toast.makeText(getApplicationContext(), "Send", Toast.LENGTH_LONG).show();
                    break;
                case R.id.kontak_id:
                    Toast.makeText(getApplicationContext(), "Draft", Toast.LENGTH_LONG).show();
                    break;
                case R.id.alamat_id:
                    Toast.makeText(getApplicationContext(), "AllMail", Toast.LENGTH_LONG).show();
                    break;
                case R.id.keluar: {
                    builder.setMessage("apakah anda ingin keluar?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.out.println("ya");
                            Toast.makeText(getApplicationContext(), "Ya", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.out.println("Tidak");
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();
                    break;
                }
            }
        }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

    }

}



