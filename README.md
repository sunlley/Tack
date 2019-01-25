# Tack[![](https://jitpack.io/v/kayoSun/Tack.svg)](https://jitpack.io/#kayoSun/Tack)


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
	annotationProcessor "com.github.yjfnypeu.Parceler:compiler:$LastestVersion"
	implementation "com.github.yjfnypeu.Parceler:api:$LastestVersion"
}
```