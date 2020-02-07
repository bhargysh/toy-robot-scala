package com.toyrobotscala
import com.toyrobotscala.Direction.North

import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {

    println("👋🏽 From Toy 🤖 App")
    val rawStr = readLine().toUpperCase

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val game = Game(grid, robot)

    while (Input.isValid(rawStr)) {
      val command = Input.parseValidCommand(rawStr)
      if (command == Command.Report) {
        Converter.logRobotPosition(robot)
        return
      } else {
        game.play(command)
      }
    }
    println("🙅‍🙅‍🙅‍ Invalid command 🙅‍🙅‍🙅‍")
  }
}
// TODO; only accept place as first command
// TODO: use validated
