package com.muz.webrtc;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.Random;

public class VideoCallActivity extends AppCompatActivity {

    EditText userIdEditText;
    Button btn;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);
        userIdEditText = findViewById(R.id.user_id_edit_text);
        btn = findViewById(R.id.back);

        btn.setOnClickListener(v -> {

            Fragment fragment = new CallFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.video_call_button,fragment).commit();
        });
        startBtn = findViewById(R.id.start_button);
        startBtn.setOnClickListener(v -> {
            String userID = userIdEditText.getText().toString().trim();
            if(userID.isEmpty()){
                return;
            }
            //start the service
            Application application = getApplication(); // Android's application context
            long appID = 628042061;   // yourAppID
            String appSign ="9666cb429754b605fa31fad5ee9c088f7ac0867e77bac8e16638883994eb9ce6";  // yourAppSign
            String userName = userID;   // yourUserName

            ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
            callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
            ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
            notificationConfig.sound = "zego_uikit_sound_call";
            notificationConfig.channelID = "CallInvitation";
            notificationConfig.channelName = "CallInvitation";
            ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
            Intent intent = new Intent(VideoCallActivity.this,CallActivity.class);
            intent.putExtra("userID",userID);
            startActivity(intent);
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}