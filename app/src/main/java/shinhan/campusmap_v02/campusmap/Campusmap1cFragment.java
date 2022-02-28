package shinhan.campusmap_v02.campusmap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import shinhan.campusmap_v02.MainActivity;
import shinhan.campusmap_v02.R;

public class Campusmap1cFragment extends Fragment {

    private ImageButton campusmap_1c_ebenezer,
            campusmap_1c_zion,
            campusmap_1c_moriah,
            campusmap_1c_gwangya,
            campusmap_1c_gido1,campusmap_1c_gido2,
            campusmap_1c_byeonhyeok,
            campusmap_1c_eunhye1,campusmap_1c_eunhye2,campusmap_1c_eunhye3,campusmap_1c_eunhye4,
            campusmap_1c_bichgwasogeum1,campusmap_1c_bichgwasogeum2,
            campusmap_1c_mideum,
            campusmap_1c_haengham1,campusmap_1c_haengham2,
            campusmap_1c_jinri1,campusmap_1c_jinri2,
            campusmap_1c_malsseum1,campusmap_1c_malsseum2,campusmap_1c_malsseum3,
            campusmap_1c_bedel1,campusmap_1c_bedel2,
            campusmap_1c_uihak1,campusmap_1c_uihak2;

    public Campusmap1cFragment() {

    }

    public static Campusmap1cFragment newInstance(String param1, String param2) {
        Campusmap1cFragment fragment = new Campusmap1cFragment();
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
        View v = inflater.inflate(R.layout.fragment_campusmap_1c, container, false);

        campusmap_1c_ebenezer = v.findViewById(R.id.campusmap_1c_ebenezer);

        campusmap_1c_ebenezer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("101"));
            }
        });

        campusmap_1c_zion = v.findViewById(R.id.campusmap_1c_zion);

        campusmap_1c_zion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("115"));
            }
        });

        campusmap_1c_moriah = v.findViewById(R.id.campusmap_1c_moriah);

        campusmap_1c_moriah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("114"));
            }
        });

        campusmap_1c_gwangya = v.findViewById(R.id.campusmap_1c_gwangya);

        campusmap_1c_gwangya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("113"));
            }
        });

        campusmap_1c_gido1 = v.findViewById(R.id.campusmap_1c_gido1);

        campusmap_1c_gido1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("112"));
            }
        });

        campusmap_1c_gido2 = v.findViewById(R.id.campusmap_1c_gido2);

        campusmap_1c_gido2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("112"));
            }
        });

        campusmap_1c_byeonhyeok = v.findViewById(R.id.campusmap_1c_byeonhyeok);

        campusmap_1c_byeonhyeok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("111"));
            }
        });

        campusmap_1c_eunhye1 = v.findViewById(R.id.campusmap_1c_eunhye1);

        campusmap_1c_eunhye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("108"));
            }
        });

        campusmap_1c_eunhye2 = v.findViewById(R.id.campusmap_1c_eunhye2);

        campusmap_1c_eunhye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("108"));
            }
        });

        campusmap_1c_eunhye3 = v.findViewById(R.id.campusmap_1c_eunhye3);

        campusmap_1c_eunhye3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("108"));
            }
        });

        campusmap_1c_eunhye4 = v.findViewById(R.id.campusmap_1c_eunhye4);

        campusmap_1c_eunhye4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("108"));
            }
        });

        campusmap_1c_bichgwasogeum1 = v.findViewById(R.id.campusmap_1c_bichgwasogeum1);

        campusmap_1c_bichgwasogeum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("110"));
            }
        });

        campusmap_1c_bichgwasogeum2 = v.findViewById(R.id.campusmap_1c_bichgwasogeum2);

        campusmap_1c_bichgwasogeum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("110"));
            }
        });

        campusmap_1c_mideum = v.findViewById(R.id.campusmap_1c_mideum);

        campusmap_1c_mideum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("109"));
            }
        });

        campusmap_1c_haengham1 = v.findViewById(R.id.campusmap_1c_haengham1);

        campusmap_1c_haengham1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("104"));
            }
        });

        campusmap_1c_haengham2 = v.findViewById(R.id.campusmap_1c_haengham2);

        campusmap_1c_haengham2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("104"));
            }
        });

        campusmap_1c_jinri1 = v.findViewById(R.id.campusmap_1c_jinri1);

        campusmap_1c_jinri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("105"));
            }
        });

        campusmap_1c_jinri2 = v.findViewById(R.id.campusmap_1c_jinri2);

        campusmap_1c_jinri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("105"));
            }
        });

        campusmap_1c_malsseum1 = v.findViewById(R.id.campusmap_1c_malsseum1);

        campusmap_1c_malsseum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("106"));
            }
        });

        campusmap_1c_malsseum2 = v.findViewById(R.id.campusmap_1c_malsseum2);

        campusmap_1c_malsseum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("106"));
            }
        });

        campusmap_1c_malsseum3 = v.findViewById(R.id.campusmap_1c_malsseum3);

        campusmap_1c_malsseum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("106"));
            }
        });

        campusmap_1c_bedel1 = v.findViewById(R.id.campusmap_1c_bedel1);

        campusmap_1c_bedel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("102"));
            }
        });

        campusmap_1c_bedel2 = v.findViewById(R.id.campusmap_1c_bedel2);

        campusmap_1c_bedel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("102"));
            }
        });

        campusmap_1c_uihak1 = v.findViewById(R.id.campusmap_1c_uihak1);

        campusmap_1c_uihak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("103"));
            }
        });

        campusmap_1c_uihak2 = v.findViewById(R.id.campusmap_1c_uihak2);

        campusmap_1c_uihak2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapBuildingFragment.newInstance("103"));
            }
        });

        return v;
    }
}