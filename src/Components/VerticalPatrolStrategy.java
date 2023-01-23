package Components;

public class VerticalPatrolStrategy extends AbstractPatrolStrategy
{
    @Override
    public void update(float deltaT)
    {
        this.pos.getPos().y += this.speed * deltaT;
    }
}
