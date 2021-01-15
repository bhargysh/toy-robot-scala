package com.toyrobotscala

import com.toyrobotscala.Direction.{East, North, South, West}
import org.specs2.mutable.Specification

class GameSpec extends Specification {
  "Game" should {
    val grid = Grid(Range(0, 5), Range(0, 5))
    val robot = Robot(4, 3, North)
    val game = Game(grid, robot, Set.empty[Obstacle])
    "return an updated game" in {
      "when command is place" in {
        val newRobot = Robot(2, 3, South)
        val newGame = Game(grid, newRobot)

        game.play(CommandUpdatingPosition.Place(2, 3, South)) shouldEqual newGame
      }
      "when command is move" in {
        val newRobot = Robot(4, 4, North)
        val newGame = Game(grid, newRobot)

        game.play(CommandUpdatingPosition.Move) shouldEqual newGame
      }
      "when command is left" in {
        val newRobot = Robot(4, 3, West)
        val newGame = Game(grid, newRobot)

        game.play(CommandUpdatingPosition.Left) shouldEqual newGame
      }
      "when command is right" in {
        val newRobot = Robot(4, 3, East)
        val newGame = Game(grid, newRobot)

        game.play(CommandUpdatingPosition.Right) shouldEqual newGame
      }
      "when command is place obstacle" in {
        val newGame = Game(grid, robot, Set(Obstacle(4, 4)))

        game.play(CommandUpdatingPosition.PlaceObstacle) shouldEqual newGame
      }
      "when command is place obstacle and there is an obstacle there already" in {
        val game = Game(grid, robot, Set(Obstacle(4, 4)))
        val newGame = Game(grid, robot, Set(Obstacle(4, 4)))

        game.play(CommandUpdatingPosition.PlaceObstacle) shouldEqual newGame
      }
    }
    "return current game" in {
      "when command is place and is not on grid" in {
        game.play(CommandUpdatingPosition.Place(5, 5, South)) shouldEqual game
      }
      "when command is move and robot would fall off grid" in {
        val robot = Robot(4, 4, East)
        val game = Game(grid, robot)

        game.play(CommandUpdatingPosition.Move) shouldEqual game
      }
    }
  }
}
