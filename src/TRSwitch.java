public class TRSwitch extends Switch
{
  public TRSwitch(int trackX, int trackY, int guiX, int guiY, Component leftComponent, Component rightComponent, Component switchComponent) {
    super(trackX, trackY, guiX, guiY, leftComponent, rightComponent, switchComponent);
    canGoFromLeft = new boolean[]{true, true};
    canGoFromRight = new boolean[]{false, true};
    canGoFromSwitch = new boolean[]{true, false};
  }
}