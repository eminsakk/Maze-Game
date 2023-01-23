package Components;

import Actors.Player;
import Actors.Wall;
import Util.AABB;

import java.util.ArrayList;

public class PlayerWallCollision implements ICollisionListener
{

    private Player player;
    private ArrayList<AABB> walls;
    public PlayerWallCollision(Player player, ArrayList<AABB> walls)
    {
        this.player = player;
        this.walls = walls;
    }
    @Override
    public void aCollisionIsHappened(AABB box) {

        for(AABB wall : walls)
        {
            if(wall == box)
                player.moveIfCollide(box);
        }
    }
}
