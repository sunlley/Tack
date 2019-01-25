# Tack  [![](https://jitpack.io/v/kayoSun/Tack.svg)](https://jitpack.io/#kayoSun/Tack) [![](https://github.com/kayoSun/resource/blob/master/svgs/apachelicense.svg)](LICENSE.txt)


Tack is an easy, fast and lightweight extension framework for Bundle data injection and storage.

With Tack, you can achieve:

1. Read common data type data from Bundle\Intent
2. Save common data type data to Bundle\Intent
3. Read data from Bundle\Intent and inject it into the variable in the Activity
4. Read data from Bundle\Intent and inject variables into Fragment
5. Create Bundles more easily through the factory, avoid hard coding

## Dependencies

> Add JitPack repository

```Groovy
maven { url 'https://jitpack.io' }
```

> Add dependency

```Groovy
dependencies {
	annotationProcessor 'com.github.kayoSun.Tack:tackprocessor:0.0.2'
	implementation 'com.github.kayoSun.Tack:tackapi:0.0.2'
}
```
## Proguard
```Proguard
-keep class com.kayo.lib.tack.annos.**
-keep class * implements com.kayo.lib.tack.api.Inject
-keepclasseswithmembernames class * {
    @com.kayo.lib.tack.annos.* <fields>;
}
```

##  Gradle Precautions

| Tack version | Android Projects |
|--------------|------------------|
| 0.0.2        | Gradle 4.6       |


## About Author

Thank's for your issues!<br>


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