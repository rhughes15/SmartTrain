import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class CanvasListener implements EventHandler
{
  private List<Component> components;
  private String message;

  public CanvasListener(List components)
  {
    this.components = components;
    message = "";
  }

  @Override
  public void handle(Event event)
  {
    for(Component c : components)
    {
      if()
    }
  }
}
