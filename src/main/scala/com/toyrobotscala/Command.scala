package com.toyrobotscala

import com.toyrobotscala.Direction.{East, North, South, West}

sealed trait Command

object Command {
  sealed trait UpdateRobotPosition extends Command {
    def execute(grid: Grid, robot: Robot): Robot
  }
  sealed trait RotateRobot extends Command {
    def rotate(robot: Robot): Robot
  }
  sealed trait UnchangedRobot extends Command

  case class Place(x: Int, y: Int, direction: Direction)
      extends UpdateRobotPosition {
    override def execute(grid: Grid, robot: Robot): Robot = {
      if (robot.hasValidPositionOn(grid) && grid.x.contains(x) && grid.y
            .contains(y)) {
        Robot(x, y, direction)
      } else {
        robot
      }
    }
  }

  case object Move extends UpdateRobotPosition {
    override def execute(grid: Grid, robot: Robot): Robot = {
      if (robot.hasValidPositionOn(grid)) {
        robot.direction match {
          case North =>
            newRobotOrCurrent(grid, robot.copy(y = robot.y + 1), robot)
          case East =>
            newRobotOrCurrent(grid, robot.copy(x = robot.x + 1), robot)
          case South =>
            newRobotOrCurrent(grid, robot.copy(y = robot.y - 1), robot)
          case West =>
            newRobotOrCurrent(grid, robot.copy(x = robot.x - 1), robot)
        }
      } else {
        robot
      }
    }
  }

  case object Right extends RotateRobot {
    override def rotate(robot: Robot): Robot =
      robot.direction match {
        case North => robot.copy(direction = East)
        case East  => robot.copy(direction = South)
        case South => robot.copy(direction = West)
        case West  => robot.copy(direction = North)
      }
  }

  case object Left extends RotateRobot {
    override def rotate(robot: Robot): Robot =
      robot.direction match {
        case North => robot.copy(direction = West)
        case East  => robot.copy(direction = North)
        case South => robot.copy(direction = East)
        case West  => robot.copy(direction = South)
      }
  }

  case object Report extends UnchangedRobot

  private def newRobotOrCurrent(grid: Grid,
                                newRobot: Robot,
                                currentRobot: Robot): Robot = {
    if (newRobot.hasValidPositionOn(grid)) newRobot else currentRobot
  }
}
