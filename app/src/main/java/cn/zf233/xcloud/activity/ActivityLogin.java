package cn.zf233.xcloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

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

public class ActivityLogin extends AppCompatActivity {

    private final UserService userService = new UserServiceImpl();

    private EditText usernameLoginText;
    private EditText passwordLoginText;
    private View loginUserLayout;

    private Animation clickAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLoginText = findViewById(R.id.usernameLoginText);
        passwordLoginText = findViewById(R.id.passwordLoginText);
        loginUserLayout = findViewById(R.id.loginUserLayout);
        clickAnimation = AnimationUtils.loadAnimation(this, R.anim.click);

        findViewById(R.id.qqLoginLayout).setOnClickListener(v -> {
            ToastUtil.showLongToastCenter("正在开发中...");
            ToastUtil.showLongToastCenter("正在开发中...");
        });

        // login
        loginUserLayout.setOnClickListener(v -> {

            loginUserLayout.startAnimation(clickAnimation);

            String username = usernameLoginText.getText().toString().trim();
            String password = passwordLoginText.getText().toString().trim();

            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                ToastUtil.showShortToast("用户名密码不可为空");
                return;
            }
            if (username.length() < 4 || password.length() < 5) {
                ToastUtil.showShortToast("用户名密码格式有误");
                return;
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(MD5Util.md5(Const.DOMAIN_NAME.getDesc() + password));

            ServerResponse<User> response = userService.login(RequestUtil.getRequestUtil(), user);

            if (response.isSuccess()) {
                user.setId(response.getData().getId());

                FileUtil.removeShared(ActivityLogin.this, Const.CURRENT_USER.getDesc());
                FileUtil.outputShared(ActivityLogin.this, Const.CURRENT_USER.getDesc(), user);

                MainActivity.mainActivity.finish();
                Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
                intent.putExtra(Const.MSG.getDesc(), response.getMsg());
                JumpActivityUtil.jumpActivity(this, intent, 100L, true);

                return;
            }
            ToastUtil.showLongToast(response.getMsg());
        });
    }
}

