package com.toyrobotscala

sealed trait Command

object Command {
  case class UpdateRobotPosition(command: CommandUpdatingPosition) extends Command
  case class UnchangedRobot(command: CommandNotUpdatingPosition) extends Command
}

sealed trait CommandUpdatingPosition

object CommandUpdatingPosition {
  case class Place(x: Int, y: Int, direction: Direction) extends CommandUpdatingPosition
  case object Move extends CommandUpdatingPosition
  case object Right extends CommandUpdatingPosition
  case object Left extends CommandUpdatingPosition
  case object PlaceObstacle extends CommandUpdatingPosition
}

sealed trait CommandNotUpdatingPosition

object CommandNotUpdatingPosition {
  case object Report extends CommandNotUpdatingPosition
}