package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentDetailFlragmentBinding;
import com.example.myapplication.model.Hero;

public class DetailFlragment extends Fragment {

private FragmentDetailFlragmentBinding binding;


    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = FragmentDetailFlragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            DetailFlragmentArgs args = DetailFlragmentArgs.fromBundle(getArguments());

            Hero hero = args.getHero();
            onDisplay(hero);
        }
    }
void onDisplay(Hero hero){
        binding.txtId.setText(String.valueOf(hero.getId()));
        binding.txtUserTitle.setText(String.valueOf(hero.getTitle()));
        binding.txtUserBody.setText(String.valueOf(hero.getBody()));
}
    @Override
    public void onResume() {
        super.onResume();

       // Toast.makeText(getActivity(), hero.getTitle() + " \n" + "Test", Toast.LENGTH_LONG).show();
    }
}