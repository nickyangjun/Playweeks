package com.example.playweeks;


import java.io.File;

import cn.bmob.v3.Bmob;

import com.example.update.DownloadTask;
import com.example.update.UpdateInfo;
import com.example.update.UpdateInfoService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends Activity{
	private ImageView iv_welcome;
	LinearLayout ll;
	private TextView tv_ver;
	private String version;
	private UpdateInfo info;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		iv_welcome = (ImageView) findViewById(R.id.welcome);
		Animation mAnimation = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.welcome_scale);
		iv_welcome.startAnimation(mAnimation);
		
		ll = (LinearLayout) findViewById(R.id.weclome_layout);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
		alphaAnimation.setDuration(2000);
		ll.startAnimation(alphaAnimation);
		
		tv_ver = (TextView) findViewById(R.id.app_version);
		tv_ver.setText(getString(R.string.app_version)+getVersion());
		
		version = getVersion();
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage(getString(R.string.is_downloader));
		
		//checking the new version
		new Thread()
		{
			public void run() 
			{
				try
				{
					UpdateInfoService updateInfoService = new UpdateInfoService(WelcomeActivity.this);
					info = updateInfoService.getUpdateInfo(R.string.serverUrl);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			};
		}.start();
		
		
		new Thread()
		{
			public void run() 
			{
				try
				{
					sleep(3000);
					handler.sendEmptyMessage(0);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			};
		}.start();
		
	}
	
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg) 
		{
			if(isNeedUpdate(version))
			{
				showUpdateDialog();
			}
		};
	};
	
	private void showUpdateDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.updatetitle);
		builder.setMessage(info.getDescription());
		builder.setCancelable(false);
		
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
					File dir = new File(Environment.getExternalStorageDirectory(), "/Ring_F3/update");
					if(!dir.exists())
					{
						dir.mkdirs();
					}
					String apkPath = Environment.getExternalStorageDirectory() + "/Ring_F3/update/new.apk";
					UpdateTask task = new UpdateTask(info.getUrl(), apkPath);
					progressDialog.show();
					new Thread(task).start();
				}
				else
				{
					Toast.makeText(WelcomeActivity.this, R.string.nosdcard, Toast.LENGTH_SHORT).show();
					loadMainUI();
				}
			}
		});
		builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				loadMainUI();
			}
			
		});
		builder.create().show();
	}
	
	private String getVersion()
	{
		try
		{
			PackageManager packageManager = getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
			
			return packageInfo.versionName;
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
			return "0";
		}
	}
	
	private boolean isNeedUpdate(String version)
	{
		if(info == null)
		{
			loadMainUI();
			return false;
		}
		String v = info.getVersion();
		if(v.equals(version))
		{
			loadMainUI();
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private void loadMainUI()
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	private void install(File file)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		finish();
		startActivity(intent);
	}
	
	
	class UpdateTask implements Runnable
	{
		private String path;
		private String filePath;
		
		public UpdateTask(String path, String filePath)
		{
			this.path = path;
			this.filePath = filePath;
		}

		@Override
		public void run()
		{
			try
			{
				File file = DownloadTask.getFile(path, filePath, progressDialog);
				progressDialog.dismiss();
				install(file);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				progressDialog.dismiss();
				Toast.makeText(WelcomeActivity.this, R.string.updatefail, Toast.LENGTH_SHORT).show();
				loadMainUI();
			}
		}
		
	}

}
