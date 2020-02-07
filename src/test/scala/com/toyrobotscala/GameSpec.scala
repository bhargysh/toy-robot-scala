package com.toyrobotscala

import com.toyrobotscala.Direction.{North, South}
import org.specs2.mutable.Specification

class GameSpec extends Specification {
  "Game" should {
    "play the game" in {
      val testRobot = Robot(0, 1, North)
      val testGrid = Grid(Range(1, 5), Range(1, 5))
      val testGame = Game(testGrid, testRobot)

      val newGame = Game(testGrid, testRobot.copy(x = 2, y = 3, direction = South))

      val testCommand = Command.Place(2, 3, South)
      testGame.play(testCommand) shouldEqual newGame
    }
  }

}
