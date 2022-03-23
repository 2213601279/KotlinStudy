package FirstCommon

/**
 *
 * @author Hariji
 * @date 2022-03-23 15:42
 * @description：三尺秋水尘不染
 */
//TODO 类的声明方式

class Rectangle(var height: Double, var length: Double) {
    var perimeter = (height + length) * 2
}

//类之间继承由冒号（:）声明。默认情况下类都是 final 的；如需让一个类可继承， 请将其标记为 open。
open class Shape

class Rectangle1(var height: Double, var length: Double): Shape() {
    var perimeter = (height + length) * 2
}
fun main() {
//sampleStart
    val rectangle = Rectangle(5.0, 2.0)
    val re = Rectangle1(6.0,8.0)
    println("The perimeter is ${rectangle.perimeter}")
    println(re)
    println(re.perimeter)
    println(re.height)
    println(re.height)
//sampleEnd
}