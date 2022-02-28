package shinhan.campusmap_v02.campusmap;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import shinhan.campusmap_v02.MainActivity;
import shinhan.campusmap_v02.R;

public class CampusmapFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageButton campusmap_imagebutton_microphone;
    private EditText campusmap_edittext_destination;
    private ImageButton campusmap_imagebutton_search;
    private FloatingActionButton campusmap_change_btn;

    private FrameLayout campusmap_framelayout;
    private FragmentTransaction transaction;

    private Integer Clicknum = 0;

    private Intent i;
    private static final int RESULT_SPEECH = 1; // REQUEST_CODE로 쓰임
    TextToSpeech tts;

    public CampusmapFragment() {
        // Required empty public constructor
    }

    public static CampusmapFragment newInstance(String param1, String param2) {
        CampusmapFragment fragment = new CampusmapFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campusmap, container, false);

        campusmap_imagebutton_microphone = v.findViewById(R.id.campusmap_imagebutton_microphone);
        campusmap_edittext_destination = v.findViewById(R.id.campusmap_edittext_destination);
        campusmap_imagebutton_search = v.findViewById(R.id.campusmap_imagebutton_search);
        campusmap_framelayout = v.findViewById(R.id.campusmap_framelayout);
        campusmap_change_btn = v.findViewById(R.id.campusmap_change_btn);

        campusmap_imagebutton_microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // Intent 생성
                i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getActivity().getPackageName()); // 호출한 패키지
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR"); // 인식할 언어를 설정한다.
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "검색어를 말해주세요"); // 유저에게 보여줄 문자

                try {
                    startActivityForResult(i, RESULT_SPEECH);
                }catch(ActivityNotFoundException e) {
                    Toast.makeText(getContext(),"Speech To Text를 지원하지 않습니다.",Toast.LENGTH_SHORT).show();
                    e.getStackTrace();
                }
            }
        });

        campusmap_edittext_destination.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapListFragment.newInstance("blank"));
                return false;
            }
        });

        campusmap_imagebutton_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = String.valueOf(campusmap_edittext_destination.getText());
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapListFragment.newInstance(search));
            }
        });

        campusmap_change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicknum++;
                if(Clicknum % 2 == 0)
                    ((MainActivity)getActivity()).replaceCampusmapFragment(Campusmap1cFragment.newInstance("b", "b"));
                else
                    ((MainActivity)getActivity()).replaceCampusmapFragment(Campusmap2cFragment.newInstance("b", "b"));
            }
        });

        ((MainActivity)getActivity()).replaceCampusmapFragment(Campusmap1cFragment.newInstance("a", "b"));

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == getActivity().RESULT_OK  && (requestCode == RESULT_SPEECH)) {
            ArrayList<String> sstResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String result_sst = sstResult.get(0);
            campusmap_edittext_destination.setText("" + result_sst); // 텍스트 뷰에 보여준다.
            String search = String.valueOf(campusmap_edittext_destination.getText());
            ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapListFragment.newInstance(search));
        }
    }
}