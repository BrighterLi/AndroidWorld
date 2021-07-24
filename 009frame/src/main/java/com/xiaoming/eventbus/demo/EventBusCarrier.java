package com.xiaoming.eventbus.demo;

public class EventBusCarrier {
    private String eventType; //区分事件的类型
    private Object object;  //事件的实体类

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
