package com.toyrobotscala
import com.toyrobotscala.Direction.North

import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {

    println("👋🏽 From Toy 🤖 App")

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val initialGame = Game(grid, robot)

    go(initialGame)
  }
  @scala.annotation.tailrec
  def go(game: Game): Nothing = {
    val str = readLine().toUpperCase
    val maybeComand = Input.parseValidCommand.lift(str)

    maybeComand match {
      case Some(Command.Report) =>
        println(game.currentRobot)
        go(game)
      case Some(command) =>
        val newGame = game.play(command)
        go(newGame)
      case None => go(game)
    }
  }
}
