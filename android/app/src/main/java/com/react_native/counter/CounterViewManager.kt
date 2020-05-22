package com.react_native.counter

import android.graphics.Color
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class CounterViewManager : SimpleViewManager<CounterView>() {
    override fun getName(): String {
        return REACT_CLASS
    }

    override fun createViewInstance(reactContext: ThemedReactContext): CounterView {
        return CounterView(reactContext, reactContext.currentActivity!!)
    }

    @ReactProp(name = "numberColor")
    fun setNumberColor(view: CounterView, color: String?) {
        val parsedColor = Color.parseColor(color)
        view.setCountColor(parsedColor)
    }

    override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any>? {
        return MapBuilder.of(
                "onCountChange",
                MapBuilder.of("registrationName", "onCountChange") as Any
        )
    }

    companion object {
        private const val REACT_CLASS = "RCTCounterView"
    }
}
