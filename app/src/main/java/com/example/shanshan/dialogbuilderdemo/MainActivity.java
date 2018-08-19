package com.example.shanshan.dialogbuilderdemo;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private CustomerDialogFragment.Builder builder;
    private int timeCount=10;
    private int otherCount=5;
    private Timer timer;
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=new Timer();
        myHandler=new MyHandler(this);
        builder=CustomerDialogFragment.newCostomerBuilder();
        TextView textView=findViewById(R.id.show_dialog);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTitleDialog();
                timer.schedule(timerTask,1000,1000);
            }
        });
    }

    public void showTitleDialog(){
        builder.setTitle("请稍候")
                .setMsg(timeCount+"秒")
                .setSmallMsg("12345678")
                .setTip("1111111")
                .build()
                .show(getSupportFragmentManager(),"dialog");
    }

    public void updateMsg(){
        builder.updateMsg(timeCount+"秒");
    }

    public void updateOneMsg(){
        builder.updateMsg(otherCount+"秒");
    }

    public void showOneBtn(){
        builder.updateMsg("33333")
                .showBtn("关闭","")
                .build()
                .setOnDialogResultListener(new OnDialogButtonClickListener<Integer>() {
            @Override
            public void setOnClickListener(Integer result) {
                Toast.makeText(MainActivity.this,"关闭",Toast.LENGTH_LONG).show();
                Log.e("MainActivity",result+"");
            }
        });
    }

    public void showTwoBtn(){
        builder.updateMsg("111111")
                .showBtn("确定","取消")
                .build()
                .setOnDialogResultListener(new OnDialogButtonClickListener<Integer>() {
                    @Override
                    public void setOnClickListener(Integer result) {
                        if (result==0){
                            timer.schedule(otherTask,1000,1000);
                        }else {
                            otherTask.cancel();
                            showOneBtn();
                        }

                        Toast.makeText(MainActivity.this,result==0?"确定":"取消",Toast.LENGTH_LONG).show();
                        Log.e("MainActivity",result+"");
                    }
                });
    }

    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            if (timeCount<=0){
                timerTask.cancel();
                myHandler.sendEmptyMessage(MyHandler.UPDATESHOWBTN);
            }else {
                timeCount--;
                myHandler.sendEmptyMessage(MyHandler.UPDATETITLE);
            }

        }
    };

    private TimerTask otherTask=new TimerTask() {
        @Override
        public void run() {
            if (otherCount<=0){
                otherTask.cancel();
                myHandler.sendEmptyMessage(MyHandler.UPDATESHOWONBTN);
            }else {
                otherCount--;
                myHandler.sendEmptyMessage(MyHandler.UPDATEMEG);
            }

        }
    };

}
