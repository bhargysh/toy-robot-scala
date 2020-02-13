package com.toyrobotscala

import scala.util.matching.Regex

object Input {
  def isValid(rawInput: String): Boolean = rawInput match {
    case "MOVE" | "LEFT" | "RIGHT" | "REPORT" => true
    case _ => isPlace(rawInput)
  }

  val parseValidCommand: PartialFunction[String, Command] = {
    case "MOVE"   => Command.Move
    case "LEFT"   => Command.Left
    case "RIGHT"  => Command.Right
    case "REPORT" => Command.Report
    case placePattern(x, y, direction) => Command.Place(x.toInt, y.toInt, Direction.fromStr(direction))
    case _ => ???
  }

  private val placePattern: Regex = """^PLACE (\d+),(\d+),(NORTH|SOUTH|EAST|WEST)$""".r
  def isPlace(rawInput: String): Boolean = {
    placePattern.matches(rawInput)
  }

}