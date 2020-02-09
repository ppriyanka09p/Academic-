package com.example.roomhunter;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddRoomActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, RoomInfo.GetRoomInfo, RoomDetails.GetRoomDetail, RoomLocation.GetLocationAmenitiesPref {

    private ViewPager mViewPager;
    private AddRoomPagerAdapter mAddRoomPagerAdapter;

    private RoomPhotos addPhotosFragment;
    public String date;
    private RoomDetails addRoomDetailFragment;

    //the room attributes captured in the other fragments
    /*
     * 1. AddRoomInfoFragment attributes
     * 2. AddDetailInfoFragment attributes
     * 3. AddLocationFragment attributes
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_room);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //include back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //icon in appbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);      //back button in android
        getSupportActionBar().setHomeButtonEnabled(true);

        mAddRoomPagerAdapter = new AddRoomPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        //keep 3 fragments on either side of the current fragment alive in memory
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAddRoomPagerAdapter);
    }

    //method to move fragment to activity via button click
    public void setCurrentItem(int item, boolean smoothScroll){
        mViewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Bundle bundle = new Bundle();
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //format the date
        Date theDate = c.getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(this);
        String date = dateFormat.format(theDate);

        //pass it back to activity
        RoomDetails fragment = (RoomDetails) mAddRoomPagerAdapter.getRegisteredFragment(1);
        fragment.setAvailableDate(date);

        Log.d("Year", date);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    /*methods in the interface implemented to set room data supplied in AddRoomInfoFragment
     * these methods in the interface supply the information which is then
     * routed and forwarded to AddPhotosFragment, the last step in adding room attributes to
     * the backend.
     * */

    //1.
    @Override
    public void getRoomNumBedrooms(int numBeds) {
        Log.d("Number of Beds: ", Integer.toString(numBeds));
        //communicate the data to the AddPhotosFragment
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setNumBeds(numBeds);
    }

    @Override
    public void getRoomNumBaths(int numBaths) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setNumBaths(numBaths);
    }

    @Override
    public void getRoomNumToilets(int numToilets) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setNumToilets(numToilets);
    }

    @Override
    public void getRoomPropertyType(String propertyType) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setPropertyType(propertyType);
    }

    @Override
    public void getRoomSharingYesOrNo(String sharingYesOrNo) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setPropertySharedOrNot(sharingYesOrNo);
    }

    //2.
    @Override
    public void passMonthlyRent(int rentAmount) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setMonthlyRent(rentAmount);
    }

    @Override
    public void passDeposit(int deposit) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setDepost(deposit);
    }

    @Override
    public void passRentInclOrExclOfBills(String rentInclOrExlcOfBills) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setRentInclOrNotOfBills(rentInclOrExlcOfBills);
    }

    @Override
    public void passAvailableDate(String fromDate) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setFromDate(fromDate);
    }

    @Override
    public void passRoomDescription(String roomDescription) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setRoomDescription(roomDescription);
    }

    //3.
    @Override
    public void passCity(String city) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setCity(city);
    }

    @Override
    public void passSuburb(String suburb) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setSuburb(suburb);
    }

    @Override
    public void passBIC(boolean bic) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setBIC(bic);
    }

    @Override
    public void passOwnEntrance(boolean ownEntrance) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setOwnEntrance(ownEntrance);
    }

    @Override
    public void passKitchen(boolean kitchen) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setKitchen(kitchen);
    }

    @Override
    public void passParkingAvail(boolean parkingAvail) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setParking(parkingAvail);
    }

    @Override
    public void passWifiAvail(boolean wifiAvail) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setWifi(wifiAvail);
    }

    @Override
    public void passSecure(boolean secure) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setSecure(secure);
    }

    @Override
    public void passFurnished(boolean furnished) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setFurnished(furnished);
    }

    @Override
    public void passPrepaidZesa(boolean prepaidZesa) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setPrepaidZesa(prepaidZesa);
    }

    @Override
    public void passFittedWardrobe(boolean fittedWardrobe) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setFittedWardrobe(fittedWardrobe);
    }

    @Override
    public void passPrepaidWater(boolean prepaidWater) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setPrepaidWater(prepaidWater);
    }

    @Override
    public void passSmallFamilyPreferred(boolean smallFamily) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setSmallFamily(smallFamily);
    }

    @Override
    public void passFemalePreferred(boolean female) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setFemale(female);
    }

    @Override
    public void passMalePreferred(boolean male) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setMale(male);
    }

    @Override
    public void passProfessionalPreferred(boolean professional) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setProfessional(professional);
    }

    @Override
    public void passCouplePreferred(boolean couple) {
        addPhotosFragment = (RoomPhotos)mAddRoomPagerAdapter.getRegisteredFragment(3);
        addPhotosFragment.setCouple(couple);
    }

    /*methods to set room attributes captured in AddRoomDetailFragment*/
    //2.

}

