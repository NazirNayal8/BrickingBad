package domain.model.brick;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;

public class HalfMetalBrick extends Brick {

    // TODO: Implement
    public HalfMetalBrick(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
    }

    @Override
    public void collide(MovableShape obj) {
        if(obj.getType() == Type.Ball && obj.getCenter().getY()<this.getCenter().getY()){
            this.destroy();
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.HalfMetalBrick;
    }

    @Override
    public String toString() {
        return "Half Metal Brick at " + super.getPosition().toString();
    }
    @Override
    public MovableShape copy(){
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }

}
