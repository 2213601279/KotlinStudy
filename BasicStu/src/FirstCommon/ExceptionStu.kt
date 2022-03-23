package FirstCommon

import jdk.nashorn.internal.runtime.regexp.joni.Config.log

/**
 *
 * @author Hariji
 * @date 2022-03-23 16:12
 * @description：三尺秋水尘不染
 */
fun main() {
//sampleStart
    throw Exception("Hi There!")
    var message="erro"
    log.append(message)
//sampleEnd
}