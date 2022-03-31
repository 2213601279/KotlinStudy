# KotlinStudy
newLanguage study

> 如果迷茫多一点的话就各自努力吧,顶峰再见了晨静

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

# 2.学习Kotlin语法

> [安卓 Kotlin 基础知识课程 |安卓开发人员 (google.cn)](https://developer.android.google.cn/courses/android-basics-kotlin/course?hl=zh-cn)

我很抱歉需要翻墙才可看到视频内容,我的做法是找其他代替品.

> [基本类型 · Kotlin 官方文档 中文版 (kotlincn.net)](https://book.kotlincn.net/text/basic-types.html#字符串模板)

### 2.1来说说变量和参数

>可变长参数函数
>
>函数的变长参数可以用 **`vararg`** 关键字进行标识：

```kotlin
fun vars(vararg v:Int){
    for(vt in v){
        print(vt)
    }
}

// 测试
fun main(args: Array<String>) {
    vars(1,2,3,4,5)  // 输出12345
}
```

### 2.1.1匿名函数的学习

#### **lambda表达式与局部函数的区别**

>[ kotlin - lambda表达式_guojingbu的博客-CSDN博客](https://blog.csdn.net/guojingbu/article/details/121185370)

- Lambda 表达式总是被大括号括着
- 定义 Lambda 表达式不需要 fun 关键字，无须指定函数名。
- 形参列表（如果有的话）在->之前声明，参数类型可以省略。
- 函数体（Lambda 表达式执行体）放在->之后。
- 函数的最后一个表达式自动被作为Lambda 表达式的返回值，无须使用 return 关键字。

```kotlin
val/var 变量名: (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }
可等价于
 // 此种写法：即表达式的返回值类型会根据操作的代码自推导出来。
val/var 变量名 = { 参数1:类型，参数2:类型, ... -> 操作参数的代码 }

```

##### 当有参数的时候

```kotlin
// 源代码
fun test(a : Int , b : Int) : Int{
    return a + b
}

// lambda
val test1 : (Int , Int) -> Int = {a , b -> a + b}
// 或者
//推荐使用该种方法来写可以为无法判断参数类型的时候来写
val test2 = {a : Int , b : Int -> a + b}

// 调用
test(3,5) => 结果为：8

```

##### 作为方法的参数的话

```kotlin
// lambda
fun test(a : Int , b : ( Int ,Int) -> Int) : Int{
    return a + b.invoke(3,5)
}
//调用
test(10,{ num1: Int, num2: Int ->  num1 + num2 })
//等价于:
test(10){ num1: Int, num2: Int ->  num1 + num2 } 

```

- 从上面的代码可以看出在如果函数的最后一个参数是一个lambda表达式，可以把lambda表达式提出到括号外面。
- b.invoke()等价于b()
- 如果一个lambda表达式作为一个函数的参数时，定义的时候只是指定了这个表达式的参数类型和返回值，所以我们在调用高阶函数的时候要传入该Lambda表达式具体的实现。
- **由于lambda表达式只一个功能灵活的代码块，所以我们完全可以把它赋值给一个变量或直接调用lambda表达式。**示例代码如下：

```kotlin
fun main(args: Array<String>) {
    //定义一个 Lambda 表达式，并在它后面添加括号来调用该 Lambda 表达式
    val result = { base: Int, exponent: Int ->
        var result = 1
        for (i in 1..exponent) {
            result *= base
        }
        result
    }(4, 3)
    println(result) //输出 64
}

```

lambda表达式与匿名函数的return

匿名函数只是一个没有名字的函数，本质上还是一个函数，因此匿名函数的return返回的是匿名函数本身。
lambda表达式他只是一个代码块return返回的是它所在的函数，而不是lambda表达式。 例如下面代码：

```kotlin
fun main(args: Array<String>) {
    var list = listOf(1,2,3,4,5)
    //匿名函数
    list.forEach(fun(value){
        println("元素为：${value}")
        return
    })
    //lambda表达式
    list.forEach {
        println("元素为：$​{it}")
        return
    }
}
```



lambda表达式打印结果如下：

```bash
元素为：1
```

匿名函数打印的结果：

```bash
元素为：1
元素为：2
元素为：3
元素为：4
元素为：5
```

从上面函数的打印结果可以验证，我们上面所说的结论，lambda表达式中的return直接返回它所在的函数。lambda表达式一般不写return，如果非得在lambda表达式中使用return语句来返回lambda表达式，可以使用如下方式：

```kotlin
list.forEach {
    println("元素为：${it}")
    return@forEach
}
```


```kotlin

```

##### 使用 lambda 表达式来过滤（filter）与映射（map）集合：

```kotlin
//sampleStart
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
      .filter { it.startsWith("a") }
      .sortedBy { it }
      .map { it.uppercase() }
      .forEach { println(it) }
//sampleEnd
```

来聊聊遇到的实际中,直接return 在lambda中会导致程序的异常终止

```kotlin
list.forEach {
        println("元素为：${it}")
    }
```

加上后后面的就不会执行了

### 2.2.重点来讲讲异常

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

这里介绍一下kotlin 的安全机制以及他独特的NULL检查机制

### 2

### 3.0 NULL检查机制

Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，字段后加?像Java一样抛出空异常，另一种字段后加?可不做处理返回值为 `null`或者`?: `做空判断处理

```kotlin
fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

//sampleStart
fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}
//sampleEnd

fun main() {
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")
}
```

# 数据类

> [数据类 · Kotlin 官方文档 中文版 (kotlincn.net)](https://book.kotlincn.net/text/data-classes.html)

创建一些只保存数据的类是件寻常的事。 在这些类中，一些标准功能以及一些工具函数往往是由数据机械推导而来的。在 Kotlin 中，这叫做 *数据类* 并以 `data` 标记：

```kotlin
data class User(val name: String, val age: Int)
```

编译器自动从主构造函数中声明的所有属性导出以下成员：

- `equals()`/`hashCode()` 对
- `toString()` 格式是 `"User(name=John, age=42)"`
- [`componentN()` 函数](https://book.kotlincn.net/text/destructuring-declarations.html) 按声明顺序对应于所有属性。
- `copy()` 函数（见下文）

为了确保生成的代码的一致性以及有意义的行为，数据类必须满足以下要求：

- 主构造函数需要至少有一个参数。
- 主构造函数的所有参数需要标记为 `val` 或 `var`。
- 数据类不能是抽象、开放、密封或者内部的。

此外，数据类成员的生成遵循关于成员继承的这些规则：

- 如果在数据类体中有显式实现 `equals()`、 `hashCode()` 或者 `toString()`，或者这些函数在父类中有 `final` 实现，那么不会生成这些函数，而会使用现有函数。
- 如果超类型具有 `open` 的 `componentN()` 函数并且返回兼容的类型， 那么会为数据类生成相应的函数，并覆盖超类的实现。如果超类型的这些函数由于签名不兼容或者是 final 而导致无法覆盖，那么会报错。
- 不允许为 `componentN()` 以及 `copy()` 函数提供显式实现。

数据类可以扩展其他类（示例请参见[密封类](https://book.kotlincn.net/text/sealed-classes.html)）。

> 在 JVM 中，如果生成的类需要含有一个无参的构造函数，那么属性必须指定默认值。（参见[构造函数](https://book.kotlincn.net/text/classes.html#构造函数)）。
>
> <svg width="24" height="24" fill="#4dbb5f" viewBox="0 0 24 24"><path d="M21 12a9 9 0 1 1-9-9 9 9 0 0 1 9 9zM10.5 7.5A1.5 1.5 0 1 0 12 6a1.5 1.5 0 0 0-1.5 1.5zm-.5 3.54v1h1V18h2v-6a.96.96 0 0 0-.96-.96z"></path></svg>

```kotlin
data class User(val name: String = "", val age: Int = 0)
```

### 在类体中声明的属性

请注意，对于那些自动生成的函数，编译器只使用在主构造函数内部定义的属性。 如需在生成的实现中排除一个属性，请将其声明在类体中：

```kotlin
data class Person(val name: String) {
    var age: Int = 0
}
```

在 `toString()`、 `equals()`、 `hashCode()` 以及 `copy()` 的实现中只会用到 `name` 属性， 并且只有一个 component 函数 `component1()`。虽然两个 `Person` 对象可以有不同的年龄， 但它们会视为相等。

```kotlin
data class Person(val name: String) {
    var age: Int = 0
}
fun main() {
//sampleStart
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20
//sampleEnd
    println("person1 == person2: ${person1 == person2}")
    println("person1 with age ${person1.age}: ${person1}")
    println("person2 with age ${person2.age}: ${person2}")
}
```

### 复制

Use the `copy()` function to copy an object, allowing you to alter *some* of its properties while keeping the rest unchanged. The implementation of this function for the `User` class above would be as follows:

```kotlin
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
```

然后可以这样写：

```kotlin
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)
```

### 数据类与解构声明

为数据类生成的 *component 函数* 使它们可在[解构声明](https://book.kotlincn.net/text/destructuring-declarations.html)中使用：

```kotlin
val jane = User("Jane", 35)
val (name, age) = jane
println("$name, $age years of age") // 输出 "Jane, 35 years of age"
```

### 标准数据类

标准库提供了 `Pair` 与 `Triple` 类。尽管在很多情况下具名数据类是更好的设计选择， 因为它们通过为属性提供有意义的名称使代码更具可读性。

>1. Defines a data class with the 属性定义数据类`data` modifier. 修饰语
>2. Override the default 覆盖默认值`equals` method by declaring users equal if they have the same 方法，如果用户具有相同的值，则声明用户相等`id`.
>3. Method 方法`toString` is auto-generated, which makes 是自动生成的`println` output look nice. 输出看起来不错
>4. Our custom 我们的风俗`equals` considers two instances equal if their 认为两个情况相等，如果他们的`id`s are equal. S 是相等的
>5. Data class instances with exactly matching attributes have the same 具有完全匹配属性的数据类实例具有相同的属性`hashCode`.
>6. Auto-generated 自动生成`copy` function makes it easy to create a new instance. 函数使创建新实例变得容易
>7. `copy` creates a new instance, so the object and its copy have distinct references. 创建一个新实例，因此对象及其副本具有不同的引用
>8. When copying, you can change values of certain properties. 复制时，可以更改某些属性的值`copy` accepts arguments in the same order as the class constructor. 接受与类构造函数相同顺序的参数
>9. Use 使用`copy` with named arguments to change the value despite of the properties order. 使用命名参数来更改值，尽管属性顺序不同
>10. Auto-generated 自动生成`componentN` functions let you get the values of properties in the order of declaration. 函数让你按声明的顺序得到属性的值
>
>

# 枚举类型

>1. Defines an enum class with a property and a method. 用属性和方法定义枚举类
>2. Each enum constant must pass an argument for the constructor parameter. 每个枚举常数必须为构造函数参数传递一个参数
>3. Enum class members are separated from the constant definitions by a semicolon. 枚举类成员与常量定义之间用分号隔开
>4. The default 默认`toString` returns the name of the constant, here 返回这个常量的名称`"RED"`.
>5. Calls a method on an enum constant. 调用枚举常数上的方法
>6. Calls a method via enum class name. 通过枚举类名调用方法
>7. The RGB values of 的 RGB 值`RED` and 及`YELLOW` share first bits ( 共享第一位`FF`) so this prints 'true'. )所以这打印’真实’

# 密封类

> 限制继承的使用
>
> 密封类允许您限制继承的使用。一旦声明了一个类密封，它只能从声明密封类的同一个包中进行子类化。它不能在声明密封类的包之外被子类化。

```kotlin
sealed class Mammal(val name: String)                                                   // 1

class Cat(val catName: String) : Mammal(catName)                                        // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal): String {
    when (mammal) {                                                                     // 3
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5     
    }                                                                                   // 6
}

fun main() {
    println(greetMammal(Cat("Snowy")))
}
```

1. Defines a sealed class. 定义一个密封类
2. Defines subclasses. Note that all subclasses must be in the same package.
3. 定义子类。注意所有子类必须在同一个包中。
4. Uses an instance of the sealed class as an argument in a `when` expression.
5. 在 when 表达式中将密封类的实例用作参数。
6. A smartcast is performed, casting `Mammal` to `Human`.
7. 进行智能铸造，从哺乳动物铸造到人类。
8. A smartcast is performed, casting `Mammal` to `Cat`.
9. 执行 smartcast，从哺乳动物到猫。
10. The `else`-case is not necessary here since all possible subclasses of the sealed class are covered. With a non-sealed superclass `else` would be required.
11. 这里不需要 else-case，因为已经包含了密封类的所有可能的子类。如果是非密封的超类，则需要其他类。

## 对象表达式

1. Creates a function with parameters. 创建带有参数的函数
2. Creates an object to use when calculating the result value.
3. 创建在计算结果值时使用的对象。
4. Accesses the object's properties. 访问对象的属性
5. Prints the result. 打印结果
6. Calls the function. This is when the object is actually created.
7. 调用函数。这是在实际创建对象的时候。

下面是对象表达式的一个基本典型用法: 简单的对象/属性结构。在类声明中不需要这样做: 您创建一个对象，声明其成员并在一个函数中访问它。这样的对象通常在 Java 中创建为匿名类实例

```kotlin
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }

    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

    print("Total price: $$total")                                               //4

} 
fun main(){
    rentPrice(10,2,1)
}
```

## 伴侣对象



1. Defines a class. 定义一个类
2. Defines a companion. Its name can be omitted.
3. 定义一个同伴。它的名称可以省略。
4. Defines a companion object method. 定义一个伴随对象方法
5. Calls the companion object method via the class name.
6. 通过类名调用伴随对象方法。



# Java 与 Kotlin 中的字符串

This guide contains examples of how to perform typical tasks with strings in Java and Kotlin. It will help you migrate from Java to Kotlin and write your code in the authentically Kotlin way.

## 字符串连接

In Java, you can do this in the following way:

```java
// Java
String name = "Joe";
System.out.println("Hello, " + name);
System.out.println("Your name is " + name.length() + " characters long");
```

In Kotlin, use the dollar sign (`$`) before the variable name to interpolate the value of this variable into your string:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val name = "Joe"
    println("Hello, $name")
    println("Your name is ${name.length} characters long")
//sampleEnd
}
```

You can interpolate the value of a complicated expression by surrounding it with curly braces, like in `${name.length}`. See [string templates](https://book.kotlincn.net/text/basic-types.html#字符串模板) for more information.

## 构建字符串

In Java, you can use the [StringBuilder](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html):

```java
// Java
StringBuilder countDown = new StringBuilder();
for (int i = 5; i > 0; i--) {
    countDown.append(i);
    countDown.append("\n");
}
System.out.println(countDown);
```

In Kotlin, use [buildString()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/build-string.html) – an [inline function](https://book.kotlincn.net/text/inline-functions.html) that takes logic to construct a string as a lambda argument:

```kotlin
fun main() {
//sampleStart
       // Kotlin
       val countDown = buildString {
           for (i in 5 downTo 1) {
               append(i)
               appendLine()
           }
       }
       println(countDown)
//sampleEnd
}
```

Under the hood, the `buildString` uses the same `StringBuilder` class as in Java, and you access it via an implicit `this` inside the [lambda](https://book.kotlincn.net/text/lambdas.html#带有接收者的函数字面值).

Learn more about [lambda coding conventions](https://book.kotlincn.net/text/coding-conventions.html#lambda-表达式).

## 由集合内元素创建字符串

In Java, you use the [Stream API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/package-summary.html) to filter, map, and then collect the items:

```java
// Java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
String invertedOddNumbers = numbers
        .stream()
        .filter(it -> it % 2 != 0)
        .map(it -> -it)
        .map(Object::toString)
        .collect(Collectors.joining(", "));
System.out.println(invertedOddNumbers);
```

In Kotlin, use the [joinToString()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/join-to-string.html) function, which Kotlin defines for every List:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val invertedOddNumbers = numbers
        .filter { it % 2 != 0 }
        .joinToString{ "${-it}" }
    println(invertedOddNumbers)
//sampleEnd
}
```

Learn more about [joinToString()](https://book.kotlincn.net/text/collection-transformations.html#字符串表示) usage.

## 如果字符串为空白就设置默认值

In Java, you can use the [ternary operator](https://en.wikipedia.org/wiki/%3F:):

```java
// Java
public void defaultValueIfStringIsBlank() {
    String nameValue = getName();
    String name = nameValue.isBlank() ? "John Doe" : nameValue;
    System.out.println(name);
}

public String getName() {
    Random rand = new Random();
    return rand.nextBoolean() ? "" : "David";
}
```

Kotlin provides the inline function [ifBlank()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/if-blank.html) that accepts the default value as an argument:

```kotlin
// Kotlin
import kotlin.random.Random

//sampleStart
fun main() {
    val name = getName().ifBlank { "John Doe" }
    println(name)
}

fun getName(): String =
    if (Random.nextBoolean()) "" else "David"
//sampleEnd
```

## 替换字符串首尾处的（多个）字符

In Java, you can use the [replaceFirst()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#replaceFirst(java.lang.String,java.lang.String)) and the [replaceAll()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#replaceAll(java.lang.String,java.lang.String)) functions. The `replaceAll()` function in this case accepts the regular expression `##$`, which defines a string ending with `##`:

```java
// Java
String input = "##place##holder##";
String result = input.replaceFirst("##", "").replaceAll("##$", "");
System.out.println(result);
```

In Kotlin, use the [removeSurrounding()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/remove-surrounding.html) function with the string delimiter `##`:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val input = "##place##holder##"
    val result = input.removeSurrounding("##")
    println(result)
//sampleEnd
}
```

## 正则替换

In Java, you can use the [Pattern](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html) and the [Matcher](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Matcher.html) classes, for example, to obfuscate some data:

```java
// Java
String input = "login: Pokemon5, password: 1q2w3e4r5t";
Pattern pattern = Pattern.compile("\\w*\\d+\\w*");
Matcher matcher = pattern.matcher(input);
String replacementResult = matcher.replaceAll(it -> "xxx");
System.out.println("Initial input: '" + input + "'");
System.out.println("Anonymized input: '" + replacementResult + "'");
```

In Kotlin, you use the [Regex](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/) class that simplifies working with regular expressions. Additionally, use [raw strings](https://book.kotlincn.net/text/basic-types.html#字符串字面值) to simplify a regex pattern by reducing the count of backslashes:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val regex = Regex("""\w*\d+\w*""") // raw string
    val input = "login: Pokemon5, password: 1q2w3e4r5t"
    val replacementResult = regex.replace(input, replacement = "xxx")
    println("Initial input: '$input'")
    println("Anonymized input: '$replacementResult'")
//sampleEnd
}
```

## 字符串拆分

In Java, to split a string with the period character (`.`), you need to use shielding (`\\`). This happens because the [split()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#split(java.lang.String)) function of the `String` class accepts a regular expression as an argument:

```java
// Java
System.out.println(Arrays.toString("Sometimes.text.should.be.split".split("\\.")));
```

In Kotlin, use the Kotlin function [split()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/split.html), which accepts varargs of delimiters as input parameters:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    println("Sometimes.text.should.be.split".split("."))
//sampleEnd
}
```

If you need to split with a regular expression, use the overloaded `split()` version that accepts the `Regex` as a parameter.

## 取子串

In Java, you can use the [substring()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#substring(int)) function, which accepts an inclusive beginning index of a character to start taking the substring from. To take a substring after this character, you need to increment the index:

```java
// Java
String input = "What is the answer to the Ultimate Question of Life, the Universe, and Everything? 42";
String answer = input.substring(input.indexOf("?") + 1);
System.out.println(answer);
```

In Kotlin, you use the [substringAfter()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/substring-after.html) function and don’t need to calculate the index of the character you want to take a substring after:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val input = "What is the answer to the Ultimate Question of Life, the Universe, and Everything? 42"
    val answer = input.substringAfter("?")
    println(answer)
//sampleEnd
}
```

Additionally, you can take a substring after the last occurrence of a character:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val input = "To be, or not to be, that is the question."
    val question = input.substringAfterLast(",")
    println(question)
//sampleEnd
}
```

## 使用多行字符串

Before Java 15, there were several ways to create a multiline string. For example, using the [join()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#join(java.lang.CharSequence,java.lang.CharSequence...)) function of the `String` class:

```java
// Java
String lineSeparator = System.getProperty("line.separator");
String result = String.join(lineSeparator,
       "Kotlin",
       "Java");
System.out.println(result);
```

In Java 15, [text blocks](https://docs.oracle.com/en/java/javase/15/text-blocks/index.html) appeared. There is one thing to keep in mind: if you print a multiline string and the triple-quote is on the next line, there will be an extra empty line:

```java
// Java
String result = """
    Kotlin
    Java
    """.stripIndent();
System.out.println(result);
```

The output: ![Java 15 multiline output](https://book.kotlincn.net/images/java-15-multiline-output.png)

If you put the triple-quote on the same line as the last word, this difference in behavior disappears.

In Kotlin, you can format your line with the quotes on the new line, and there will be no extra empty line in the output. The left-most character of any line identifies the beginning of the line.

```kotlin
fun main() {
//sampleStart
    // Kotlin   
    val result = """
        Kotlin
        Java 
    """.trimIndent()
    println(result)
//sampleEnd
}
```

The output: ![Kotlin multiline output](https://book.kotlincn.net/images/kotlin-multiline-output.png)

In Kotlin, you can also use the [trimMargin()](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/trim-margin.html) function to customize the indents:

```kotlin
// Kotlin
fun main() {
    val result = """
       #  Kotlin
       #  Java
   """.trimMargin("#")
    println(result)
}
```

Learn more about [multiline strings](https://book.kotlincn.net/text/coding-conventions.html#strings).

# 集合类

## Java和Kotlin的对比

集合是一组数量可变的项(可能为零) ，这些项对正在解决的问题非常重要，并且通常对其进行操作。本指南解释和比较了爪哇和 Kotlin 的收集概念和操作。它将帮助您从 Java 迁移到 Kotlin，并以真正的 Kotlin 方式编写代码。

### 对 list、 set、 queue 与 deque 的操作

### Operations on list, set, queue, and deque

| 描述 Description                                             | 共有操作 Shared operation                                 | 更多 Kotlin 替代方式More Kotlin Alternatives                 |
| ------------------------------------------------------------ | --------------------------------------------------------- | ------------------------------------------------------------ |
| Add an element or elements 添加一个或多个元素                | `add()`, `addAll()`Add () ，addAll ()                     | Use the [`plusAssign`(`+=`) operator](https://book.kotlincn.net/text/collection-plus-minus.html): `collection += element`, `collection += anotherCollection`.使用 plusAssign (+ =)操作符: collection + = element，collection + = anotherCollection。 |
| Check whether a collection contains an element or elements检查集合是否包含元素或元素 | `contains()`, `containsAll()`Contains () ，containsAll () | Use the [`in` keyword](https://book.kotlincn.net/text/collection-elements.html#检测元素存在与否) to call `contains()` in the operator form: `element in collection`.使用 in 关键字调用操作符窗体 contains () : element in collection。 |
| Check whether a collection is empty检查集合是否为空          | `isEmpty()`                                               | Use [`isNotEmpty()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html) to check whether a collection is not empty.使用 isNotEmpty ()检查集合是否为空。 |
| Remove under a certain condition 在一定条件下移除            | `removeIf()`                                              |                                                              |
| Leave only selected elements 只保留选定的元素                | `retainAll()`                                             |                                                              |
| Remove all elements from a collection从集合中移除所有元素    | `clear()`                                                 |                                                              |
| Get a stream from a collection从集合中获取流                 | `stream()`                                                | Kotlin has its own way to process streams: [sequences](https://book.kotlincn.net/text/java-to-kotlin-collections-guide.html#序列) and methods like [`map()`](https://book.kotlincn.net/text/collection-filtering.html) and [`filter()`](https://book.kotlincn.net/text/java-to-kotlin-collections-guide.html#过滤元素).Kotlin 有自己的处理流的方法: 序列和方法，比如 map ()和 filter ()。 |
| Get an iterator from a collection从集合中获取迭代器          | `iterator()`                                              |                                                              |

### 对 map 的操作

### Operation on map

| 描述 Description                                             | 共有操作 Shared operation                                    | 更多 Kotlin 替代方式More Kotlin Alternatives                 |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Add an element or elements 添加一个或多个元素                | `put()`, `putAll()`, `putIfAbsent()`Put () ，puttall () ，putifapsent () | In Kotlin, the assignment `map[key] = value` behaves the same as `put(key, value)`. Also, you may use the [`plusAssign`(`+=`) operator](https://book.kotlincn.net/text/collection-plus-minus.html): `map += Pair(key, value)` or `map += anotherMap`.在 Kotlin，赋值映射[ key ] = value 的行为与 put (key，value)相同。另外，您可以使用 plusAssign (+ =)操作符: map + = Pair (key，value)或 map + = anotherMap。 |
| Replace an element or elements 替换一个或多个元素            | `put()`, `replace()`, `replaceAll()`Put ()、 replace ()、 replaceAll () | Use the indexing operator `map[key] = value` instead of `put()` and `replace()`.使用索引操作符 map [ key ] = value 而不是 put ()和 replace ()。 |
| Get an element 获取一个元素                                  | `get()`                                                      | Use the indexing operator to get an element: `map[index]`.使用索引操作符获取元素: map [ index ]。 |
| Check whether a map contains an element or elements检查映射是否包含元素或元素 | `containsKey()`, `containsValue()`容器 key () ，容器 value () | Use the [`in` keyword](https://book.kotlincn.net/text/collection-elements.html#检测元素存在与否) to call `contains()` in the operator form: `element in map`.使用 in 关键字在操作符形式: map 中的元素中调用 contains ()。 |
| Check whether a map is empty检查地图是否为空                 | `isEmpty()`                                                  | Use [`isNotEmpty()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html) to check whether a map is not empty.使用 isNotEmpty ()检查映射是否为空。 |
| Remove an element 删除一个元素                               | `remove(key)`, `remove(key, value)`删除(键) ，删除(键，值)   | Use the [`minusAssign`(`-=`) operator](https://book.kotlincn.net/text/collection-plus-minus.html): `map -= key`.使用 minisassign (- =)操作符: map-= key。 |
| Remove all elements from a map从地图中移除所有元素           | `clear()`                                                    |                                                              |
| Get a stream from a map从地图上获取一条小溪                  | `stream()` on entries, keys, or values对条目、键或值执行 stream () |                                                              |

### 仅针对 list 的操作

### List only

| 描述 Description                                       | 共有操作 Shared operation                     | 更多 Kotlin 替代方式More Kotlin Alternatives                 |
| ------------------------------------------------------ | --------------------------------------------- | ------------------------------------------------------------ |
| Get an index of an element获取元素的索引               | `indexOf()`                                   |                                                              |
| Get the last index of an element获取元素的最后一个索引 | `lastIndexOf()`                               |                                                              |
| Get an element 获取一个元素                            | `get()`                                       | Use the indexing operator to get an element: `list[index]`.使用索引操作符获取元素: list [ index ]。 |
| Take a sublist 拿一个子列表来说                        | `subList()`                                   |                                                              |
| Replace an element or elements 替换一个或多个元素      | `set()`, `replaceAll()`Set () ，replaceAll () | Use the indexing operator instead of `set()`: `list[index] = value`.使用索引操作符代替 set () : list [ index ] = value。 |

## 略有不同的操作

## Slightly different operation

### 对任意集合类型的操作

### An operation on an arbitrary collection type

| 描述 Description                                             | Java 爪哇                                                    | Kotlin                                                       |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Get a collection's size 得到一个系列的大小                   | `size()`                                                     | `count()`, `size`计数() ，大小                               |
| Get flat access to nested collection elements获取对嵌套集合元素的平面访问 | `collectionOfCollections.forEach(flatCollection::addAll)` or `collectionOfCollections.stream().flatMap().collect()`Foreach (flatCollection: : addAll)或 collections.stream () . flatMap () . collect () | [`flatten()`](https://book.kotlincn.net/text/collection-transformations.html#展平) or [`flatMap()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/flat-map.html)Flatten ()或 flatMap () |
| Apply the given function to every element对每个元素应用给定的函数 | `stream().map().collect()`                                   | [`map()`](https://book.kotlincn.net/text/collection-filtering.html) |
| Apply the provided operation to collection elements sequentially and return the accumulated result按顺序对集合元素应用所提供的操作并返回累积的结果 | `stream().reduce()`                                          | [`reduce()`, `fold()`](https://book.kotlincn.net/text/collection-aggregate.html#fold-与-reduce)[减少，减少，减少](https://book.kotlincn.net/text/collection-aggregate.html#fold-与-reduce) |
| Group elements by a classifier and count them用分类器对元素进行分组并计数 | `stream().collect(Collectors.groupingBy(classifier, counting()))``Stream () . collect (Collectors.groupingBy (classifier，counting ()))` | [`eachCount()`](https://book.kotlincn.net/text/collection-grouping.html) |
| Filter by a condition 按条件过滤                             | `stream().filter().collect()`                                | [`filter()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter.html) |
| Check whether collection elements satisfy a condition检查集合元素是否满足条件 | `stream().noneMatch()`, `stream().anyMatch()`, `stream().allMatch()`Stream () . noneMatch () ，stream () . anyMatch () ，stream () . allMatch () | [`none()`, `any()`, `all()`](https://book.kotlincn.net/text/collection-filtering.html)[没有() ，没有() ，都()](https://book.kotlincn.net/text/collection-filtering.html) |
| Sort elements 排序元素                                       | `stream().sorted().collect()`                                | [`sorted()`](https://book.kotlincn.net/text/collection-ordering.html#自然顺序) |
| Take the first N elements 以前 n 个元素为例                  | `stream().limit(N).collect()`                                | [`take(N)`](https://book.kotlincn.net/text/collection-parts.html#take-与-drop) |
| Take elements with a predicate 使用具有谓词的元素            | `stream().takeWhile().collect()`                             | [`takeWhile()`](https://book.kotlincn.net/text/collection-parts.html#take-与-drop) |
| Skip the first N elements 跳过前 n 个元素                    | `stream().skip(N).collect()`                                 | [`drop(N)`](https://book.kotlincn.net/text/collection-parts.html#take-与-drop) |
| Skip elements with a predicate 带有谓词的跳过元素            | `stream().dropWhile().collect()`                             | [`dropWhile()`](https://book.kotlincn.net/text/collection-parts.html#take-与-drop) |
| Build maps from collection elements and certain values associated with them从集合元素和与它们关联的某些值构建映射 | `stream().collect(toMap(keyMapper, valueMapper))`            | [`associate()`](https://book.kotlincn.net/text/collection-transformations.html#关联) |

To perform all of the operations listed above on maps, you first need to get an `entrySet` of a map.

要执行上面列出的映射中的所有操作，首先需要获得映射的 entrySet。

### 对 list 的操作

### Operation on list

| 描述 Description                                             | Java 爪哇                                                 | Kotlin                                                       |
| ------------------------------------------------------------ | --------------------------------------------------------- | ------------------------------------------------------------ |
| Sort a list into natural order将列表按自然顺序排序           | `sort(null)`                                              | `sort()`                                                     |
| Sort a list into descending order将列表按降序排序            | `sort(comparator)`                                        | `sortDescending()`                                           |
| Remove an element from a list从列表中删除一个元素            | `remove(index)`, `remove(element)`删除(索引) ，删除(元素) | `removeAt(index)`, `remove(element)` or [`collection -= element`](https://book.kotlincn.net/text/collection-plus-minus.html)removeAt (index)、 remove (element)或 collection-= element |
| Fill all elements of a list with a certain value用特定的值填充列表的所有元素 | `Collections.fill()`                                      | [`fill()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/fill.html) |
| Get unique elements from a list从列表中获取唯一的元素        | `stream().distinct().toList()`                            | [`distinct()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/distinct.html) |

## Java 标准库中不存在的操作

## Operations that do not exist in the Java standard library

- [`zip()`, `unzip()`](https://book.kotlincn.net/text/collection-transformations.html) – transform a collection.
- Zip ()、 unzip ()-转换一个集合。
- [`aggregate()`](https://book.kotlincn.net/text/collection-grouping.html) – group by a condition.
- 集合()-按条件分组。
- [`takeLast()`, `takeLastWhile()`, `dropLast()`, `dropLastWhile()`](https://book.kotlincn.net/text/collection-parts.html#take-与-drop) – take or drop elements by a predicate.
- takeLast ()、 takeLastWhile ()、 dropLast ()、 dropLastWhile ()-通过谓词获取或删除元素。
- [`slice()`, `chunked()`, `windowed()`](https://book.kotlincn.net/text/collection-parts.html) – retrieve collection parts.
- Slice () ，chunked () ，windowed ()-retrieve 收集部件。
- [Plus (`+`) and minus (`-`) operators](https://book.kotlincn.net/text/collection-plus-minus.html) – add or remove elements.
- 加(+)和减(-)操作符-添加或删除元素。

## 可变性

## Variability

In Java, there are mutable collections:

在 Java 中，有可变的集合:

```java
// Java
// This list is mutable!
public List<Customer> getCustomers() { … }
```

Partially mutable ones:

部分易变的:

```java
// Java
List<String> numbers = Arrays.asList("one", "two", "three", "four");
numbers.add("five"); // Fails in runtime with `UnsupportedOperationException`
```

And immutable ones:

还有不可改变的:

```java
// Java
List<String> numbers = new LinkedList<>();
// This list is immutable!
List<String> immutableCollection = Collections.unmodifiableList(numbers);
immutableCollection.add("five"); // Fails in runtime with `UnsupportedOperationException`
```

If you write the last two pieces of code in IntelliJ IDEA, the IDE will warn you that you're trying to modify an immutable object. This code will compile and fail in runtime with `UnsupportedOperationException`. You can't tell whether a collection is mutable by looking at its type.

如果你在 IntelliJ IDEA 中编写了最后两段代码，IDE 会警告你你正在修改一个不可变物件。此代码将在运行时编译和失败，并带有 UnsupportedOperationException。您不能通过查看一个集合的类型来判断它是否可变。

Unlike in Java, in Kotlin you explicitly declare mutable or read-only collections depending on your needs. If you try to modify a read-only collection, the code won't compile:

与 Java 不同，在 Kotlin，您可以根据需要显式声明可变集合或只读集合。如果您尝试修改只读集合，则代码不能编译:

```kotlin
// Kotlin
val numbers = mutableListOf("one", "two", "three", "four")
numbers.add("five")            // This is OK
val immutableNumbers = listOf("one", "two")
//immutableNumbers.add("five") // Compilation error - Unresolved reference: add
```

Read more about immutability on the [Kotlin coding conventions](https://book.kotlincn.net/text/coding-conventions.html#不可变性) page.

在 Kotlin 编码约定页面上阅读更多关于不变性的内容。

## 协变性

## Covariation

In Java, you can't pass a collection with a descendant type to a function that takes a collection of the ancestor type. For example, if `Rectangle` extends `Shape`, you can't pass a collection of `Rectangle` elements to a function that takes a collection of `Shape` elements. To make the code compilable, use the `? extends Shape` type so the function can take collections with any inheritors of `Shape`:

在 Java 中，不能将具有子代类型的集合传递给接受祖先类型集合的函数。例如，如果 Rectangle 扩展了 Shape，则不能将 Rectangle 元素的集合传递给接受 Shape 元素集合的函数。要使代码可编译，请使用？扩展 Shape 类型，这样函数就可以与 Shape 的任何继承者一起使用集合:

```java
// Java
class Shape {}

class Rectangle extends Shape {}

public void doSthWithShapes(List<? extends Shape> shapes) {
/* If using just List<Shape>, the code won't compile when calling
this function with the List<Rectangle> as the argument as below */
}

public void main() {
    var rectangles = List.of(new Rectangle(), new Rectangle());
    doSthWithShapes(rectangles);
}
```

In Kotlin, read-only collection types are [covariant](https://book.kotlincn.net/text/generics.html#型变). This means that if a `Rectangle` class inherits from the `Shape` class, you can use the type `List<Rectangle>` anywhere the `List<Shape>` type is required. In other words, the collection types have the same subtyping relationship as the element types. Maps are covariant on the value type, but not on the key type. Mutable collections aren't covariant – this would lead to runtime failures.

在 Kotlin，只读集合类型是协变的。这意味着，如果一个 Rectangle 类继承自 Shape 类，那么您可以在需要 List < Shape > 类型的任何地方使用 List < Rectangle > 类型。换句话说，集合类型与元素类型具有相同的子类型关系。映射在值类型上是协变的，但在键类型上不是。可变集合不是协变的——这会导致运行时故障。

```kotlin
// Kotlin
open class Shape(val name: String)

class Rectangle(private val rectangleName: String) : Shape(rectangleName)

fun doSthWithShapes(shapes: List<Shape>) {
    println("The shapes are: ${shapes.joinToString { it.name }}")
}

fun main() {
    val rectangles = listOf(Rectangle("rhombus"), Rectangle("parallelepiped"))
    doSthWithShapes(rectangles)
}
```

Read more about [collection types](https://book.kotlincn.net/text/collections-overview.html#集合类型)here.

点击这里阅读更多关于集合排版的内容。

## 区间与数列

## Interval and sequence

In Kotlin, you can create intervals using [ranges](https://book.kotlincn.net/text/ranges.html#区间). For example, `Version(1, 11)..Version(1, 30)` includes all of the versions from `1.11` to `1.30`. You can check that your version is in the range by using the `in` operator: `Version(0, 9) in versionRange`.

在 Kotlin，你可以使用 range 创建音程。例如，Version (1,11)。.版本(1,30)包括从1.11到1.30的所有版本。您可以使用 versionRange 中的 in 操作符: Version (0,9)来检查您的版本是否在范围内。

In Java, you need to manually check whether a `Version` fits both bounds:

在 Java 中，你需要手动检查一个版本是否符合两个边界:

```java
// Java
class Version implements Comparable<Version> {

    int major;
    int minor;

    Version(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    @Override
    public int compareTo(Version o) {
        if (this.major != o.major) {
            return this.major - o.major;
        }
        return this.minor - o.minor;
    }
}

public void compareVersions() {
    var minVersion = new Version(1, 11);
    var maxVersion = new Version(1, 31);

   System.out.println(
           versionIsInRange(new Version(0, 9), minVersion, maxVersion));
   System.out.println(
           versionIsInRange(new Version(1, 20), minVersion, maxVersion));
}

public Boolean versionIsInRange(Version versionToCheck, Version minVersion, 
                                Version maxVersion) {
    return versionToCheck.compareTo(minVersion) >= 0 
            && versionToCheck.compareTo(maxVersion) <= 0;
}
```

In Kotlin, you operate with a range as a whole object. You don't need to create two variables and compare a `Version` with them:

在 Kotlin，你把一个范围作为一个整体来操作。你不需要创建两个变量并与它们比较一个版本:

```kotlin
// Kotlin
class Version(val major: Int, val minor: Int): Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        }
        return this.minor - other.minor
    }
}

fun main() {
    val versionRange = Version(1, 11)..Version(1, 30)

    println(Version(0, 9) in versionRange)
    println(Version(1, 20) in versionRange)
}
```

As soon as you need to exclude one of the bounds, like to check whether a version is greater than or equal to (`>=`) the minimum version and less than (`<`) the maximum version, these inclusive ranges won't help.

一旦您需要排除一个边界，比如检查一个版本是否大于或等于(> =)最小版本和小于(<)最大版本，这些包含范围将不起作用。

## 按照多个维度比较

## It's a multi-dimensional comparison

In Java, to compare objects by several criteria, you may use the [`comparing()`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#comparing-java.util.function.Function-) and [`thenComparingX()`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#thenComparing-java.util.Comparator-) functions from the [`Comparator`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html) interface. For example, to compare people by their name and age:

在 Java 中，要根据几个条件比较对象，可以使用 Comparator 接口中的 comparing ()和 comparingx ()函数。例如，通过名字和年龄来比较人们:

```Java
class Person implements Comparable<Person> {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " + age;
    }
}

public void comparePersons() {
    var persons = List.of(new Person("Jack", 35), new Person("David", 30), 
            new Person("Jack", 25));
    System.out.println(persons.stream().sorted(Comparator
            .comparing(Person::getName)
            .thenComparingInt(Person::getAge)).collect(toList()));
}
```

In Kotlin, you just enumerate which fields you want to compare:

在 Kotlin，你只需要列举你想要比较的字段:

```kotlin
data class Person(
    val name: String,
    val age: Int
)

fun main() {
    val persons = listOf(Person("Jack", 35), Person("David", 30), 
        Person("Jack", 25))
    println(persons.sortedWith(compareBy(Person::name, Person::age)))
}
```

## 序列

## Sequence

In Java, you can generate a sequence of numbers this way:

在 Java 中，你可以这样生成一系列数字:

```java
// Java
int sum = IntStream.iterate(1, e -> e + 3)
    .limit(10).sum();
System.out.println(sum); // Prints 145
```

In Kotlin, use *[sequences](https://book.kotlincn.net/text/sequences.html)*. Multi-step processing of sequences is executed lazily when possible – actual computing happens only when the result of the whole processing chain is requested.

在 Kotlin，使用序列。在可能的情况下，对序列进行多步处理，只有在要求整个处理链的结果时才进行实际计算。

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val sum = generateSequence(1) {
        it + 3
    }.take(10).sum()
    println(sum) // Prints 145
//sampleEnd
}
```

Sequences may reduce the number of steps that are needed to perform some filtering operations. See the [sequence processing example](https://book.kotlincn.net/text/sequences.html#序列处理示例), which shows the difference between `Iterable` and `Sequence`.

序列可以减少执行某些筛选操作所需的步骤数。请参阅序列处理示例，该示例显示了 Iterable 和 Sequence 之间的差异。

## 从列表中删除元素

## Deletes an element from the list

In Java, the [`remove()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html#remove(int)) function accepts an index of an element to remove.

在 Java 中，remove ()函数接受要删除的元素的索引。

When removing an integer element, use the `Integer.valueOf()` function as the argument for the `remove()` function:

在删除整数元素时，使用 Integer.valueOf ()函数作为 remove ()函数的参数:

```java
// Java
public void remove() {
    var numbers = new ArrayList<>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    numbers.add(1);
    numbers.remove(1); // This removes by index
    System.out.println(numbers); // [1, 3, 1]
    numbers.remove(Integer.valueOf(1));
    System.out.println(numbers); // [3, 1]
}
```

In Kotlin, there are two types of element removal: by index with [`removeAt()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/remove-at.html) and by value with [`remove()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/remove.html).

在 Kotlin，有两种类型的元素删除: 使用 removeAt ()的索引和使用 remove ()的值。

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val numbers = mutableListOf(1, 2, 3, 1)
    numbers.removeAt(0)
    println(numbers) // [2, 3, 1]
    numbers.remove(1)
    println(numbers) // [2, 3]
//sampleEnd
}
```

## 遍历 map

## Traversal map

In Java, you can traverse a map via [`forEach`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html#forEach(java.util.function.BiConsumer)):

在 Java 中，你可以通过 forEach 遍历地图:

```java
// Java
numbers.forEach((k,v) -> System.out.println("Key = " + k + ", Value = " + v));
```

In Kotlin, use a `for` loop or a `forEach`, similar to Java's `forEach`, to traverse a map:

在 Kotlin，使用 for 循环或 forEach (类似于 Java 的 forEach)来遍历 map:

```kotlin
// Kotlin
for ((k, v) in numbers) {
    println("Key = $k, Value = $v")
}
// Or
numbers.forEach { (k, v) -> println("Key = $k, Value = $v") }
```

## 获取可能会空的集合的首末元素

## Gets the first and last elements of a potentially empty collection

In Java, you can safely get the first and the last items by checking the size of the collection and using indices:

在 Java 中，您可以通过检查集合的大小和使用索引安全地获得第一个和最后一个项目:

```java
// Java
var list = new ArrayList<>();
//...
if (list.size() > 0) {
    System.out.println(list.get(0));
    System.out.println(list.get(list.size() - 1));
}
```

You can also use the [`getFirst()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Deque.html#getFirst()) and [`getLast()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Deque.html#getLast()) functions for [`Deque`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Deque.html) and its inheritors:

您还可以为 Deque 及其继承者使用 getFirst ()和 getLast ()函数:

```java
// Java
var deque = new ArrayDeque<>();
//...
if (deque.size() > 0) {
    System.out.println(deque.getFirst());
    System.out.println(deque.getLast());
}
```

In Kotlin, there are the special functions [`firstOrNull()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/first-or-null.html) and [`lastOrNull()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/last-or-null.html). Using the [`Elvis operator`](https://book.kotlincn.net/text/null-safety.html#elvis-操作符), you can perform further actions right away depending on the result of a function. For example, `firstOrNull()`:

在 Kotlin，有一些特殊的函数 firstOrNull ()和 lastOrNull ()。使用 Elvis 操作符，您可以根据函数的结果立即执行进一步的操作。例如，firstOrNull () :

```kotlin
// Kotlin
val emails = listOf<String>() // Might be empty
val theOldestEmail = emails.firstOrNull() ?: ""
val theFreshestEmail = emails.lastOrNull() ?: ""
```

## 由 list 创建 set

## Set is created by list

In Java, to create a [`Set`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Set.html) from a [`List`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html), you can use the [`Set.copyOf`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Set.html#copyOf(java.util.Collection)) function:

在 Java 中，要从 List 创建一个 Set，你可以使用 Set.copyOf)函数:

```java
// Java
public void listToSet() {
    var sourceList = List.of(1, 2, 3, 1);
    var copySet = Set.copyOf(sourceList);
    System.out.println(copySet);
}
```

In Kotlin, use the function `toSet()`:

在 Kotlin，使用函数 toSet () :

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val sourceList = listOf(1, 2, 3, 1)
    val copySet = sourceList.toSet()
    println(copySet)
//sampleEnd
}
```

## 元素分组

## Element grouping

In Java, you can group elements with the [Collectors](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html) function `groupingBy()`:

在 Java 中，可以使用 Collectors 函数 groupingBy ()对元素进行分组:

```Java
// Java
public void analyzeLogs() {
    var requests = List.of(
        new Request("https://kotlinlang.org/docs/home.html", 200),
        new Request("https://kotlinlang.org/docs/home.html", 400),
        new Request("https://kotlinlang.org/docs/comparison-to-java.html", 200)
    );
    var urlsAndRequests = requests.stream().collect(
            Collectors.groupingBy(Request::getUrl));
    System.out.println(urlsAndRequests);
}
```

In Kotlin, use the function [`groupBy()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/group-by.html):

在 Kotlin，使用函数 groupBy () :

```kotlin
class Request(
    val url: String,
    val responseCode: Int
)

fun main() {
//sampleStart
    // Kotlin
    val requests = listOf(
        Request("https://kotlinlang.org/docs/home.html", 200),
        Request("https://kotlinlang.org/docs/home.html", 400),
        Request("https://kotlinlang.org/docs/comparison-to-java.html", 200)
    )
    println(requests.groupBy(Request::url))
//sampleEnd
}
```

## 过滤元素

## Filter element

In Java, to filter elements from a collection, you need to use the [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html). The Stream API has `intermediate` and `terminal` operations. `filter()` is an intermediate operation, which returns a stream. To receive a collection as the output, you need to use a terminal operation, like `collect()`. For example, to leave only those pairs whose keys end with `1` and whose values are greater than `10`:

在 Java 中，要从集合中过滤元素，您需要使用 streamapi。Stream API 具有中间和终端操作。Filter ()是一个中间操作，它返回一个流。要接收集合作为输出，您需要使用终端操作，如 collect ()。例如，只留下键以1结尾且值大于10的对:

```java
// Java
public void filterEndsWith() {
    var numbers = Map.of("key1", 1, "key2", 2, "key3", 3, "key11", 11);
    var filteredNumbers = numbers.entrySet().stream()
        .filter(entry -> entry.getKey().endsWith("1") && entry.getValue() > 10)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    System.out.println(filteredNumbers);
}
```

In Kotlin, filtering is built into collections, and `filter()` returns the same collection type that was filtered. So, all you need to write is the `filter()` and its predicate:

在 Kotlin，筛选被内置到集合中，filter ()返回与筛选的集合类型相同的集合类型。因此，您所需要写的就是 filter ()及其谓词:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val numbers = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredNumbers = numbers.filter { (key, value) -> key.endsWith("1") && value > 10 }
    println(filteredNumbers)
//sampleEnd
}
```

Learn more about [filtering maps](https://book.kotlincn.net/text/map-operations.html#过滤) here.

你可在此了解更多有关过滤地图的资料。

### 按类型过滤元素

### Filter elements by type

In Java, to filter elements by type and perform actions on them, you need to check their types with the [`instanceof`](https://docs.oracle.com/en/java/javase/17/language/pattern-matching-instanceof-operator.html) operator and then do the type cast:

在 Java 中，要按类型过滤元素并对其执行操作，需要使用 instanceof 操作符检查它们的类型，然后进行类型强制转换:

```java
// Java
public void objectIsInstance() {
    var numbers = new ArrayList<>();
    numbers.add(null);
    numbers.add(1);
    numbers.add("two");
    numbers.add(3.0);
    numbers.add("four");
    System.out.println("All String elements in upper case:");
    numbers.stream().filter(it -> it instanceof String)
        .forEach( it -> System.out.println(((String) it).toUpperCase()));
}
```

In Kotlin, you just call [`filterIsInstance()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter-is-instance.html) on your collection, and the type cast is done by [Smart casts](https://book.kotlincn.net/text/typecasts.html#智能转换):

在 Kotlin，你只需在你的集合上调用 filterIsInstance < needed _ type > () ，类型强制转换由 Smart 类型转换完成:

```kotlin
// Kotlin
fun main() {
//sampleStart
    // Kotlin
    val numbers = listOf(null, 1, "two", 3.0, "four")
    println("All String elements in upper case:")
    numbers.filterIsInstance<String>().forEach {
        println(it.uppercase())
    }
//sampleEnd
}
```

### 检验谓词

### Check predicate

Some tasks require you to check whether all, none, or any elements satisfy a condition. In Java, you can do all of these checks via the [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html) functions [`allMatch()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html#allMatch(java.util.function.Predicate)), [`noneMatch()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html#noneMatch(java.util.function.Predicate)), and [`anyMatch()`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html#anyMatch(java.util.function.Predicate)):

有些任务要求您检查所有、无或任何元素是否满足条件。在 Java 中，可以通过 Stream API 函数 allMatch ()、 noneMatch ()和 anyMatch ()执行所有这些检查:

```java
// Java
public void testPredicates() {
    var numbers = List.of("one", "two", "three", "four");
    System.out.println(numbers.stream().noneMatch(it -> it.endsWith("e"))); // false
    System.out.println(numbers.stream().anyMatch(it -> it.endsWith("e"))); // true
    System.out.println(numbers.stream().allMatch(it -> it.endsWith("e"))); // false
}
```

In Kotlin, the [extension functions](https://book.kotlincn.net/text/extensions.html) `none()`, `any()`, and `all()` are available for every [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/#kotlin.collections.Iterable) object:

在 Kotlin，扩展函数 none ()、 any ()和 all ()对每个 Iterable 对象都可用:

```kotlin
fun main() {
//sampleStart
// Kotlin
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.none { it.endsWith("e") })
    println(numbers.any { it.endsWith("e") })
    println(numbers.all { it.endsWith("e") })
//sampleEnd
}
```

Learn more about [test predicates](https://book.kotlincn.net/text/collection-filtering.html#检验谓词).

了解关于测试谓词的更多信息。

## 集合转换操作

## Set conversion operation

### 元素合拢

### Element closure

In Java, you can make pairs from elements with the same positions in two collections by iterating simultaneously over them:

在 Java 中，你可以通过同时迭代两个集合中具有相同位置的元素来创建对:

```java
// Java
public void zip() {
    var colors = List.of("red", "brown");
    var animals = List.of("fox", "bear", "wolf");

    for (int i = 0; i < Math.min(colors.size(), animals.size()); i++) {
        String animal = animals.get(i);
        System.out.println("The " + animal.substring(0, 1).toUpperCase()
               + animal.substring(1) + " is " + colors.get(i));
   }
}
```

If you want to do something more complex than just printing pairs of elements into the output, you can use [Records](https://blogs.oracle.com/javamagazine/post/records-come-to-java). In the example above, the record would be `record AnimalDescription(String animal, String color) {}`.

如果希望执行比仅仅将成对的元素打印到输出中更复杂的操作，可以使用 Records。在上面的例子中，记录是 AnimalDescription (String animal，String color){}。

In Kotlin, use the [`zip()`](https://book.kotlincn.net/text/collection-transformations.html#合拢) function to do the same thing:

在 Kotlin，使用 zip ()函数做同样的事情:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val colors = listOf("red", "brown")
    val animals = listOf("fox", "bear", "wolf")

    println(colors.zip(animals) { color, animal -> 
        "The ${animal.replaceFirstChar { it.uppercase() }} is $color" })
//sampleEnd
}
```

`zip()` returns the List of [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/) objects.

Zip ()返回 Pair 对象列表。

> If collections have different sizes, the result of `zip()` is the smaller size. The last elements of the larger collection are not included in the result.
>
> 如果集合具有不同的大小，zip ()的结果是更小的大小。较大集合的最后一个元素不包含在结果中。
>
> <svg width="24" height="24" fill="#4dbb5f" viewBox="0 0 24 24"><path d="M21 12a9 9 0 1 1-9-9 9 9 0 0 1 9 9zM10.5 7.5A1.5 1.5 0 1 0 12 6a1.5 1.5 0 0 0-1.5 1.5zm-.5 3.54v1h1V18h2v-6a.96.96 0 0 0-.96-.96z"></path></svg>

### 元素关联

### Element Association

In Java, you can use the [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html) to associate elements with characteristics:

在 Java 中，你可以使用 Stream API 来关联元素和特征:

```java
// Java
public void associate() {
    var numbers = List.of("one", "two", "three", "four");
    var wordAndLength = numbers.stream()
        .collect(toMap(number -> number, String::length));
    System.out.println(wordAndLength);
}
```

In Kotlin, use the [`associate()`](https://book.kotlincn.net/text/collection-transformations.html#关联) function:

在 Kotlin，使用 associate ()函数:

```kotlin
fun main() {
//sampleStart
    // Kotlin
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length })
//sampleEnd
}
```

