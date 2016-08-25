package cn.tarena.weather.view;
import cn.tarena.weather.R;
import cn.tarena.weather.R.id;
import cn.tarena.weather.R.layout;
import cn.tarena.weather.updatable.BMainActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends BMainActivity implements OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadLayout(R.layout.activity_main);
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
		btn3=(Button) findViewById(R.id.button3);
		btn4=(Button) findViewById(R.id.button4);
		btn5=(Button) findViewById(R.id.button5);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				showWeatherBack(btn1.getText().toString());
				break;
			case R.id.button2:
				showWeatherBack(btn2.getText().toString());
				break;
			case R.id.button3:	
				showWeatherBack(btn3.getText().toString());
				break;
			case R.id.button4:	
				showWeatherBack(btn4.getText().toString());
				break;
			case R.id.button5:	
				showWeatherBack(btn5.getText().toString());
				break;
			}
	}
}
