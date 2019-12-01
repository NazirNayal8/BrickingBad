package domain.model.brick;

import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class HalfMetalBrick extends Brick {

    // TODO: Implement
    public HalfMetalBrick(Position position, int length, int width) {
        super(new NoMovement(position), length, width);
    }

    @Override
    public void collide(MovableShape obj) {
        if(obj.getCenter().getY()<this.getCenter().getY()){
            this.destroy();
        }
    }

    @Override
    public void move() {
        //TODO: Implement
        return;
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
        return;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.HalfMetalBrick;
    }

    @Override
    public String toString() {
        return "Half Metal Brick at " + getPosition().toString(); //TODO add position.toString here;
    }

}