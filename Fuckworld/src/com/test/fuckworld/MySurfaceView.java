package com.test.fuckworld;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

public class MySurfaceView extends GLSurfaceView{
private MyRenderer mrr;
public final float X_AXIS=1.0f;
public final float Y_AXIS=2.0f;
public final float Z_AXIS=3.0f;
public final float MISS=100.f;
float axis=0.0f;
private static float old_x0,old_x1,old_y0,old_y1,new_x0,new_x1,new_y0,new_y1;
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mrr=new MyRenderer(); 
        setRenderer(mrr); 
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); 
        
        	
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
    {
	  	int pointerCount = event.getPointerCount(); 
	  	 if(pointerCount<2){
	  		 axis=0.0f;
	  		 
	  	 }
	  	//初始化两点数据，判断在哪个轴上进行变换
	  	if(pointerCount==2&&axis==0.0f){
	  		float xlength,ylength;
			old_x0=event.getX(0);
			old_x1=event.getX(1);
			old_y0=event.getY(0);
			old_y1=event.getY(1);
			xlength=old_x1-old_x0;
	        ylength=old_y1-old_y0;
	        
	        //判断指定的坐标轴
	        if(ylength>-MISS&&ylength<MISS){
	        	 
	        	axis= X_AXIS;
	        }
	        else if(xlength>-MISS&&xlength<MISS){
	        	 
	        	axis= Y_AXIS;
	        }
	         
	        else{
	        	axis= Z_AXIS;
	        }
	         
	  		return true;
	  	}
	  	//对选定轴的移动进行变换
        if(pointerCount==2&&event.getAction()==MotionEvent.ACTION_MOVE){
        	new_x0=event.getX(0);
			new_x1=event.getX(1);
			new_y0=event.getY(0);
			new_y1=event.getY(1);
			//关于x轴的交互变换
        	if(axis==X_AXIS){
        		//判定y轴方向变化在误差内为平移缩放，否则为旋转
    			if(new_y0-new_y1<MISS&&new_y0-new_y1>-MISS){
    				
    				if((new_x0-old_x0)*(new_x1-old_x1)>0){
    					//translate
    					
    					float transl=(new_x0-old_x0+new_x1-old_x1)/2;
    					mrr.mytransl(transl, axis);
    					 
    				}
    				else{
    					//scale
    					float scale=(event.getX(0)-event.getX(1))/(old_x0-old_x1);
    					mrr.myscale(scale, axis);
    					 
    				}
    			}
    			else{
    				//rotate
    				float rotate=(new_y0-new_y1)/(new_x0-new_x1);
    				float angle=(float)Math.toDegrees(Math.atan(rotate));
    				mrr.myrotate(angle, axis);
    				  
    			}
    			
    		}
        	//关于y轴的交互变换
    		if(axis==Y_AXIS){
    			
    			if(new_x0-new_x1<MISS&&new_x0-new_x1>-MISS){
    				
    				if((new_y0-old_y0)*(new_y1-old_y1)>0){
    					//translate
    					
    					float transl=-(new_y0-old_y0+new_y1-old_y1)/2;
    					mrr.mytransl(transl, axis);
    					
    				}
    				else{
    					//scale
    					float scale=(event.getY(0)-event.getY(1))/(old_y0-old_y1);
    					mrr.myscale(scale, axis);
    					
    				}
    			}
    			else{
    				//rotate
    				float rotate=(new_x0-new_x1)/(new_y0-new_y1);
    				float angle=(float)Math.toDegrees(Math.atan(rotate));
    				mrr.myrotate(angle, axis);
    				  
    			}
    			 
    		}
    		//关于z轴的交互变换
    		if(axis==Z_AXIS){
    			//求出手指移动后的角度
    			float new_angle=(float)Math.toDegrees(Math.atan((new_x0-new_x1)/(new_y0-new_y1)));
    			//计算原角度
    			float old_angle=(float)Math.toDegrees(Math.atan((old_x0-old_x1)/(old_y0-old_y1)));
    			//判定角度变换误差值内为平移和缩放，否则为旋转。
    			if(new_angle-old_angle>-MISS/10&&new_angle-old_angle<MISS/10 ){
    				
    				if((new_y0-old_y0)*(new_y1-old_y1)>0){
    					//translate
    					
    					float transl=-(new_y0-old_y0+new_y1-old_y1)/2;
    					mrr.mytransl(transl, axis);
    					Toast.makeText(getContext(), "get z axis", Toast.LENGTH_SHORT).show();
    				}
    				else{
    					//scale
    					float scale=(event.getY(0)-event.getY(1))/(old_y0-old_y1);
    					mrr.myscale(scale, axis);
    					
    				}
    			}
    			else{
    				//rotate
    				float rotate=(new_x0-new_x1)/(new_y0-new_y1);
    				float angle=(float)Math.toDegrees(Math.atan(rotate))-old_angle;
    				mrr.myrotate(angle, axis);
    				  
    			}
    			 
    			
    		}
        }
        
        this.requestRender();
        return true;
        	
	  		 
       
    }
	
	
}
