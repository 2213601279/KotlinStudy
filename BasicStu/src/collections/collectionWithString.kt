package collections

import kotlin.random.Random

/**
 *
 * @author Hariji
 * @date 2022-03-26 20:47
 * @description：三尺秋水尘不染
 */

//TODO 思考在java中如果要过滤一个list的偶数最后输出要如何？？

/*
// Java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
String invertedOddNumbers = numbers
        .stream()
        .filter(it -> it % 2 != 0)
        .map(it -> -it)
        .map(Object::toString)
        .collect(Collectors.joining(", "));
System.out.println(invertedOddNumbers);
* */
//TODO 在kotlin 中需要我们使用joinString（）函数来内置的使用lambda表达式来输出

//
fun main() {

    println("Now use JoinString() function")
    //sampleStart
    // Kotlin
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val invertedOddNumbers = numbers
        .filter { it % 2 != 0 }
            //TODO it 是lambda 的内置对象
        .joinToString{ "${-it}" }

    println(invertedOddNumbers)
//sampleEnd
//TODO 使用内置的ifBank（）接受默认值作为参数
    val name = getName().ifBlank { "John Doe" }
    println(name)



//sampleEnd
    //TODO 链接字符串
    //sampleStart
    // Kotlin
    val name1 = "Joe"
    println("Hello, $name1")
    println("Your name is ${name1.length} characters long")
//sampleEnd

    //TODO String Builder 在kotlin 中的使用
    //sampleStart
    // Kotlin
    val countDown = buildString {
        for (i in 5 downTo 1) {
            append(i)
           appendLine(6)
        }
    }
    println(countDown)
//sampleEnd

    //TODO 去掉多余的字符
    //sampleStart
    // Kotlin
    println("去掉多余的字符")
    val input = "##place##holder##"
    val result = input.removeSurrounding("##")
    println(input.reversed())
    println(result)
//sampleEnd
    //TODO regex正则表达式的写法
    println("Now regex 的书写")
//sampleStart
    // Kotlin
    val regex = Regex("""\w*\d+\w*""") // raw string

    val input1 = "login: Pokemon5, password: 1q2w3e4r5t"

    val replacementResult = regex.replace(input1, replacement = "xxx")

    println("Initial input: '$input1'")

    println("Anonymized input: '$replacementResult'")

//sampleEnd
    //TODO split（）函数的使用
    //sampleStart
    // Kotlin
    println("Sometimes.text.should.be.split".split("."))
//sampleEnd

//TODO 区字串操作
    //sampleStart
    // Kotlin
    val input2 = "What is the answer to the Ultimate Question of Life, the Universe, and Everything? 42"
    val answer = input2.substringAfter("e")
    println("找到最后出现的，"+input2.substringAfterLast(","))
    println("找到第一次出现的e之后的" + answer)
//sampleEnd

//TODO
    //sampleStart
    // Kotlin
    println("多行文本")
    val result2 = """
        Kotlin
        Java 
    """.trimIndent()
    println(result2)
//sampleEnd
    val result4 = """
       #  Kotlin
       #  Java
   """.trimMargin("#")
    println(result4)
}

fun getName(): String =
    if (Random.nextBoolean()) "" else "David"