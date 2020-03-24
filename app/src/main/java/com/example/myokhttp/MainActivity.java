package com.example.myokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);


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
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (Myhttp.translationtext.length!=0&&Myhttp.translationtext!=null) {

                            String str = Myhttp.translationtext[1];
                            textView.setText(str);
                        }
                    }
                }).start();
            }
        });

    }

}
