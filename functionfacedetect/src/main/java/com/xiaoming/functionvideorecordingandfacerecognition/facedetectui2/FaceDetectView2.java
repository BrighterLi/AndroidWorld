package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.xiaoming.functionvideorecordingandfacerecognition.R;

public class FaceDetectView2 extends RelativeLayout  {

    private FaceMaskView2 mFaceMaskView;
    private ImageView mIvScanLine;
    private ImageView mIvGroundGrass;
    private TextView mTvDetectResult;
    private SurfaceView mCameraSurface = null;
    private TextView mFaceDetectTipText;
    private RelativeLayout mFaceDetectView;


    public FaceDetectView2(Context context) {
        this(context, null);
    }

    public FaceDetectView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFaceDetectView = ((RelativeLayout) LayoutInflater.from(context).inflate(R.layout.face_detect2, this));
        //initView();
    }


    private void initView() {
        mCameraSurface = (SurfaceView) findViewById(R.id.surface_view_detect);
        mIvScanLine = findViewById(R.id.iv_scan_line);
        mIvGroundGrass = findViewById(R.id.iv_ground_grass);
        mTvDetectResult = findViewById(R.id.tv_detect_result);
        mFaceMaskView = findViewById(R.id.face_mask_view);
        mFaceMaskView.setPaintColor(Color.BLUE);
        mFaceDetectTipText = findViewById(R.id.tv_face_detect_tip_text);
    }

}