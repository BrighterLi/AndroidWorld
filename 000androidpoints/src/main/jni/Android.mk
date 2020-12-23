LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# 定义生成so文件的名字
LOCAL_MODULE    := androidknowledgepoints
# 需要编译的文件，由于在一个文件夹下，不用写路径
LOCAL_SRC_FILES := jnitools.c

LOCAL_LDLIBS :=  -L$(SYSROOT)/usr/lib -llog

include $(BUILD_SHARED_LIBRARY)
# 指定生成全部的ABI的so文件
APP_ABI := all
