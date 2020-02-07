package com.toyrobotscala

import cats.effect.IO
import com.toyrobotscala.Direction.{East, North, South, West}

sealed trait Command {
  def execute(grid: Grid, robot: Robot): Robot
}

object Command {
  case class Place(x: Int, y:Int, direction: Direction) extends Command {
    override def execute(grid: Grid, robot: Robot): Robot = {
      if(robot.hasValidPositionOn(grid) && grid.x.contains(x) && grid.y.contains(y)) {
        Robot(x, y, direction)
      } else {
        robot
      }
    }
  }
  case object Left extends Command {
    override def execute(grid: Grid, robot: Robot): Robot = {
      if(robot.hasValidPositionOn(grid)) {
        robot.direction match {
          case North => robot.copy(direction = West)
          case East => robot.copy(direction = North)
          case South => robot.copy(direction = East)
          case West => robot.copy(direction = South)
        }
      } else {
        robot
      }
    }
  }
  case object Right extends Command {
    override def execute(grid: Grid, robot: Robot): Robot = {
      if(robot.hasValidPositionOn(grid)) {
        robot.direction match {
          case North => robot.copy(direction = East)
          case East => robot.copy(direction = South)
          case South => robot.copy(direction = West)
          case West => robot.copy(direction = North)
        }
      } else {
        robot
      }
    }
  }
  case object Move extends Command {
    override def execute(grid: Grid, robot: Robot): Robot = {
      if (robot.hasValidPositionOn(grid)) {
        robot.direction match {
          case North => {
            newRobotOrCurrent(grid, robot.copy(y = robot.y + 1), robot)
          }
          case East => {
            newRobotOrCurrent(grid, robot.copy(x = robot.x + 1), robot)
          }
          case South => {
            newRobotOrCurrent(grid, robot.copy(y = robot.y - 1), robot)
          }
          case West => {
            newRobotOrCurrent(grid, robot.copy(x = robot.x - 1), robot)
          }
        }
      } else {
        robot
      }
    }
  }
  case object Report extends Command {
    override def execute(grid: Grid, robot: Robot): Robot = robot
//    TODO: converter that takes a robot and prints its position i.e. returns IO[Unit]
  }
  private def newRobotOrCurrent(grid: Grid, newRobot: Robot, currentRobot: Robot): Robot = {
    if(newRobot.hasValidPositionOn(grid)) newRobot else currentRobot
    //  TODO: should it log if it could not move?
  }
}