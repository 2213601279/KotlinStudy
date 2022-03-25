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


//TODO 匿名函数的学习
/*
- Lambda 表达式总是被大括号括着
- 定义 Lambda 表达式不需要 fun 关键字，无须指定函数名。
- 形参列表（如果有的话）在->之前声明，参数类型可以省略。
- 函数体（Lambda 表达式执行体）放在->之后。
- 函数的最后一个表达式自动被作为Lambda 表达式的返回值，无须使用 return 关键字。
*/

//TODO Lambda function
//推荐的使用方式

val  test1 :(Int,Int) -> Int = {a,b -> a + b}
// TODO lambda function as val

fun asVal(a: Int,b: (Int,Int) -> Int):Int{
    return a + b(0,999)
    //return a + b.invoke(0,55)
    //和这个等价的写法
}



fun main() {
    println("There was test for function")
    println(sum(3,5));
    println(sum1(1,9))
    println("sum of 19 and 23 is ${sum(19, 23)}")
    println("There was test lambda function")
    //TODO test lambda function
    println(test1(3,500))
    //TODO test lambda as val
    //    println(asVal(999,99)) 这样子调用是错误的
    //调用
    println("There was use lambda function")
   var a= asVal(1,{ num1: Int, num2: Int ->  num1 + num2 })
    println(a)
//等价于:
    var b=asVal(6){ num1: Int, num2: Int ->  num1 + num2 }
    println(b)

    println("TODO use lambda with collections")
//TODO use lambda with collections
    var list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 98)
    //匿名函数
    list.forEach(fun (v){
        println("元素为：${v}")
        return
    })
    //lambda
    println("Now there was lambda ")
    //it 代表他自己的内部迭代对象
    list.forEach {
        println("元素为：${it}")
    }

    println("Now see there was with not next value")
    list.forEach {
        println("元素为：${it}")
        return@forEach
        println("lambda 提交返回值")
    }
    //sampleStart
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }
//sampleEnd
}