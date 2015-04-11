package com.test.fuckworld;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;


public class MyRender implements Renderer {
float r=90,theta=0;
float tx,ty;
float wide,heigh;
float[] color=new float[]{
	1.0f,0.0f,0.0f,0.0f,//red
	0.0f,1.0f,0.0f,0.0f,//green
	0.0f,0.0f,1.0f,0.0f,//blue
	//1.0f,1.0f,0.0f,0.0f,//yellow
};
byte[] face=new byte[]{
	0,1,2,
	0,2,3,
	2,3,7,
	2,6,7,
	0,3,7,
	0,4,7,
	4,5,6,
	4,6,7,
	0,1,4,
	1,4,5,
	1,2,6,
};
float[] vertex=new float[]{
		0.5f,0.5f,0.5f,
		0.5f,0.5f,-0.5f,
		0.5f,-0.5f,-0.5f,
		0.5f,-0.5f,0.5f,
		
		-0.5f,0.5f,0.5f,
		-0.5f,0.5f,-0.5f,
		-0.5f,-0.5f,-0.5f,
		-0.5f,-0.5f,0.5f,
};
FloatBuffer vertexbuffer,colorbuffer;
ByteBuffer facebuffer;

public MyRender(){
	vertexbuffer=floatbuffer(vertex);
	facebuffer=ByteBuffer.wrap(face);
	colorbuffer=floatbuffer(color);
}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		//------------first 3D object---------------------
		gl.glLoadIdentity();
		gl.glRotatef(r, 1.0f, 1.0f, 1.0f);
		gl.glTranslatef(tx, ty, 0);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexbuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, colorbuffer);
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, facebuffer.remaining(), GL10.GL_UNSIGNED_BYTE, facebuffer);
	    r+=theta;
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
    public void rotate_cube(){
    	theta=1;
    }
    public void trans_cube(float x,float y){
    	tx=x/wide;
    	ty=y/heigh;
    }
	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		wide=w;
		heigh=h;
		gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        float ratio = (float)w/h;
        gl.glFrustumf(-ratio, ratio, -1, -1, 1, 10);
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		// TODO Auto-generated method stub
		
	    
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0.8f, 0.8f, 0.8f, 0);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
	}

	private FloatBuffer floatbuffer(float[] arr){
		FloatBuffer mybuffer;
		ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length*4);
		qbb.order(ByteOrder.nativeOrder());
		mybuffer = qbb.asFloatBuffer();
		mybuffer.put(arr);
		mybuffer.position(0);
		return mybuffer;
		
	}
}
