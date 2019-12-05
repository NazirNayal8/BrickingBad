package domain.game;

import domain.model.Ball;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.brick.BrickFactory;
import domain.model.brick.HalfMetalBrick;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.util.ArrayList;
import java.util.List;

// TODO move all movable objects - ones has hasNextPosition() == true
// check all movable objects whether they are collided or not
// remove all collided objects

// Board contains all movables, paddle and ball
public class Board {

  static final Logger logger = Logger.getLogger(Board.class);
  private List<MovableShape> movables;
  private Paddle paddle;
  private Ball ball;
  private int bricksLeft; // TODO get this from list

  public Board(GameData data) {
    if (data == null) {
      throw new IllegalArgumentException();
    }
    paddle = data.getPaddle();
    ball = data.getBall();
    movables = data.getMovables();
  }

  public Board() throws IllegalArgumentException {
    movables = new ArrayList<>();
    defaultMovables();
  }

  private void defaultMovables() {
    for (int i = 0; i < 10; i++) {
      if (i % 3 == 2)
        movables.add(BrickFactory.get(SpecificType.MineBrick, new Position(10 * i - 100, 300)));
    }
    // TODO: remove constants from here
    ball = new Ball(new Position(310, 300), Constants.BALL_DIAMETER / 2);
    ball.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, Constants.BALL_INITIAL_VY));
    paddle = new Paddle(new Position(300, 700));

    // TODO: ball and paddle are added to movables for now for sake of collision checking
      movables.add(ball);
      movables.add(paddle);

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 6; j++) {
        Position curpos = new Position(80 * i + 20, 40 * j + 10);
        if (i % 2 == 1) movables.add(BrickFactory.get(SpecificType.SimpleBrick, curpos));
        else movables.add(new HalfMetalBrick(curpos, 60, 20));
      }
    }
  }

  public void animate() {
    // advance all movables one step and check collisions and remove collided ones
    moveBall();
    moveAllMovables();
    checkCollisions();
    removeDestroyedMovables();
    // TODO need to check whether ball is dropped or not then check remaining lives
  }

  private void moveBall() {
    ball.move();
    if (ball.getPosition().getY() > Constants.maxY) {
      ball.setPosition(paddle.getPosition().incrementY(-100).incrementX(paddle.getLength() / 2));
      ball.setVelocity(Constants.defaultRespawnVelocity);
    }
  }

  private void moveAllMovables() {
    // move all objects once
    for (MovableShape movableShape : movables) {
      movableShape.move();
    }
  }

  private void checkCollisions() {
    // check all movables pair-wise whether they are collided or not
    for (int i = 0; i < movables.size(); i++) {
      for (int j = i + 1; j < movables.size(); j++) {
        CollisionRuleEngine.collide(movables.get(i), movables.get(j));
      }
    }
  }

  private void removeDestroyedMovables() {
    movables.removeIf(
        movableShape -> {
          if (movableShape.isDestroyed()) logger.debug(movableShape + " is destroyed.");
          return movableShape.isDestroyed();
        });
    // logger.debug("# of remaining movables: " + movables.size());
  }

  public void addMovable(MovableShape mshape) {
    movables.add(mshape);
  }

  public void movePaddleLeft() {
    paddle.moveLeft();
  }

  public void movePaddleRight() {
    paddle.moveRight();
  }

  public void rotatePaddleRight() {
    paddle.rotateRight();
  }

  public void rotatePaddleLeft() {
    paddle.rotateLeft();
  }
  // TODO: check copy behavior
  public GameData getData() {
    Paddle p = (Paddle) paddle.copy();
    Ball b = (Ball) ball.copy();
    List<MovableShape> movableList = new ArrayList<>();
    for (MovableShape ms : movables) {
      movableList.add(ms.copy());
    }
    return new GameData(p, b, movableList);
  }
}
