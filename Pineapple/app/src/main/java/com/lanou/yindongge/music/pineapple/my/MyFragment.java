package com.lanou.yindongge.music.pineapple.my;

import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by dllo on 17/2/18.
 */


public class MyFragment extends BaseFragment implements View.OnClickListener {
    private static final String[] AVATARS = new String[400];
    private Button getCodeBtn;
    EditText account = null, password = null;
    EditText codeEt = null;
    private String code;
    private Button commitBtn;

    private SMSBroadcastReceiver mSMSBroadcastReceiver;
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";



    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        account = byView(R.id.number);
        password = byView(R.id.password);
        getCodeBtn = byView(R.id.sms_code);
        codeEt = byView(R.id.sms_code_et);
        commitBtn = byView(R.id.login);

    }

    @Override
    public void initData() {
        getCodeBtn.setOnClickListener(this);
        commitBtn.setOnClickListener(this);

        SMSSDK.initSDK(context, "1bc28ede6b21a",
                "81305c9fe8b947d7b6bf9c8847c6ec62");

        //注册短信回调
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = Message.obtain();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });

        //生成广播处理
        mSMSBroadcastReceiver = new SMSBroadcastReceiver();
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter(ACTION);
        intentFilter.setPriority(Integer.MAX_VALUE);
        //注册广播
        context.registerReceiver(mSMSBroadcastReceiver, intentFilter);

//        setListener();
    }

//    private void setListener() {
//        scan.setOnClickListener(this);
//        smsCode.setOnClickListener(this);
//        login.setOnClickListener(this);
//        qq.setOnClickListener(this);
//        share.setOnClickListener(this);
//
//    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                Toast.makeText(context, "回调完成", Toast.LENGTH_SHORT).show();
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Toast.makeText(context, "验证码正确, 正在登陆......", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Toast.makeText(context, "获取验证码成功", Toast.LENGTH_SHORT).show();

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    Toast.makeText(context, "返回支持发送验证码的国家列表", Toast.LENGTH_SHORT).show();
                }
            } else {
                //回调失败
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击获取验证码控件
            case R.id.sms_code:
                Log.d("MainActivity", "456:" + 456);
                getCodeBtn.requestFocus();
                if (vaildateinfo()) {
                    //启动获取验证码 86是中国
                    String zh = account.getText().toString().trim();
                    SMSSDK.getVerificationCode("86", zh);

                    timer.start();
                }
                break;
            //点击提交信息按钮
            case R.id.login:
                VaildateputInfo();
                Log.d("MainActivity", "123:" + 123);

                break;

            case R.id.QQ:
//                mobLogin();
                break;
            case R.id.share:
//                showShare();
        }
    }
//    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle("");
//        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
////        oks.setTitleUrl(songUrl);
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("说点什么吧:");
//        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
////        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
//        oks.setImageUrl("http://p2.qhimg.com/t01c529bfd9870e4075.png");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://bolo.163.com/new/home");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("我的bolo");
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://bolo.163.com/new/home");
//        // 启动分享GUI
//        oks.show(context);
//    }


//    public void mobLogin() {
//        Platform platform = ShareSDK.getPlatform(QQ.NAME);
//        if (platform == null) {
//            Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // 第二次登录会进入的方法, 不在走网页, 而是直接从本地数据库中取数据
//        if (platform.isAuthValid()) {
//            String name = platform.getDb().getUserName();
//            String icon = platform.getDb().getUserIcon();
//            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
//            Log.d("LoginActivity", name);
//
//        }
//        else {
//            platform.setPlatformActionListener(this);  // 回调接口返回
//            platform.SSOSetting(false);
//            platform.showUser(null);
//        }
//    }
//
//    // 第一次登录会进入的方法
//    @Override
//    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//        Log.d("zzz", "123:" + 123);
//        platform.removeAccount();
//        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
////        finish();
//    }
//
//    @Override
//    public void onError(Platform platform, int i, Throwable throwable) {
//        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onCancel(Platform platform, int i) {
//        Toast.makeText(context, "取消登录", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 1.验证验证码
     * 2.提交用户信息
     */
    private void VaildateputInfo() {
        vaildatePassword();
    }

    //验证 验证码
    private void vaildatePassword() {
        code = codeEt.getText().toString().trim();
        String zh = account.getText().toString().trim();
        SMSSDK.submitVerificationCode("86", zh, code);
        putUserInfo("86", zh);
    }

    //提交用户信息
    private void putUserInfo(String country, String phone) {
        Random rnd = new Random();
        int id = Math.abs(rnd.nextInt());
        String uid = String.valueOf(id);
        String nickName = "SmsSDK_User_" + uid;
        String avatar = AVATARS[id % 12];
        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
    }

    //计时器
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getCodeBtn.setText((millisUntilFinished / 1000) + "秒后可重发");


            mSMSBroadcastReceiver.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {
                @Override
                public void onReceived(String message) {

                    message = message.substring(message.length() - 4, message.length());
                    codeEt.setText(message);
                    Log.d("MainActivity", message);

                }
            });
        }

        @Override
        public void onFinish() {
            getCodeBtn.setEnabled(true);
            getCodeBtn.setText("获取验证码");


        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();


        //注销短信监听广播
        context.unregisterReceiver(mSMSBroadcastReceiver);
    }

    //验证注册信息
    private boolean vaildateinfo() {

        String zh = account.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        //首先要判断是否为空
        if (!zh.equals("") || null != zh) {
            if (zh.length() == 11) {
                if (!pwd.equals("") || null != pwd) {
                    if (pwd.length() == 8) {
                        return true;
                    } else {
                        Toast.makeText(context, "密码不足8位", Toast.LENGTH_SHORT).show();
                        password.requestFocus();
                    }
                } else {
                    Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                }
            } else {
                Toast.makeText(context, "手机号不足11位", Toast.LENGTH_SHORT).show();
                account.requestFocus();
            }
        } else {
            Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
            account.requestFocus();
        }
        return false;
    }

//    private static final String NUMBER = "123456";
//    private static final String PASSWORD = "123456";
//    private LinearLayout scan, qq;
//    private Button smsCode, login;
//    private EditText number, password;
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_my;
//    }
//
//    @Override
//    public void initView(View view) {
//        scan = byView(R.id.scan);
//        smsCode = byView(R.id.sms_code);
//        login = byView(R.id.login);
//        number = byView(R.id.number);
//        password = byView(R.id.password);
//        qq = byView(R.id.QQ);
//    }
//
//    @Override
//    public void initData() {
//
//        SMSSDK.initSDK(context, "1bc28ede6b21a",
//                "81305c9fe8b947d7b6bf9c8847c6ec62");
//
//        setListener();
//    }
//
//    private void setListener() {
//        scan.setOnClickListener(this);
//        smsCode.setOnClickListener(this);
//        login.setOnClickListener(this);
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.scan:
//                startActivityForResult(new Intent(context, CaptureActivity.class), 0);
//                break;
//            case R.id.sms_code:
//                //打开注册页面
//                RegisterPage registerPage = new RegisterPage();
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int result, Object data) {
//                        // 解析注册结果
//                        if (result == SMSSDK.RESULT_COMPLETE) {
//                            @SuppressWarnings("unchecked")
//                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//                            // 提交用户信息（此方法可以不调用）
////                            registerUser(country, phone);
//                        }
//                    }
//                });
//                registerPage.show(context);
//                break;
//            case R.id.login:
//                String num = number.getText().toString();
//                String psw = password.getText().toString();
//                if (num.equals(NUMBER) && psw.equals(PASSWORD)) {
//                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "登录失败,账号或密码错误", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case R.id.QQ:
//
//        }
//    }
}
