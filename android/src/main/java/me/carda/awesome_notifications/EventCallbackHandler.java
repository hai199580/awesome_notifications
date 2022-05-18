package me.carda.awesome_notifications;
import android.os.Looper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

public class EventCallbackHandler implements EventChannel.StreamHandler{
    private EventChannel.EventSink eventSink = null;

    @Override
    public void onListen(Object argurments, EventChannel.EventSink sink) {
        this.eventSink = sink;
    }
    public void send(String event, Map<String, ?> body){
        Map<String,Object> data = new HashMap<>();
        data.put("event",event);
        data.put("body",body);
        android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                eventSink.success(data);
            }
        });
    }
    @Override
    public void onCancel(Object argurments){
        this.eventSink = null;
    }
}
