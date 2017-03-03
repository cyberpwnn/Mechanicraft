package mechanicraft.util;

public abstract class ASYNC
{
	public ASYNC()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				async();
			}
		}.start();
	}
	
	public abstract void async();
}
