package com.widget.dialog;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.widget.R;

public class AlertDialogActivity extends AppCompatActivity {
    private Button showDialogBt;
    private View mDialogSetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        mDialogSetView = View.inflate(this, R.layout.dialog_alert_view, null);
        showDialogBt = findViewById(R.id.show_dialog_bt);
        showDialogBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
                //showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                //.setTitle("标题")
                //.setMessage("内容")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.setView(mDialogSetView);
        dialog.show();
    }
}

