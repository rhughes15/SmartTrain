public class BLSwitch extends Switch
{
  public BLSwitch(int trackX, int trackY, int guiX, int guiY,
                  Component leftComponent, Component rightComponent, Component switchComponent)
  {
    super(trackX, trackY, guiX, guiY, leftComponent, rightComponent, switchComponent);
    canGoFromLeft = new boolean[]{false, true};
    canGoFromRight = new boolean[]{true, true};
    canGoFromSwitch = new boolean[]{true, false};
  }


}
