import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Math.*;
import java.text.SimpleDateFormat;

public class HorlogeDateApp
{
	private JFrame fenetre;
	private GregorianCalendar calend;
	private int h,min,sec,xc,yc;
	private HorlogeDatePanel HorlogeDatePanel;
	private HorlogeDateThread HorlogeDateThread;
	private Font font;
	
	//constructeur 
	HorlogeDateApp()
	{
		fenetre=new JFrame();
		
		fenetre.addWindowListener( new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		calend=new GregorianCalendar();
		h=calend.get(GregorianCalendar.HOUR_OF_DAY);
		min=calend.get(GregorianCalendar.MINUTE);
		sec=calend.get(GregorianCalendar.SECOND);
		
		
		HorlogeDatePanel=new HorlogeDatePanel();
		fenetre.getContentPane().add(HorlogeDatePanel);
		fenetre.setSize(400,400);
		fenetre.show();
		HorlogeDateThread=new HorlogeDateThread(this);
		HorlogeDateThread.start();
		
	}/* Fin constructeur*/
	
	class HorlogeDatePanel extends JPanel
	{
		public void paintComponent (Graphics g)
		{
			super.paintComponent(g);
			//repère 
			 xc=getWidth()/2;
			 yc=getHeight()/2;
			 //rayon avec les repères 
		     int rayon=Math.min(xc,yc)*80/100;
		     
		     font= new Font("Calibri",0,15);
		   
		     g.setFont(font);
		    
			
			for(int i=1;i<=12;i++)
			{
			     double angle=i*Math.PI/6.0-Math.PI/2.0;
			     double x=xc+rayon*Math.cos(angle);
			     double y=yc+rayon*Math.sin(angle);
			      g.drawString("."+i,(int)x,(int)y);
			}
			
			 
			// affichage de la date 
			SimpleDateFormat formater = null;
		    Date aujourdhui = new Date();
		    formater = new SimpleDateFormat("EEEE, d MMM yyyy");

		    g.setFont(font);
		    g.setColor(Color.red);
		    g.drawString(formater.format(aujourdhui), 150, 200);
		    
		    
			// aiguilles des secondes
			double anglesec=(sec*((Math.PI)/30.0)-(Math.PI/2.0));
			int xsf=xc+(int)(0.7*rayon*Math.cos(anglesec));
			int ysf=yc+(int)(0.7*rayon*Math.sin(anglesec));
			g.setColor(Color.black);
			g.drawLine(xc,yc,xsf,ysf);
				
			//aiguilles des minutes 
			double anglemin=(min*((Math.PI)/30.0)-(Math.PI/2.0));
			int xmf=xc+(int)(0.6*rayon*Math.cos(anglemin));
			int ymf=yc+(int)(0.6*rayon*Math.sin(anglemin));
			g.setColor(Color.black);
			g.drawLine(xc,yc,xmf,ymf);
			
			//aiguilles des heures 
			double angleheure=(h*((2*Math.PI)/12.0)-(Math.PI/2.0));
			int xhf=xc+(int)(0.4*rayon*Math.cos(angleheure));
			int yhf=yc+(int)(0.4*rayon*Math.sin(angleheure));
			g.setColor(Color.black);
			g.drawLine(xc,yc,xhf,yhf);
				
		
		}/* fin de paintComponent */ 
	
	}/* fin de classe HorlogeDatePanel */
	
	public void increment()
	{
		sec=sec+1;
		if(sec>60)
		{
			min=min+1;
			sec=1;
			if(min>60)
			{
				min=1;
				h=h+1;
				if(h>12)
				{
					h=1;
				}
			}
		}
		HorlogeDatePanel.repaint();
	}
	
	
}/* fin de classe HorlogeDateApp*/

