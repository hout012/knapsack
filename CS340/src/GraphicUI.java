import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class GraphicUI {
	int W = 0;
	int n =0;
	public GraphicUI() {
		
		BinaryKnapSack test = new BinaryKnapSack();
		JFrame win = new JFrame();
		JTabbedPane t1 = new JTabbedPane();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p2.setBackground(Color.black);
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem imp = new JMenuItem("Import");
		JMenuItem exp = new JMenuItem("Export");
		menu.add(imp);menu.add(exp);bar.add(menu);
		win.setJMenuBar(bar);
		JButton add = new JButton("Add Item");
		JLabel maxWeight = new JLabel("Maximum Weight");
		JLabel knap = new JLabel("Knapsack solution");
		JTextField w = new JTextField(5); 
		w.setText("0");
		JButton dks = new JButton("Start");
		win.setLayout(new BoxLayout(win.getContentPane(),BoxLayout.X_AXIS));
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		p2.setPreferredSize(new Dimension(400,100));
		p2.setBackground(Color.white);
		t1.setPreferredSize(new Dimension(200,100));
		p1.add(add);p1.add(maxWeight);p1.add(w);p1.add(knap);p1.add(dks);
		t1.add(p1,"Items");
		win.add(p2);win.add(t1);
		win.setBounds(50, 50, 600, 450);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  JTextField name = new JTextField(10);
				  JTextField weight = new JTextField(5);
			      JTextField price =  new JTextField(5);
			      JPanel myPanel = new JPanel();
			      myPanel.setLayout(new BoxLayout(myPanel,BoxLayout.PAGE_AXIS));
			      myPanel.add(new JLabel("Name"));
			      myPanel.add(name);
			      myPanel.add(new JLabel("Weight"));
			      myPanel.add(weight);
			      myPanel.add(new JLabel("Price"));
			      myPanel.add(price);
			      
			      int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Item infos", JOptionPane.OK_CANCEL_OPTION);
			      if(result == 0 ) {
			    	  n++;
			    	  test.createItem(name.getText(), Integer.parseInt(weight.getText()),  Integer.parseInt(price.getText()));
			    	  String str = "Name: "+name.getText()+", Price ($): "+price.getText()+", Weight (kg): "+weight.getText();
			    	  JLabel n = new JLabel(str);
			    	  p2.add(n);
			    	  win.revalidate();
			    	
			      }
				
				
				
			}
		});
		
		dks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				W = Integer.parseInt(w.getText());
				test.knapSackDp(W,n);
				
				p2.removeAll();
				for (Integer num : test.getResult()) { 		      
					String str = "Name: "+test.getNameList().get(num)+", Price ($): "+
							test.getPriceList().get(num)+", Weight (kg): "+test.getWeightList().get(num);
					JLabel n = new JLabel(str);
					n.setForeground(Color.green);
					p2.add(n);		
			      }
				p2.revalidate();
				
				
			}
		});
		
		imp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
				jc.setFileFilter(filter);
				int i = jc.showOpenDialog(jc);
			}
		});
	}
	public static void main(String args[]) {
		GraphicUI test = new GraphicUI();
	}
	
	

}
