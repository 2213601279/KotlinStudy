package collections

/**
 *
 * @author Hariji
 * @date 2022-03-28 19:08
 * @description：三尺秋水尘不染
 */
//TODO 这里的枚举类型为三个基础类型
//  可以通过静态变量的方式来访问
//
enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}

enum class Color(val rgb: Int) {                      // 1
    RED(0xFF0000),                                    // 2
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);

    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3
}
fun main() {
    val state = State.RUNNING                         // 2
  //TODO 从而 会自动的表示
    val message = when (state) {                      // 3
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }
    println(message)
    println("包含属性和方法")
    val red = Color.RED
    println(red)                                      // 4
    println(red.containsRed())                        // 5
    println(Color.BLUE.containsRed())                 // 6
    println(Color.YELLOW.containsRed())               // 7
}



