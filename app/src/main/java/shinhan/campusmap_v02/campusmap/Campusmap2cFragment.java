package shinhan.campusmap_v02.campusmap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import shinhan.campusmap_v02.MainActivity;
import shinhan.campusmap_v02.R;

public class Campusmap2cFragment extends Fragment {

    private ImageButton campusmap_2c_bethesda,
                        campusmap_2c_beniel,
                        campusmap_2c_sharon,
                        campusmap_2c_rodem,
                        campusmap_2c_mainbuilding,
                        campusmap_2c_annex;

    public Campusmap2cFragment() {

    }

    public static Campusmap2cFragment newInstance(String param1, String param2) {
        Campusmap2cFragment fragment = new Campusmap2cFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campusmap_2c, container, false);

        campusmap_2c_bethesda = v.findViewById(R.id.campusmap_2c_bethesda);

        campusmap_2c_bethesda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("206"));
            }
        });

        campusmap_2c_beniel = v.findViewById(R.id.campusmap_2c_beniel);

        campusmap_2c_beniel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("205"));
            }
        });

        campusmap_2c_sharon = v.findViewById(R.id.campusmap_2c_sharon);

        campusmap_2c_sharon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("204"));
            }
        });

        campusmap_2c_rodem = v.findViewById(R.id.campusmap_2c_rodem);

        campusmap_2c_rodem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("203"));
            }
        });

        campusmap_2c_mainbuilding = v.findViewById(R.id.campusmap_2c_mainbuilding);

        campusmap_2c_mainbuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("201"));
            }
        });

        campusmap_2c_annex = v.findViewById(R.id.campusmap_2c_annex);

        campusmap_2c_annex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("202"));
            }
        });

        return v;
    }
}