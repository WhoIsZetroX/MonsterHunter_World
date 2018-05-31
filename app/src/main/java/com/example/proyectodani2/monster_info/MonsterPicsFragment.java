package com.example.proyectodani2.monster_info;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectodani2.R;
import com.example.proyectodani2.monster_list.MonsterViewModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MonsterPicsFragment extends Fragment {
    public static final int PICK_IMAGE = 1;
    public String theMonsterKey;
    Uri filePath;
    MonsterPicsAdapter monsterPicsAdapter;
    FloatingActionButton fab;
    ImageView imageView;
    ProgressDialog pd;
    //XML;
    RecyclerView recyclerView;
    //Conectar a la base de datos
    private DatabaseReference mReference = null;

    public MonsterPicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monster_pics, container, false);
        pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading....");
        fab = view.findViewById(R.id.fab);
        imageView=view.findViewById(R.id.imageView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MonsterViewModel MonsterViewModel = ViewModelProviders.of(getActivity()).get(MonsterViewModel.class);
        MonsterViewModel.getMonsterKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String monsterKey) {
                theMonsterKey=monsterKey;
                loadMonsterPics(monsterKey);
            }
        });

        //showMonsterKey(monsterKey);

        return view;

    }

    void loadMonsterPics(final String monsterKey) {
        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(mReference.child("monsters/data").child(monsterKey).child("monsterPics"), String.class)
                .setLifecycleOwner(this)
                .build();

        monsterPicsAdapter = new MonsterPicsAdapter(getActivity(), options);
        recyclerView.setAdapter(monsterPicsAdapter);

        monsterPicsAdapter.startListening();
    }

    //Metodo para mostrar los datos del monstruo seleccionado
   /* void loadMonsterPics(final String monsterKey) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("monsters").child("data").child(monsterKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Monster monster = dataSnapshot.getValue(Monster.class);

                        System.out.println("AAAAAAAA");


                        for (Map.Entry<String, String> entry : monster.monsterPics.entrySet()) {

                            ImageView imageView = new ImageView(getContext());
                            //setting image position
                            imageView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
                            //adding view to layout
                            linearLayout.addView(imageView);
                            //setting image resource
                            Glide.with(MonsterPicsFragment.this).load(entry.getValue()).into(imageView);
                            System.out.println(entry.getKey() + " : " + entry.getValue() + " AAAAAAAAA");

                        }
                        // Así se mostraría la imagen
                        //Glide.with(MonsterPicsFragment.this).load(value).into(viewHolder.ivContent);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("tag", "getUser:onCancelled", databaseError.toException());
                    }
                });


    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);

                //Setting image to ImageView
                imageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                uploadImage();
            }
        }
    }

    void chooseImage(){
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    void uploadImage(){
        if(filePath != null) {
            pd.show();
            StorageReference mStorageRef;

            mStorageRef = FirebaseStorage.getInstance().getReference();

            int random = (int)(Math.random() * 99999) + 1;
            StorageReference childRef = mStorageRef.child("PicFragment/UploadPics/"+theMonsterKey+"/" + "_"+random);

            //uploading the image
            UploadTask uploadTask = childRef.putFile(filePath);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    String imageURL =  downloadUrl.toString();
                    int random = (int)(Math.random() * 99999) + 1;
                    FirebaseDatabase.getInstance().getReference().child("monsters/data").child(theMonsterKey).child("monsterPics").child("url"+random).setValue(imageURL);
                    Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getContext(), "Select an image", Toast.LENGTH_SHORT).show();
        }
    }

}
