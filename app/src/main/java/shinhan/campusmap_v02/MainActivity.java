package shinhan.campusmap_v02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import shinhan.campusmap_v02.bus.BusFragment;
import shinhan.campusmap_v02.campusmap.CampusmapFragment;
import shinhan.campusmap_v02.link.LinkFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceMainFragment(CampusmapFragment.newInstance("a", "b"));

        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    public void replaceMainFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceCampusmapFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.campusmap_framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.campusmapItem:
                    replaceMainFragment(CampusmapFragment.newInstance("a", "b"));
                    break;
                case R.id.busItem:
                    replaceMainFragment(BusFragment.newInstance("a", "b"));
                    break;
                case R.id.linkItem:
                    replaceMainFragment(LinkFragment.newInstance("a", "b"));
                    break;
            }
            return true;
        }
    }
}