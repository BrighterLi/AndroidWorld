视频播放组件
(1) MediaPlayer
MediaPlayer:视频播放功能类
(2)VideoView(extends SurfaceView)


视频录制


本地视频选择
本地视频选择；视频压缩




SurfaceView
VideoView—>SurfaceView

视频压缩、图片压缩、文件压缩
视频压缩
Android视频播放，选择，压缩，上传：https://www.jianshu.com/p/78b7176c041e
VideoProcessor -- 视频压缩，体积小，速度快
https://github.com/yellowcath/VideoProcessor
android视频压缩库fishwjy/VideoCompressor与yellowcath/VideoProcessor 的比较：https://mingdeju.com/archives/android-VideoCompressor.html

VideoCompressor -- 比VideoProcessor还快，但是没有进度回调
https://github.com/fishwjy/VideoCompressor

FFmpeg -- 视频压缩 体积大，压缩时间长，功能完善强大
FFmpegAndroid -- android端基于FFmpeg
FFMPEG-AAC-264-Android-32-64 -- 编译好的ffmpeg压缩aar
FFmpegDemo --lastYear使用FFmpeg压缩的Demo

SiliCompressor -- 保证质量，但只能压缩，不能控制码率和进度
Android视频压缩并且上传   https://www.cnblogs.com/wzqnxd/p/10038881.html

android视频压缩七牛sdk -- 要收费，废弃
small-video-record -- 采用FFmpeg，3.1k 的star
Android视频压缩：https://blog.csdn.net/wang_k516/article/details/79071773
