
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class represents a train within our system. It is
// created by the CanvasListener when one of the stations is
// clicked. It is passed the Component that it will start at,
// a station, and after being created it waits for further
// instructions from the travel, move, and update methods
//***********************************

public class Train
{
  public void setCurrentComponent(Component currentComponent)
  {
    this.currentComponent = currentComponent;
  }
  private Component currentComponent;
  private ArrayList<Component> path;
  private Image image;
  private boolean flipped= false;
  public Train(Component component)
  {
    currentComponent = component;
    Station c =(Station)currentComponent;
    path=c.getPath();
    image = new Image("resources/Train.png");
  }

  //**********************************
  // This is the method that is responsible for
  // displaying the train component. It takes in a graphics context
  // and returns nothing.
  //***********************************
  public synchronized void display(GraphicsContext gc)
  {

    if(currentComponent==null);
    else if(flipped && currentComponent instanceof Station) gc.drawImage(image,currentComponent.getGuiX()+90, currentComponent.getGuiY()-20 + Reference.length,-90,23);
    else if(flipped) gc.drawImage(image,currentComponent.getGuiX()+90, currentComponent.getGuiY()-20 ,-90,23);
    else if(currentComponent instanceof Station) gc.drawImage(image,currentComponent.getGuiX(), currentComponent.getGuiY()-20 + Reference.length);
    else gc.drawImage(image,currentComponent.getGuiX(), currentComponent.getGuiY()-20);
  }
  public boolean isFlipped()
  {
    return flipped;
  }

  public void setFlipped(boolean flipped)
  {
    this.flipped = flipped;
  }
  public ArrayList<Component> getPath()
  {
    return path;
  }

  public void setPath(ArrayList<Component> path)
  {
    this.path = path;
  }
}
