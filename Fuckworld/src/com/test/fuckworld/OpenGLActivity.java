package com.test.fuckworld;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGLActivity extends Activity{
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	/*
    	GLSurfaceView glView = new GLSurfaceView(this);
    	MyRender render = new MyRender();
    	glView.setRenderer(render);
    	setContentView(glView);*/
    	MySurface msf = new MySurface(this);
    	setContentView(msf); 
    	msf.requestFocus();
    	msf.setFocusableInTouchMode(true);
    }
} 
