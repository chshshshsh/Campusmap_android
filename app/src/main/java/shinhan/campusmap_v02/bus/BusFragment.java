package shinhan.campusmap_v02.bus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import shinhan.campusmap_v02.R;

public class BusFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BusFragment() {
        // Required empty public constructor
    }

    public static BusFragment newInstance(String param1, String param2) {
        BusFragment fragment = new BusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bus, container, false);

        final TextView bus_main_nameTxt = v.findViewById(R.id.bus_main_nameTxt);
        final ImageView bus_main_img = v.findViewById(R.id.bus_main_img);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bus_main_2cBtn :
                        bus_main_nameTxt.setText("지행출발 버스시간표");
                        bus_main_img.setImageResource(R.drawable.bus_stationstart);
                        break ;
                    case R.id.bus_main_2cDomBtn :
                        bus_main_nameTxt.setText("2캠출발 버스시간표");
                        bus_main_img.setImageResource(R.drawable.bus_domstart);
                        break ;
                    case R.id.bus_main_1c2cBtn :
                        bus_main_nameTxt.setText("1캠↔2캠 버스시간표");
                        bus_main_img.setImageResource(R.drawable.bus_c1c2);

                        break ;
                    case R.id.bus_main_llsanBtn :
                        bus_main_nameTxt.setText("일산행 버스시간표");
                        bus_main_img.setImageResource(R.drawable.bus_llsan);
                        break ;
                }
            }
        } ;

        Button bus_main_2cBtn = v.findViewById(R.id.bus_main_2cBtn) ;
        bus_main_2cBtn.setOnClickListener(onClickListener) ;
        Button bus_main_2cDomBtn = v.findViewById(R.id.bus_main_2cDomBtn) ;
        bus_main_2cDomBtn.setOnClickListener(onClickListener) ;
        Button bus_main_1c2cBtn = v.findViewById(R.id.bus_main_1c2cBtn) ;
        bus_main_1c2cBtn.setOnClickListener(onClickListener) ;
        Button bus_main_llsanBtn = v.findViewById(R.id.bus_main_llsanBtn) ;
        bus_main_llsanBtn.setOnClickListener(onClickListener) ;

        return v;
    }
}