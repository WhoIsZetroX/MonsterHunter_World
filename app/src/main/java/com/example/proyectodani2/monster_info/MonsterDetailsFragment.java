package com.example.proyectodani2.monster_info;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.proyectodani2.R;
import com.example.proyectodani2.monster.Monster;
import com.example.proyectodani2.monster_list.MonsterViewModel;
import com.example.proyectodani2.photo_view.PhotoviewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MonsterDetailsFragment extends Fragment {

    //XML
    TextView monsterName, monsterDesc;
    ImageView monsterImage, monsterWeakness;
    //Conectar a la base de datos
    private DatabaseReference mDatabase = null;
    ExpandableListView lv;
    private String[] groups;
    private String[][] children;
    View view;
    private int lastExpandedPosition = -1;
    Monster monster = null;

    public MonsterDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_monster_detail, container, false);

        groups = new String[] { "Details & Locations", "Combat Info", "Weaknesses"};





        monsterName = view.findViewById(R.id.monsterName);
        monsterDesc = view.findViewById(R.id.monsterDesc);
        monsterImage = view.findViewById(R.id.monsterImage);
        //monsterWeakness = view.findViewById(R.id.monsterWeakness);

        //String monsterKey="-L5PJ79zN1mmghpBW2KV";
        MonsterViewModel monsterViewModel = ViewModelProviders.of(getActivity()).get(MonsterViewModel.class);
        monsterViewModel.getMonsterKey().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String monsterKey) {
                showMonsterKey(monsterKey);
            }
        });
        //showMonsterKey(monsterKey);

        return view;

    }

    //Metodo para mostrar los datos del monstruo seleccionado
    void showMonsterKey(String monsterKey){

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("monsters").child("data").child(monsterKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        monster = dataSnapshot.getValue(Monster.class);

                        monsterName.setText(monster.name);
                        monsterDesc.setText(monster.desc);
                        Glide.with(MonsterDetailsFragment.this).load(monster.mDetailPic).into(monsterImage);
                        //Glide.with(MonsterDetailsFragment.this).load(monster.weakness).into(monsterWeakness);

                        children = new String [][] {
                            { monster.detLoc },
                            { monster.combatInfo },
                            { "" }
                        };

                        loadExpList();

                        monsterImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", monster.getmDetailPic());
                                getContext().startActivity(intent);
                            }
                        });

                        /*monsterWeakness.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", monster.getWeakness());
                                getContext().startActivity(intent);
                            }
                        });*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("tag", "getUser:onCancelled", databaseError.toException());
                    }
                });
    }

    void loadExpList(){
        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableListAdapter(groups, children));
        lv.setGroupIndicator(null);

        lv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                    if (lastExpandedPosition != -1
                            && groupPosition != lastExpandedPosition) {
                        lv.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;
            }
        });

    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] groups;
        private String[][] children;
        /*private int[][] cat_images = new int[][] {
                {R.drawable.bath_body_sales, R.drawable.bath_body_sales, R.drawable.bath_body_sales},
                {R.drawable.beauty_sales, R.drawable.beauty_sales, R.drawable.beauty_sales},
                {R.drawable.hair_products_sales, R.drawable.hair_products_sales, R.drawable.hair_products_sales}
                };*/

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.groups = groups;
            this.children = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListItem);
                holder.image=(ImageView) convertView.findViewById(R.id.img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String name= getGroup(groupPosition).toString();

            System.out.println("LAAAAA "+name);

            if(name.equals("Weaknesses")){
                //holder.image.setImageResource(0);//.setVisibility(VISIIBLE);
                Glide.with(MonsterDetailsFragment.this).load(monster.weakness).into(holder.image);
                holder.image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), PhotoviewActivity.class);
                                intent.putExtra("photourl", monster.getWeakness());
                                getContext().startActivity(intent);
                            }
                        });
            }else{
                holder.image.setImageResource(0);//.setVisibility(VISIIBLE);
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.list_group, parent, false);
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.lblListHeader);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
            ImageView image;
        }
    }

}
