1 flutter依赖方式：
(1) 源码依赖方式
1)app的build.gradle：
implementation project(':flutter')
2)settings.gradle
setBinding(new Binding([gradle: this]))
evaluate(new File(settingsDir, '../flutter_mix/.android/include_flutter.groovy'))
(2) arr产物引入方式
1) app的build.gradle：
repositories {
    maven {
        url '../flutter_output/repo'
    }
    maven {
        url 'http://download.flutter.io'
    }

    debugImplementation 'com.example.fenqile_flutter:flutter_debug:1.0'
    releaseImplementation 'com.example.fenqile_flutter:flutter_release:1.0'
}