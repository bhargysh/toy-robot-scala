package com.toyrobotscala
import cats.{Functor, Monad}
import cats.effect.{ExitCode, IO, IOApp}
import com.toyrobotscala.Direction.North
import cats.syntax.functor._
import cats.syntax.flatMap._

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    def greeting[F[_] : Console](): F[Unit] = printLine("👋🏽 From Toy 🤖 App")

    val robot = Robot(0, 0, North)
    val grid = Grid(Range(0, 5), Range(0, 5))
    val initialGame = Game(grid, robot)

    greeting()
      .flatMap( _ => go(initialGame))
      .map(_ => ExitCode.Success)
  }
  def go[F[_] : Console : Monad](game: Game): F[Unit] = {
    ioOfMaybeCommand().flatMap {
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

  private def printLine[F[_] : Console](str: String): F[Unit] = Console[F].printLine(str)
  private def getInput[F[_] : Console : Functor]: F[String] = Console[F].readLine().map { str => str.toUpperCase }
  private def ioOfMaybeCommand[F[_]: Console : Monad](): F[Option[Command]] = {
    getInput
      .map(inputStr => Input.parseValidCommand.lift(inputStr))
  }
  private def ioOfReportCommand[F[_]: Console](game: Game): F[Unit] = {
    Console[F].printLine(game.currentRobot.toString)
  }
}
