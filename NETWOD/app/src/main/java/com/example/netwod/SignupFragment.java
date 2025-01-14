package com.example.netwod;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText signupid;
    EditText signuppassword;
    EditText signuppasswordcheck;
    Button signupbutton;

    MainActivity activity;

    private ViewGroup rootView;
    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_signup , container, false);
        signupid=rootView.findViewById(R.id.signupid);
        signuppassword=rootView.findViewById(R.id.signuppassword);
        signuppasswordcheck=rootView.findViewById(R.id.signuppasswordcheck);
        signupbutton=rootView.findViewById(R.id.signupbutton);


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userid = signupid.getText().toString();
                String userpassword = signuppassword.getText().toString();
                String userpasswordcheck = signuppasswordcheck.getText().toString();

            if(userpassword.equals(userpasswordcheck)) {
       //시작
                activity.mAuth=FirebaseAuth.getInstance();
                activity.mAuth.createUserWithEmailAndPassword(userid, userpassword).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
        System.out.println("회원가입 성공ID와 비번은"+userid+userpassword);
                                    FirebaseUser user = activity.mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    System.out.println("회원가입 실패ID와 비번은"+userid+userpassword);
                                    //Toast.makeText(getContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();

                                }

                                // [START_EXCLUDE]

                                // [END_EXCLUDE]
                            }
                        });

//끝
                /*
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> user = new HashMap<>();
                user.put("id", userid);
                user.put("password", userpassword);


                // Add a new document with a generated ID
                String excelname;
                excelname = activity.excelscrapper.userinfo.getUserName();
                db.collection("UserInfo").document(excelname)
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

      */

            }
            else{

            Toast.makeText(getContext(),"Your passwork confirmation does not match.",Toast.LENGTH_LONG).show();

            }
///
            }
        });




        //return inflater.inflate(R.layout.fragment_menu1, container, false);
        //saveuserprofile();
        return rootView;
    }

    //View view = inflater.inflate(R.layout.fragment_menu1, container, false);
    //tv_fragment = (TextView)view.findViewById(R.id.NametextView);
    //btn_fragment = (Button)view.findViewById(R.id.Editbutton);

    public void onAttach(Context context) {
        super.onAttach(context);
        //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (MainActivity) getActivity();
    }

}