package com.react_native

import android.net.Uri
import android.widget.VideoView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class VideoViewManager : SimpleViewManager<VideoView>() {
    override fun getName(): String {
        return REACT_CLASS
    }

    override fun createViewInstance(reactContext: ThemedReactContext): VideoView {
        return VideoView(reactContext)
    }

    @ReactProp(name = "url")
    fun setVideoPath(videoView: VideoView, urlPath: String?) {
        val uri = Uri.parse(urlPath)
        videoView.setVideoURI(uri)
        videoView.start()
    }

    companion object {
        const val REACT_CLASS = "VideoView"
    }
}
