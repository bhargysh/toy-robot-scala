package com.toyrobotscala

case class Game(grid: Grid, currentRobot: Robot) {

  def play(command: Command): Game = {
    command match {
      case position: Command.UpdateRobotPosition => Game(grid, position.execute(grid, currentRobot))
      case rotateRobot: Command.RotateRobot => Game(grid, rotateRobot.rotate(currentRobot))
      case _: Command.UnchangedRobot => Game(grid, currentRobot)
    }
  }
}
