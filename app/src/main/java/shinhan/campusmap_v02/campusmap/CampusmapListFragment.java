package shinhan.campusmap_v02.campusmap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import shinhan.campusmap_v02.ListviewAdapter;
import shinhan.campusmap_v02.MainActivity;
import shinhan.campusmap_v02.R;
import shinhan.campusmap_v02.db.DBConnAsync;

public class CampusmapListFragment extends Fragment {

    private static final String ARG_SEARCH = "param1";

    private String mSearch;

    private ListView campusmap_list_class_listview;
    private ListviewAdapter listviewAdapter;
    private String[] list;
    private ArrayList<String> bcode, ccode, bcname;

    public CampusmapListFragment() {

    }

    public static CampusmapListFragment newInstance(String param1) {
        CampusmapListFragment fragment = new CampusmapListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearch = getArguments().getString(ARG_SEARCH);
        }

        // class_info 모든 class_name 가져오기
        bcode = new ArrayList<String>();
        ccode = new ArrayList<String>();
        bcname = new ArrayList<String>();

        DBConnAsync dbConnAsync = new DBConnAsync();
        String result = null;
        try {
            result = dbConnAsync.execute("/campusmap/classAllList", "search=" + mSearch).get();

            if (!result.isEmpty()) {
                list = result.split(",");
            }

            for (int i = 0; i < list.length; i++) {
                if (i % 3 == 0) {
                    bcode.add(list[i]);
                } else if (i % 3 == 1) {
                    ccode.add(list[i]);
                } else if (i % 3 == 2) {
                    bcname.add(list[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campusmap_list, container, false);

        campusmap_list_class_listview = v.findViewById(R.id.campusmap_list_class_listview);

        listviewAdapter = new ListviewAdapter(getContext(), bcname);
        campusmap_list_class_listview.setAdapter(listviewAdapter);

        campusmap_list_class_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapClassDetailFragment
                        .newInstance(bcode.get(position), ccode.get(position)));
            }
        });

        return v;
    }
}