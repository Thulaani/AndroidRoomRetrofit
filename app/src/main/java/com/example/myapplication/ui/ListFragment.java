package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.HeroAdapter.HeroAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.Hero;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.RetrofitService;
import com.example.myapplication.room.AppDatabase;
import com.example.myapplication.room.Dao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    private ListFragmentDirections.ActionListFragmentToDetailFlragment action;
    private HeroAdapter heroAdapter;
    private ArrayList<Hero> arrayList = new ArrayList<>();
    private ArrayList<Hero> arrayList1 = new ArrayList<>();
    public RecyclerView recyclerView;
    private RetrofitClient retrofitClient;
    private ProgressBar progressBar;
    private TextView err;
    private AppDatabase appDatabase;
    private Dao dao;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnInit(view);

        OnCallMethods();
    }

    private void OnCallMethods() {
        OnCreateUsers();
        OnGetUsersFromDB();
    }

    void OnInit(View view){
        appDatabase = AppDatabase.getInstance(getActivity());
        dao = appDatabase.getDao();

        navController = Navigation.findNavController(view);
        err = view.findViewById(R.id.err);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rl);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        retrofitClient = RetrofitService.getInstance();
        heroAdapter = new HeroAdapter(getActivity(), navController, action);

    }

    private void OnCreateUsers() {
        Call<List<Hero>> call = retrofitClient.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                progressBar.setVisibility(View.GONE);
                dao.OnDelete();

                if(response.isSuccessful()){

                    arrayList= (ArrayList<Hero>) response.body();
                    dao.OnCreateHero(arrayList);

                    for(Hero item: response.body()){

                    }

                }


            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }

    void OnGetUsersFromDB(){
        arrayList1 = (ArrayList<Hero>) dao.OnGetHeroes();

        Log.d("DFD", String.valueOf(arrayList1.size()));

        if(arrayList1.size() > 0){
            heroAdapter.submitList(arrayList1);

            err.setVisibility(View.GONE);
        } else{
            err.setVisibility(View.VISIBLE);
        }

        recyclerView.setAdapter(heroAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);

    }
}