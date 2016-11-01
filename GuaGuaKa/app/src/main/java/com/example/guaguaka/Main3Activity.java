package com.example.guaguaka;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
private ImageView imageView;
    private  float x;
    private float y;
    private Matrix oldMatrix = new Matrix();
    private Matrix newMatrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(listener);
    }

    View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) { // 判断操作类型
                case MotionEvent.ACTION_DOWN:
                    //按下时记住x,y的坐标
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    oldMatrix.set(imageView.getImageMatrix());
                    break;
                case MotionEvent.ACTION_MOVE://移动时
                    //用另一个模型记住按下时的位置
                    newMatrix.set(oldMatrix);
                    //移动模型
                    newMatrix.setTranslate(motionEvent.getX() - x, motionEvent.getY() - y);
                    break;
            }
            //把图片放入移动后的模型中
            imageView.setImageMatrix(newMatrix);
            return true;
        }
    };

}
