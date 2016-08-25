package cn.tarena.weather.view;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity{
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			loadLayout();
			initView();
			initData();
			initListener();
			
		}
		public void loadLayout(){
		}
		public void initView(){};
		public void initData(){};
		public void initListener(){}
}
