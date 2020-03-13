package com.toyrobotscala

import cats.effect.IO

import scala.io.StdIn

trait Console[F[_]] {
  def printLine(string: String): F[Unit]
  def readLine(): F[String]
}

object Console {

  def apply[F[_]](implicit console: Console[F]): Console[F] = implicitly[Console[F]]

  implicit val ioConsole: Console[IO] = new Console[IO] {
    override def printLine(string: String): IO[Unit] = IO(println(string))

    override def readLine(): IO[String] = IO(StdIn.readLine())
  }
}


