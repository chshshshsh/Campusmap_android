package shinhan.campusmap_v02.campusmap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import shinhan.campusmap_v02.ListviewAdapter;
import shinhan.campusmap_v02.MainActivity;
import shinhan.campusmap_v02.R;
import shinhan.campusmap_v02.db.DBConnAsync;
import shinhan.campusmap_v02.db.DBConnImage;
import shinhan.campusmap_v02.db.DBConnImageDownload;

public class CampusmapBuildingFragment extends Fragment {

    private static final String ARG_BCODE = "";

    // 건물 정보
    private String mBcode, mBname, mDetails, mEp;
    private int mCampus;

    private TextView campusmap_building_title, campusmap_building_details;

    // 건물 관련 시설 리스트
    private ListView campusmap_building_class_listview;
    private String[] classArray;
    private ListviewAdapter listviewAdapter;
    private ArrayList<String> ccodeArray = new ArrayList<String>();;
    private ArrayList<String> cnameArray = new ArrayList<String>();;

    // 길찾기
    private FloatingActionButton campusmap_route_btn;

    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;
    private String mSp;

    // 사진 추가
    private ImageView campusmap_building_mainImage;
    private ImageButton campusmap_building_picaddBtn;

    final String TAG = getClass().getSimpleName();
    private final int TAKE_PICTURE_CODE = 672;
    private final int GALLERY_CODE = 1112;
    private static Uri photoUri;
    private File file;
    private String img_path;

    // 메인 이미지
    private Bitmap representImg;
    private String imageRoutes[];
    private String images[];

    private RecyclerView campusmap_building_recyclerview;
    private ArrayList<String> ImgUrl = new ArrayList<String>();
    private GridLayoutManager Manager;
    private Adapter adapter;

    public CampusmapBuildingFragment() {

    }

    public static CampusmapBuildingFragment newInstance(String bcode) {
        CampusmapBuildingFragment fragment = new CampusmapBuildingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BCODE, bcode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBcode = getArguments().getString(ARG_BCODE);
        }

        // 빌딩 정보 가져오기
        DBConnAsync dbConnAsync = new DBConnAsync();
        String result = null;
        try {
            result = dbConnAsync.execute("campusmap/buildingSearch", "bcode=" + mBcode).get();

            Log.d("Test", result);

            if(!result.isEmpty()) {
                String buildingArray[] = result.split(",");
                mBcode = buildingArray[0];
                mBname = buildingArray[1];
                mCampus = Integer.parseInt(buildingArray[2]);
                mDetails = buildingArray[3];
                mDetails = mDetails.replace("<br>", System.getProperty("line.separator"));
                mEp = buildingArray[4] + "," + buildingArray[5];
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        // 빌딩 관련 클래스 정보 가져오기
        DBConnAsync dbConnAsync2 = new DBConnAsync();
        result = null;
        try {
            result = dbConnAsync2.execute("campusmap/classBcodeList", "bcode=" + mBcode).get();

            if(!result.isEmpty()) {
                classArray = result.split(",");
            }

            Log.d("Test", result);

            for (int i = 0; i < classArray.length; i++){
                if (i % 2 == 0) {
                    ccodeArray.add(classArray[i]);
                }
                else if(i % 2 == 1) {
                    cnameArray.add(classArray[i]);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        // 대표 사진 경로 가져오기
        DBConnAsync dbConnAsync3 = new DBConnAsync();
        String representImageRoute = null;
        try {
            representImageRoute = dbConnAsync3.execute("image/sendRepresentImage", "bcode=" + mBcode).get();

            Log.d("Test", "represent" + representImageRoute);
        } catch(Exception e){
            e.printStackTrace();
        }

        // 위의 대표 사진 경로를 이용하여 사진 다운로드 하기
        DBConnImageDownload dbConnImageDownload = new DBConnImageDownload();
        try{
            representImg = dbConnImageDownload.execute(representImageRoute).get();
        }catch(Exception e){
            e.printStackTrace();
        }

        Log.d("Test", "represent  " + representImg);

        // 그리드뷰에 띄울 이미지 경로들 가져오기
        DBConnAsync dbConnAsync4 = new DBConnAsync();
        String representImageRoutes = null;
        try {
            representImageRoutes = dbConnAsync4.execute("image/sendImages", "bcode=" + mBcode).get();

            if(!representImageRoutes.isEmpty()) {
                imageRoutes = representImageRoutes.split(",");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        Log.d("Test", "images" + representImageRoutes);

        for(int i=0;i<imageRoutes.length;i++){
            ImgUrl.add("http://172.30.1.30:8080" + imageRoutes[i]);
        }

        //권한 체크
        TedPermission.with(getActivity().getApplicationContext())
                .setPermissionListener(PermissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다")
                .setDeniedMessage("거부하셨습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_campusmap_building, container, false);

        campusmap_building_title = v.findViewById(R.id.campusmap_building_title);
        campusmap_building_title.setText("[" + mCampus + "캠퍼스] " + mBname);

        campusmap_building_details = v.findViewById(R.id.campusmap_building_details);
        campusmap_building_details.setText(mDetails);

        campusmap_building_class_listview = v.findViewById(R.id.campusmap_building_class_listview);
        listviewAdapter = new ListviewAdapter(getContext(), cnameArray);
        campusmap_building_class_listview.setAdapter(listviewAdapter);
        setListViewHeightBasedOnItems(campusmap_building_class_listview);

        campusmap_building_class_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Log.i("Test", mBcode+","+ccodeArray.get(position)+","+cnameArray.get(position));
                ((MainActivity)getActivity()).replaceCampusmapFragment(CampusmapClassDetailFragment.newInstance(mBcode, ccodeArray.get(position)));
            }
        });

        campusmap_route_btn = v.findViewById(R.id.campusmap_route_btn);
        campusmap_route_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자의 현재 위치 수신을 위한 세팅
                locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
                //사용자의 현재 위치
                Location userLocation = getMyLocation();
                if( userLocation != null ) {
                    double latitude = userLocation.getLatitude();
                    double longitude = userLocation.getLongitude();
                    mSp = latitude + "," + longitude;
                }

                try{
                    String url = "kakaomap://route?sp=" + mSp + "&ep=" + mEp + "&by=FOOT";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }catch(Exception e){
                    String url = "market://details?id=net.daum.android.map";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        Log.d("Test", "gridview start");

        // 사진 리사이클러 뷰로 보여주기
        campusmap_building_recyclerview = (RecyclerView)v.findViewById((R.id.campusmap_building_recyclerview));
        Log.d("Test", "gridview start");

        Manager = new GridLayoutManager(getContext(),2);
        //Manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        campusmap_building_recyclerview.setLayoutManager(Manager);
        adapter = new Adapter(ImgUrl, getActivity());
        campusmap_building_recyclerview.setAdapter(adapter);

        campusmap_building_mainImage = v.findViewById(R.id.campusmap_building_mainImage);
        campusmap_building_mainImage.setImageBitmap(representImg);

        // 사진 추가 + 버튼
        campusmap_building_picaddBtn = v.findViewById(R.id.campusmap_building_picaddBtn);
        campusmap_building_picaddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                    .setTitle("사진 업로드").setIcon(R.drawable.campusmap_pic_gallery)
                        .setMessage("사진이 부적절할 시 삭제될 수 있습니다.")
                    .setNeutralButton("사진 촬영",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 사진 촬영 클릭
                                takePicture();
                            }
                        })
                    .setPositiveButton("앨범 선택",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 앨범에서 선택
                                selectGallery();
                            }
                        })
                    .show();
            }
        });

        return v;
    }

    PermissionListener PermissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };

    // 스크롤뷰안에 리스트뷰 높이 조절 하는 함수
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            //setDynamicHeight(listView);
            return true;

        } else {
            return false;
        }
    }

    // 사용자의 위치를 수신
    private Location getMyLocation() {
        Location currentLocation = null;

        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            System.out.println("////////////사용자에게 권한을 요청해야함");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    this.REQUEST_CODE_LOCATION);
            getMyLocation(); //이건 써도되고 안써도 되지만, 전 권한 승인하면 즉시 위치값 받아오려고 썼습니다!
        }
        else {
            System.out.println("////////////권한요청 안해도됨");

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                double lng = currentLocation.getLongitude();
                double lat = currentLocation.getLatitude();
            }
        }
        return currentLocation;
    }
    //===========================================사진=================================================

    // 사진 촬영하는 경우
    public void takePicture() {
        if (file == null) {
            try {
                file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Uri fileUri = FileProvider.getUriForFile(getActivity().getApplicationContext(),"shinhan.campusmap_v02.fileprovider", file);
        Intent tIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        tIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        tIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (tIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(tIntent, TAKE_PICTURE_CODE);
        }
    }

    // 촬영한 사진 이미지 파일로 만들기
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        if(!image.exists()){
            storageDir.mkdirs();
        }

        // Save a file: path for use with ACTION_VIEW intents
        img_path = image.getAbsolutePath();
        return image;
    }

    // 갤러리에서 가져오는 경우
    private void selectGallery() {
        Intent gIntent = new Intent(Intent.ACTION_PICK);
        gIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        gIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (gIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(gIntent, GALLERY_CODE);
        }
    }

    // 사진 보여주기
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Bitmap bitmap = null;

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == TAKE_PICTURE_CODE){
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 8;
//                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//
//                if (bitmap != null) {
//                    ExifInterface exif = null;
//                    try {
//                        exif = new ExifInterface(img_path);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    int exifOrientation, exifDegree;
//
//                    if (exif != null) {
//                        exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
//                                ExifInterface.ORIENTATION_UNDEFINED);
//                        exifDegree = exifOrientationToDegress(exifOrientation);
//                    }
//                    else {
//                        exifDegree = 0;
//                    }
//
//                    campusmap_building_mainImage.setImageBitmap(rotateImage(bitmap, exifDegree));
//                }
            }
            else if(requestCode == GALLERY_CODE){
                Uri selectedImageUri = intent.getData();
                img_path = getImagePathToUri(selectedImageUri);
//                campusmap_building_mainImage.setImageURI(selectedImageUri);
            }

            DBConnImage dbConnImage = new DBConnImage();
            String result = null;
            try {
                result = dbConnImage.execute(img_path, mBcode).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 토스트 메시지 띄우기
            if(result.equals("true")){
                Toast.makeText(getActivity().getApplicationContext(), "사진 저장 성공", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity().getApplicationContext(), "사진 저장 실패", Toast.LENGTH_SHORT).show();
            }

        }
    }

    // 사진의 돌아간 각도 계산하는 함수
    private int exifOrientationToDegress(int exifOrientation){
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    // 이미지 경로 받아오기
    public String getImagePathToUri(Uri data) {
        //사용자가 선택한 이미지의 정보를 받아옴
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.d("test", imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
        //this.imageName = imgName;

        return imgPath;
    }

    // 돌아간 사진 바르게 세우기
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
}
