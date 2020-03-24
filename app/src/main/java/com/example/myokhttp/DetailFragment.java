package com.example.myokhttp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private MyViewModel mViewModel;
    TextView textView;
    Button button;
    NavController navController;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void onResume() {
        textView.setHint("正在翻译...");
        super.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        textView=view.findViewById(R.id.textView2);
        button=view.findViewById(R.id.button2);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mViewModel=new ViewModelProvider(getActivity()).get(MyViewModel.class);
        mViewModel.getLiveData().observe(getActivity(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                MutableLiveData<String[]> liveData = mViewModel.getLiveData();
                textView.setText(liveData.getValue()[1]);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_detailFragment_to_homeFragment);
            }
        });
        // TODO: Use the ViewModel
    }

}
