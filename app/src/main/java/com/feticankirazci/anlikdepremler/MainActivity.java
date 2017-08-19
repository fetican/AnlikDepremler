package com.feticankirazci.anlikdepremler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.feticankirazci.anlikdepremler.models.EarthQuakesList;
import com.feticankirazci.anlikdepremler.models.ImportantEarthQuakesList;
import com.feticankirazci.anlikdepremler.network.Factory;
import com.feticankirazci.anlikdepremler.network.ImportantFactory;
import com.feticankirazci.anlikdepremler.services.ImportantServiceEvent;
import com.feticankirazci.anlikdepremler.services.ResponseServiceEvent;
import com.feticankirazci.anlikdepremler.ui.EarthQuakesListFragment;
import com.feticankirazci.anlikdepremler.ui.ImportantEarthQuakesFragment;
import com.feticankirazci.anlikdepremler.ui.MainMenuFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.container)
    FrameLayout mContainer;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private boolean isWifiConn,isMobileConn;
//    private ArrayList<Contacts> mContactsList;
//    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        isMobileConn = networkInfo.isConnected();
        if (!isWifiConn & !isMobileConn){
            Toast.makeText(this, "İnternet bağlantısı sağlanamadı. Lütfen bağlantınızı kontrol ediniz.", Toast.LENGTH_SHORT).show();
        }
        getEarthQuakeList();
        getImportantEarthQuakesList();
//        showContacts();
        setBottomNavigationBar();
        initial();
    }

    private void setBottomNavigationBar() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.main_menu:
                        getEarthQuakeList();
                        selectedFragment = MainMenuFragment.newInstance();
                        break;
                    case R.id.earth_quakes:
                        getEarthQuakeList();
                        selectedFragment = EarthQuakesListFragment.newInstance();
                        break;
                    case R.id.contacts:
                        getImportantEarthQuakesList();
                        selectedFragment = ImportantEarthQuakesFragment.newInstance();
                        break;
                }
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, selectedFragment);
                ft.commit();
                return true;
            }
        });
    }

    private void initial() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, MainMenuFragment.newInstance());
        ft.commit();
    }

    private void getEarthQuakeList() {
        Factory.getInstance().getEarthQuakesList().enqueue(new Callback<EarthQuakesList>() {
            @Override
            public void onResponse(Call<EarthQuakesList> call, Response<EarthQuakesList> response) {
                EventBus.getDefault().postSticky(new ResponseServiceEvent(null, response));
            }

            @Override
            public void onFailure(Call<EarthQuakesList> call, Throwable t) {
                Log.d(MainActivity.class.getName(),"Failure",t);
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getImportantEarthQuakesList(){
        ImportantFactory.getInstance().getImportantEarthQuakesList().enqueue(new Callback<ImportantEarthQuakesList>() {
            @Override
            public void onResponse(Call<ImportantEarthQuakesList> call, Response<ImportantEarthQuakesList> response) {
                EventBus.getDefault().postSticky(new ImportantServiceEvent(null, response));
            }

            @Override
            public void onFailure(Call<ImportantEarthQuakesList> call, Throwable t) {
                Log.d(MainActivity.class.getName(),"Failure",t);
            }
        });
    }

//    private void showContacts(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
//        } else { getContactList(); }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission is granted
//                showContacts();
//            } else {
//                Toast.makeText(this, "İzin vermediğiniz takdirde Kişiler'inize ulaşamayız.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void getContactList() {
//
//            mContactsList = new ArrayList<>();
//            ContentResolver resolver = getContentResolver();
//            Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//
//            while (cursor.moveToNext()) {
//                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
//
//                while (phoneCursor.moveToNext()) {
//                    String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    mContactsList.add(new Contacts(name, phoneNumber));
//                }
//            }
//            EventBus.getDefault().postSticky(new ContactsListEvent(null, mContactsList));
//        }
    }

