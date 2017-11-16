

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to construct our system of tracks, signals, switches
// and stations. It reads the json called "components" in the resource folder
// and checks the validity of the map as it goes. It links the necessary
// components to each other so that they can communicate appropriately.
// When it finishes constructing our components it does nothing.
//***********************************


public class Builder
{
    List<Switch> tlSwitch= new ArrayList<>();;
    List<Switch> trSwitch = new ArrayList<>();
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
            BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("resources/components.json")));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            JSONObject a = (JSONObject) parser.parse(sb.toString());


            JSONArray components = (JSONArray) a.get("components");
            int x = 0;
            int y = 0;

            boolean secondStation = false;
            for (Object c : components)
            {
                JSONObject object1 = (JSONObject) c;
                String type = (String) object1.get("type");
                if (type.contains("switch"))
                {
                    if (type.contains("bl"))
                    {
                        BLSwitch component = new BLSwitch(x, y, lastComponent);

                        component.setPartner(tlSwitch.get(0));
                        tlSwitch.get(0).setPartner(component);
                        tlSwitch.remove(0);

                        componentList.add(component);
                        lastComponent.setRightComponent(component);
                        lastComponent = component;
                    } else if (type.contains("br"))
                    {
                        BRSwitch component = new BRSwitch(x, y, lastComponent);

                        component.setPartner(trSwitch.get(0));
                        trSwitch.get(0).setPartner(component);
                        trSwitch.remove(trSwitch.get(0));

                        componentList.add(component);
                        lastComponent.setRightComponent(component);
                        lastComponent = component;
                    } else if (type.contains("tl"))
                    {
                        TLSwitch component = new TLSwitch(x, y, lastComponent);
                        tlSwitch.add(component);

                        componentList.add(component);
                        lastComponent.setRightComponent(component);
                        lastComponent = component;
                    } else if(type.contains("tr"))
                    {
                        TRSwitch component = new TRSwitch(x, y, lastComponent);
                        trSwitch.add(component);

                        componentList.add(component);
                        lastComponent.setRightComponent(component);
                        lastComponent = component;
                    }
                } else if (type.contains("track"))
                {
                    Track component = new Track(x, y, lastComponent);
                    componentList.add(component);
                    lastComponent.setRightComponent(component);
                    lastComponent = component;
                } else if (type.contains("signal"))
                {

                    Signal component = new Signal(x, y, lastComponent);
                    componentList.add(component);
                    lastComponent.setRightComponent(component);
                    lastComponent = component;

                } else if (type.contains("station"))
                {
                    String stationName = (String) object1.get("station");
                    Station component = new Station(x, y, stationName);
                    componentList.add(component);
                    component.setLeftComponent(lastComponent);
                    if (lastComponent != null) lastComponent.setRightComponent(component);
                    lastComponent = component;
                    if (secondStation)
                    {
                        y++;
                        x=-1;
                    }
                    secondStation = !secondStation;
                }
                else
                {
                    System.out.println("Not a valid component");
                    System.exit(1);
                }

                x++;
            }
            for(Component c : componentList)
            {
                if(c instanceof Switch)
                {
                    Switch aSwitch = (Switch) c;
                    System.out.println("This:" +aSwitch.getTrackX() + "" + aSwitch.getTrackY());
                    System.out.println(aSwitch.getPartnerComponent() == null);
                    System.out.println("Partner:" + aSwitch.getPartnerComponent().getTrackX() + "" + aSwitch.getPartnerComponent().getTrackY());
                    if((aSwitch.getPartnerComponent() == null)
                            ||(aSwitch.getPartnerComponent().getTrackY() != aSwitch.getTrackY()+1 && aSwitch.getPartnerComponent().getTrackY()+1 != aSwitch.getTrackY())
                            ||(aSwitch.getPartnerComponent().getTrackX() != aSwitch.getTrackX()))
                    {
                        System.out.println("ERROR: Mismatched switches.");
                        System.exit(1);
                    }
                }
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex);
        } catch (IOException ex2)
        {
            System.out.println(ex2);
        } catch (ParseException ex3)
        {
            System.out.println(ex3);
        }

    }
}