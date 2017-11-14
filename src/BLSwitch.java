public class BLSwitch extends Switch
{
  public BLSwitch(int id, int trackX, int trackY, Component leftNeighbor)
  {
    super(id, trackX, trackY, leftNeighbor);
    canGoFromLeft = new boolean[]{false, true, false};
    canGoFromRight = new boolean[]{true, false, true};
    canGoFromSwitch = new boolean[]{false, true, false};
  }
}
