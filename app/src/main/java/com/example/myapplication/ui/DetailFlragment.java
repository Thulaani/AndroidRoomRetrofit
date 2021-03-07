package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.model.Hero;

public class DetailFlragment extends Fragment {


    private Hero hero;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_flragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            DetailFlragmentArgs args = DetailFlragmentArgs.fromBundle(getArguments());

            hero = args.getHero();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getActivity(), hero.getTitle() + " \n" + "Test", Toast.LENGTH_LONG).show();
    }
}