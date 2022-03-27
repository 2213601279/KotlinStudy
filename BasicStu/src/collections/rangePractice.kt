package collections

/**
 *
 * @author Hariji
 * @date 2022-03-27 20:03
 * @description：三尺秋水尘不染
 */
/*
for (i in 1..100) { …… }  // 闭区间：包含 100
for (i in 1 until 100) { …… } // 半开区间：不包含 100
for (x in 2..10 step 2) { …… }
for (x in 10 downTo 1) { …… }
(1..10).forEach { …… }

 */

abstract class MyAbstractClass {
    abstract fun doSomething()
    abstract fun sleep()
}
///when表达式
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}

fun main() {
    val myObject = object : MyAbstractClass() {
        override fun doSomething() {
            // ……
            println("This is doSomething")
        }
//TODO 重写equals和hashCode
        override fun hashCode(): Int {
            return super.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
        override fun sleep() {
        // ……
            println("This is sleep")
        }
    }
    myObject.doSomething()

    //TODO when 表达式
    println(""+ transform("Red"))
    println(""+ transform("Read"))
}