package com.example.netwod;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewRecord extends RecyclerView.Adapter<RecyclerViewRecord.ViewHolder> {
    private String[] type;

    // public MainActivity.ExcelScrapper.activity.excelscrapper.userinfo activity.excelscrapper.userinfo;
    private String[] title;
    public MainActivity activity;
    private String[] content;
    private OnItemClick mCallback;


    public ArrayList<Integer> clickedlist=new ArrayList<Integer>();

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView2;
        public ImageView imageView;
        public TextView textView3;
        public Button listdeletebutton;
        public Button rankaddbutton;
        public ViewHolder(View view) {
            super(view);

            this.textView = view.findViewById(R.id.recordwodnametextview);
            this.textView2 = view.findViewById(R.id.recordwodrecordtextview);
            this.textView3 = view.findViewById(R.id.recordwodscoretextview);
            this.rankaddbutton=activity.findViewById(R.id.rankingaddbutton);
            this.listdeletebutton=activity.findViewById(R.id.recorddeltebutton);

        }
    }

    @Override
    public RecyclerViewRecord.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userrecord, parent, false);
        RecyclerViewRecord.ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
    RecyclerViewRecord(MainActivity a) {
        //this.activity.excelscrapper.userinfo=new MainActivity.ExcelScrapper.activity.excelscrapper.userinfo();

        this.activity=a;

    }

    @Override
    public void onBindViewHolder(RecyclerViewRecord.ViewHolder holder, final int position) {
        //holder.textView.setText(title[position]);


        //holder.textView2.setText(type[position]);
        for(int i=0;i<activity.excelscrapper.userinfo.getWodrecord().wodlist.size();i++){
            clickedlist.add(0);


        }
        holder.listdeletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                activity.excelscrapper.userinfo.getWodrecord().wodlist.remove(activity.excelscrapper.userinfo.getCurrentwodindex());
                activity.excelscrapper.userinfo.getWodrecord().getRecordlist().remove(activity.excelscrapper.userinfo.getCurrentwodindex());
                activity.excelscrapper.userinfo.getWodrecord().getScorelist().remove(activity.excelscrapper.userinfo.getCurrentwodindex());

                notifyDataSetChanged();
            }
        });
        holder.rankaddbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
                String formatDate = sdfNow.format(date);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> user = new HashMap<>();

                user.put("WOD", activity.excelscrapper.userinfo.wodrecord.getWodlist().get(activity.excelscrapper.userinfo.getCurrentwodindex()));
                user.put("SCORE", activity.excelscrapper.userinfo.wodrecord.getScorelist().get(activity.excelscrapper.userinfo.getCurrentwodindex()));
                user.put("RECORD", activity.excelscrapper.userinfo.wodrecord.getRecordlist().get(activity.excelscrapper.userinfo.getCurrentwodindex()));
                user.put("RANKING", "0"); //아직 랭킹정렬 미구현
                user.put("Username",activity.excelscrapper.userinfo.getUserName());
                user.put("Date",formatDate);
                db.collection("RankInfo").document()
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }

        });


        int recordsize =activity.excelscrapper.userinfo.getWodrecord().recordlist.get(position).length();
        int namesize = activity.excelscrapper.userinfo.getWodrecord().wodlist.get(position).getWODname().length();
        int scoresize = activity.excelscrapper.userinfo.getWodrecord().scorelist.get(position).length();
       String scorestring="";
        String namestring="";
        String recordstring="";
        for(int i=0; i<namesize;i++){
            namestring=namestring+activity.excelscrapper.userinfo.getWodrecord().wodlist.get(position).getWODname().charAt(i);

        }
        for(int i=0; i<scoresize;i++){
            scorestring=scorestring+activity.excelscrapper.userinfo.getWodrecord().scorelist.get(position).charAt(i);
        }
        if(activity.excelscrapper.userinfo.getWodrecord().wodlist.get(position).getWODtype().equals("FORTIME")) {
            for (int i = 0; i < recordsize; i++) {
                recordstring = recordstring + activity.excelscrapper.userinfo.getWodrecord().recordlist.get(position).charAt(i);
            }
        }
        else{
            for (int i = 0; i < 2; i++) {
                recordstring = recordstring + activity.excelscrapper.userinfo.getWodrecord().recordlist.get(position).charAt(i);
            }
            recordstring=recordstring+"rounds";
            for (int i = 3; i < 5; i++) {
                recordstring = recordstring + activity.excelscrapper.userinfo.getWodrecord().recordlist.get(position).charAt(i);
            }
            recordstring=recordstring+"times";
        }
        holder.textView.setText(namestring);
        holder.textView2.setText(recordstring);
        holder.textView3.setText(scorestring);
        if(clickedlist.get(position)==1) {
            holder.textView.setBackgroundColor(Color.YELLOW);
        }
        else holder.textView.setBackgroundColor(Color.WHITE);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), position+"번 째!", Toast.LENGTH_SHORT).show();
                if(clickedlist.get(position)==0) {
                    holder.textView.setBackgroundColor(Color.YELLOW);
                    activity.excelscrapper.userinfo.setCurrentwodindex(position);
                    clickedlist.set(position, 1);


                }
                else {
                    holder.textView.setBackgroundColor(Color.WHITE);

                    clickedlist.set(position, 0);
                }




            }
        });




    }

    @Override
    public int getItemCount() {

        return activity.excelscrapper.userinfo.getWodrecord().wodlist.size();

        //return 3;
    }
}