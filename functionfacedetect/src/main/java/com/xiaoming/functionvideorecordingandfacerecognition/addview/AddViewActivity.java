package com.xiaoming.functionvideorecordingandfacerecognition.addview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

public class AddViewActivity extends Activity {
    private Button mBtAddView;
    private Button mBtSetInvisible;
    FaceDetectView faceDetectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        initView();

        faceDetectView = new FaceDetectView(this);
    }

    private void initView() {
        mBtAddView = findViewById(R.id.bt_add_view);
        mBtSetInvisible = findViewById(R.id.bt_set_invisible);

        mBtAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                if(faceDetectView.getParent() != null) {
                    ((FrameLayout) faceDetectView.getParent()).removeView(faceDetectView);
                }
                addContentView(faceDetectView, params);

               /*View view = getContentView(AddViewActivity.this);
                ((FrameLayout) view.getParent()).removeView(view);*/

            }
        });

        mBtSetInvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faceDetectView.setVisibility(View.INVISIBLE);
                //faceDetectView.setVisibility(View.GONE); 使用View.GONE后，onPreviewFrame的 camera.getParameters().getPreviewSize()会报错
            }
        });
    }

    public  View getContentView(Activity ac){
        ViewGroup view = (ViewGroup)ac.getWindow().getDecorView();
        FrameLayout content = (FrameLayout)view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }
}
