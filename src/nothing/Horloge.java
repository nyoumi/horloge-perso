package nothing;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

public class Horloge
  implements Observable
{
  private String hour = "";
  private String date = "";
  private ArrayList<Observateur> listObservateur = new ArrayList();

  public void run()
  {
    while (true)
    {
      Calendar cal = Calendar.getInstance(Locale.FRANCE);
      this.hour = new StringBuilder().append(cal.get(11)).append(" : ").
    		  append(cal.get(12) < 10 ? new StringBuilder().append("0").
    				  append(cal.get(12)).toString() : Integer.valueOf(cal.get(12))).
    				  append(" : ").append(cal.get(13) < 10 ? new StringBuilder()
    				  .append("0").append(cal.get(13)).toString() : Integer.valueOf(cal.get(13))).toString();
      DateFormat localDateFormat = DateFormat.getDateInstance(1, Locale.FRANCE);
      this.date = localDateFormat.format(cal.getTime());
      updateObservateur();
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }

  public void addObservateur(Observateur paramObservateur)
  {
    this.listObservateur.add(paramObservateur);
  }

  public void delObservateur()
  {
    this.listObservateur = new ArrayList();
  }

  public void updateObservateur()
  {
    Iterator localIterator = this.listObservateur.iterator();
    while (localIterator.hasNext())
    {
      Observateur localObservateur = (Observateur)localIterator.next();
      localObservateur.update(this.hour, this.date);
    }
  }
}
