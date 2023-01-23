package Components;
import Actors.Enemy;
import Actors.Player;
import Util.AABB;

import java.util.ArrayList;

public class PlayerEnemyCollision implements ICollisionListener{

    private Player player;
    ArrayList<Enemy> enemies;
    public PlayerEnemyCollision(Player player,ArrayList<Enemy> enemies)
    {
        this.player = player;
        this.enemies = enemies;
    }
    @Override
    public void aCollisionIsHappened(AABB box) {

        for(AABB tmp : enemies)
        {
            if(box == tmp)
                this.player.setStatus(true);
        }

    }
}
