# KotlinStudy
newLanguage study
# Android Studio 入门

> 写在开头:
>
> ​		1.规范是学习的第一要点
>
> ​		2.无论哪个新语言都要好好学习基本语法
>
> ​				2.2所以我会从新开始学习`Kotlin`
>
> 3.☞自己:
>
> ​		3.1:无端,永无止境的学习下去.
>
> ​		3.2:**选择即是热爱.**
>
> 记录:``2022-3-22 20:49:56`

# 1.下载Android Studio 

认识规范安卓版本:

![image-20220322205640392](https://gitee.com/jade_flute/stu-images/raw/master/image-20220322205640392.png)

# 2.学习Kotlin语法

> [安卓 Kotlin 基础知识课程 |安卓开发人员 (google.cn)](https://developer.android.google.cn/courses/android-basics-kotlin/course?hl=zh-cn)

我很抱歉需要翻墙才可看到视频内容,我的做法是找其他代替品.

> [基本类型 · Kotlin 官方文档 中文版 (kotlincn.net)](https://book.kotlincn.net/text/basic-types.html#字符串模板)

### 2.1.重点来讲讲异常

#### 异常类

Kotlin 中所有异常类继承自 `Throwable` 类。 每个异常都有消息、堆栈回溯信息以及可选的原因。

使用 `throw` 表达式来抛出异常：

```kotlin
fun main() {
//sampleStart
    throw Exception("Hi There!")
//sampleEnd
}
```

使用 `try`……`catch` 表达式来捕获异常：

```kotlin
try {
    // 一些代码
} catch (e: SomeException) {
    // 处理程序
} finally {
    // 可选的 finally 块
}
```

可以有零到多个 `catch` 块，`finally` 块可以省略。 但是 `catch` 与 `finally` 块至少需有一个。

#### Try 是一个表达式

`try` 是一个表达式，意味着它可以有一个返回值：

```kotlin
val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
```

`try`-表达式的返回值是 `try` 块中的最后一个表达式或者是（所有）`catch` 块中的最后一个表达式。 `finally` 块中的内容不会影响表达式的结果。

### 受检异常

Kotlin 没有受检异常。这其中有很多原因，但我们会提供一个简单的示例 that illustrates why it is the case。

以下是 JDK 中 `StringBuilder` 类实现的一个示例接口：

```java
Appendable append(CharSequence csq) throws IOException;
```

这个签名是说，每次我追加一个字符串到一些东西（一个 `StringBuilder`、某种日志、一个控制台等）上时，我就必须捕获 `IOException`。 为什么？因为相应实现可能正在执行 IO 操作（`Writer` 也实现了 `Appendable`）。 其结果是这种代码随处可见：

```kotlin
try {
    log.append(message)
} catch (IOException e) {
    // 必须要安全
}
```

这并不好，看看[《Effective Java》第三版](https://www.oracle.com/technetwork/java/effectivejava-136174.html) 第 77 条：*不要忽略异常* 就知道了。

Bruce Eckel says this about checked exceptions:

> 通过一些小程序测试得出的结论是异常规范会同时提高开发者的生产力与代码质量，但是大型软件项目的经验表明一个不同的结论——生产力降低、代码质量很少或没有提高。

And here are some additional thoughts on the matter:

- [《Java 的受检异常是一个错误》（Java's checked exceptions were a mistake）](https://radio-weblogs.com/0122027/stories/2003/04/01/JavasCheckedExceptionsWereAMistake.html)（Rod Waldhoff）
- [《受检异常的烦恼》（The Trouble with Checked Exceptions）](https://www.artima.com/intv/handcuffs.html)（Anders Hejlsberg）

If you want to alert callers about possible exceptions when calling Kotlin code from Java, Swift, or Objective-C, you can use the `@Throws` annotation. Read more about using this annotation [for Java](https://book.kotlincn.net/text/java-to-kotlin-interop.html#受检异常) and [for Swift and Objective-C](https://book.kotlincn.net/text/native-objc-interop.html#errors-and-exceptions).

#### Nothing 类型

在 Kotlin 中 `throw` 是表达式，所以你可以使用它（比如）作为 Elvis 表达式的一部分：

```kotlin
val s = person.name ?: throw IllegalArgumentException("Name required")
```

`throw` 表达式的类型是 `Nothing` 类型。 这个类型没有值，而是用于标记永远不能达到的代码位置。 在你自己的代码中，你可以使用 `Nothing` 来标记一个永远不会返回的函数：

```kotlin
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```

当你调用该函数时，编译器会知道在该调用后就不再继续执行了：

```kotlin
val s = person.name ?: fail("Name required")
println(s)     // 在此已知“s”已初始化
```

当处理类型推断时还可能会遇到这个类型。这个类型的可空变体 `Nothing?` 有一个可能的值是 `null`。如果用 `null` 来初始化一个要推断类型的值，而又没有其他信息可用于确定更具体的类型时，编译器会推断出 `Nothing?` 类型：

```kotlin
val x = null           // “x”具有类型 `Nothing?`
val l = listOf(null)   // “l”具有类型 `List<Nothing?>
```

