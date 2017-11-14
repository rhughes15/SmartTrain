import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class GUI
{
  private Stage stage;
  private List<Component> componentList;
  private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK, Color.TURQUOISE, Color.PURPLE};
  public GUI(Stage stage, List<Component> componentList)
  {
    this.stage = stage;
    this.componentList = componentList;
    stage.show();
    stage.setScene(welcomeScreen());
    stage.setTitle("Smart Train");
  }

  private Scene welcomeScreen()
  {
    Label labelTitle = new Label("Smart Train");
    labelTitle.setFont(Font.font(60));
    Button buttonStart = new Button("Start Simulation");
    VBox vboxWelcome = new VBox();
    vboxWelcome.setAlignment(CENTER);
    vboxWelcome.setSpacing(10);
    vboxWelcome.getChildren().addAll(labelTitle, buttonStart);
    Scene scene = new Scene(vboxWelcome, 1200,800);
    buttonStart.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override public void handle(ActionEvent e)
      {
        stage.setScene(drawBoard(componentList));
        buttonStart.setDisable(true);
      }
    });
    return scene;

  }

  private Scene drawBoard(List<Component> componentList)
  {
    int color = 0;
    int length = 85;
    int y = 150;
    Group root = new Group();
    Canvas canvas = new Canvas(1200, 800);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    for (Component c : componentList)
    {
      System.out.println(c.getTrackX());
      gc.setStroke(Color.rgb(0,0,0));
      gc.setLineWidth(5);
      if((c.getClass().toString()).contains("Station"))
      {

        gc.setFill(colors[color%colors.length]);
        color++;
        gc.fillRect(length*c.getTrackX() + 50, y*c.getTrackY()+200 - length, length ,length);

      }
      else if ((c.getClass().toString()).contains("TRSwitch"))
      {
        gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200, length +length*c.getTrackX()+44, y*c.getTrackY() + 200);
        gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200, length/2 +length*c.getTrackX()+50, (y)*c.getTrackY() + 200 + y/2);
      }
      else if ((c.getClass().toString()).contains("BRSwitch"))
      {
        gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200, length +length*c.getTrackX()+44, y*c.getTrackY() + 200);
        gc.strokeLine(length/2 + length*c.getTrackX()+50, y*c.getTrackY() - y/2 + 200, length +length*c.getTrackX()+44, (y)*c.getTrackY() + 200 );
      }
      else if ((c.getClass().toString()).contains("TLSwitch"))
      {
        gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200, length +length*c.getTrackX()+44, y*c.getTrackY() + 200);
        gc.strokeLine(length/2 + length*c.getTrackX()+50, y*c.getTrackY() + y/2 + 200, length +length*c.getTrackX()+44, (y)*c.getTrackY() + 200 );
      }
      else if ((c.getClass().toString()).contains("BLSwitch"))
      {
        gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200, length +length*c.getTrackX()+44, y*c.getTrackY() + 200);
        gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200,  length/2 +length*c.getTrackX()+50, (y)*c.getTrackY() + 200 - y/2);
      }
      else if ((c.getClass().toString()).contains("Signal"))
      {
        Signal signal = (Signal) c;
        if(signal.isGreen())gc.setFill(Color.GREEN);
        else gc.setFill(Color.RED);
        gc.fillOval(length/2 + length*c.getTrackX()+40, y*c.getTrackY() + 175 , 20, 20);
        gc.strokeLine(length * c.getTrackX() + 55, y * c.getTrackY() + 200, length + length * c.getTrackX() + 44, y * c.getTrackY() + 200);
      }

      else gc.strokeLine(length*c.getTrackX()+55, y*c.getTrackY() + 200, length +length*c.getTrackX()+44, y*c.getTrackY() + 200);
      System.out.println(c.getClass().toString());
    }
    root.getChildren().add(canvas);
    Scene trainScene = new Scene(root, 1200, 800);
    return trainScene;
  }
}
