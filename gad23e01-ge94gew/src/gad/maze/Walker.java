package gad.maze;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;

enum Direction {
	UP, DOWN, LEFT, RIGHT
}

class History {
	int x, y;
	Direction dir;

	History(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (o instanceof History)
			return ((History) o).x == this.x && ((History) o).y == this.y && ((History) o).dir == this.dir;
		return false;
	}
}

public class Walker {

	Collection<History> hist;
	private final boolean[][] maze;
	private final Result logger;
	private Direction dir;
	private int locX;
	private int locY;

	public Walker(boolean[][] maze, Result result) {
		this.maze = maze;
		this.logger = result;

		this.dir = Direction.DOWN;
		this.locX = 1;
		this.locY = 0;

		hist = new ArrayList<>();
		hist.add(new History(locX, locY, dir));
		logger.addLocation(locX, locY);
	}

	public boolean walk() {

		while (true) {

			if (!getRight()) {
				turn(Direction.RIGHT);
				move();
			} else if (getFront()) {
				turn(Direction.LEFT);
			} else {
				move();
			}

			if (locX == maze.length - 1) return true;
			if (locY == 0) return false;

			// History current = new History(locX, locY, dir);
			// if (hist.contains(current)) return false;
			// hist.add(current);
		}
	}

	/// Turns Walker in one direction
	private void turn(Direction whichDir) {
		if (whichDir == Direction.RIGHT) {
			this.dir = switch (this.dir) {
				case RIGHT -> Direction.DOWN;
				case LEFT -> Direction.UP;
				case UP ->  Direction.RIGHT;
				case DOWN -> Direction.LEFT;
			};
		} else if (whichDir == Direction.LEFT) {
			this.dir = switch (this.dir) {
				case RIGHT -> Direction.UP;
				case LEFT -> Direction.DOWN;
				case UP ->  Direction.LEFT;
				case DOWN -> Direction.RIGHT;
			};
		}
	}

	/// Moves walker one step forward
	private void move() {
		switch (this.dir) {
			case RIGHT -> locX++;
			case LEFT -> locX--;
			case DOWN -> locY++;
			case UP -> locY--;
		}
		logger.addLocation(locX, locY);
	}

	/// Gets the block to the right of the walker
	private boolean getRight() {
		return switch (this.dir) {
			case DOWN -> maze[locX - 1][locY];
			case LEFT -> maze[locX][locY - 1];
			case UP -> maze[locX + 1][locY];
			case RIGHT -> maze[locX][locY + 1];
		};
	}

	/// Gets the block in front of the walker
	private boolean getFront() {
		return switch (this.dir) {
			case DOWN -> maze[locX][locY + 1];
			case LEFT -> maze[locX - 1][locY];
			case UP -> maze[locX][locY - 1];
			case RIGHT -> maze[locX + 1][locY];
		};
	}

	public static void main(String[] args) {
		boolean[][] maze = Maze.generateStandardMaze(1000, 300);
		StudentResult result = new StudentResult();
		Walker walker = new Walker(maze, result);
		System.out.println(walker.walk());
		Maze.draw(maze, result);
	}
}
