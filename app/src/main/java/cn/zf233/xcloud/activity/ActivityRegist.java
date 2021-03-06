package cn.zf233.xcloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang.StringUtils;

import cn.zf233.xcloud.R;
import cn.zf233.xcloud.common.Const;
import cn.zf233.xcloud.common.ServerResponse;
import cn.zf233.xcloud.entity.User;
import cn.zf233.xcloud.service.UserService;
import cn.zf233.xcloud.service.impl.UserServiceImpl;
import cn.zf233.xcloud.util.FileUtil;
import cn.zf233.xcloud.util.JumpActivityUtil;
import cn.zf233.xcloud.util.MD5Util;
import cn.zf233.xcloud.util.RequestUtil;
import cn.zf233.xcloud.util.ToastUtil;

public class ActivityRegist extends AppCompatActivity {

    private final UserService userService = new UserServiceImpl();

    private EditText usernameRegistText;
    private EditText passwordRegistText;
    private EditText codeRegistText;
    private View registUserLayout;
    private Animation clickAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        usernameRegistText = findViewById(R.id.usernameRegistText);
        passwordRegistText = findViewById(R.id.passwordRegistText);
        codeRegistText = findViewById(R.id.codeRegistText);
        registUserLayout = findViewById(R.id.registUserLayout);
        clickAnimation = AnimationUtils.loadAnimation(this, R.anim.click);

        // regist
        registUserLayout.setOnClickListener(v -> {
            registUserLayout.startAnimation(clickAnimation);
            String username = usernameRegistText.getText().toString().trim();
            String password = passwordRegistText.getText().toString().trim();
            String email = codeRegistText.getText().toString().trim();
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                Toast.makeText(ActivityRegist.this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                return;
            }
            if (StringUtils.isBlank(email)) {
                Toast.makeText(ActivityRegist.this, "??????????????????", Toast.LENGTH_SHORT).show();
                return;
            }
            if (username.length() < 4 || password.length() < 5) {
                Toast.makeText(ActivityRegist.this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(MD5Util.md5(Const.DOMAIN_NAME.getDesc() + password));
            ServerResponse<User> response = userService.regist(RequestUtil.getRequestUtil(), user, email);
            if (response.isSuccess()) {
                user.setId(response.getData().getId());
                FileUtil.removeShared(ActivityRegist.this, Const.CURRENT_USER.getDesc());
                FileUtil.outputShared(ActivityRegist.this, Const.CURRENT_USER.getDesc(), user);
                MainActivity.mainActivity.finish();
                Intent intent = new Intent(ActivityRegist.this, ActivityLogin.class);
                ToastUtil.showLongToast(response.getMsg());
                JumpActivityUtil.jumpActivity(this, intent, 2000L, true);
                return;
            }
            ToastUtil.showLongToast(response.getMsg());
        });
    }
}