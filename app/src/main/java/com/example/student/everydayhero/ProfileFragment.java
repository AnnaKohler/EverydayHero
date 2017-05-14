package com.example.student.everydayhero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anna on 23.03.2017.
 */


public class ProfileFragment extends Fragment {

    public static String EXTRA_PROFILE_EDIT="com.everydayhero.profile_edit";
    private DBHandler mDBHandeler;

    private User mUser;
    private TextView txtName;
    private TextView txtAge;
    private TextView txtBMI;
    private ImageView avatar;


    public static ProfileFragment newInstance(boolean edit) {

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_PROFILE_EDIT, edit);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean edit = getArguments().getBoolean(EXTRA_PROFILE_EDIT); //TODO: Добавить возможность редактирования профиля
        mDBHandeler=new DBHandler(getContext());
        mUser=mDBHandeler.getUserInfo();

        //svgAvatar = SVGParser.getSVGFromResource(getContext().getResources(), R.raw.woman4);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        avatar=(ImageView)v.findViewById(R.id.imageView);
        txtName=(TextView)v.findViewById(R.id.txtName);
        txtAge=(TextView)v.findViewById(R.id.dataProfileAge);
        txtBMI=(TextView)v.findViewById(R.id.dataProfileBMI);


        txtName.setText(mUser.getName());
        txtAge.setText(mUser.getAge()+"");

        txtBMI.setText((mUser.getWeight() /(mUser.getHeight()* mUser.getHeight())+""));

        /*

        //Setting up the avatar
        //SVG vectorAvatar= SVGParser.getSVGFromResource(getResources(), R.raw.woman4);


        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        int dispHeight=size.x;
        int avatarSize=dispHeight / 4;

        /*Picture firstPicture=svgAvatar.getPicture();
        Bitmap bitmap = Bitmap.createBitmap(avatarSize, avatarSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Picture newResizePicture = new Picture();
        canvas = newResizePicture.beginRecording(avatarSize, avatarSize);
        canvas.drawPicture(firstPicture, new Rect(0,0, avatarSize, avatarSize));

        newResizePicture.endRecording(); //TODO: вынести в отдельный метод
        Drawable vectorDrawable = new PictureDrawable(newResizePicture); */

        //avatar.setImageDrawable(vectorDrawable);
        return v;
    }
}
