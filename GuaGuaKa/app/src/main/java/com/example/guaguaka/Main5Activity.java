package com.example.guaguaka;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class Main5Activity extends AppCompatActivity {

    private ImageView imageView;
    private int screen_width=0;//设置屏幕宽
    private int screen_height=0;//设置屏幕高
    private int image_width=0;
    private int image_height=0;
    private int imageX=0;
    private int imageY=0;
    private SurfaceHolder SurfaceHolder=null;
    private Bitmap bitmap=null;

    @SuppressWarnings("depracation")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        super.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        this.screen_width=super.getWindowManager().getDefaultDisplay().getWidth();
        this.screen_height=super.getWindowManager().getDefaultDisplay().getHeight();
        this.bitmap= BitmapFactory.decodeResource(super.getResources(),R.mipmap.m006);
        this.image_width=this.bitmap.getWidth();
        this.image_height=this.bitmap.getHeight();
        this.imageX=(this.screen_width-this.image_width)/2;
        this.imageY=(this.screen_height-this.image_height)/2;
        super.setContentView(new MySurfaceView(this));
        imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointCount= motionEvent.getPointerCount();
                if(pointCount==2){
                    float pointA= motionEvent.getY(0);
                    float pointB=motionEvent.getY(1);
                    if (pointA<pointB){
                        float temp=pointA;
                        pointA=pointB;
                        pointB=temp;
                    }
                    if (!(motionEvent.getAction()==motionEvent.ACTION_UP)){
                        float scale=this.getScale(pointA,pointB);
                        Main5Activity.this.setImage(scale,350,650);

                    }
                }
                return true;
            }

            private float getScale(float pointA, float pointB) {
                return pointA/pointB;
            }


        });
    }
    private class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
    {
        public MySurfaceView(Context context){
            super(context);
            Main5Activity.this.SurfaceHolder=super.getHolder();
            Main5Activity.this.SurfaceHolder.addCallback(this);
            super.setFocusable(true);

        }
        @Override
        public void surfaceChanged(SurfaceHolder SurfaceHolder,int format ,int width,int height )
        {

        }
        @Override
        public void surfaceCreated(SurfaceHolder SurfaceHolder){
            Main5Activity.this.setImage(1,350,500);

        }
        @Override
        public void surfaceDestroyed(SurfaceHolder SurfaceHolder){

        }
    }
       private void setImage(float scale, int width, int height) {
           Canvas Canvas=Main5Activity.this.SurfaceHolder.lockCanvas();
           Paint Paint=new Paint();
           Canvas.drawRect(0,0,Main5Activity.this.screen_width,Main5Activity.this.screen_height,Paint);
           Matrix Matrix=new Matrix();
           Matrix.postScale(scale,scale);
           Bitmap target= Bitmap.createBitmap(Main5Activity.this.bitmap,0,0,width,height,Matrix,true);
           this.image_width=target.getWidth();
           this.image_height=target.getHeight();
           this.imageX=(this.screen_width-this.image_width)/2;
           this.imageY=(this.screen_height-this.image_height)/2;
           Canvas.translate(imageX,imageY);
           Canvas.drawBitmap(this.bitmap,Matrix,Paint);
           Main5Activity.this.SurfaceHolder.unlockCanvasAndPost(Canvas);

    }


}
