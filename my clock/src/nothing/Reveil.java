package nothing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
public class Reveil extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private String heure;
	private String min;
	protected boolean ReveilActif;
	protected static String HeureReveil;
	public Reveil(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		setTitle("reveil");
		this.setSize(241, 118);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponent(){
		//Icône
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		//Le nom
		
												JPanel content = new JPanel();
												content.setBackground(Color.white);
												
												JLabel lblHeure = new JLabel("heure du r\u00E9veil : ");
												content.add(lblHeure);
												
												comboBox = new JComboBox();
												comboBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01",
														"02", "03", "04", "05", "06", "07", "08", "09", "10", "11", 
														"12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
														"22", "23"}));
												content.add(comboBox);

												final JComboBox comboBox_1 = new JComboBox();
												//possibilité de faire une boucle
												comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"00", 
														"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
														"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
														"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
														"32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", 
														"43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", 
														"54", "55", "56", "57", "58", "59"}));
												content.add(comboBox_1);
												
												final JCheckBox chckbxActiver = new JCheckBox("Activer");
												
												JPanel control = new JPanel();
												JButton okBouton = new JButton("OK");
												okBouton.addActionListener(new ActionListener(){
													public void actionPerformed(ActionEvent arg0) {
														if(chckbxActiver.isSelected()){
																
																heure=(String)comboBox.getSelectedItem();
																min=(String)comboBox_1.getSelectedItem();
														
																HeureReveil = heure.concat(" : "+min);
																ReveilActif=chckbxActiver.isSelected();
																
																
																DDD.heureReveil=Reveil.HeureReveil;
																DDD.ReveilActif=ReveilActif;
																DDD.reveilCheckBox.setState(ReveilActif);
																
																
																Fichier.ecrireFichierConfiguration("reveilActif = \""+String.valueOf(ReveilActif)+"\"", "reveilActif");

																Fichier.ecrireFichierConfiguration("heureReveil = \""+HeureReveil+"\"", "heureReveil");
																
																setVisible(false);
													}
														}
													
												});
												JButton cancelBouton = new JButton("Annuler");
												cancelBouton.addActionListener(new ActionListener(){
													public void actionPerformed(ActionEvent arg0) {
														setVisible(false);
													}
												});
												control.add(okBouton);
												control.add(cancelBouton);
												this.getContentPane().add(panIcon, BorderLayout.WEST);
												this.getContentPane().add(content, BorderLayout.CENTER);
												
												
												chckbxActiver.setSelected(true);
												chckbxActiver.setBackground(Color.WHITE);
												content.add(chckbxActiver);
												
												this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}