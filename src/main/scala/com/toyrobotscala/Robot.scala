package com.toyrobotscala

case class Robot(x: Int, y: Int, direction: Direction) {
  def hasValidPositionOn(grid: Grid): Boolean = {
    grid.x.contains(x) && grid.y.contains(y)
  }
}