package com.example.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.fragment.tools.ToRoundBitmap;
import com.example.playweeks.R;
import com.example.playweeks.R.layout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ViewPagerFragmentLogin  extends Fragment implements OnClickListener{

	private View view;
	
	public static final int TAKE_PHOTO = 0;
	public static final int TAKE_PHOTO1 = 1;
	public static final int CROP_PHOTO = 2;
	public static final int CROP_PHOTO1 = 3;

	private ImageView pictureImage;
	private TextView nameText;
	private AlertDialog.Builder dialog;
	private Uri imageUri,originalUri;
	private EditText editText;

	private LinearLayout nameLayout;
	private LinearLayout pictureLayout;
	private Context mContext;


	public ViewPagerFragmentLogin(Context mContext){

        super();
        this.mContext = mContext;
    }
    /**

     * 覆盖此函数，先通过inflater inflate函数得到view最后返回

     */

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	view = inflater.inflate(R.layout.main_tab_my_page, container, false);
    	init();
        return view;

    }
    
    private void init() {
    	nameLayout = (LinearLayout) view.findViewById(R.id.set_name);
		pictureLayout = (LinearLayout) view.findViewById(R.id.picture);
    	
		nameText = (TextView) view.findViewById(R.id.name_);
		pictureImage = (ImageView) view.findViewById(R.id.picture_);
		
		nameLayout.setOnClickListener(this);
		pictureLayout.setOnClickListener(this);
    }
    
    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
    	if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
    		// this device has a camera
    		return true;
    	} else {
    		// no camera on this device
    		return false;
    	}
    }
    @Override
	public void onClick(View arg0) {
    	String[] str;
    	if(checkCameraHardware(mContext)){
    	 str = new String[]{getString(R.string.take_photo), getString(R.string.gallery)};
    	}else{
    		 str = new String[]{getString(R.string.gallery)};	
    	}
		switch (arg0.getId()) {	
		case R.id.picture:
			dialog = new AlertDialog.Builder(getActivity());
			dialog.setTitle(getString(R.string.photo_src));
			dialog.setNegativeButton(getString(R.string.cancle), null);
		
				dialog.setItems(str,	
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
							switch (arg1) {
							case 0:
								File outputImage = new File(Environment
										.getExternalStorageDirectory(),
										"picture.jpg");
								try {
									if (outputImage.exists()) {
										outputImage.delete();
									}
									outputImage.createNewFile();
								} catch (IOException e) {
									e.printStackTrace();
								}
								imageUri = Uri.fromFile(outputImage);
								Intent intent = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);

								intent.putExtra(MediaStore.EXTRA_OUTPUT,
										imageUri);
								startActivityForResult(intent, TAKE_PHOTO1);

								break;
							case 1:

								Intent intent1 = new Intent(
										Intent.ACTION_PICK,
										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
								// intent1.putExtra(MediaStore.EXTRA_OUTPUT,
								// imageUri);
								startActivityForResult(intent1, CROP_PHOTO1);

								break;
							}
							// 照片的原始资源地址

						}
					});
			dialog.show();

			//pedometerDB.updateUser(user);
			break;
		case R.id.set_name:
			dialog = new AlertDialog.Builder(getActivity());
			editText = new EditText(getActivity());
			editText.setSingleLine(true);
			dialog.setView(editText);

			dialog.setTitle(getString(R.string.inputname));
			dialog.setPositiveButton(getString(R.string.truely),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							nameText.setText(editText.getText().toString());
							//user.setName(editText.getText().toString());
							//pedometerDB.updateUser(user);
						}
					});
			dialog.show();
			break;

		case R.id.male:

		//	user.setSex("男");

			break;
		case R.id.female:
			//user.setSex("女");
			break;
		}

	}
    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// this.pictureIntent = data;

		switch (requestCode) {
		case TAKE_PHOTO:
			if (resultCode == getActivity().RESULT_OK) {

				Bitmap bitmap;
				try {
					bitmap = BitmapFactory.decodeStream(getActivity()
							.getContentResolver().openInputStream(imageUri));
					pictureImage.setImageBitmap(bitmap);
				//	user.setPicture(Environment.getExternalStorageDirectory()
				//			+ "/picture.jpg");
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}

			}
			break;
		case TAKE_PHOTO1:
			if (resultCode == getActivity().RESULT_OK) {
				
				Intent intent = new Intent("com.android.camera.action.CROP");

				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);

				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);

			}
			break;
		case CROP_PHOTO:
			if (resultCode == getActivity().RESULT_OK) {
				try {
			//		user.setPicture(originalUri.toString());
					Bitmap bitmap = ToRoundBitmap.toRoundBitmap(BitmapFactory
							.decodeStream(getActivity().getContentResolver()
									.openInputStream(originalUri)));
					pictureImage.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			break;
		case CROP_PHOTO1:
			if (resultCode == getActivity().RESULT_OK) {
				
				originalUri = data.getData();
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(originalUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra("crop", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, originalUri);
				startActivityForResult(intent, CROP_PHOTO);
			}
			break;
		default:
			break;
		}
	}
    

}


