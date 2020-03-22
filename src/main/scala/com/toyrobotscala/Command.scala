package com.toyrobotscala

sealed trait Command

object Command {
  sealed trait UpdateRobotPosition extends Command
  sealed trait ChangeDirectionOfRobot extends Command
  sealed trait UnchangedRobot extends Command

  case class Place(x: Int, y: Int, direction: Direction) extends UpdateRobotPosition
  case object Move extends UpdateRobotPosition

  case object Right extends ChangeDirectionOfRobot
  case object Left extends ChangeDirectionOfRobot

  case object Report extends UnchangedRobot
}
