package com.example.roomhunter;


import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    /* PagerAdapter will provide fragments for each of the sections.
    * We are using its derivative FragmentPagerAdapter which will keep every loaded fragment in memory.*/
    private HomeSectionsPagerAdapter mHomeSectionsPagerAdapter;


    private DrawerLayout drawerLayout;//navigation drawer
    private NavigationView navigationView;
    Toolbar toolbar;
    private ViewPager viewPager;//ViewPager will host the section content


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tracking number of times app is opened by user
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // adapter created that will return a fragment for each of the three primary sections of the activity.
        mHomeSectionsPagerAdapter = new HomeSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(mHomeSectionsPagerAdapter);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //sync the toggle state
        toggle.syncState();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Add new listing",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                Toast.makeText(MainActivity.this,"1 of 4",Toast.LENGTH_LONG).show();
                Intent addListingIntent = new Intent(MainActivity.this, AddRoomActivity.class);
                startActivity(addListingIntent);
            }
        });

    }
    private void navToLogin() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);

        /*MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);*/

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String city) {
                Toast.makeText(getApplicationContext(), "Search submit", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(), "Search change", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ParseUser currentUser = ParseUser.getCurrentUser();

        /* Handle action bar item clicks here. The action bar will automatically handle clicks
        * on the HomeFragment/Up button, so long as you specify a parent activity in AndroidManifest.xml.*/
        switch(item.getItemId()) {
            case R.id.action_add_room:
                Intent addListingIntent = new Intent(this, AddRoomActivity.class);
                startActivity(addListingIntent);
                break;
            case R.id.action_sign_in:
                if(currentUser == null){
                    //navigate to login
                    navToLogin();
                }else{
                    //tell user is already signed in
                    Toast.makeText(this, "You are already logged in as" + currentUser.getUsername().toString(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.action_sign_out:
                //log current user out
                //check if there is a user then log her out
                if(currentUser != null){
                    Toast.makeText(this, "Logging out", Toast.LENGTH_LONG).show();
                    ParseUser.logOut();
                    navToLogin();
                }else{
                    Toast.makeText(this, "Not logged in, please log in", Toast.LENGTH_LONG).show();
                    showLoginDialog();
                }
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int selection = item.getItemId();

        //the fragments to load on selection
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (selection){
            case R.id.browse_rooms:
                //launch rooms fragments
                Toast.makeText(this, "Fetching rooms", Toast.LENGTH_LONG).show();
                fragmentClass = HomeFragment.class;
                break;
            case R.id.my_rooms:
                fragmentClass = MyRoomsFragment.class;

                //check if logged in
                if(ParseUser.getCurrentUser() == null){
                    //ask to login
                    Toast.makeText(this, "Please login to access your rooms", Toast.LENGTH_LONG).show();
                    //login dialog
                    showLoginDialog();
                }else {
                    //launch my rooms fragment
                    Toast.makeText(this, "My rooms", Toast.LENGTH_LONG).show();
                    //direct to users' rooms
                    Intent myRoomsIntent = new Intent(this, MyRoomsActivity.class);
                    startActivity(myRoomsIntent);
                }
                break;
            case R.id.share:
                //share the app
                Toast.makeText(this, "Sharing the app", Toast.LENGTH_LONG).show();

                Intent shareAppIntent = new Intent();
                shareAppIntent.setAction(Intent.ACTION_SEND);
                shareAppIntent.putExtra(Intent.EXTRA_TEXT, "Get the Rooms App \nhttp://roomhunter.com");
                shareAppIntent.setType("text/plain");
                Intent chooser = Intent.createChooser(shareAppIntent, "Share App Via:");
                //verify whether there is an app capable of handling the intent
                if (shareAppIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(chooser);
                }
                break;
            case R.id.feedback:
                //launch contact us activity/fragment
                Toast.makeText(this, "Give us your valuable feedback", Toast.LENGTH_LONG).show();
                break;
            case R.id.contact:
                //launch contact us
                Toast.makeText(this, "Contact us", Toast.LENGTH_LONG).show();
                Intent aboutIntent = new Intent(this, ContactUsActivity.class);
                startActivity(aboutIntent);
                break;
        }


        //close the drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLoginDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(fragmentManager, "fragment_alert");
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
