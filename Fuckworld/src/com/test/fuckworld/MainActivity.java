package com.test.fuckworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Button;
import android.opengl.GLSurfaceView.Renderer;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */
   

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        // 实例化MyRenderer,GLSurfaceView类
    	/**/
       
        // 为当前Activity类指定视图
        setContentView(R.layout.activity_main);
        
        
        Button button1 =(Button) findViewById(R.id.button2);
    	Button.OnClickListener listener1= new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		        Intent intent2=new Intent(MainActivity.this,Geometry.class);
		        startActivity(intent2);
		        
			}
    		
    		
    	};
       button1.setOnClickListener(listener1);
    }
    /*
    public void DisplayToast(String str)
    {
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    
    public boolean onTouchEvent(MotionEvent event)
    {
        int Action = event.getAction();
        float X = event.getX();
        float Y = event.getY();
        
        DisplayToast("Position = (" + X + " , " + Y + ")");
        return true;
    }
*/
   
}