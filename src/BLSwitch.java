public class BLSwitch extends Switch
{
  public BLSwitch(int id, int trackX, int trackY, int guiX, int guiY, int partnerId, Component leftNeighbor)
  {
    super(id, trackX, trackY, guiX, guiY, partnerId, leftNeighbor);
    canGoFromLeft = new boolean[]{false, true};
    canGoFromRight = new boolean[]{true, true};
    canGoFromSwitch = new boolean[]{true, false};
  }


}
