package collections

/**
 *
 * @author Hariji
 * @date 2022-03-31 15:58
 * @description：三尺秋水尘不染
 */
//TODO list 集合操作
// 和Java对比 主要有list，set，queue，deque
fun main() {
    //TODO ListOf用法
    val listTest = mutableListOf<String>("a", "b", "c", "d", "e")
    //后续使用api方法 添加 集合
    //TODO Java版本的添加集合
    listTest.add("c")
    println("This is add a element: "+listTest)
    println("All in all : "+listTest.addAll(listTest))
    //TODO Kotlin add
    listTest += "aaaaa"
    println("This is Kotlin add use operator : "+listTest)

}