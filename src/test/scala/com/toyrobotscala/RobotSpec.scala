package com.toyrobotscala

import com.toyrobotscala.Direction.North
import org.specs2.mutable.Specification
import com.toyrobotscala.Seed._

class RobotSpec extends Specification {
  "Robot" should {
    "return true if it is on the grid" in {
      val testRobot = Robot(0, 1, North)
      val testGrid = Grid(Range(0, 5), Range(0, 5))
      testRobot.hasValidPositionOn(testGrid) should beTrue
    }
    "return false if it is off the grid" in {
      val testRobot = Robot(5, 1, North)
      val testGrid = Grid(Range(0, 5), Range(0, 5))
      testRobot.hasValidPositionOn(testGrid) should beFalse
    }

    "return false" in {
      createRobot(initialSeed).hasValidPositionOn(createGrid(initialSeed)) shouldEqual true
    }

  }

  private def createRobot(seed: Seed): Robot = {
    val (seed1, x) = nextInt(seed)
    val (seed2, y) = nextInt(seed1)
    val (seed3, direction) = nextDirection(seed2)
    Robot(x, y, direction)
  }

  private def createGrid(seed: Seed): Grid = {
    val (seed1, start) = nextInt(seed)
    val (seed2, end) = nextInt(seed1)
    Grid(Range(start, end), Range(start, end))
  }
  private val initialSeed = Seed(10)

}

// What is the state in this case? State => (State, A)
// Seed
// ? => (?, Boolean)