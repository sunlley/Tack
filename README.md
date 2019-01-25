# Tack  [![](https://jitpack.io/v/kayoSun/Tack.svg)](https://jitpack.io/#kayoSun/Tack) [![](https://github.com/kayoSun/resource/blob/master/svgs/apachelicense.svg)](LICENSE.txt)


Tack是一款简单、快捷、轻量级的Bundle数据注入和存储的扩展框架

使用Tack，你可以实现：

1. 从Bundle\Intent读取常用数据类型数据
2. 向Bundle\Intent存入常用数据类型数据
3. 从Bundle\Intent读取数据并注入到Activity中的变量
4. 从Bundle\Intent读取数据并注入到Fragment中的变量
5. 通过工厂更方便的创建Bundle，避免硬编码

## 依赖

> 添加JitPack仓库

```Groovy
maven { url 'https://jitpack.io' }
```

> 添加依赖

```Groovy
dependencies {
	annotationProcessor 'com.github.kayoSun.Tack:tackprocessor:0.0.2'
	implementation 'com.github.kayoSun.Tack:tackapi:0.0.2'
}
```
## 混淆配置
```Proguard
-keep class com.kayo.lib.tack.annos.**
-keep class * implements com.kayo.lib.tack.api.Inject
-keepclasseswithmembernames class * {
    @com.kayo.lib.tack.annos.* <fields>;
}
```

##  Gradle版本注意事项

| Tack version | Android Projects |
|--------------|------------------|
| 0.0.2        | Gradle 4.6       |


## 联系作者

欢迎提issue<br>


## License
```
Copyright 2018 Kayo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```