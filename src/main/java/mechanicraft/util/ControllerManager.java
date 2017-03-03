package mechanicraft.util;

public class ControllerManager
{
	private GList<Controllable> controllers;
	private GList<Controllable> ticked;
	
	public ControllerManager()
	{
		controllers = new GList<Controllable>();
		ticked = new GList<Controllable>();
	}
	
	public void register(Controllable c)
	{
		controllers.add(c);
		
		if(c.getClass().isAnnotationPresent(Ticked.class))
		{
			ticked.add(c);
		}
	}
	
	public void start()
	{
		for(Controllable i : controllers)
		{
			try
			{
				((Controller) i).start();
			}
			
			catch(Exception e)
			{
				
			}
		}
		
		new Task(0)
		{
			@Override
			public void run()
			{
				tick();
			}
		};
	}
	
	public void stop()
	{
		for(Controllable i : controllers)
		{
			try
			{
				((Controller) i).stop();
			}
			
			catch(Exception e)
			{
				
			}
		}
	}
	
	public void tick()
	{
		for(Controllable i : ticked)
		{
			try
			{
				((Controller) i).tick();
			}
			
			catch(Exception e)
			{
				
			}
		}
	}
}
