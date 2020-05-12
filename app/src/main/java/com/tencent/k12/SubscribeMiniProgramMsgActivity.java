package com.tencent.k12;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by willenwu on 2018/5/10
 */



/**
 * Created by willenwu on 2018/5/10
 */

public class SubscribeMiniProgramMsgActivity extends Activity {

    public final String TAG = getClass().getName();

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID,false);
        setContentView(R.layout.subscribe_mini_program_msg);
        Button checkSubscribeMsgBtn = (Button)findViewById(R.id.check_subscribe_message_btn);
        checkSubscribeMsgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean supported = api.getWXAppSupportAPI() >= Build.SUBSCRIBE_MINI_PROGRAM_MSG_SUPPORTED_SDK_INT;
                Toast.makeText(SubscribeMiniProgramMsgActivity.this, supported ? "support" : "not support", Toast.LENGTH_SHORT).show();
            }
        });

        final EditText miniProgramAppIdEt = findViewById(R.id.mini_program_appid_et);

        Button subscribeMsgBtn = findViewById(R.id.subscribe_message_btn);
        subscribeMsgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                openMiNi();

                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();

                req.userName = "gh_7abb4c323870";
                req.path = "/pages/webview/index?url=https%3A%2F%2Ffudao.qq.com";

                boolean ret = api.sendReq(req);
                String message = String.format("sendReq ret : %s", ret);
                Toast.makeText(SubscribeMiniProgramMsgActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d(TAG+123,  message+ "");
            }
        });
    }
//    let r = WXLaunchMiniProgramReq.object()
//    r.userName = "gh_7abb4c323870"
////r.path = "/pages/webview/index?url=https%3A%2F%2Ffudao.qq.com"
//    r.path = "/pages/webview/index?url=https%3A%2F%2Ffudao.qq.com%2Fcourse.html%3Foverlay%3D1%26_bid%3D2379%26course_id%3D203424%26startTime%3D1589200154051%26grayshadow%3D1%26overlay%3D1"
//    r.miniProgramType = .release
//WXApi.send(r) { (b) in
//        LogI("maconellog", "wx resp \(b)")
//    }
    public void openMiNi(){
        // 填Android应用AppId
        // 填应用AppId
        String appId = "wx36ed58457a087ec8";
        IWXAPI api = WXAPIFactory.createWXAPI(this, appId);

        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        // 填小程序原始id
        req.userName = "gh_7abb4c323870";
        //拉起小程序页面的可带参路径，不填默认拉起小程序首页
        req.path = "/pages/webview/index?url=https%3A%2F%2Ffudao.qq.com";
        // 可选打开 开发版，体验版和正式版
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
        api.sendReq(req);
    }

}
