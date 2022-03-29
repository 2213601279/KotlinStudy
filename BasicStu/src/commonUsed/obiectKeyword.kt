package commonUsed

import java.util.*


/**
 *
 * @author Hariji
 * @date 2022-03-29 13:11
 * @description：三尺秋水尘不染
 */


//TODO 你也可以使用 object 关键字，它用来获得一个数据类型，只需要一个实现。
class LuckDispatcher {                    //1
    fun getNumber() {
        //2
        //TODO 如果使用kotlin 自带的函数无法创建表达式
        var objRandom = Random()

        println(objRandom.nextInt(90))

    }
}

//TODO 只需要声明一个对象不需要他的类
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1

    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }
    println(standardDays)
    println(festivityDays)
    println(specialDays)
    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3

    print("Total price: $$total")                                               //4

}
//TODO 和上面的类比做比较
object DoAuth {                                                 //1
    fun takeParams(username: String, password: String) {        //2
        println("input Auth parameters = $username:$password")
    }
}
//TODO 伴侣对象 在语法上，它类似于 Java 中的静态方法: 使用类名作为限定符来调用对象成员。如果您计划在 Kotlin 使用一个伴随对象，可以考虑使用一个包级别的函数。
class BigBen {                                  //1
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}


fun main() {
    val d1 = LuckDispatcher()             //3
    val d2 = LuckDispatcher()

    d1.getNumber()                        //4
    d2.getNumber()
    println("this is object expression")
    rentPrice(10,2,3)
    println("object declaration")
    DoAuth.takeParams("foo", "qwerty")                          //3
    BigBen.getBongs(12)





}