package com.toyrobotscala
import com.toyrobotscala.Direction.North

import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {

    println("👋🏽 From Toy 🤖 App")
    val rawStr: String = readLine().toUpperCase

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val game = Game(grid, robot)
    val command = Input.parseValidCommand(rawStr)

    def input(str: String): Game = {
      if(!Input.parseValidCommand.isDefinedAt(rawStr)) {
        input(rawStr)
      }
      else if (command == Command.Report) {
        println(robot)
        input(rawStr)
      }
      else {
        val newGame = game.play(command)
        newGame
//        input(rawStr)
      }
    }
    input(rawStr)

//    while (true) {
//      val command = Input.parseValidCommand(rawStr)
//      if (command == Command.Report) {
//        println(robot)
//      } else {
//        val newGame = game.play(command)
//        newGame
//      }
//    }

  }
}
// TODO: throws an Exception if no input or invalid input is given
// TODO: only accept place as first command
