package com.tonyDash.wanandroid.ui.main.mine.command;

import android.content.Intent;
import android.os.RemoteException;

import com.cjy.baselibrary.AppContext;
import com.cjy.baselibrary.utils.GsonUtil;
import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface;
import com.cjy.webviewlibrary.command.ICommand;
import com.google.auto.service.AutoService;
import com.tonyDash.wanandroid.store.UserInfoStore;
import com.tonyDash.wanandroid.ui.main.mine.LoginActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@AutoService(ICommand.class)
public class LoginCommand implements ICommand {
    @NotNull
    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(@Nullable Map<?, ?> params, @NotNull I2WebViewProcessAidlInterface callback) {
        try {
            boolean isLogin = UserInfoStore.INSTANCE.isLogin();
            if (isLogin) {
                String userInfoStr = GsonUtil.Companion.getInstance().toJsonString(UserInfoStore.INSTANCE.getUserInfo());
                callback.onResult("login", true, userInfoStr);
            } else {
                Intent intent = new Intent();
                intent.setClass(AppContext.INSTANCE, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AppContext.INSTANCE.startActivity(intent);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
