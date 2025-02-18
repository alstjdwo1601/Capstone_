package com.example.netwod;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordlistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewGroup rootView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public Button listselectbutton;
    public Button listdeletebutton;
    MainActivity activity;
    public RecordlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordlistFragment newInstance(String param1, String param2) {
        RecordlistFragment fragment = new RecordlistFragment();
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
        activity = (MainActivity) getActivity();
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recordlist , container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(activity);


        listselectbutton= rootView.findViewById(R.id.recorddeltebutton);
        // listdeletebutton=rootView.findViewById(R.id.listdeletebutton);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewRecord(activity);
        recyclerView.setAdapter(adapter);
        listselectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //여기에 버튼 선택시

                if(activity.excelscrapper.userinfo.getUserwodlist().get(activity.excelscrapper.userinfo.getCurrentwodindex()).getWODtype().equals("FORTIME")) {


                    activity.onFragmentChange(10);
                }
                else {
                    activity.onFragmentChange(11);

                }
            }
        });

        return rootView;
    }
}