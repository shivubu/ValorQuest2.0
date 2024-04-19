package com.example.valorquest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

public class ScoreBoard extends AppCompatActivity {
    DatabaseReference mref;
    TextView tn1,tn2,tn3,tn4,tn5,ts1,ts2,ts3,ts4,ts5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score_board);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tn1=findViewById(R.id.textView2);tn2=findViewById(R.id.textView3);
        tn3=findViewById(R.id.textView4);tn4=findViewById(R.id.textView5);tn5=findViewById(R.id.textView6);
        ts1=findViewById(R.id.textView7);ts2=findViewById(R.id.textView8);ts3=findViewById(R.id.textView9);
        ts4=findViewById(R.id.textView10);ts5=findViewById(R.id.textView11);
        mref= FirebaseDatabase.getInstance().getReference();
        mref.child("ScoreBoard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1=snapshot.child("tn1").getValue().toString();
                String name2=snapshot.child("tn2").getValue().toString();
                String name3=snapshot.child("tn3").getValue().toString();
                String name4=snapshot.child("tn4").getValue().toString();
                String name5=snapshot.child("tn5").getValue().toString();
                String Score1=snapshot.child("ts1").getValue().toString();
                String Score2=snapshot.child("ts2").getValue().toString();
                String Score3=snapshot.child("ts3").getValue().toString();
                String Score4=snapshot.child("ts4").getValue().toString();
                String Score5=snapshot.child("ts5").getValue().toString();
                tn1.setText(name1);tn2.setText(name2);tn3.setText(name3);tn4.setText(name4);tn5.setText(name5);
                ts1.setText(Score1);ts2.setText(Score2);ts3.setText(Score3);ts4.setText(Score4);ts5.setText(Score5);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(ScoreBoard.this, MainScreen.class);
        startActivity(i);

    }
}