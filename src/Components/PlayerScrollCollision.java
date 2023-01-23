package Components;

import Actors.Player;
import Actors.PowerUp;
import Util.AABB;

import java.util.ArrayList;

public class PlayerScrollCollision implements ICollisionListener
{

    Player player;
    ArrayList<PowerUp> powerUps;

    public PlayerScrollCollision(Player player, ArrayList<PowerUp> powerUps)
    {
        this.player = player;
        this.powerUps = powerUps;
    }

    @Override
    public void aCollisionIsHappened(AABB box) {
        for(PowerUp powerUp:powerUps)
        {
            if(powerUp == box)
            {
                powerUp.changeStatus(true);
                powerUp = null;
            }


        }

    }
}
