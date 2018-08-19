package com.example.shanshan.dialogbuilderdemo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class CustomerDialogFragment extends DialogFragment {

    private static final String TITLE="title";
    private static final String MESSAGE="msg";
    private static final String SMALLMSG="small_msg";
    private static final String TIP="tip";
    private static final String LEFTBUTTON="left_btn";
    private static final String CENTERBUTTON="center_btn";
    private static final String RIGHTBUTTON="right_btn";


    private OnDialogButtonClickListener dialogButtonClickListener;


    private static CustomerDialogFragment newInstance(Builder builder){
        CustomerDialogFragment customerDialogFragment=new CustomerDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString(TITLE,builder.title);
        bundle.putString(MESSAGE,builder.msg);
        bundle.putString(SMALLMSG,builder.smallMsg);
        bundle.putString(TIP,builder.tip);
        bundle.putString(LEFTBUTTON,builder.btnLeft);
        bundle.putString(CENTERBUTTON,builder.btnCenter);
        bundle.putString(RIGHTBUTTON,builder.btnRight);
        customerDialogFragment.setArguments(bundle);
        return customerDialogFragment;
    }

    TextView title;
    TextView msg;
    Button leftBtn;
    Button rightBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取Window
        Window window = getDialog().getWindow();
        //无标题
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        // 透明背景
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置宽高
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WRAP_CONTENT;
        wlp.height = WRAP_CONTENT;
        window.setAttributes(wlp);
        View view=inflater.inflate(R.layout.dialog_fragment,container,false);

        title=view.findViewById(R.id.title);
        TextView divider=view.findViewById(R.id.divider);
        String mtitle=getArguments().getString(TITLE);
        if (TextUtils.isEmpty(mtitle)){
            title.setVisibility(View.INVISIBLE);
            divider.setVisibility(View.VISIBLE);
        }else {
            title.setVisibility(View.VISIBLE);
            divider.setVisibility(View.GONE);
            title.setText(mtitle);
        }

        msg=view.findViewById(R.id.msg);
        if (!TextUtils.isEmpty(getArguments().getString(MESSAGE))){
            msg.setText(getArguments().getString(MESSAGE));
        }

        TextView small_msg=view.findViewById(R.id.small_msg);
        if (!TextUtils.isEmpty(getArguments().getString(SMALLMSG))){
            small_msg.setText(getArguments().getString(SMALLMSG));
        }

        TextView tip=view.findViewById(R.id.tip);
        String mtip=getArguments().getString(TIP);
        if (TextUtils.isEmpty(mtip)){
            tip.setVisibility(View.INVISIBLE);
        }else {
            tip.setVisibility(View.VISIBLE);
            tip.setText(mtip);
        }


        leftBtn=view.findViewById(R.id.left_btn);
        String mleftBtn=getArguments().getString(LEFTBUTTON);
        if (TextUtils.isEmpty(mleftBtn)){
            leftBtn.setVisibility(View.GONE);
        }else {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setText(mleftBtn);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogButtonClickListener!=null){
                        dialogButtonClickListener.setOnClickListener(0);
                    }
                }
            });
        }

        rightBtn=view.findViewById(R.id.right_btn);
        String mrightBtn=getArguments().getString(RIGHTBUTTON);
        if (TextUtils.isEmpty(mrightBtn)){
            rightBtn.setVisibility(View.GONE);
        }else {
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setText(mrightBtn);
            rightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogButtonClickListener!=null){
                        dialogButtonClickListener.setOnClickListener(1);
                    }
                }
            });
        }


        Button centerBtn=view.findViewById(R.id.certen_btn);
        String mcenterBtn=getArguments().getString(CENTERBUTTON);
        if (TextUtils.isEmpty(mcenterBtn)){
            centerBtn.setVisibility(View.GONE);
        }else {
            centerBtn.setVisibility(View.VISIBLE);
            centerBtn.setText(mcenterBtn);
            centerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogButtonClickListener!=null){
                        dialogButtonClickListener.setOnClickListener(2);
                    }
                }
            });
        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    private void updateMsg(String buildText){
        Log.e("buildText",buildText);
        msg.setText(buildText);
    }

    private void setClickEvnt(String tleftBtn,String trightBtn){
        if (TextUtils.isEmpty(tleftBtn)){
            leftBtn.setVisibility(View.GONE);
        }else {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setText(tleftBtn);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogButtonClickListener!=null){
                        dialogButtonClickListener.setOnClickListener(0);
                    }
                }
            });
        }

        if (TextUtils.isEmpty(trightBtn)){
            rightBtn.setVisibility(View.GONE);
        }else {
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setText(trightBtn);
            rightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogButtonClickListener!=null){
                        dialogButtonClickListener.setOnClickListener(1);
                    }
                }
            });
        }
    }

    public CustomerDialogFragment setOnDialogResultListener(OnDialogButtonClickListener onDialogResultListener){
        this.dialogButtonClickListener=onDialogResultListener;
        return this;
    }

    public static Builder newCostomerBuilder(){
        return new Builder();
    }

    public static class Builder {
        CustomerDialogFragment customerDialogFragment;
        private String title;
        private String msg;
        private String smallMsg;
        private String tip;
        private String btnLeft;
        private String btnCenter;
        private String btnRight;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setSmallMsg(String smallMsg) {
            this.smallMsg = smallMsg;
            return this;
        }

        public Builder setTip(String tip) {
            this.tip = tip;
            return this;
        }

        public Builder setBtnLeft(String btnLeft) {
            this.btnLeft = btnLeft;
            return this;
        }

        public Builder setBtnCenter(String btnCenter) {
            this.btnCenter = btnCenter;
            return this;
        }

        public Builder setBtnRight(String btnRight) {
            this.btnRight = btnRight;
            return this;
        }

        public CustomerDialogFragment build(){
            if (customerDialogFragment==null){
                customerDialogFragment=CustomerDialogFragment.newInstance(this);
            }
            return customerDialogFragment;
        }

        public Builder updateMsg(String msg) {
            this.customerDialogFragment.updateMsg(msg);
            return this;
        }

        public Builder showBtn(String tleft,String tright){
            this.customerDialogFragment.setClickEvnt(tleft,tright);
            return this;
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }
}
