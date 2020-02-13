package com.toyrobotscala
import com.toyrobotscala.Direction.North

import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {

    println("👋🏽 From Toy 🤖 App")

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val game = Game(grid, robot)
    val stringToMaybeCommand: String => Option[Command] = Input.parseValidCommand.lift

    @scala.annotation.tailrec
    def input(str: String): Game = {
      if(stringToMaybeCommand(str).isEmpty) {
        input(readLine().toUpperCase)
      }
      else if (stringToMaybeCommand(str).contains(Command.Report)) {
        println(robot)
        input(readLine().toUpperCase)
      }
      else {
        stringToMaybeCommand(str) match {
          case Some(command) =>
            val newGame = game.play(command)
            newGame
          case None => input(readLine().toUpperCase)
        }
      }
    }
    input(readLine().toUpperCase)
  }
}
// TODO: only accept place as first command
