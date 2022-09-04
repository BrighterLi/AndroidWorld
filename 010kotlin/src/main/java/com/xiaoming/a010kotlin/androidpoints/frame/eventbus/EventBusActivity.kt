package com.xiaoming.a010kotlin.androidpoints.frame.eventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.databinding.ActivityEventBusBinding
import com.xiaoming.a010kotlin.databinding.ActivityViewBindingBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityEventBusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEventBusBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

       EventBus.getDefault().register(this)

        viewBinding.btEventBus.setOnClickListener {
            sendEvent()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(messageEvent: MessageEvent) {
        viewBinding.tvEventBus.text = messageEvent.message
    }

    private fun sendEvent() {
        val event = MessageEvent("Hello EventBus")
        EventBus.getDefault().post(event)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}