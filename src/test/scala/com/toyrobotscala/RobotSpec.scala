package com.toyrobotscala

import com.toyrobotscala.Direction.North
import org.specs2.mutable.Specification

class RobotSpec extends Specification {
  "Robot" should {
    "return true if it is on the grid" in {
      val testRobot = Robot(0, 1, North)
      val testGrid = Grid(Range(0, 5), Range(0, 5))
      testRobot.hasValidPositionOn(testGrid) should beTrue
    }
    "return false if it is off the grid" in {
      val testRobot = Robot(6,1,North)
      val testGrid = Grid(Range(0,5), Range(0,5))
      testRobot.hasValidPositionOn(testGrid) should beFalse
    }
  }

}
