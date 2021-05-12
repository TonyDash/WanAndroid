package com.tonyDash.wanandroid.ui.main.mine.command;

import android.os.RemoteException;

import com.cjy.webviewlibrary.I2WebViewProcessAidlInterface;
import com.cjy.webviewlibrary.command.ICommand;
import com.google.auto.service.AutoService;

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
            callback.onResult("login","true");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
