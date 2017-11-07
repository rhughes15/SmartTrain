

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Builder
{
    Component lastComponent;
    List<Component> componentList = new ArrayList<Component>();

    public List<Component> getComponentList()
    {
        return componentList;
    }

    public void buildTracksFromJSON()
    {

        JSONParser parser = new JSONParser();
       try
       {
           JSONObject a = (JSONObject) parser.parse(new FileReader("src/resources/components.json"));

               JSONArray components = (JSONArray) a.get("components");

               for (Object c : components)
               {
                   JSONObject object1 = (JSONObject) c;
                   int id = ((Long)(object1.get("id"))).intValue();
                   String type = (String) object1.get("type");
                   int x = ((Long)object1.get("x")).intValue();
                   int y = ((Long) object1.get("y")).intValue();
                   int length = ((Long) object1.get("length")).intValue();
                   System.out.println("id: " + id + ", x:" + x + ", y:" + y + ", type:" + type);
                   if(type.contains("switch"))
                   {
                       int partnerID = ((Long) object1.get("partner")).intValue();
                       if(type.contains("bl"))
                       {
                           BLSwitch component = new BLSwitch(id, x,y,0,0,partnerID, lastComponent);
                           componentList.add(component);
                           lastComponent = component;
                       }
                       else if(type.contains("br"))
                       {
                           BRSwitch component = new BRSwitch(id, x,y,0,0,partnerID, lastComponent);
                           componentList.add(component);
                           lastComponent = component;
                       }
                       else if(type.contains("tl"))
                       {
                           TLSwitch component = new TLSwitch(id, x,y,0,0,partnerID, lastComponent);
                           componentList.add(component);
                           lastComponent = component;
                       }
                       else
                       {
                           TRSwitch component = new TRSwitch(id, x,y,0,0,partnerID, lastComponent);
                           componentList.add(component);
                           lastComponent = component;
                       }
                   }
                   else if (type.contains("track"))
                   {
                       Track component = new Track(id, length, x,y,0,0, lastComponent);
                       componentList.add(component);
                       lastComponent = component;
                   }
                   else if (type.contains("signal"))
                   {
                       Signal component = new Signal(id, x,y,0,0,lastComponent);
                       componentList.add(component);
                       lastComponent = component;

                   }
                   else if (type.contains("station"))
                   {

                       Station component = new Station(id, x, y, 0, 0);
                       componentList.add(component);
                       lastComponent = component;
                   }
                   else System.out.println("Not a valid component");



           }
       }
       catch(FileNotFoundException ex)
       {
           System.out.println(ex);
       }
       catch (IOException ex2)
       {
           System.out.println(ex2);
       }
       catch (ParseException ex3)
       {
           System.out.println(ex3);
       }

    }
}
