package Components;

// On a real-time system all components may want to update wrt.
// time change
public interface IComponent
{
    public void update(float deltaT);
}
