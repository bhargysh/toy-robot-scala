//package com.toyrobotscala
//
//import org.specs2.mutable.Specification
//
//class InputSpec extends Specification {
//  "Input" should {
//    "return the move command when input is move" in {
//      val inputStr = "MOVE"
//      val result = Command.Move //TODO: for each valid input
//      Input.parseValidCommand(inputStr) shouldEqual result
//    }
//    "return the place command when input is place" in {
//      val inputStr = "PLACE 0,1,NORTH"
//      val result = Command.Place(0, 1, Direction.North) //TODO: for all the valid place possibilities
//      Input.parseValidCommand(inputStr) shouldEqual result
//    }
//  }
//
//}
