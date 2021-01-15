package com.toyrobotscala


final case class Seed(int: Int) {
  private val r = scala.util.Random
  def next = Seed(r.nextInt(100))
}

object Seed {
  def nextInt(seed: Seed): (Seed, Int) = (seed.next, seed.int)
  private def randomDirectionGenerator(randomInt: Int): Direction = {
    randomInt match {
      case 0 => Direction.North
      case 1 => Direction.East
      case 2 => Direction.West
      case 3 => Direction.South
    }
  }
  private val r = scala.util.Random
  def nextDirection(seed: Seed): (Seed, Direction) = (seed.next, randomDirectionGenerator(r.nextInt(4)))
}
