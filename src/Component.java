public interface Component extends Runnable
{
  void acceptMessage(String message);
  void lock();
}
