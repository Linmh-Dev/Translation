package com.example.myokhttp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeFragment extends Fragment {
        EditText editText;
        Button button;
        NavController navController;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment, container, false);
        editText=inflate.findViewById(R.id.editText);
        button=inflate.findViewById(R.id.button);
        return inflate;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      // mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController= Navigation.findNavController(v);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Myhttp myhttp = new Myhttp();
                        myhttp.execute(String.valueOf(editText.getText()));
                        //  Log.d("lin", "click"+Myhttp.content);
                        navController.navigate(R.id.detailFragment);
                    }
                }).start();
            }
        });
        // TODO: Use the ViewModel
    }

}
