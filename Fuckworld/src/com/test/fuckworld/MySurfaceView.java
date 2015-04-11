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
	  	//��ʼ���������ݣ��ж����ĸ����Ͻ��б任
	  	if(pointerCount==2&&axis==0.0f){
	  		float xlength,ylength;
			old_x0=event.getX(0);
			old_x1=event.getX(1);
			old_y0=event.getY(0);
			old_y1=event.getY(1);
			xlength=old_x1-old_x0;
	        ylength=old_y1-old_y0;
	        
	        //�ж�ָ����������
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
	  	//��ѡ������ƶ����б任
        if(pointerCount==2&&event.getAction()==MotionEvent.ACTION_MOVE){
        	new_x0=event.getX(0);
			new_x1=event.getX(1);
			new_y0=event.getY(0);
			new_y1=event.getY(1);
			//����x��Ľ����任
        	if(axis==X_AXIS){
        		//�ж�y�᷽��仯�������Ϊƽ�����ţ�����Ϊ��ת
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
        	//����y��Ľ����任
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
    		//����z��Ľ����任
    		if(axis==Z_AXIS){
    			//�����ָ�ƶ���ĽǶ�
    			float new_angle=(float)Math.toDegrees(Math.atan((new_x0-new_x1)/(new_y0-new_y1)));
    			//����ԭ�Ƕ�
    			float old_angle=(float)Math.toDegrees(Math.atan((old_x0-old_x1)/(old_y0-old_y1)));
    			//�ж��Ƕȱ任���ֵ��Ϊƽ�ƺ����ţ�����Ϊ��ת��
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
