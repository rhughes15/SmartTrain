import javafx.animation.AnimationTimer;

public class Timer
{
  private AnimationTimer timer;
  private GUI gui;

  public Timer(GUI gui)
  {
    this.gui = gui;
    timer = new AnimationTimer() {
      @Override
      public void handle(long now)
      {
        gui.updateAllComponents();
      }
    };
  }

  public void start()
  {
    timer.start();
  }
}
