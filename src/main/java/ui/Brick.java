package ui;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import utils.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Brick implements Drawable , MouseListener {

    private MovableShape ms;
    private BrickingBad brickingBad;
    private static boolean removeFlag = false;

    public Brick(MovableShape ms, BrickingBad bb){
        this.ms = ms;
        brickingBad = bb;
    }

    public void draw(Graphics g){
        int x = ms.getPosition().getX();
        int y = ms.getPosition().getY();
        int length = ms.getLength();
        int width = ms.getWidth();
        g.fillRect(x,y,length,width);
        g.drawRect(x,y,length,width);
    }

    public static void setRemoveFlag(boolean state){
        removeFlag = state;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {


        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        int myX = ms.getPosition().getX();
        int myY = ms.getPosition().getY();
        int len = ms.getLength();
        int wid = ms.getWidth();
        boolean flag = (x <= myX + wid && x >= myX ) && (y >= myY && y <= myY + len);
        if(flag && removeFlag){
            brickingBad.removeBrick(new Position(myX,myY));
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
