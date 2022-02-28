package shinhan.campusmap_v02.link;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import shinhan.campusmap_v02.ListviewAdapter;
import shinhan.campusmap_v02.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkFragment extends Fragment {

    ListView link_main_list;
    private ArrayList<String> linkname;

    public LinkFragment() {
    }

    public static LinkFragment newInstance(String param1, String param2) {
        LinkFragment fragment = new LinkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_link, container, false);
        link_main_list = v.findViewById(R.id.link_main_list);

        linkname = new ArrayList<String>();
        linkname.add("신한대학교");
        linkname.add("종합정보시스템");
        linkname.add("사이버캠퍼스");
        linkname.add("중앙도서관");
        linkname.add("취창업지원처");
        linkname.add("인터넷증명발급");
        linkname.add("학생상담센터");
        linkname.add("장애학생지원센터");
        linkname.add("Office365 이용안내");
        linkname.add("평생교육원[제1,2캠퍼스]");
        linkname.add("국제교류처");
        linkname.add("경기도북부여성비전센터");
        linkname.add("성희롱·성폭력상담실");
        linkname.add("교수학습센터");
        linkname.add("기숙사");

        ListviewAdapter LinkTxtAdapter = new ListviewAdapter(getContext(), linkname);
        link_main_list.setAdapter(LinkTxtAdapter);

        link_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent mIntent = new Intent(Intent.ACTION_VIEW);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position ==  0) {
                    Uri u = Uri.parse("http://www.shinhan.ac.kr/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  1) {
                    Uri u = Uri.parse("http://stins.shinhan.ac.kr/irj/portal");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  2) {
                    Uri u = Uri.parse("http://cyber.shinhan.ac.kr/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  3) {
                    Uri u = Uri.parse("http://lib.shinhan.ac.kr/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  4) {
                    Uri u = Uri.parse("https://job.shinhan.ac.kr/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  5) {
                    Uri u = Uri.parse("http://stins.shinhan.ac.kr/irj/portal");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  6) {
                    Uri u = Uri.parse("http://sh.certpia.com/default.asp");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  7) {
                    Uri u = Uri.parse("https://counsel.shinhan.ac.kr/counsel/index.do");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  8) {
                    Uri u = Uri.parse("https://support.shinhan.ac.kr/support/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  9) {
                    Uri u = Uri.parse("https://www.shinhan.ac.kr/kr/193/subview.do");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  10) {
                    Uri u = Uri.parse("https://life.shinhan.ac.kr/main/index.jsp");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  11) {
                    Uri u = Uri.parse("https://new.shinhan.ac.kr/globle/index.do");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  12) {
                    Uri u = Uri.parse("http://cafe.daum.net/ivisioncenter");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  13) {
                    Uri u = Uri.parse("https://helper.shinhan.ac.kr/sung/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  14) {
                    Uri u = Uri.parse("https://ctl.shinhan.ac.kr/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
                if (position ==  14) {
                    Uri u = Uri.parse("http://www.shinhandorm.ac.kr/");
                    mIntent.setData(u);
                    startActivity(mIntent);
                }
            }
        });

        return v;
    }
}