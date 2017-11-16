import javafx.animation.AnimationTimer;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to give the AnimationTimer
// its own thread. The timer calls the display and update functions
// on all the components in the GUI's list of components
//***********************************

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
