package com.lyft.mordor;

import android.app.Application;

import mortar.Mortar;
import mortar.MortarContext;
import mortar.MortarScope;

/**
 * Created by viacheslavpanasenko on 3/11/14.
 */
public class MordorApplication extends Application implements MortarContext {

    private MortarScope applicationScope;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationScope = Mortar.createRootScope(BuildConfig.DEBUG);
    }

    @Override
    public MortarScope getMortarScope() {
        return applicationScope;
    }
}
