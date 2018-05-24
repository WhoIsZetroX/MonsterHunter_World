package com.example.proyectodani2.lala;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proyectodani2.R;
import com.example.proyectodani2.others_things.SigninActivity;
import com.example.proyectodani2.start_menu.MainActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
//import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
//import com.google.firebase.database.ValueEventListener;


public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mReference;
    ImageView imageMonster;
    LinearLayout linearLayout;
    TextView monsterName, monsterDesc;
    ImageView monsterImage;
    MediaPlayer mp;// = new MediaPlayer();
    EditText editText;
    Button button;
    String key="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mReference = FirebaseDatabase.getInstance().getReference();
        //mReference.child("chat").child("data").child("looool").setValue("yey");//FirebaseAuth.getInstance().getCurrentUser());

        recyclerView = findViewById(R.id.reciclerView_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        editText = findViewById(R.id.textChat);
        button = findViewById(R.id.buttonChat);

        loadChat();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    transaction();
                }finally {
                    String lala = String.valueOf(editText.getText());
                    String user = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
                    Chat chat = new Chat(lala, user + "", key+"");
                    System.out.println("HAHAHA " + key + "\n" +
                            FirebaseAuth.getInstance().getCurrentUser() + "\n" +
                            FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);
                    mReference.child("chat").child("-" + key).setValue(chat);//FirebaseAuth.getInstance().getCurrentUser());
                    //mReference.child("chat").child(key).child("text").setValue(lala);
                }
            }
        });



    }

    void transaction(){
        mReference.child("num").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                String post = mutableData.getValue(String.class);
                if (post == null) {
                    return Transaction.success(mutableData);
                }
                    key = post;
                    int postL = Integer.parseInt(post) + 1;
                    post = String.valueOf(postL);

                mutableData.setValue(post);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {}
        });
    }

    void loadChat() {

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Chat>()
                .setQuery(mReference.child("chat").limitToFirst(10), Chat.class)
                .setLifecycleOwner(this)
                .build();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Chat, ChatViewHolder>(options) {
            @Override
            public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new ChatViewHolder(inflater.inflate(R.layout.item_chat, viewGroup, false));
            }



            @Override
            protected void onBindViewHolder(final ChatViewHolder viewHolder, final int position, final Chat music) {
                System.out.println("LALALA  " +music.text + " - " + music.user );

                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
                String cap = music.user.substring(0, 1);
                System.out.println("OOOH"+cap);
                viewHolder.tvContent.setTextColor(Color.parseColor(setUserColor(cap)));
                viewHolder.tvContent.setText(music.user);
                viewHolder.tvContent2.setText(music.text);

                viewHolder.itemView.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                System.out.println( " LALALA esto es imposibleS");
                            }
                        });
            }

        };

        recyclerView.setAdapter(adapter);

            /*mReference = FirebaseDatabase.getInstance().getReference();

            FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                    .setQuery(mReference.child("music"), String.class)
                    .setLifecycleOwner(this)
                    .build();

            monsterMusicAdapter = new MonsterMusicAdapter(getActivity(), options);
            recyclerView.setAdapter(monsterMusicAdapter);

            monsterMusicAdapter.startListening();*/
    }

    String setUserColor(String character){
        String color;
        switch (character){
            case "a":
                color="#ff0000";
                break;
            case "b":
                color="#0066ff";
                break;
            case "c":
                color="#ffcc00";
                break;
            case "d":
                color="#cc6600";
                break;
            case "e":
                color="#cc0066";
                break;
            case "f":
                color="#6600ff";
                break;
            case "g":
                color="#009999";
                break;
            case "h":
                color="#006600";
                break;
            case "i":
                color="#999966";
                break;
            case "j":
                color="#6666ff";
                break;
            case "k":
                color="#00cc99";
                break;
            case "l":
                color="#ff6666";
                break;
            case "m":
                color="#669999";
                break;
            case "n":
                color="#993333";
                break;
            case "ñ":
                color="#660066";
                break;
            case "o":
                color="#000066";
                break;
            case "p":
                color="#003366";
                break;
            case "q":
                color="#003300";
                break;
            case "r":
                color="#663300";
                break;
            case "s":
                color="#666699";
                break;
            case "t":
                color="#ff6600";
                break;
            case "u":
                color="#00ffcc";
                break;
            case "v":
                color="#993300";
                break;
            case "w":
                color="#666633";
                break;
            case "x":
                color="#339966";
                break;
            case "y":
                color="#003399";
                break;
            case "z":
                color="#993366";
                break;
                default:
                    color="#000000";
        }
        return color;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
