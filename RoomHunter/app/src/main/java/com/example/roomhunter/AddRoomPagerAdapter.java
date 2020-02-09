package com.example.roomhunter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class AddRoomPagerAdapter extends MyFragmentStatePagerAdapter {
    public AddRoomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a HomeFragment (defined as a static inner class below).
        switch(position){
            case 0:
                return new RoomInfo();
            case 1:
                return new RoomDetails();
            case 2:
                return new RoomLocation();
            case 3:
                return new RoomPhotos();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Room Info.";
            case 1:
                return "Price & Availability";
            case 2:
                return "Location";
            case 3:
                return "Photos";
        }
        return null;
    }
}
