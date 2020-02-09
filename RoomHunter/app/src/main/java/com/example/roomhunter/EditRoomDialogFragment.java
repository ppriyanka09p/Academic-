package com.example.roomhunter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditRoomDialogFragment extends DialogFragment {

    MyRoomDetailActivity activity;
    TextView textView22, textView23,textView24;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MyRoomDetailActivity) getActivity();
    }

    public EditRoomDialogFragment(){

    }

    public static EditRoomDialogFragment newInstance(){
        EditRoomDialogFragment fragment = new EditRoomDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstance){
        //return layoutInflater.inflate(R.layout.fragment_edit_room_dialog, container);
        ViewGroup rootView = (ViewGroup)layoutInflater.inflate(R.layout.fragment_edit_room_dialog,container,false);


        textView22 = (TextView)rootView.findViewById(R.id.textView22);
        textView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Room Reserved", Toast.LENGTH_LONG).show();
            }
        });
        textView23 = (TextView)rootView.findViewById(R.id.textView23);
        textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Room Occupied", Toast.LENGTH_LONG).show();
            }
        });
        textView24 = (TextView)rootView.findViewById(R.id.textView24);
        textView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Room Delete", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;


    }

}

