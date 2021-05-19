package com.tonyDash.wanandroid.command;

import android.content.Intent;

import com.cjy.baselibrary.AppContext;
import com.cjy.commonlibrary.I2WebViewProcessAidlInterface;
import com.cjy.commonlibrary.command.ICommand;
import com.cjy.commonlibrary.utils.Constants;
import com.google.auto.service.AutoService;
import com.tonyDash.wanandroid.ui.main.mine.activity.LoginActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@AutoService(ICommand.class)
public class LoginCommand implements ICommand {
    @NotNull
    @Override
    public String name() {
        return Constants.COMMAND_NAME_LOGIN;
    }

    @Override
    public void execute(@Nullable Map<?, ?> params, @NotNull I2WebViewProcessAidlInterface callback) {
        Intent intent = new Intent();
        intent.setClass(AppContext.INSTANCE, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppContext.INSTANCE.startActivity(intent);
    }
}
