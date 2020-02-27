package com.toyrobotscala
import cats.effect.IO
import com.toyrobotscala.Direction.North

import scala.io.StdIn.readLine

object Main {

  def main(args: Array[String]): Unit = {

    printLine(("👋🏽 From Toy 🤖 App")).unsafeRunSync()

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val initialGame = Game(grid, robot)

    go(initialGame)
  }
  def go(game: Game): IO[Unit] = runIoOfMaybeCommand match {
    case Some(Command.Report) =>
      runIoOfReportCommand(game)
      go(game)
    case Some(command) =>
      val newGame = game.play(command)
      go(newGame)
    case None => go(game)
  }
  private def printLine(str: String): IO[Unit] = IO(println(str))
  private def getInput: IO[String] = IO(readLine().toUpperCase)
  private def runIoOfMaybeCommand: Option[Command] = {
    getInput
      .map(inputStr => Input.parseValidCommand.lift(inputStr))
      .unsafeRunSync()
  }
  private def runIoOfReportCommand(game: Game): Unit = {
    printLine(game.currentRobot.toString).unsafeRunSync()
  }
}
