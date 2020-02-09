package com.example.roomhunter;

import android.os.Bundle;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RoomDetails extends Fragment {
    private EditText mMonthlyRent;
    private EditText mDeposit;
    private RadioButton mRentInclYesOfBills;
    private RadioButton mRentInclNoOfBills;
    private TextView mAvailableDate;
    private EditText mRoomDescription;
    private FloatingActionButton mStep2fab;
    private String date;
    private String date2;

    protected String mRent;
    private String mRentInclOrExclOfBills;
    private int mRentAmount;
    private int mDepositAmount;


    GetRoomDetail getRoomDetailOnNext;

    public interface GetRoomDetail{
        public void passMonthlyRent(int rentAmount);
        public void passDeposit(int deposit);
        public void passRentInclOrExclOfBills(String rentInclOrExclOfBills);
        public void passAvailableDate(String availableDate);
        public void passRoomDescription(String roomDescription);
    }



    public RoomDetails() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.room_details_fragment, container, false);

        mMonthlyRent = (EditText) rootView.findViewById(R.id.monthly_rent_edit_text);
        mDeposit = (EditText) rootView.findViewById(R.id.deposit_edit_text);
        mRentInclYesOfBills = (RadioButton) rootView.findViewById(R.id.radioButton);
        mRentInclNoOfBills = (RadioButton) rootView.findViewById(R.id.radioButton2);
        mAvailableDate = (TextView) rootView.findViewById(R.id.textView14);
        mRoomDescription = (EditText) rootView.findViewById(R.id.deposit_edit_text);
        mStep2fab = (FloatingActionButton) rootView.findViewById(R.id.step2_fab);

        mRentInclYesOfBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadiobuttonClicked(v);

            }
        });

        mRentInclNoOfBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadiobuttonClicked(v);
            }
        });



        mAvailableDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePickerFragment = new DatePicker();
                datePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });

        mStep2fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to step 3
                ((AddRoomActivity) getActivity()).setCurrentItem(2, true);
                Toast.makeText(getActivity().getApplicationContext(), "3 of 4", Toast.LENGTH_LONG)
                        .show();
                //pass the data capture via the interface methods to the container activity
                /*
                 * this is Google's best practice for communicating with other fragments
                 * since the data captured will be used in AddPhotos, the last step in gathering
                 * room data to create the room object
                 * */
                //check whether any of the elements are null
                if((mMonthlyRent.getText().toString()).equals("")){
                    mRentAmount = 0;
                }else{
                    mRentAmount = Integer.parseInt(mMonthlyRent.getText().toString());
                }

                if((mDeposit.getText().toString()).equals("")) {
                    mDepositAmount = 0;
                }else{
                    mDepositAmount = Integer.parseInt(mDeposit.getText().toString());
                }

                getRoomDetailOnNext.passMonthlyRent(mRentAmount);
                getRoomDetailOnNext.passDeposit(mDepositAmount);
                getRoomDetailOnNext.passRentInclOrExclOfBills(mRentInclOrExclOfBills);
                getRoomDetailOnNext.passAvailableDate(mAvailableDate.getText().toString());
                getRoomDetailOnNext.passRoomDescription(mRoomDescription.getText().toString());
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            getRoomDetailOnNext = (GetRoomDetail)getActivity();
        } catch(ClassCastException cce){
            throw new ClassCastException(getActivity().toString()+"container must implement GetRoomDetail");
        }
    }

    public void setAvailableDate(String date){
        this.date = date;
        Log.d("Final date",this.date);
        mAvailableDate.setText(this.date);
    }


    public void onRadiobuttonClicked(View v){
        boolean checked = ((RadioButton)v).isChecked();
        switch(v.getId()) {
            case R.id.radioButton:
                if(checked){
                    mRentInclOrExclOfBills = "incl. bills";
                }
                break;
            case R.id.radioButton2:
                if(checked){
                    mRentInclOrExclOfBills = "excl. bills";
                }
                break;
        }
    }




}
