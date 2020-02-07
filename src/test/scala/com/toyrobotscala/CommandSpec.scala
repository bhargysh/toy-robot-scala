package com.toyrobotscala

import com.toyrobotscala.Command.{Move, Place, Report}
import com.toyrobotscala.Direction.{East, North, South, West}
import org.specs2.mutable.Specification

// TODO: property based testing or table driven testing? this is ugly
class CommandSpec extends Specification {
  "Command" should {
    val testGrid: Grid = Grid(Range(0, 5), Range(0, 5))
    "move" should {
      "return a new robot when current direction is North" in {
        val testRobot = Robot(0, 1, North)
        val expectedRobot = Robot(0, 2, North)
        Move.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot when current direction is East" in {
        val testRobot = Robot(0, 1, East)
        val expectedRobot = Robot(1, 1, East)
        Move.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot when current direction is South" in {
        val testRobot = Robot(0, 1, South)
        val expectedRobot = Robot(0, 0, South)
        Move.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot when current direction is West" in {
        val testRobot = Robot(0, 1, West)
        val expectedRobot = Robot(0, 1, West) // after 'Move' robot will have x=-1 i.e. fall off grid
        Move.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
    }
    "left" should {
      "return a new robot facing West when current direction is North" in {
        val testRobot = Robot(0, 1, North)
        val expectedRobot = Robot(0, 1, West)
        Command.Left.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot facing South when current direction is West" in {
        val testRobot = Robot(0, 1, West)
        val expectedRobot = Robot(0, 1, South)
        Command.Left.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot facing East when current direction is South" in {
        val testRobot = Robot(0, 1, South)
        val expectedRobot = Robot(0, 1, East)
        Command.Left.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot facing North when current direction is East" in {
        val testRobot = Robot(0, 1, East)
        val expectedRobot = Robot(0, 1, North)
        Command.Left.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
    }
    "right" should {
      "return a new robot facing East when current direction is North" in {
        val testRobot = Robot(0, 1, North)
        val expectedRobot = Robot(0, 1, East)
        Command.Right.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot facing North when current direction is West" in {
        val testRobot = Robot(0, 1, West)
        val expectedRobot = Robot(0, 1, North)
        Command.Right.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot facing West when current direction is South" in {
        val testRobot = Robot(0, 1, South)
        val expectedRobot = Robot(0, 1, West)
        Command.Right.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
      "return a new robot facing South when current direction is East" in {
        val testRobot = Robot(0, 1, East)
        val expectedRobot = Robot(0, 1, South)
        Command.Right.execute(testGrid, testRobot) shouldEqual expectedRobot
      }
    }
    "place" should {
      "return a new robot with new position if on grid" in {
        val currentRobot = Robot(0, 1, North)
        val expectedRobot = Robot(2, 3, South)
        Place(2, 3, South).execute(testGrid, currentRobot) shouldEqual expectedRobot
      }
      "return current robot if not on grid" in {
        val currentRobot = Robot(0, 1, North)
        Place(6, 3, South).execute(testGrid, currentRobot) shouldEqual currentRobot
      }
    }
    "report" should {
      "return the current robot" in {
        val robot = Robot(2, 3, South)
        Report.execute(testGrid, robot) shouldEqual robot
      }
    }
  }

}
