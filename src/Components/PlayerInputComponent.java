package Components;

import Actors.AbstractActor;
import Actors.Bullet;
import Actors.Player;
import Actors.Wall;
import Core.GameWindow;
import Util.AABB;
import Util.Position2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerInputComponent implements IRealTimeComponent, KeyListener
{
    // Internal States
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean firePressed;
    private AbstractActor.Directions lastDirection;
    private ArrayList<Bullet> bullets;
    private float speed;

    private Position2D<Float> position;
    private AABB boundingBox;

    private ArrayList<AABB> walls;


    public PlayerInputComponent(int speed,AABB pos,ArrayList<Bullet> bullets,ArrayList<AABB> walls)
    {
        this.bullets = bullets;
        this.leftPressed = false;
        this.rightPressed = false;
        this.upPressed = false;
        this.downPressed = false;
        this.firePressed = false;

        GameWindow.GetInstance().attachKeyListener(this);
        this.lastDirection = AbstractActor.Directions.NOTHING;

        this.speed = speed;
        this.position = pos.getPos();
        this.boundingBox = pos;
        this.walls = walls;
    }

    @Override
    public void update(float deltaT)
    {
        // TODO:
        if(this.rightPressed)
        {
            // add to Current x Position.
            this.lastDirection = AbstractActor.Directions.RIGHT;
            float movementOffset = deltaT * this.speed;
            this.position.x += movementOffset;

        }
        if(this.upPressed)
        {
            // add to Current y Position.
            this.lastDirection = AbstractActor.Directions.UP;
            float movementOffset = deltaT * this.speed;
            this.position.y -=movementOffset;

        }

        if(this.leftPressed)
        {
            this.lastDirection = AbstractActor.Directions.LEFT;
            float movementOffset = deltaT * this.speed;
            this.position.x -= movementOffset;

        }
        if(this.downPressed)
        {
            this.lastDirection = AbstractActor.Directions.DOWN;
            float movementOffset = deltaT * this.speed;
            this.position.y +=movementOffset;

        }
        if(this.firePressed)
        {
            this.firePressed = false;

            // Create bullets respect to the lastDirection.
            if(this.lastDirection != AbstractActor.Directions.NOTHING)
            {
                // Create Bullet.
                Position2D<Float> bullPos = new Position2D<>(this.position.x + 1,this.position.y + 1);
                Bullet bullet = new Bullet(bullPos, boundingBox.getSizeX(), boundingBox.getSizeY(),lastDirection,this.walls);
                this.bullets.add(bullet);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Do nothing */ }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_UP) upPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) upPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = false;
        // Enforce release operation on fire
        if(e.getKeyCode() == KeyEvent.VK_SPACE) firePressed = true;

    }
}
