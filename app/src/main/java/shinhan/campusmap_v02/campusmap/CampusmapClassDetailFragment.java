package shinhan.campusmap_v02.campusmap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import shinhan.campusmap_v02.R;
import shinhan.campusmap_v02.db.DBConnAsync;

public class CampusmapClassDetailFragment extends Fragment {

    private static final String ARG_BCODE = "param1111";
    private static final String ARG_CCODE = "param22222";

    private String mBcode;
    private String mCcode;

    private String campus, bname, cname, place, details;

    private TextView campusmap_detail_classname, campusmap_detail_place, campusmap_detail_details;

    private ImageView campusmap_detail_image;

    public CampusmapClassDetailFragment() {

    }

    public static CampusmapClassDetailFragment newInstance(String param1, String param2) {
        CampusmapClassDetailFragment fragment = new CampusmapClassDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BCODE, param1);
        args.putString(ARG_CCODE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBcode = getArguments().getString(ARG_BCODE);
            mCcode = getArguments().getString(ARG_CCODE);
        }

        Log.d("Test", mBcode+mCcode);

        // bcode, ccode 이용해서 시설 정보 가져오기
        DBConnAsync dbConnAsync = new DBConnAsync();
        String result = null;
        try {
            String info = mBcode + "," + mCcode;
            Log.i("bcode ccode", info);
            result = dbConnAsync.execute("campusmap/classSearch", "info=" + info).get();

            if(!result.isEmpty()) {
                Log.i("classInfo", result);
                String classArray[] = result.split(",");
                campus = classArray[0];
                bname = classArray[1];
                cname = classArray[2];
                place = classArray[3];
                details = classArray[4];
                details = details.replace("<br>", System.getProperty("line.separator"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campusmap_class_detail, container, false);
        Log.i("p1 p2", mBcode+","+mCcode+","+campus);


        campusmap_detail_classname = v.findViewById(R.id.campusmap_detail_classname);
        campusmap_detail_classname.setText("[" + campus + "캠퍼스 " + bname + "] " + cname);
        campusmap_detail_place = v.findViewById(R.id.campusmap_detail_place);
        campusmap_detail_place.setText(bname + " " + place);
        campusmap_detail_details = v.findViewById(R.id.campusmap_detail_details);
        campusmap_detail_details.setText(details);

        campusmap_detail_image = v.findViewById(R.id.campusmap_detail_image);

        //2캠
        if(mCcode.equals("20137")){   //종합자료실
            campusmap_detail_image.setImageResource(R.drawable.campusmap_2c_lib);
        }
        else if(mCcode.equals("20138")){   //종합자료실
            campusmap_detail_image.setImageResource(R.drawable.campusmap_2c_lib);
        }
        else if(mCcode.equals("20140")){    //정보검색실

        }
        else if(mCcode.equals("20141")){ //북카페

        }
        else if(mCcode.equals("20142")){ //소그룹실

        }
        else if(mCcode.equals("20143")){ //소그룹실

        }
        else if(mCcode.equals("20144")){ //소그룹실

        }
        else if(mCcode.equals("20145")) {   //자유열람실
            campusmap_detail_image.setImageResource(R.drawable.campusmap_2c_reading_room);
        }
        else if(mCcode.equals("20146")) {   //집중열람실
            campusmap_detail_image.setImageResource(R.drawable.campusmap_2c_reading_room);
        }
        else if(mCcode.equals("20151")){   //매점
            campusmap_detail_image.setImageResource(R.drawable.campusmap_detail_cafeteria);
        }
        else if(mCcode.equals("20152")){ //식당

        }
        else if(mCcode.equals("20301")){   //로뎀 강당
            campusmap_detail_image.setImageResource(R.drawable.campusmap_2c_lecture_hall);
        }
        else if(mCcode.equals("20501")){   //씨유
            campusmap_detail_image.setImageResource(R.drawable.campusmap_detail_cu);
        }
        else if(mCcode.equals("20502")){

        }
        else if(mCcode.equals("20701")){   //기숙사
            campusmap_detail_image.setImageResource(R.drawable.campusmap_2c_dorm);
        }

        //1캠
        else if(mCcode.equals("10101")){   //컨벤셜홀
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_convention_hall);
        }
        else if(mCcode.equals("10102")){   //소강당
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_lecture_hall);
        }
        else if(mCcode.equals("10113")) {   //우체국
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_post);
        }
        else if(mCcode.equals("10114")) {   //국민은행
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_kb);
        }
        else if(mCcode.equals("10115")){ //서점

        }
        else if(mCcode.equals("10117")) {   //씨유
            campusmap_detail_image.setImageResource(R.drawable.campusmap_detail_cu);
        }
        else if(mCcode.equals("10118")){   //푸드코트
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_cafeteria);
        }
        else if(mCcode.equals("10119")){ //에벤에셀 카페

        }
        else if(mCcode.equals("10136")) {   //더밥구내식당
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_thebob);
        }
        else if(mCcode.equals("10414")){ //카페

        }
        else if(mCcode.equals("10501")){ //북카페

        }
        else if(mCcode.equals("10503")) {   //제1자유열람실

        }
        else if(mCcode.equals("10508")) {   //제2자유열람실

        }
        else if(mCcode.equals("10509")){   //종합자료실

        }
        else if(mCcode.equals("10601")) {   //씨유
            campusmap_detail_image.setImageResource(R.drawable.campusmap_detail_cu);
        }
        else if(mCcode.equals("10602")){  //소풍간김밥

        }
        else if(mCcode.equals("11701")){   //기숙사
            campusmap_detail_image.setImageResource(R.drawable.campusmap_1c_dorm);
        }

        return v;
    }
}