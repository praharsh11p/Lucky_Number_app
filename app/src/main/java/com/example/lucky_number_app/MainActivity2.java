package com.example.lucky_number_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView txt2, txt3;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        btn2 = findViewById(R.id.btn2);
        Intent i = getIntent();
        String userName = i.getStringExtra("name");
        int random_num = generateRandomNumber();
        txt3.setText("" + random_num);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, random_num);

            }
        });
    }
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public void shareData(String username, int randomNum) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "Their lucky number is: " + randomNum);
        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}