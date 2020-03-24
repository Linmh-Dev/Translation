package com.example.myokhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.myokhttp.MyActivity.getMcontext;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText editText;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        myViewModel.getLiveData().observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                MutableLiveData<String[]> liveData = myViewModel.getLiveData();
                textView.setText(liveData.getValue()[1]);
            }
        });

        Log.d("lim", "onCreate: ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Myhttp myhttp = new Myhttp();
                        myhttp.execute(String.valueOf(editText.getText()));
                        //  Log.d("lin", "click"+Myhttp.content);
                    }
            }).start();
        }
    });

}

}
