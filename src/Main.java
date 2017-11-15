import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class Main extends Application
{
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    Builder builder = new Builder();
    List<Component> componentList = builder.getComponentList();
    GUI gui = new GUI(primaryStage, componentList);
  }

  public static void main(String[]args)
  {
    launch(args);
  }
}