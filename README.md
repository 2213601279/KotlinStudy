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

# 对象表达式

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

