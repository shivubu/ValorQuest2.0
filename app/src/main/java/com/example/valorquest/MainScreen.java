package com.example.valorquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainScreen extends AppCompatActivity {
    ImageView screen;
    YouTubePlayerView view1 ;
    ImageButton ib1,ib2,ib3;
    DatabaseReference mDatabase;
    String videoId;
    TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mDatabase=FirebaseDatabase.getInstance().getReference();
        view1= findViewById(R.id.youtube_player_view);
        mDatabase.child("Youtube").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                videoId=snapshot.getValue().toString();
                view1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.cueVideo(videoId, 0);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        t1=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView4);
        t3=findViewById(R.id.textView5);
        t4=findViewById(R.id.textView6);
        t5=findViewById(R.id.textView7);
        screen= findViewById(R.id.imageView4);
        ib1= findViewById(R.id.imageButton);
        ib1.setImageResource(R.drawable.l1);
        ib2= findViewById(R.id.imageButton2);
        ib2.setImageResource(R.drawable.aur1);
        ib3= findViewById(R.id.imageButton3);
        ib3.setImageResource(R.drawable.sbr1);
        Animation fadein= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        screen.startAnimation(fadein);
        view1.startAnimation(fadein);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib1.setImageResource(R.drawable.l2);
                Intent i = new Intent(MainScreen.this, Transition.class);
                startActivity(i);
                finish();
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib2.setImageResource(R.drawable.aur2);
                Intent i = new Intent(MainScreen.this, AboutUs.class);
                startActivity(i);
                finish();
            }
        });
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib3.setImageResource(R.drawable.sbr2);
                Intent i = new Intent(MainScreen.this, ScoreBoard.class);
                startActivity(i);
                finish();
            }
        });
        mDatabase.child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String event1=snapshot.child("Event1").getValue().toString();
                String event2=snapshot.child("Event2").getValue().toString();
                String event3=snapshot.child("Event3").getValue().toString();
                String event4=snapshot.child("Event4").getValue().toString();
                String time=snapshot.child("Time").getValue().toString();
                t1.setText(event1);
                t2.setText(event2);
                t3.setText(event3);
                t4.setText(event4);
                t5.setText(time);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

}