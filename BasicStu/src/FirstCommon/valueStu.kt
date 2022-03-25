package FirstCommon

import javax.sound.sampled.Line

/**
 *
 * @author Hariji
 * @date 2022-03-23 15:20
 * @description：三尺秋水尘不染
 */


fun vars(vararg v:Int){
    for(vt in v){
        print(vt)
    }
}

// 测试

// TODO kotlin 变量和内置数据类型
fun main() {
        //TODO 声明变量
    /*
    * var 可读可改
    * */
    var name : String = "hello";
    print(name)
    //TODO 内置数据类型
    /*
    * String
    * Char
    * Boolean
    * Int
    * List
    * Double
    * Set
    * Map
    * */


    //TODO 只读变量
    //不会被修改
    var info:String = " word"
    print(info)
    var age=99
    println(age)
    vars(1,2,3,4,5)  // 输出12345
}