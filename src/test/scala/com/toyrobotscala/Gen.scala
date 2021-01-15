package com.toyrobotscala

import scala.util.Random

trait Gen[A] {
  def generate(): A
  def map[B](f: A => B): Gen[B] = new Gen[B] {
    override def generate(): B = f(Gen.this.generate())
  }
  def flatMap[B](f: A => Gen[B]): Gen[B] = new Gen[B] {
    override def generate(): B = f(Gen.this.generate()).generate()
  }
}

object Gen {
  val genDirection: Gen[Direction] = new Gen[Direction] {
    override def generate(): Direction = Random.nextInt(4) match {
      case 0 => Direction.North
      case 1 => Direction.East
      case 2 => Direction.West
      case 3 => Direction.South
      case _ => throw AssertionError
    }
  }

  val genInt: Gen[Int] = () => Random.nextInt()

  val genRobot: Gen[Robot] = {
//    val maybeDirection: Option[Direction] = ???
//    val maybeX: Option[Int] = ???
//    val maybeY: Option[Int] = ???
    for {
      dir <- genDirection
      x <- genInt
      y <- genInt
    } yield Robot(x, y, dir)
  }
}


