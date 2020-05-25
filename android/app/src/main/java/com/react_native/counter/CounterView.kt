package com.react_native.counter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.react_native.R

class CounterView : FrameLayout, View.OnClickListener {
    private var reactContext: ReactContext
    private var mCountTextView: TextView? = null
    private var count: Int

    constructor(context: Context) : super(context) {
        reactContext = context as ReactContext
        count = 0
    }

    constructor(context: Context, activity: Activity) : super(context) {
        reactContext = context as ReactContext
        count = 0
        val counterLayout = activity.layoutInflater.inflate(R.layout.counter, null) as FrameLayout
        counterLayout.setOnClickListener(this)
        this.addView(counterLayout)
        mCountTextView = counterLayout.findViewById(R.id.count_tv)
        mCountTextView?.text = count.toString()
    }

    fun setCountColor(color: Int) {
        mCountTextView?.setTextColor(color)
    }

    override fun onClick(view: View) {
        count++
        mCountTextView?.text = count.toString()
        val event = Arguments.createMap()
        event.putInt("count", count)
        reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(
                id,
                "onCountChange",
                event)
    }

    override fun requestLayout() {
        super.requestLayout()
        post(measureAndLayout)
    }

    private val measureAndLayout = Runnable {
        measure(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
        layout(left, top, right, bottom)
    }
}
