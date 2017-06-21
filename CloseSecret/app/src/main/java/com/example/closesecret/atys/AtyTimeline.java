package com.example.closesecret.atys;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.closesecret.Config;
import com.example.closesecret.R;
import com.example.closesecret.ld.MyContacts;
import com.example.closesecret.net.Message;
import com.example.closesecret.net.MyMessage;
import com.example.closesecret.net.Timeline;
import com.example.closesecret.net.UploadContacts;
import com.example.closesecret.tools.MD5Tool;

public class AtyTimeline extends ListActivity {

    private String phone_num, token, phone_md5;
	private AtyTimelineMessageListAdapter adapter = null;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
			//去除状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setTheme(R.style.CustomTitleBarTheme);
			super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			setContentView(R.layout.aty_timeline);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.hometitlebtn);



			adapter = new AtyTimelineMessageListAdapter(this);
			setListAdapter(adapter);

			phone_num = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
			token = getIntent().getStringExtra(Config.KEY_TOKEN);
			phone_md5 = MD5Tool.md5(phone_num);

		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));

		new UploadContacts(phone_md5, token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallback() {

			@Override
			public void onSuccess() {
				loadMessage();
				//Toast.makeText(AtyTimeline.this, "1", Toast.LENGTH_LONG).show();
				pd.dismiss();
			}
		}, new UploadContacts.FailCallback() {

			@Override
			public void onFail(int errorCode) {
				pd.dismiss();

				if (errorCode == Config.RESULT_STATUS_INVALID_TOKEN) {
					startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
					//Toast.makeText(AtyTimeline.this, "2", Toast.LENGTH_LONG).show();
					finish();
				} else {
					loadMessage();
					//Toast.makeText(AtyTimeline.this, "3", Toast.LENGTH_LONG).show();

				}
			}
		});


	}

	private void loadMessage(){
		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));

		new Timeline(phone_md5, token, 1, 20, new Timeline.SuccessCallback() {

			@Override
			public void onSuccess(int page, int perpage, List<Message> timeline) {
				pd.dismiss();

				adapter.clear();
				adapter.addAll(timeline);
			}
		}, new Timeline.FailCallback() {

			@Override
			public void onFail(int errorCode) {
				pd.dismiss();

				if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN) {
					startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
					finish();
				}else{
					Toast.makeText(AtyTimeline.this, R.string.fail_to_load_timeline_data, Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Message msg = adapter.getItem(position);
		Intent i = new Intent(this, AtyMessage.class);
		i.putExtra(Config.KEY_MSG, msg.getMsg());
		i.putExtra(Config.KEY_MSG_ID, msg.getMsgId());
		i.putExtra(Config.KEY_PHONE_MD5, msg.getPhone_md5());
		i.putExtra(Config.KEY_MSG_TIME, msg.getMsg_time());
		i.putExtra(Config.KEY_TOKEN, token);
		startActivity(i);
	}

	public void fab(View v) {
		Intent i = new Intent(AtyTimeline.this, AtyPublish.class);
		i.putExtra(Config.KEY_PHONE_MD5, phone_md5);
		i.putExtra(Config.KEY_TOKEN, token);
		startActivityForResult(i, 0);
	}

	public void fabup(View v) {
		loadMessage();
	}

	public void fabmy(View v) {
		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));

		new MyMessage(phone_md5, token, 1, 20, new MyMessage.SuccessCallback() {

			@Override
			public void onSuccess(int page, int perpage, List<Message> my_message) {
				pd.dismiss();

				adapter.clear();
				adapter.addAll(my_message);
			}
		}, new MyMessage.FailCallback() {

			@Override
			public void onFail(int errorCode) {
				pd.dismiss();

				if (errorCode==Config.RESULT_STATUS_INVALID_TOKEN) {
					startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
					finish();
				}else{
					Toast.makeText(AtyTimeline.this, R.string.fail_to_load_timeline_data, Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case Config.ACTIVITY_RESULT_NEED_REFRESH:
			loadMessage();
			break;
		default:
			break;
		}
	}
}







//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menu_aty_timeline, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.menuShowAtyPublish:
//			Intent i = new Intent(AtyTimeline.this, AtyPublish.class);
//			i.putExtra(Config.KEY_PHONE_MD5, phone_md5);
//			i.putExtra(Config.KEY_TOKEN, token);
//			startActivityForResult(i, 0);
//			break;
//		default:
//			break;
//		}
//
//		return true;
//	}




//	GestureDetector gd;
//	@Override
//	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//						   float velocityY) {
//		// TODO Auto-generated method stub
//		if(e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 0)
//		{
//
//			Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
//		}
//		else if (e2.getX()-e1.getX() > 20 && Math.abs(velocityX) > 0) {
//
//			Intent intent = new Intent(AtyTimeline.this,AtyMessage.class);
//			startActivity(intent);
//			Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//		// TODO Auto-generated method stub
//		return gd.onTouchEvent(event);
//	}
//
//	@Override
//	public void onShowPress(MotionEvent e) {
//
//	}
//
//	@Override
//	public boolean onDown(MotionEvent e){
//		return true;
//	}
//
//	@Override
//	public void onLongPress(MotionEvent e) {
//
//	}
//
//	@Override
//	public boolean onSingleTapUp(MotionEvent e) {
//		return true;
//	}
//
//	@Override
//	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//		return true;
//	}



//		gd = new GestureDetector((GestureDetector.OnGestureListener)this);
//		RelativeLayout test = (RelativeLayout) findViewById(R.id.id_timeline);
//		test.setOnTouchListener(this);
//		test.setLongClickable(true);