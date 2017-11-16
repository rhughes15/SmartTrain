

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Builder
{
    Switch tlSwitch, trSwitch, blSwitch, brSwitch;
    Component lastComponent;
    List<Component> componentList = new ArrayList<Component>();
    public Builder()
    {
        buildTracksFromJSON();
    }

    public List<Component> getComponentList()
    {
        return componentList;
    }

    private void buildTracksFromJSON()
    {
        JSONParser parser = new JSONParser();
       try
       {
           BufferedReader br =new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("resources/components.json")));
           StringBuilder sb = new StringBuilder();
           String line;
           while ((line = br.readLine()) != null) {
               sb.append(line);
           }
           JSONObject a = (JSONObject) parser.parse(sb.toString());


           JSONArray components = (JSONArray) a.get("components");

               for (Object c : components)
               {
                   int counter = 0;
                   JSONObject object1 = (JSONObject) c;
                   int id = ((Long)(object1.get("id"))).intValue();
                   String type = (String) object1.get("type");
                   int x = ((Long)object1.get("x")).intValue();
                   int y = ((Long) object1.get("y")).intValue();
                   int length = ((Long) object1.get("length")).intValue();
                   if(type.contains("switch"))
                   {
                       if(type.contains("bl"))
                       {
                           BLSwitch component = new BLSwitch(id, x,y, lastComponent);
                           blSwitch = component;
                           component.setPartner(tlSwitch);
                           tlSwitch.setPartner(component);
                           componentList.add(component);
                           lastComponent.setRightComponent(component);
                           lastComponent = component;
                       }
                       else if(type.contains("br"))
                       {
                           BRSwitch component = new BRSwitch(id, x,y, lastComponent);
                           brSwitch = component;
                           component.setPartner(trSwitch);
                           trSwitch.setPartner(component);
                           componentList.add(component);
                           lastComponent.setRightComponent(component);
                           lastComponent = component;
                       }
                       else if(type.contains("tl"))
                       {
                           TLSwitch component = new TLSwitch(id, x,y, lastComponent);
                           tlSwitch = component;
                           componentList.add(component);
                           lastComponent.setRightComponent(component);
                           lastComponent = component;
                       }
                       else
                       {
                           TRSwitch component = new TRSwitch(id, x,y, lastComponent);
                           trSwitch = component;
                           componentList.add(component);
                           lastComponent.setRightComponent(component);
                           lastComponent = component;
                       }
                   }
                   else if (type.contains("track"))
                   {
                       Track component = new Track(id, length, x,y, lastComponent);
                       componentList.add(component);
                       lastComponent.setRightComponent(component);
                       lastComponent = component;
                   }
                   else if (type.contains("signal"))
                   {

                       Signal component = new Signal(x,y,lastComponent);
                       componentList.add(component);
                       lastComponent.setRightComponent(component);
                       lastComponent = component;

                   }
                   else if (type.contains("station"))
                   {
                       String stationName = (String) object1.get("station");
                       Station component = new Station(x, y, stationName);
                       componentList.add(component);
                       component.setLeftComponent(lastComponent);
                       if(lastComponent != null) lastComponent.setRightComponent(component);
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
