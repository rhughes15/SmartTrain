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

import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class GUI
{
  private Stage stage;
  private List<Component> componentList;
  private int length = Reference.length;
  private int y = Reference.y;

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

    Group root = new Group();
    Canvas canvas = new Canvas(1200, 800);
    root.getChildren().add(canvas);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    new AnimationTimer() {
      @Override
      public void handle(long now)
      {
        gc.clearRect(0,0, 1200, 800);
        for (Component c: componentList)
        {
          System.out.println(c.getTrackX());
          gc.setStroke(Color.rgb(0,0,0));
          gc.setLineWidth(5);
          c.display(gc);
        }
      }
    }.start();


    Scene trainScene = new Scene(root, 1200, 800);
    return trainScene;
  }
}
