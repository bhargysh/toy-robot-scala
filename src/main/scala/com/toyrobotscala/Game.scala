package com.toyrobotscala

case class Game(grid: Grid, currentRobot: Robot) {

  def play(command: Command): Game = {
    Game(grid, command.execute(grid, currentRobot))
  }
}
