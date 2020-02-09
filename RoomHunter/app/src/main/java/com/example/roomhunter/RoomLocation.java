package com.example.roomhunter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.libraries.places.compat.ui.PlaceAutocomplete;

public class RoomLocation extends Fragment{

    private EditText cityTown, mSuburb;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private String TAG = "CreateRide";



    private ImageView mBic, mOwnToilet, mKitchen, mParking, mWifi, mSecure, mFurnished,
            mBorehole, mPrepaidZesa, mWardrobe, mPrepaidWater, mFam, mFemale, mMale, mSoberHabit,
            mProfessional, mCouple;

    //labels
    private TextView mBicLabel, mOwnEntranceLabel, mOwnToiletLabel, mKitchenLabel, mParkingLabel,
            mWifiLabel, mSecureLabel, mFurnishedLabel, mBoreholeLabel, mPrepaidZesaLabel,
            mWardrobeLabel, mPrepaidWaterLabel, mFamLabel, mFemaleLabel, mMaleLabel,
            mSoberHabitLabel, mProfessionalLabel, mCoupleLabel;

    private FloatingActionButton addRoomStep3;

    //bitmap
    private Bitmap finalBitmap;

    private GetLocationAmenitiesPref mGetLocAmenitiesPrefOnNext;



    public interface GetLocationAmenitiesPref{
        void passCity(String city);
        void passSuburb(String suburb);
        void passBIC(boolean bic);
        void passOwnEntrance(boolean ownEntrance);
        //void passOwnToilet(boolean ownToilet);
        void passKitchen(boolean kitchen);
        void passParkingAvail(boolean parkingAvail);
        void passWifiAvail(boolean wifiAvail);
        void passSecure(boolean secure);
        void passFurnished(boolean furnished);
        //void passBoreholeAvail(boolean boreholeAvail);
        void passPrepaidZesa(boolean prepaidZesa);
        void passFittedWardrobe(boolean fittedWardrobe);
        void passPrepaidWater(boolean prepaidWater);
        void passSmallFamilyPreferred(boolean smallFamily);
        void passFemalePreferred(boolean female);
        void passMalePreferred(boolean male);
        //void passSoberHabitsPreferred(boolean soberHabits);
        void passProfessionalPreferred(boolean professional);
        void passCouplePreferred(boolean couple);
    }
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddLocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomLocation newInstance(String param1, String param2) {
        RoomLocation fragment = new RoomLocation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomLocation() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.add_location_fragment, container, false);

        //attach the views to the fragment
        cityTown = (EditText)rootView.findViewById(R.id.add_room_town_edit_text);

        mSuburb = (EditText)rootView.findViewById(R.id.add_room_suburb_edit_text);

        //imageviews
        mBic = (ImageView)rootView.findViewById(R.id.add_room_bic_imageview);
        //mOwnToilet = (ImageView)rootView.findViewById(R.id.add_room_toilet_imageview);
        mKitchen = (ImageView)rootView.findViewById(R.id.add_room_kitchen_imageview);
        mParking = (ImageView)rootView.findViewById(R.id.add_room_parking_imageview);
        mWifi = (ImageView)rootView.findViewById(R.id.add_room_wifi_imageview);
        mSecure = (ImageView)rootView.findViewById(R.id.add_room_secure_imageview);
        mFurnished = (ImageView)rootView.findViewById(R.id.add_room_furnished_imageview);
        //mBorehole = (ImageView)rootView.findViewById(R.id.add_room_borehole_imageview);
        mPrepaidZesa = (ImageView)rootView.findViewById(R.id.add_room_prezesa_imageview);
        mWardrobe = (ImageView)rootView.findViewById(R.id.add_room_wardrobe_imageview);
        mPrepaidWater = (ImageView)rootView.findViewById(R.id.add_room_prewater_imageview);
        mFam = (ImageView)rootView.findViewById(R.id.add_room_fam_imageview);
        mFemale = (ImageView)rootView.findViewById(R.id.add_room_female_imageview);
        mMale = (ImageView)rootView.findViewById(R.id.add_room_male_imageview);
        //mSoberHabit = (ImageView)rootView.findViewById(R.id.add_room_sober_imageview);
        mProfessional = (ImageView)rootView.findViewById(R.id.add_room_pro_imageview);
        mCouple = (ImageView)rootView.findViewById(R.id.add_room_couple_imageview);

        //labels
        mBicLabel = (TextView)rootView.findViewById(R.id.add_room_bic_label);
        //mOwnEntranceLabel = (TextView)rootView.findViewById(R.id.add_room_entrance_label);
        //mOwnToiletLabel = (TextView)rootView.findViewById(R.id.add_room_toilet_label);
        mKitchenLabel = (TextView)rootView.findViewById(R.id.add_room_kitchen_label);
        mParkingLabel = (TextView)rootView.findViewById(R.id.add_room_parking_label);
        mWifiLabel = (TextView)rootView.findViewById(R.id.add_room_wifi_label);
        mSecureLabel = (TextView)rootView.findViewById(R.id.add_room_secure_label);
        mFurnishedLabel = (TextView)rootView.findViewById(R.id.add_room_furnished_label);
        //mBoreholeLabel = (TextView)rootView.findViewById(R.id.add_room_borehole_label);
        mPrepaidZesaLabel = (TextView)rootView.findViewById(R.id.add_room_prezesa_label);
        mWardrobeLabel = (TextView)rootView.findViewById(R.id.add_room_wardrobe_label);
        mPrepaidWaterLabel = (TextView)rootView.findViewById(R.id.add_room_prewater_label);
        mFamLabel = (TextView)rootView.findViewById(R.id.add_room_fam_label);
        mFemaleLabel = (TextView)rootView.findViewById(R.id.add_room_female_label);
        mMaleLabel = (TextView)rootView.findViewById(R.id.add_room_male_label);
        //mSoberHabitLabel = (TextView)rootView.findViewById(R.id.add_room_sober_label);
        mProfessionalLabel = (TextView)rootView.findViewById(R.id.add_room_pro_label);
        mCoupleLabel = (TextView)rootView.findViewById(R.id.add_room_couple_label);

        //imageview and label actions
        mBic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                //change label colour and imageview colour
                changeLabelColour(mBicLabel);

            }
        });
        mBicLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLabelColour(mBicLabel);
            }
        });

        mKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mKitchenLabel);
            }
        });
        mParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mParkingLabel);
            }
        });
        mWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mWifiLabel);
            }
        });
        mSecure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mSecureLabel);
            }
        });
        mFurnished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mFurnishedLabel);
            }
        });

        mPrepaidZesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mPrepaidZesaLabel);
            }
        });
        mWardrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mWardrobeLabel);
            }
        });
        mPrepaidWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mPrepaidWaterLabel);
            }
        });
        mFam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mFamLabel);
            }
        });
        mFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mFemaleLabel);
            }
        });
        mMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mMaleLabel);
            }
        });

        mProfessional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mProfessionalLabel);
            }
        });
        mCouple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                changeLabelColour(mCoupleLabel);
            }
        });


        addRoomStep3 = (FloatingActionButton)rootView.findViewById(R.id.add_room_step3_fab);

        addRoomStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddRoomActivity) getActivity()).setCurrentItem(3, true);
                Toast.makeText(getContext().getApplicationContext(), "4 of 4", Toast.LENGTH_LONG).show();
                //pass the data captured to container activity
                //data will be accessed via the interface implemented
                /*
                 * for the data captured via colour change
                 * 1. check if colour of value is #ff4081
                 *   if true, set property value, else set to null and return
                 * */

               mGetLocAmenitiesPrefOnNext.passCity(cityTown.getText().toString());
                mGetLocAmenitiesPrefOnNext.passSuburb(mSuburb.getText().toString());



                if(mBicLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)){
                    //get value, set to true
                    mGetLocAmenitiesPrefOnNext.passBIC(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passBIC(false);
                }

                if (mKitchenLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passKitchen(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passKitchen(false);
                }

                if (mParkingLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passParkingAvail(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passParkingAvail(false);
                }

                if (mWifiLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passWifiAvail(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passWifiAvail(false);
                }

                if (mSecureLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passSecure(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passSecure(false);
                }

                if (mFurnishedLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passFurnished(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passFurnished(false);
                }

                if (mPrepaidZesaLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passPrepaidZesa(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passPrepaidZesa(false);
                }

                if (mWardrobeLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passFittedWardrobe(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passFittedWardrobe(false);
                }

                if (mPrepaidWaterLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passPrepaidWater(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passPrepaidWater(false);
                }

                //preferences
                if (mFamLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passSmallFamilyPreferred(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passSmallFamilyPreferred(false);
                }

                if (mFemaleLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passFemalePreferred(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passFemalePreferred(false);
                }

                if (mMaleLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passMalePreferred(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passMalePreferred(false);
                }

                if (mProfessionalLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passProfessionalPreferred(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passProfessionalPreferred(false);
                }

                if (mCoupleLabel.getCurrentTextColor() == ContextCompat.getColor(getContext(), R.color.colorAccent)) {
                    mGetLocAmenitiesPrefOnNext.passCouplePreferred(true);
                }else{
                    mGetLocAmenitiesPrefOnNext.passCouplePreferred(false);
                }
            }
        });


        return rootView;
    }

    private void hideKeyBoard() {
        //hide the keyboard
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mBic.getWindowToken(), 0);
    }

    private void changeLabelColour(TextView mLabel) {
        TextView label = mLabel;
        if(label.getCurrentTextColor() != Color.parseColor("#ff404d") ){
            label.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorAccent)));
        }else {
            label.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.colorDull)));
        }
    }



    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //ensure container activity implements the interface
        try{
            mGetLocAmenitiesPrefOnNext = (GetLocationAmenitiesPref)getActivity();
        }catch (ClassCastException cce){
            throw new ClassCastException(getActivity().toString() + "must implement GetLocationAmentiesPref");
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

