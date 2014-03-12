package com.lyft.mordor;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lyft.mordor.core.Main;

import mortar.Mortar;
import mortar.MortarActivityScope;
import mortar.MortarContext;
import mortar.MortarScope;


public class MainActivity extends ActionBarActivity implements MortarContext {

    private MortarActivityScope activityScope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MortarScope parentScope = ((MordorApplication) getApplication()).getMortarScope();
        activityScope = Mortar.requireActivityScope(parentScope, new Main());
        Mortar.inject(this, this);

        activityScope.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        activityScope.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            activityScope.destroy();
            activityScope = null;
        }
    }

    @Override public MortarScope getMortarScope() {
        return activityScope;
    }

    private MortarScope getParentScope() {
        return Mortar.getScope(getApplicationContext());
    }
}
