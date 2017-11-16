import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CanvasListener implements EventHandler
{
  private List<Component> components;
  private Station firstStation;

  public CanvasListener(List<Component> components)
  {
    this.components = components;
    for(Component c : components)
    {
      if (c instanceof Track) System.out.println("T, guiX: " + c.getGuiX() + " guiY: " + c.getGuiY());
      else if (c instanceof Switch) System.out.println("Sw, guiX: " + c.getGuiX() + " guiY: " + c.getGuiY());
      else if (c instanceof Signal) System.out.println("Si, guiX: " + c.getGuiX() + " guiY: " + c.getGuiY());
    }
  }

  @Override
  public void handle(Event event)
  {
    for(Component c : components)
    {
      if(isCloseEnough(c, event))
      {
        if(firstStation != null)
        {
          firstStation.setTrain(new Train(firstStation));
          firstStation.getTrack().acceptMessage(firstStation.getStationName() +
            ((Station)c).getStationName(), new ArrayList<>(), true);
        }
        else
        {
          firstStation = (Station)c;
        }
      }
    }
  }

  private boolean isCloseEnough(Component comp, Event event)
  {
    if((comp instanceof Station) && (event instanceof MouseEvent))
    {
      MouseEvent me = (MouseEvent)event;
      if(me.getSceneX() - comp.getGuiX() < Reference.STATION_WIDTH &&
         me.getSceneY() - comp.getGuiY() < Reference.STATION_HEIGHT)
      {
        return true;
      }
    }
    return false;
  }
}