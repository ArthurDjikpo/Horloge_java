public class HorlogeDateThread extends Thread
{
	HorlogeDateThread(HorlogeDateApp HorlogeDate)
	{
		this.HorlogeDate=HorlogeDate;
	}
	public void run()
	{
		while(true)
		{
			HorlogeDate.increment();
			try
			{
				sleep(600);
			}
			catch(InterruptedException e)
			{
			}
				
		}
	}
	private HorlogeDateApp HorlogeDate;

}