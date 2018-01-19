package nothing;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DDD extends JDialog
{
  public static DDD fen = null;
  private static JLabel label = new JLabel();
  private static JLabel label1 = new JLabel();
  private static Horloge horloge;
  private static MenuItem menuCacher;
  private static MenuItem rdbtnmntmAfficher;
  private static PopupMenu popupMenu = new PopupMenu();
  private static MenuItem rdbtnmntmAquitter;
  private static MenuItem rdbtnmntmpropos;
  static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
  static int resolution = Toolkit.getDefaultToolkit().getScreenResolution();
  static int x2 = dim.width - 165;
  static int y2 = dim.height - 78;
  static int posx = 0;
  static int posy = 0;
  static boolean changex;
  static boolean changey = true;
  private static int color1 = 240;
  private static int color2 = 240;
  private static int color3 = 240;
  private static int color5 = 10;
  private static int color4 = 15;
  private static int color6 = 20;
  public final static JCheckBoxMenuItem reveilCheckBox = new JCheckBoxMenuItem("Activer");

  protected static boolean playsound=Boolean.valueOf(Fichier.lireFichierConfiguration("playsound"));
  public static String heureReveil;
  public static boolean ReveilActif;
private static TrayIcon tray;

  public DDD()
  {
    getContentPane().setBackground(new Color(color1, color2, color3));
    JPopupMenu localJPopupMenu = new JPopupMenu();
    addPopup(getContentPane(), localJPopupMenu);
    JMenuItem localJMenuItem1 = new JMenuItem("cacher");
    localJMenuItem1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        fen.setVisible(false);
      }
    });
    
    JMenuItem mntmReveil = new JMenuItem("Reveil");
    mntmReveil.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent arg0) {
    			Reveil reveil = new Reveil(null, "reveil", true);
    			reveil.setVisible(true);
    			
    			}
    			});	
    
    final JCheckBoxMenuItem connexionCheckBox = new JCheckBoxMenuItem("sonnerie horaire");
    connexionCheckBox.setToolTipText("chanter à chaque heure");
    localJPopupMenu.add(connexionCheckBox);
    connexionCheckBox.setState( new Boolean(Fichier.lireFichierConfiguration("playsound")));
    
    connexionCheckBox.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e) {
        	
          playsound = connexionCheckBox.isSelected();
          Fichier.ecrireFichierConfiguration("playsound = \""+playsound+"\"","playsound");
          
        }
      });
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      SwingUtilities.updateComponentTreeUI(reveilCheckBox);
    }
    catch (InstantiationException|ClassNotFoundException|IllegalAccessException|UnsupportedLookAndFeelException localInstantiationException)
    {
    }
    reveilCheckBox.setToolTipText("activer le reveil");
    reveilCheckBox.setState( new Boolean(Fichier.lireFichierConfiguration("reveilActif")));
    
    reveilCheckBox.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e) {

          ReveilActif = reveilCheckBox.isSelected();
          Fichier.ecrireFichierConfiguration("reveilActif = \""+String.valueOf(ReveilActif)+"\"", "reveilActif");
        }
      });
    localJPopupMenu.add(reveilCheckBox);
    localJPopupMenu.add(mntmReveil);
    localJPopupMenu.add(localJMenuItem1);
    JMenuItem localJMenuItem2 = new JMenuItem("A  propos");
    localJMenuItem2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
    	  try
          {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           // SwingUtilities.updateComponentTreeUI(localJOptionPane);
          }
          catch (InstantiationException|ClassNotFoundException|IllegalAccessException|UnsupportedLookAndFeelException localInstantiationException)
          {
          }
       
        JOptionPane.showMessageDialog(null, "myClock version 1.1  " +
        		"\n(c) Copyright Digital Dreams corporation 2013-2014.  tous droits reservés.", "A propos", 1);
      }
    });
    localJMenuItem2.setIcon(new ImageIcon(DDD.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
    localJPopupMenu.add(localJMenuItem2);
    JMenuItem localJMenuItem3 = new JMenuItem("quitter");
    localJMenuItem3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        System.exit(0);
      }
    });
    localJMenuItem3.setIcon(new ImageIcon(DDD.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
    localJPopupMenu.add(localJMenuItem3);
    setDefaultCloseOperation(2);
    setResizable(false);
    setSize(164, 48);
    setUndecorated(true);
    setAlwaysOnTop(true);
    setVisible(true);
    setAlwaysOnTop(true);
    
  }

  public static void lireReveil() {
	  ReveilActif=Boolean.valueOf(Fichier.lireFichierConfiguration("reveilActif"));
	  heureReveil=Fichier.lireFichierConfiguration("heureReveil");
	
}
  public static void change(boolean changeX, boolean changeY)
  {
    dim = Toolkit.getDefaultToolkit().getScreenSize();
    x2 = dim.width - 164;
    y2 = dim.height - 87;
    if (changeX)
    {
      posx = posx == 0 ? x2 : 0;
      color3 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color2 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color1 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color4 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color5 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color6 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      changex = !changeX;
    }
    else
    {
      color3 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color2 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color1 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color4 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color5 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color6 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      changex = !changeX;
    }
    if (changeY)
    {
      posy = posy == 0 ? y2 : 0;
      color3 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color2 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color1 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color4 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color5 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color6 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      changey = !changeY;
    }
    else
    {
      color3 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color2 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color1 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color4 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color5 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      color6 = (int)(Math.random() * 255.0D) * (int)(Math.random() * 255.0D) % 255;
      changey = !changeY;
    }
  }
  
  public static void changeLowOpacity() throws InterruptedException{
	  while (fen.getOpacity()>0.1F) {
		  Thread.sleep(100L);
	      fen.setOpacity(fen.getOpacity()-0.1F);
		  
	}
	  
  }
  public static void changeHighOpacity() throws InterruptedException{
	  while (fen.getOpacity()<0.9F) {
		  Thread.sleep(100L);
	      fen.setOpacity(fen.getOpacity()+0.1F);
		  
	}
	  
  }

  public static void initialise()
  {
	  
    try
    {
    	if(SystemTray.isSupported()){
    		Image localBufferedImage =new ImageIcon(DDD.class.getResource("/fun/flag1.png")).getImage();
    	      
    		tray = new TrayIcon(localBufferedImage, "MyClock 1.2",popupMenu);
    		SystemTray.getSystemTray().add(tray);

    	}
      }
    catch (AWTException localAWTException)
    {
    }
    
    fen = new DDD();
    horloge = new Horloge();
    horloge.addObservateur(new Observateur()
    {
      public void update(String paramAnonymousString1, String paramAnonymousString2)
      {
    	 if(paramAnonymousString1.length()<12) paramAnonymousString1="0"+paramAnonymousString1;

    	 
          DDD.label.setText(paramAnonymousString1);
          
    	  if(paramAnonymousString1.substring(0,8).contains(heureReveil) && ReveilActif){
      		tray.displayMessage("MyClock 1.1 reveil","Il est "+ heureReveil, MessageType.INFO);

    		  Son.jouerSon("/fun/birdtime.wav");
              Son.jouerSon("/fun/birdtime.wav"); 
              Son.jouerSon("/fun/birdtime.wav");               
              Son.jouerSon("/fun/birdtime.wav"); 
              Son.jouerSon("/fun/birdtime.wav"); 
              
              ReveilActif=false;
    	  
    	  }
        if((paramAnonymousString1.contains("00 : 00") && playsound)){
      		tray.displayMessage("MyClock 1.1","Il est "+ paramAnonymousString1.substring(0, 8), MessageType.INFO);

            Son.jouerSon("/fun/birdtime.wav"); 
            Son.jouerSon("/fun/birdtime.wav"); 
            Son.jouerSon("/fun/birdtime.wav"); 
            Son.jouerSon("/fun/birdtime.wav"); 
            Son.jouerSon("/fun/birdtime.wav"); 

        }
    

        DDD.label1.setText(paramAnonymousString2);
      }
    });
    label1.setForeground(new Color(color6, color5, color4));
    label.setForeground(label1.getForeground().brighter());
    label.setFont(new Font("Constantia", 2, 35));
    label.setHorizontalAlignment(0);
    label1.setHorizontalAlignment(0);
    fen.getContentPane().add(label, "Center");
    fen.getContentPane().add(label1, "South");
    rdbtnmntmAfficher = new MenuItem("afficher");
    rdbtnmntmAfficher.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DDD.fen.setVisible(true);
      }
    });
    rdbtnmntmAquitter = new MenuItem("quitter");
    rdbtnmntmAquitter.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        System.exit(0);
      }
    });
    rdbtnmntmpropos = new MenuItem("A  propos");
    rdbtnmntmpropos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOptionPane localJOptionPane = new JOptionPane();
        try
        {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          SwingUtilities.updateComponentTreeUI(localJOptionPane);
        }
        catch (InstantiationException|ClassNotFoundException|IllegalAccessException|UnsupportedLookAndFeelException localInstantiationException)
        {
        }
        JOptionPane.showMessageDialog(null, "myClock version 1.1 " +
        		"\n(c) Copyright Digital Dreams corporation 2013-2014. nyoumipaul@yahoo.fr \n  tous droits reservés.", "A propos", 1);
      }
    });
    menuCacher = new MenuItem("cacher");
    menuCacher.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DDD.fen.setVisible(false);
      }
    });
    popupMenu.add(rdbtnmntmAfficher);
    popupMenu.add(menuCacher);
    popupMenu.add(rdbtnmntmAquitter);
    popupMenu.add(rdbtnmntmpropos);
    MenuItem mntmReveil = new MenuItem("Reveil");
    mntmReveil.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent arg0) {
    			Reveil reveil = new Reveil(null, "reveil", true);
    			reveil.setVisible(true);
    			
    			}
    			});
    popupMenu.add(mntmReveil);
    fen.getContentPane().addMouseListener(new MouseAdapter()
    
    
    {
      public void mouseEntered(MouseEvent paramAnonymousMouseEvent)
      {
       
          DDD.fen.setOpacity(0.9F);
          try
          {
        	  
        	changeLowOpacity();
            DDD.change(DDD.changex, DDD.changey);
            
            DDD.label1.setForeground(new Color(DDD.color4, DDD.color5, DDD.color6));
            DDD.label1.setBackground(new Color(DDD.color1, DDD.color2, DDD.color3, 200));
            DDD.label.setForeground(new Color(DDD.color4, DDD.color5, DDD.color6));
            
            DDD.fen.getContentPane().setBackground(new Color(DDD.color1, DDD.color2, DDD.color3, 200));
            
            DDD.fen.setLocation(DDD.posx, DDD.posy);
            changeHighOpacity();
            
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        
      }
    });
    lireReveil();
    horloge.run();
  }

  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    try
    {
    	try
        {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

          SwingUtilities.updateComponentTreeUI(fen);
        }
        catch (InstantiationException|ClassNotFoundException|IllegalAccessException|UnsupportedLookAndFeelException localInstantiationException)
        {
        }
      initialise();
      
    }
    catch (Exception localException)
    {
      initialise();
    }
    finally
    {
      initialise();
    }
  }

  private static void addPopup(Component paramComponent, final JPopupMenu paramJPopupMenu)
  {
    paramComponent.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent paramAnonymousMouseEvent)
      {
        if (paramAnonymousMouseEvent.isPopupTrigger())
          showMenu(paramAnonymousMouseEvent);
      }

      public void mouseReleased(MouseEvent paramAnonymousMouseEvent)
      {
        if (paramAnonymousMouseEvent.isPopupTrigger())
          showMenu(paramAnonymousMouseEvent);
      }

      private void showMenu(MouseEvent paramAnonymousMouseEvent)
      {
    	  paramJPopupMenu.show(paramAnonymousMouseEvent.getComponent(), paramAnonymousMouseEvent.getX(), paramAnonymousMouseEvent.getY());
      }
    });
  }
}
