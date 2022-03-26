package FirstCommon

/**
 *
 * @author Hariji
 * @date 2022-03-25 19:53
 * @description：三尺秋水尘不染
 */


//TODO  认识基础的语法
    //类型后面加?表示可为空
    var age: String? = "23"
    //抛出空指针异常
    val ages = age!!.toInt()
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1
//
//当可能用 null 值时，必须将引用显式标记为可空。可空类型名称以问号（?）结尾。
//
//如果 str 的内容不是数字返回 null：
fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 会导致错误, 因为它们可能为 null
    if (x != null && y != null) {
        // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}
//TODO method2:  How to use
fun main() {
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")
}
