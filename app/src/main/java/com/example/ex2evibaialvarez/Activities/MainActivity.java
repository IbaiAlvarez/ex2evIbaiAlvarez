package com.example.ex2evibaialvarez.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.ex2evibaialvarez.Fragments.Mapa_Fragment;
import com.example.ex2evibaialvarez.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Fragment Manager
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        //Se inicializa el fragmento principal
        Fragment fragment_nuevo=null;
        fragment_nuevo = new Mapa_Fragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment_nuevo);
        fragmentTransaction.commit();
    }
}