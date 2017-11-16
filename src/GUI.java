import javafx.animation.AnimationTimer;
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

import java.util.ArrayList;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to display our system. It constructs our GUI,
// displays the title page for our simulation, then makes the calls
// to display and update our components. It also creates the CanvasListener
// and assigns it to our canvas. It holds the GraphicsContext used by
// all components to display themselves as well as the Timer and a list of
// all components and all stations.
//***********************************

public class GUI
{
  private Stage stage;
  private List<Component> componentList, stationList;
  private GraphicsContext gc;
  private Timer timer;

  public GUI(Stage stage, List<Component> componentList)
  {
    this.stage = stage;
    this.componentList = componentList;
    stationList = new ArrayList<>();
    for(Component c : componentList)
      if(c instanceof Station)
        stationList.add(c);
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

    Group root = new Group();
    Canvas canvas = new Canvas(1200, 800);
    canvas.setOnMouseClicked(new CanvasListener(stationList));
    root.getChildren().add(canvas);
    gc = canvas.getGraphicsContext2D();
    timer = new Timer(this);
    timer.start();
    Scene trainScene = new Scene(root, 1200, 800);
    return trainScene;
  }

  public void updateAllComponents()
  {
    for(Component c : componentList)
    {
      c.display(gc);
    }
  }
}
