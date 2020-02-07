package com.toyrobotscala

sealed trait Direction

object Direction {
  case object North extends Direction
  case object East extends Direction
  case object South extends Direction
  case object West extends Direction

  def fromStr(rawDirection: String): Direction = rawDirection match {
    case "NORTH" => North
    case "EAST"  => East
    case "SOUTH" => South
    case "WEST"  => West
  }
}
