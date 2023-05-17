package com.example.animationa;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ObjectAnimator objectAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgView = findViewById(R.id.image);
        Button btn = findViewById(R.id.btn_animate);

        // rotate
//        objectAnimator = ObjectAnimator.ofFloat(imgView,
//                "rotation", 360);

        // walk x
//        objectAnimator = ObjectAnimator.ofFloat(imgView,
//                "x", 360);

        // walk y
        objectAnimator = ObjectAnimator.ofFloat(imgView,
                "y", 1200);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });
    }
}