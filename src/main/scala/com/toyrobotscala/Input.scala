package com.toyrobotscala

import scala.util.matching.Regex

object Input {

  val parseValidCommand: PartialFunction[String, Command] = {
    case "MOVE"   => Command.UpdateRobotPosition(CommandUpdatingPosition.Move)
    case "LEFT"   => Command.UpdateRobotPosition(CommandUpdatingPosition.Left)
    case "RIGHT"  => Command.UpdateRobotPosition(CommandUpdatingPosition.Right)
    case "REPORT" => Command.UnchangedRobot(CommandNotUpdatingPosition.Report)
    case "PLACE_OBSTACLE" => Command.UpdateRobotPosition(CommandUpdatingPosition.PlaceObstacle)
    case placePattern(x, y, direction) => Command.UpdateRobotPosition(CommandUpdatingPosition.Place(x.toInt, y.toInt, Direction.fromStr(direction)))
  }

  private val placePattern: Regex = """^PLACE (\d+),(\d+),(NORTH|SOUTH|EAST|WEST)$""".r
}