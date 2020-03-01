package com.toyrobotscala
import cats.effect.{ExitCode, IO, IOApp}
import com.toyrobotscala.Direction.North

import scala.io.StdIn.readLine

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val greeting: IO[Unit] = printLine(("👋🏽 From Toy 🤖 App"))

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val initialGame = Game(grid, robot)

    greeting
      .flatMap( _ => go(initialGame))
      .map(_ => ExitCode.Success)
  }
  def go(game: Game): IO[Unit] = {
    ioOfMaybeCommand.flatMap {
      case Some(Command.Report) =>
        ioOfReportCommand(game).flatMap { _ =>
          go(game)
        }
      case Some(command) =>
        val newGame = game.play(command)
        go(newGame)
      case None => go(game)
    }
  }
  private def printLine(str: String): IO[Unit] = IO(println(str))
  private val getInput: IO[String] = IO(readLine().toUpperCase)
  private val ioOfMaybeCommand: IO[Option[Command]] = {
    getInput
      .map(inputStr => Input.parseValidCommand.lift(inputStr))
  }
  private def ioOfReportCommand(game: Game): IO[Unit] = {
    printLine(game.currentRobot.toString)
  }
}

//TODO: for yield
//TODO: command hierarchy with no OOP
