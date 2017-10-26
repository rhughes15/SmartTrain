public class TLSwitch extends Switch
{
  public TLSwitch(int trackX, int trackY, int guiX, int guiY,
                  Component leftComponent, Component rightComponent, Component switchComponent)
  {
    super(trackX, trackY, guiX, guiY, leftComponent, rightComponent, switchComponent);
    canGoFromLeft = new boolean[]{true, false};
    canGoFromRight = new boolean[]{true, true};
    canGoFromSwitch = new boolean[]{true, false};
  }
}
