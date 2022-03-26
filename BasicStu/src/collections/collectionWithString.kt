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
}

fun getName(): String =
    if (Random.nextBoolean()) "" else "David"