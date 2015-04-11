package com.test.fuckworld;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

public class MySurface extends GLSurfaceView{
private MyRender mr;
float bx,by;
	 public MySurface(Context context) {
		 
		 super(context); 
	     mr = new MyRender(); 
	        setRenderer(mr); 
	        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); 
	        bx=by=-1;
	}
	public void DisplayToast(String s){
	    	Toast.makeText(this.getContext(), s, Toast.LENGTH_SHORT).show();
	}
	@Override 
	public boolean onTouchEvent(MotionEvent e) { 
	        float x = e.getX(); 
	        float y = e.getY(); 
	        
	        int action=e.getAction();
	        
	        //mr.rotate_cube();
	       
	        if(action==2){//move
	           if(bx!=-1){
	        	   mr.trans_cube(x, y);
	           }	
	           bx=x;
	           by=y;
	           DisplayToast(String.valueOf(x)+" "+String.valueOf(y));
	        } 
	        
	        this.requestRender();
	return true;
	}
}
