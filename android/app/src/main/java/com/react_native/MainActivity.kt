package com.react_native

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.core.view.forEach
import androidx.core.view.get
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage
import com.react_native.communication.CommunicationPackage
import com.react_native.counter.CounterViewPackage
import com.react_native.counter.logic.CounterLogicPackage
import java.util.*

class MainActivity : Activity(), DefaultHardwareBackBtnHandler {

    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packages: MutableList<ReactPackage> = ArrayList()
        packages.add(MainReactPackage())
        packages.add(CommunicationPackage())
        packages.add(CounterViewPackage())
        packages.add(VideoViewPackage())
        packages.add(CounterLogicPackage())
        mReactRootView = ReactRootView(this)
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(application)
                .setCurrentActivity(this)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index.android")
                .addPackages(packages)
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build()
        mReactRootView?.startReactApplication(mReactInstanceManager, REACT_MODULE_NAME, null)
        setContentView(mReactRootView)
        getAllViews()
    }

    private fun getAllViews() {
        mReactRootView?.forEach {
            Log.d("getAllViews", "Get View Name = ${it.javaClass.simpleName}")
        }
    }

    override fun onPause() {
        super.onPause()
        if (mReactInstanceManager != null) {
            mReactInstanceManager?.onHostPause(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (mReactInstanceManager != null) {
            mReactInstanceManager?.onHostResume(this, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReactInstanceManager != null) {
            mReactInstanceManager?.onHostDestroy(this)
        }
        if (mReactRootView != null) {
            mReactRootView?.unmountReactApplication()
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager?.showDevOptionsDialog()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager?.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
//  @Override
//  protected String getMainComponentName() {
//    return "react_native";
//  }
    companion object {
        private const val REACT_MODULE_NAME = "TheNativeParts"
    }
}
