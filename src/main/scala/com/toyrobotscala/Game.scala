package com.toyrobotscala

import com.toyrobotscala.Direction.{East, North, South, West}

case class Game(grid: Grid, currentRobot: Robot, obstacles: Set[Obstacle]) {

  def play(command: CommandUpdatingPosition): Game = command match {
    case CommandUpdatingPosition.Place(x, y, direction) =>
        if (noCollisionAndValidPositionOnGrid() && grid.x.contains(x) && grid.y.contains(y)) {
          Game(grid, Robot(x, y, direction), obstacles)
        } else {
          Game(grid, currentRobot, obstacles)
        }
    case CommandUpdatingPosition.Move =>
      if (noCollisionAndValidPositionOnGrid()) {
        currentRobot.direction match {
          case North =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(y = currentRobot.y + 1), currentRobot)
            Game(grid, robot, obstacles)
          case East =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(x = currentRobot.x + 1), currentRobot)
            Game(grid, robot, obstacles)
          case South =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(y = currentRobot.y - 1), currentRobot)
            Game(grid, robot, obstacles)
          case West =>
            val robot = newRobotOrCurrent(grid, currentRobot.copy(x = currentRobot.x - 1), currentRobot)
            Game(grid, robot, obstacles)
        }
      } else {
        Game(grid, currentRobot, obstacles)
      }
    case CommandUpdatingPosition.Right =>
      currentRobot.direction match {
        case North => Game(grid, currentRobot.copy(direction = East), obstacles)
        case East  => Game(grid, currentRobot.copy(direction = South), obstacles)
        case South => Game(grid, currentRobot.copy(direction = West), obstacles)
        case West  => Game(grid, currentRobot.copy(direction = North), obstacles)
      }
    case CommandUpdatingPosition.Left =>
      currentRobot.direction match {
        case North => Game(grid, currentRobot.copy(direction = West), obstacles)
        case East  => Game(grid, currentRobot.copy(direction = North), obstacles)
        case South => Game(grid, currentRobot.copy(direction = East), obstacles)
        case West  => Game(grid, currentRobot.copy(direction = South), obstacles)
      }
    case CommandUpdatingPosition.PlaceObstacle => if (canPlaceObstacle()) {
      currentRobot.direction match {
        case North => Game(grid, currentRobot, obstacles ++ Set(Obstacle(currentRobot.x, currentRobot.y + 1)))
        case East => Game(grid, currentRobot, obstacles ++ Set(Obstacle(currentRobot.x + 1, currentRobot.y)))
        case South => Game(grid, currentRobot, obstacles ++ Set(Obstacle(currentRobot.x, currentRobot.y - 1)))
        case West => Game(grid, currentRobot, obstacles ++ Set(Obstacle(currentRobot.x - 1, currentRobot.y)))
      }
    }
    else Game(grid, currentRobot, obstacles)
  }

  private def newRobotOrCurrent(grid: Grid, newRobot: Robot, currentRobot: Robot): Robot =
    if (newRobot.hasValidPositionOn(grid)) newRobot else currentRobot

  private def doesNotCollideWithObstacles(): Boolean =
    obstacles.foldLeft(true)((_, obstacle) => obstacle.x == currentRobot.x && obstacle.y == currentRobot.y)

  private def noCollisionAndValidPositionOnGrid(): Boolean = currentRobot.hasValidPositionOn(grid) && doesNotCollideWithObstacles

  private def canPlaceObstacle(): Boolean = currentRobot.direction match {
    case Direction.North => grid.x.contains(currentRobot.x) && grid.y.contains(currentRobot.y + 1)
    case Direction.East => grid.x.contains(currentRobot.x + 1) && grid.y.contains(currentRobot.y)
    case Direction.South => grid.x.contains(currentRobot.x) && grid.y.contains(currentRobot.y - 1)
    case Direction.West => grid.x.contains(currentRobot.x - 1) && grid.y.contains(currentRobot.y)
  }
}

