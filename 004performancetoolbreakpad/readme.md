Google Breakpad Android 使用详解:https://www.jianshu.com/p/1687c92efb89
Native crash
采集：Breakpad
开发过程中Android JNI层Crash问题是个比较头疼的问题。
相对Java层来说，由于c/c++造成的crash没有输出如同Java的Exception Strace，所以定位问题是个比较艰难的事情。
该库主要是在发生native crash的时候生成文件保存到sd卡，然后分析文件，定位问题。