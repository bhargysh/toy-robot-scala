package com.toyrobotscala

import com.toyrobotscala.Direction.{East, North, South, West}

case class Game(grid: Grid, currentRobot: Robot) {

  def play(command: CommandUpdatingPosition): Game = command match {
    case CommandUpdatingPosition.Place(x, y, direction) =>
        if (currentRobot.hasValidPositionOn(grid) && grid.x.contains(x) && grid.y.contains(y)) {
          Game(grid, Robot(x, y, direction))
        } else {
          Game(grid, currentRobot)
        }
    case CommandUpdatingPosition.Move =>
      if (currentRobot.hasValidPositionOn(grid)) {
        currentRobot.direction match {
          case North =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(y = currentRobot.y + 1), currentRobot)
            Game(grid, robot)
          case East =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(x = currentRobot.x + 1), currentRobot)
            Game(grid, robot)
          case South =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(y = currentRobot.y - 1), currentRobot)
            Game(grid, robot)
          case West =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(x = currentRobot.x - 1), currentRobot)
            Game(grid, robot)
        }
      } else {
        Game(grid, currentRobot)
      }
    case CommandUpdatingPosition.Right =>
      currentRobot.direction match {
        case North => Game(grid, currentRobot.copy(direction = East))
        case East  => Game(grid, currentRobot.copy(direction = South))
        case South => Game(grid, currentRobot.copy(direction = West))
        case West  => Game(grid, currentRobot.copy(direction = North))
      }
    case CommandUpdatingPosition.Left =>
      currentRobot.direction match {
        case North => Game(grid, currentRobot.copy(direction = West))
        case East  => Game(grid, currentRobot.copy(direction = North))
        case South => Game(grid, currentRobot.copy(direction = East))
        case West  => Game(grid, currentRobot.copy(direction = South))
      }
  }

  private def newRobotOrCurrent(grid: Grid, newRobot: Robot, currentRobot: Robot): Robot =
    if (newRobot.hasValidPositionOn(grid)) newRobot else currentRobot
}

