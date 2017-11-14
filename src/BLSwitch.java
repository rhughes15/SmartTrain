public class BLSwitch extends Switch
{
  public BLSwitch(int id, int trackX, int trackY, Component leftNeighbor)
  {
    super(id, trackX, trackY, leftNeighbor);
    canGoFromLeft = new boolean[]{false, true};
    canGoFromRight = new boolean[]{true, true};
    canGoFromSwitch = new boolean[]{true, false};
  }


}
