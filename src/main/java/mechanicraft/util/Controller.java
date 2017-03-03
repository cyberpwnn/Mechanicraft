package mechanicraft.util;

import mechanicraft.Mechanicraft;

public abstract class Controller implements Controllable
{
	private D d;
	
	public Controller()
	{
		Mechanicraft.getInstance().getControllerManager().register(this);
		d = new D(getName());
	}
	
	public Controller(ControllerManager cm)
	{
		cm.register(this);
	}
	
	@Override
	public String getName()
	{
		return getClass().getSimpleName();
	}
	
	public void start()
	{
		Mechanicraft.getInstance().registerListener(this);
		onStart();
		s("Started " + getName());
	}
	
	public void stop()
	{
		onStop();
		Mechanicraft.getInstance().unRegisterListener(this);
		s("Stopped " + getName());
	}
	
	public void tick()
	{
		onTick();
	}
	
	public void i(String s)
	{
		d.i(s);
	}
	
	public void s(String s)
	{
		d.s(s);
	}
	
	public void f(String s)
	{
		d.f(s);
	}
	
	public void w(String s)
	{
		d.w(s);
	}
	
	public void o(String s)
	{
		d.o(s);
	}
	
	public Mechanicraft mc()
	{
		return Mechanicraft.getInstance();
	}
}
