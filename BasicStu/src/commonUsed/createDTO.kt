package commonUsed

/**
 *
 * @author Hariji
 * @date 2022-03-25 20:11
 * @description：三尺秋水尘不染
 */

//TODO DTO参照在我们学习JavaBean中的pojo
/*
        * 会为 Customer 类提供以下功能：

        所有属性的 getter （对于 var 定义的还有 setter）
        equals()
        hashCode()
        toString()
        copy()
        所有属性的 component1()、 component2()……等等
*
* */

//TODO
// 创建一些只保存数据的类是件寻常的事。 在这些类中，一些标准功能以及一些工具函数往往是由数据机械推导而来的。在 Kotlin 中，这叫做 数据类 并以 data 标记：
data class User(val name:String,val age :Int){

}
//TODO
// 如需在生成的实现中排除一个属性，请将其声明在类体中：
// 排除了age属性，所以在后续的测试中没有用到age为true的情况
data class Person(val name: String) {
    var age: Int = 0
}

//TODO copy（）function study
// Use the copy() function to copy an object, allowing you to alter some of its properties while keeping the rest unchanged.
// The implementation of this function for the User class above would be as follows:

fun copy(name: String , age: Int ) = User(name, age)

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
    println("copy() function test ")
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println(jack)
    println(olderJack)

    println("test for Kotlin inner function")
    //解构 类比python的解包
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // 输出 "Jane, 35 years of age"
}