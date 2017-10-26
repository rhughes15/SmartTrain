public class BRSwitch extends Switch
{
  public BRSwitch(int trackX, int trackY, int guiX, int guiY,
                  Component leftComponent, Component rightComponent, Component switchComponent)
  {
    super(trackX, trackY, guiX, guiY, leftComponent, rightComponent, switchComponent);
    canGoFromLeft = new boolean[]{true, true};
    canGoFromRight = new boolean[]{true, false};
    canGoFromSwitch = new boolean[]{false, true};
  }
}