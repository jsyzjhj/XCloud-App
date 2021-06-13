package cn.zf233.xcloud.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.zf233.xcloud.R;
import cn.zf233.xcloud.common.Const;
import cn.zf233.xcloud.common.ServerResponse;
import cn.zf233.xcloud.entity.User;
import cn.zf233.xcloud.service.UserService;
import cn.zf233.xcloud.service.impl.UserServiceImpl;
import cn.zf233.xcloud.util.FileUtil;
import cn.zf233.xcloud.util.JumpActivityUtil;
import cn.zf233.xcloud.util.RequestUtil;

public class ActivityUser extends AppCompatActivity {

    private static final UserService userService = new UserServiceImpl();
    private final List<Integer> userGradeImgID = new ArrayList<>();

    private static User user;

    private View logoutLayout;
    private ImageView userHead;
    private TextView currentUserUsernameText;
    private ProgressBar levelGroupBar;
    private ProgressBar numberCountBar;
    private ImageView userGradeImg;
    private TextView capacity;
    private TextView growth;
    private Animation clickAnimation;

    {
        userGradeImgID.add(R.mipmap.no01);
        userGradeImgID.add(R.mipmap.no02);
        userGradeImgID.add(R.mipmap.no03);
        userGradeImgID.add(R.mipmap.no04);
        userGradeImgID.add(R.mipmap.no05);
        userGradeImgID.add(R.mipmap.no06);
        userGradeImgID.add(R.mipmap.non);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        currentUserUsernameText = findViewById(R.id.currentUserUsernameText);
        levelGroupBar = findViewById(R.id.levelGroupBar);
        numberCountBar = findViewById(R.id.numberCountBar);
        userGradeImg = findViewById(R.id.userGradeImg);
        logoutLayout = findViewById(R.id.logoutLayout);
        userHead = findViewById(R.id.userHead);
        capacity = findViewById(R.id.user_capacity);
        growth = findViewById(R.id.user_growth);
        clickAnimation = AnimationUtils.loadAnimation(this, R.anim.click);

        new Thread(new UserRunnable()).start();

        logoutLayout.setOnClickListener(v -> {
            logoutLayout.startAnimation(clickAnimation);
            FileUtil.removeShared(ActivityUser.this, Const.CURRENT_USER.getDesc());
            ActivityHome.activityHome.finish();
            Intent intent = new Intent(ActivityUser.this, MainActivity.class);
            intent.putExtra(Const.MSG.getDesc(), "注销成功");
            JumpActivityUtil.jumpActivity(this, intent, 100L, true);
        });
    }

    // flush user detail UI
    private final Handler userHandler = new Handler(new Handler.Callback() {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean handleMessage(Message msg) {
            currentUserUsernameText.setText(ActivityUser.user.getUsername());
            if (ActivityUser.user.getLevel() > 6) {
                userGradeImg.setImageResource(userGradeImgID.get(6));
            } else {
                userGradeImg.setImageResource(userGradeImgID.get(user.getLevel() - 1));
            }
            int growthValue = ActivityUser.user.getGrowthValue() % 50;
            numberCountBar.setMax(Long.valueOf(ActivityUser.user.getCapacityNum() / 100000L).intValue());
            numberCountBar.setProgress(Long.valueOf(ActivityUser.user.getUseCapacityNum() / 100000L).intValue());
            levelGroupBar.setMax(50);
            levelGroupBar.setProgress(growthValue);
            capacity.setText(ActivityUser.user.getUseCapacity() + "/" + ActivityUser.user.getCapacity());
            growth.setText((ActivityUser.user.getGrowthValue() % 100) + "/" + "100");
            Glide.with(ActivityUser.this).load(ActivityUser.user.getHeadUrl()).into(userHead);

            return false;
        }
    });

    // callback
    private void jumpActivity(Intent intent) {
        ActivityHome.activityHome.finish();
        JumpActivityUtil.jumpActivity(this, intent, 100L, true);
    }

    // check and get user Detail
    private class UserRunnable implements Runnable {

        @Override
        public void run() {
            Looper.prepare();
            User user = FileUtil.inputShared(ActivityUser.this, Const.CURRENT_USER.getDesc(), User.class);
            ServerResponse<User> response = userService.login(RequestUtil.getRequestUtil(), user);
            if (!response.isSuccess()) {
                FileUtil.removeShared(ActivityUser.this, Const.CURRENT_USER.getDesc());
                Intent intent = new Intent(ActivityUser.this, MainActivity.class);
                intent.putExtra(Const.MSG.getDesc(), response.getMsg());
                jumpActivity(intent);
            } else if (response.isSuccess()) {
                user.setId(response.getData().getId());
                FileUtil.removeShared(ActivityUser.this, Const.CURRENT_USER.getDesc());
                FileUtil.outputShared(ActivityUser.this, Const.CURRENT_USER.getDesc(), user);
                ActivityUser.user = response.getData();
                userHandler.sendMessage(new Message());
            } else {
                Intent intent = new Intent(ActivityUser.this, MainActivity.class);
                jumpActivity(intent);
            }
            Looper.loop();
        }
    }

}