# SmartTrain
Lab #3 in CS351, Smart Train is a solution to the challenge to create a concurrent program with visualization to simulate a network of "smart" train tracks

## Running the Simulation

To run the simulation, click a start and ending train station. The train will then run a route from the first station to the second and back to the first.

## Editing the Config File

The configuration file is in JSON format. In order to add a component, you must add an element to the JSON file in the format:

```
{"type": (desired component type)}
```
In order to do this successfully, you must make sure that each track has the same number of components. Each full train rail begins and ends with a station.

### Possible Components
* Station
* Track
* Signal
* Switches(tlswitch, trswitch, blswitch, brswitch)

### Adding a Switch
In order to add a Switch, you must add a top and bottom section of each switch. These switches must be placed directly above each other.If you need a switch slanted left for example, you must have a tlswitch placed right above a blswitch.

### Adding a Station
In order to add a station, you must use the following format:
```
{"type": "station","station": "A"},
```
Each left station must be named an uppercase letter from A to R. Each right Station must be named an uppercase letter from S to Z.
