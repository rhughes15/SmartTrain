import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is the event listener for the canvas assigned
// in GUI. It takes a list of components that should only consist
// of all the Stations to be monitored. It is only called when a
// click occurs somewhere on the canvas. When a click occurs,
// it checks to see if the click was on a Station and either stores
// the first station of the route to be found and creates a train
// at the station, or it starts the message passing to find a route
// for the Train.
//***********************************

public class CanvasListener implements EventHandler
{
  private List<Component> components;
  private Station firstStation = null;

  public CanvasListener(List<Component> components)
  {
    this.components = components;
  }

  @Override
  public void handle(Event event)
  {
    for(Component c : components)
    {
      if(isCloseEnough(c, event))
      {
        if(firstStation == null)
        {
          firstStation = (Station)c;
        }
        else
        {
          if((((Station)c).getStationName().charAt(0)< 'S' && firstStation.getStationName().charAt(0)<'S') || ((Station)c).getStationName().charAt(0)>= 'S' && firstStation.getStationName().charAt(0)>='S') firstStation = null;
         else
          {
            firstStation.getTrack().acceptMessage(firstStation.getStationName() + ((Station) c).getStationName(), new ArrayList<>(), true);
            firstStation = null;
          }
        }
      }
    }
  }
  //**********************************
  // This method checks if the user clicked close enough to
  // a train station. It takes in no arguments and returns
  // nothing.
  //***********************************
  private boolean isCloseEnough(Component comp, Event event)
  {
    if((comp instanceof Station) && (event instanceof MouseEvent))
    {
      MouseEvent me = (MouseEvent)event;
      double difx = me.getSceneX() - comp.getGuiX();
      double dify = me.getSceneY() - comp.getGuiY();
      if(difx < Reference.length && difx > 0 &&
         dify < Reference.length && dify > 0)
      {
        return true;
      }
    }
    return false;
  }
}