package FirstCommon

/**
 *
 * @author Hariji
 * @date 2022-03-23 15:35
 * @description：三尺秋水尘不染
 */
//TODO 带参数的函数类型
fun sum(a:Int,b:Int):Int{
    return a+b
}
//函数体可以是表达式。其返回类型可以推断出来。
fun sum1(a: Int,b: Int)=a+b


//返回无意义的值的函数。
/*
*  Unit
*  可以重载
* */
fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}
fun printSum(a: Double, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}
fun main() {
    println(sum(3,5));
    println(sum1(1,9))
    println("sum of 19 and 23 is ${sum(19, 23)}")
}