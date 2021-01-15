package com.toyrobotscala

import com.toyrobotscala.Direction.North
import org.specs2.mutable.Specification

class InputSpec extends Specification with org.specs2.specification.Tables {
  override def is =
    s2"""
      return an updating robot position ${ "userInput" | "" |>
      "MOVE" ! Some(Command.UpdateRobotPosition(CommandUpdatingPosition.Move)) |
      "LEFT" ! Some(Command.UpdateRobotPosition(CommandUpdatingPosition.Left)) |
      "RIGHT" ! Some(Command.UpdateRobotPosition(CommandUpdatingPosition.Right)) |
      "PLACE 2,4,NORTH" ! Some(Command.UpdateRobotPosition(CommandUpdatingPosition.Place(2,4,North))) |
      "\n" ! None |
      "move" ! None |
      "left" ! None |
      "right" ! None |
      "place" ! None | { (userInput, result) =>
      Input.parseValidCommand.lift(userInput) shouldEqual result
    }
    }
      return a not updating robot position ${ "userInput" | "" |>
      "REPORT" ! Some(Command.UnchangedRobot(CommandNotUpdatingPosition.Report)) |
      "\n" ! None |
      "report" ! None | { (userInput, result ) =>
      Input.parseValidCommand.lift(userInput) shouldEqual result
    }
    }
      """

}
