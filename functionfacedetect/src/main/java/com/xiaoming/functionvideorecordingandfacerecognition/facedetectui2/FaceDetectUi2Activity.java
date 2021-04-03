package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui2;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

public class FaceDetectUi2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect_ui2);

        FaceDetectView2 faceDetectView2 = new FaceDetectView2(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(faceDetectView2, layoutParams);

    }
}
