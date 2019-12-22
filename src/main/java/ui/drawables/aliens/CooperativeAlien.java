package ui.drawables.aliens;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import java.awt.*;

public class CooperativeAlien implements Drawable {

    private MovableShape ms;

    public CooperativeAlien(MovableShape ms){
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        int length = ms.getLength();
        int width = ms.getWidth();
        g.setColor(Color.orange);
        g.fillRect(x, y, length, width);
        g.drawRect(x, y, length, width);
    }
}
