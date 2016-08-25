package cn.tarena.weather.updatable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class MovingPictureView extends View implements Runnable{
	float downXValue;
	long downTime;
	private float lastTouchX, lastTouchY;
	private boolean hasMoved = false;
	// 鐢ㄤ簬鏄剧ず鐨勫浘
	Bitmap bitmap;

	// 鍥剧墖鍧愭爣杞寲鐨勭嚎绋嬫槸鍚﹁繍琛岋紝false锛屽垯鍋滄杩愯
	public static boolean isRuning = true;

	// 鍥剧墖鐨凩fet锛孴op锟�
	int left = 100;
	int top = 20;

	// 鐢ㄤ簬鍚屾绾跨▼
	Handler handler;
	MoveHandler movehandler;
	int sleepSeconds;
	int goBackType;

	// 鍚戦噺锛屽彲浠ワ拷?杩囪皟鑺傛涓や釜鍙橀噺璋冭妭绉诲姩閫熷害
	int dx = 1;
	int dy = 1;
	
	public int index;
	public Thread moving;
	public boolean isstarted = false;//鏄惁宸插惎鍔ㄧ嚎锟�

	/**
	 * 
	 * @param context 
	 * @param resource 鍥剧墖璧勬簮
	 * @param left 灞呭乏
	 * @param top 灞呬笂
	 * @param sleepSeconds 绉诲姩鏃堕棿闂撮殧
	 */
	public MovingPictureView(Context context,int resource,int left,int top,int sleepSeconds) {
		super(context);
		this.left = left;
		this.top = top;
		this.sleepSeconds = sleepSeconds;
		this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		bitmap = BitmapFactory.decodeResource(getResources(), resource);
		handler = new Handler();
		movehandler = new MoveHandler();
		//new Thread(this).start();
	}

	
	public void move(){
		moving = new Thread(this);
		System.out.println("mov1.bitmap");
		moving.start();
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// 灏嗗浘鐢诲埌鐢绘澘锟�
		if(bitmap!=null){
			canvas.drawBitmap(bitmap, left, top, null);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		//isRuning = false;//鍋滄
		
		boolean consumed = super.onTouchEvent(evt);
		if (isClickable()) {
			switch (evt.getAction()) {

			case MotionEvent.ACTION_DOWN:
				lastTouchX = evt.getX();
				lastTouchY = evt.getY();
				downXValue = evt.getX();
				downTime = evt.getEventTime();
				hasMoved = false;
				break;

			case MotionEvent.ACTION_MOVE:
				hasMoved = moved(evt);
				break;

			case MotionEvent.ACTION_UP:
				float currentX = evt.getX();
				long currentTime = evt.getEventTime();

				break;
			}

		}

		return consumed || isClickable();
		
		//return true;
	}
	
	@Override
	public void run() {
		isstarted = true;
		while (MovingPictureView.isRuning) {
			// 閫氳繃璋冭妭鍚戦噺锛屾潵鎺у埗鏂瑰悜
//			dx = left < 0 || left > (getWidth() - bitmap.getWidth()) ? -dx : dx;
//			dy = top < 0 || top > (getHeight() - bitmap.getHeight()) ? -dy : dy;
//			left = left + dx;
//			top = top + dy;
			
			if((bitmap!=null) && (left > (getWidth()))){
				left = - bitmap.getWidth();
			} 

			left = left + dx;
	
//			handler.post(new Runnable() {
//				@Override
//				public void run() {
//					invalidate();
//				}
//			});
			movehandler.sendMessage(handler.obtainMessage());

			try {
				Thread.sleep(sleepSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean moved(MotionEvent evt) {

		return hasMoved || Math.abs(evt.getX() - lastTouchX) > 10.0
				|| Math.abs(evt.getY() - lastTouchY) > 10.0;
	}
	
	public class MoveHandler extends Handler{
		@Override
        public void handleMessage(Message msg) {
			MovingPictureView.this.invalidate();
		}
	}
}
