package com.feticankirazci.anlikdepremler;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.feticankirazci.anlikdepremler.models.EarthQuakesList;
import com.feticankirazci.anlikdepremler.network.Factory;
import com.feticankirazci.anlikdepremler.services.ResponseServiceEvent;
import com.feticankirazci.anlikdepremler.ui.ContactsFragment;
import com.feticankirazci.anlikdepremler.ui.EarthQuakesListFragment;
import com.feticankirazci.anlikdepremler.ui.MainMenuFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)BottomNavigationView mBottomNavigationView;
    @BindView(R.id.container)FrameLayout mContainer;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBottomNavigationBar();
        initial();
    }

    private void setBottomNavigationBar() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.main_menu:
                        selectedFragment = MainMenuFragment.newInstance();
                        break;
                    case R.id.earth_quakes:
                        getEarthQuakeList();
                        selectedFragment = EarthQuakesListFragment.newInstance();
                        break;
                    case R.id.contacts:
                        selectedFragment = ContactsFragment.newInstance();
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
        ft.replace(R.id.container,MainMenuFragment.newInstance());
        ft.commit();
    }

    private void getEarthQuakeList(){
        Factory.getInstance().getEarthQuakesList().enqueue(new Callback<EarthQuakesList>() {
            @Override
            public void onResponse(Call<EarthQuakesList> call, Response<EarthQuakesList> response) {
                EventBus.getDefault().postSticky(new ResponseServiceEvent(null,response));
            }

            @Override
            public void onFailure(Call<EarthQuakesList> call, Throwable t) {
            }
        });
    }
}
