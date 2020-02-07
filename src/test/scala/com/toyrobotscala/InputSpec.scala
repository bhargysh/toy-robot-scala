package com.toyrobotscala

import org.specs2.mutable.Specification

class InputSpec extends Specification {
  "Input" should {
    "return true if user input is move" in {
      val rawStr = "MOVE"
      Input.isValid(rawStr) should beTrue //TODO: for each valid input
    }
    "return true if user input is place" in {
      val rawStr = "PLACE 0,1,NORTH"
      Input.isValid(rawStr) should beTrue //TODO: for all the valid place possibilities
    }
    "return false if the user input is invalid" in {
      val rawStr = "😳"
      Input.isValid(rawStr) should beFalse
    }
    "return the move command when input is move" in {
      val inputStr = "MOVE"
      val result = Command.Move //TODO: for each valid input
      Input.parseValidCommand(inputStr) shouldEqual result
    }
    "return the place command when input is place" in {
      val inputStr = "PLACE 0,1,NORTH"
      val result = Command.Place(0, 1, Direction.North) //TODO: for all the valid place possibilities
      Input.parseValidCommand(inputStr) shouldEqual result
    }
  }

}
