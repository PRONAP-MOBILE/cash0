package com.mobil.pronap.cash0.Activities;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mobil.pronap.cash0.Dialog.Dialog_Regulator;
import com.mobil.pronap.cash0.Fragments.MainScreen;
import com.mobil.pronap.cash0.Fragments.Settings;
import com.mobil.pronap.cash0.Fragments.UserInfo;
import com.mobil.pronap.cash0.Fragments.UserTransactions;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.User;

import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_FINGERPRINT;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static String lang = "HT";
    public FrameLayout container;
    public DrawerLayout drawer;
    public NavigationView navigationView;
    Toolbar toolbar;
    public FloatingActionButton fab;
    ImageView ivLogoDrawer;
    TextView tvTitleDrawer;
    TextView tvDetailDrawer;
    Intent i;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fts;
    Class fragmentClass;
    Gson gson;
    Settings settings;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int REQUEST_SEND_SMS = 0;
    private static final int REQUEST_SENSOR = 0;
    private static final int REQUEST_FINGERPRINT = 0;
    private static final int REQUEST_WRITE_SMS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        container = (FrameLayout) findViewById(R.id.flContent);
        settings = new Settings();
        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        gson = new Gson();
        editor = sharedPreferences.edit();


        toolbar.setTitle("Acceuil");

        fragmentClass = MainScreen.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        fts = getSupportFragmentManager().beginTransaction();
        fts.setCustomAnimations(R.anim.right, R.anim.left);
        //fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        fts.replace(R.id.flContent, fragment).commit();

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setVisibility(View.GONE);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);

        //Phone number
        tvDetailDrawer = (TextView) hView.findViewById(R.id.tvDetailDrawer);
        //Name user
        tvTitleDrawer = (TextView) hView.findViewById(R.id.tvTitleDrawer);
        ivLogoDrawer = (ImageView) hView.findViewById(R.id.ivLogoDrawer);

        if(sharedPreferences.getString("infoUser", null)==null || sharedPreferences.getString("infoUser", null).equals("")){
            Intent i = new Intent(DrawerActivity.this, LoginActivity.class);
        }
        else{
            String userInfo = sharedPreferences.getString("userRegister", null);
            User user = gson.fromJson(userInfo, User.class);

            tvDetailDrawer.setText(user.getPhone().toString());
            tvTitleDrawer.setText(user.getName().toString());

        }

        requestPermission();



    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_logout){
            editor.putString("infoUser", null);
            editor.apply();
            i = new Intent(DrawerActivity.this, LoginActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_settings) {
            settings.show(fragmentManager, "SETTINGS");
            return true;
        }

        if (id == R.id.regulateur) {

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        fragment = null;
        //Class fragmentClass;
        int id = item.getItemId();
        switch(item.getItemId()) {
            case R.id.acceuil:
                fragmentClass = MainScreen.class;
                break;
            case R.id.transactions:
                fragmentClass = UserTransactions.class;
                break;
            case R.id.profil:
                //fragmentClass = Profil.class;
                fragmentClass = UserInfo.class;
                break;
            case R.id.regulateur:
                //fragmentClass = Profil.class;
                FragmentManager fm = getSupportFragmentManager();
                Dialog_Regulator alertDialog = Dialog_Regulator.newInstance("Limite dépense");
                alertDialog.show(fm, "alert dialog");

                break;
            case R.id.nav_settings:
                settings.show(fragmentManager, "SETTINGS");
                break;
            case R.id.nav_share:
                drawer.closeDrawers();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text shared.</p>"));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                drawer.closeDrawers();
                return true;
            default:
                //fragmentClass = Info.class;
                fragmentClass = null;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        fts = getSupportFragmentManager().beginTransaction();
        fts.setCustomAnimations(R.anim.right, R.anim.left);
        //fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        fts.replace(R.id.flContent, fragment).commit();
        item.setChecked(true);

        setTitle(item.getTitle());

        drawer.closeDrawers();
        return true;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                //Toast.makeText(getApplicationContext(), "No message", Toast.LENGTH_SHORT).show();
                //should pass result to intent
                finish();
                i = new Intent(DrawerActivity.this, DrawerActivity.class);
                startActivity(i);

            } else {
                 //should pass result to intent

                //Passing data from the QRcode to the activity for buy
                String information = result.getContents().toString();

                i = new Intent(DrawerActivity.this, DetailBuyActivity.class);
                i.putExtra("information", information);
                startActivity(i);
            }

        } else {
            Toast.makeText(getApplicationContext(), "No BarCode", Toast.LENGTH_SHORT).show();
            i = new Intent(DrawerActivity.this, DrawerActivity.class);
            startActivity(i);
        }
    }

    public void restartApp(){
        Intent mStartActivity = new Intent(DrawerActivity.this, DrawerActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent  mPendingIntent = PendingIntent.getActivity(DrawerActivity.this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) DrawerActivity.this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, 1500, mPendingIntent);

    }


    public void requestPermission(){
        if(!mayRequestContacts() && !mayRequestFingerPrint() && !mayRequestSMS() && !mayRequestWriteSMS() && !mayRequestSensor()){
            return;
        }

    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(toolbar, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    private boolean mayRequestSMS() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(toolbar, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{SEND_SMS}, REQUEST_SEND_SMS);
                        }
                    });
        } else {
            requestPermissions(new String[]{SEND_SMS}, REQUEST_SEND_SMS);
        }
        return false;
    }

    private boolean mayRequestFingerPrint() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(USE_FINGERPRINT)) {
            Snackbar.make(toolbar, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{USE_FINGERPRINT}, REQUEST_FINGERPRINT);
                        }
                    });
        } else {
            requestPermissions(new String[]{SEND_SMS}, REQUEST_FINGERPRINT);
        }
        return false;
    }

    private boolean mayRequestSensor() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(BODY_SENSORS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(BODY_SENSORS)) {
            Snackbar.make(toolbar, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{BODY_SENSORS}, REQUEST_SENSOR);
                        }
                    });
        } else {
            requestPermissions(new String[]{BODY_SENSORS}, REQUEST_SENSOR);
        }
        return false;
    }

    private boolean mayRequestWriteSMS() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(RECEIVE_SMS)) {
            Snackbar.make(toolbar, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{RECEIVE_SMS}, REQUEST_WRITE_SMS);
                        }
                    });
        } else {
            requestPermissions(new String[]{RECEIVE_SMS}, REQUEST_WRITE_SMS);
        }
        return false;
    }





    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
