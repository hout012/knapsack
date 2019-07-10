import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
public class GraphicUI {
	int W = 0;
	int n =0;
	boolean onlyint = true;
	List <Item> items = new ArrayList<Item>();
	String col[] = {"Item Name","Weight","Value"};

	DefaultTableModel tableModel = new DefaultTableModel(col, 0);

	JTable table = new JTable(tableModel);
	public GraphicUI() {
		DPSolver test = new DPSolver();
		
		JFrame win = new JFrame();
		
		JTabbedPane t1 = new JTabbedPane();
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		JPanel p2 =new JPanel(new BorderLayout());
		p2.setBackground(Color.white);
		

		//////Test (remove)

		items.add(new Item("Item1",40,2));
		items.add(new Item("Item2",50,3.14));
		items.add(new Item("Item3",100,1.98));
		items.add(new Item("Item4",95,5));
		items.add(new Item("Item5",30,3));
		
		for (int i = 0;i<items.size();i++) {
			String name = items.get(i).getName();
			int value = items.get(i).getValue();
			double weight = items.get(i).getWeight();
			
			Object[] data = {name,weight,value};
			tableModel.addRow(data);
		}
		p2.add(new JScrollPane(table));
		//////
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem imp = new JMenuItem("Import");
		JMenuItem exp = new JMenuItem("Export");
		
		menu.add(imp);menu.add(exp);bar.add(menu);
		win.setJMenuBar(bar);
		win.setLayout(new BoxLayout(win.getContentPane(),BoxLayout.X_AXIS));
		
		JButton add = new JButton("Add Item");
		JButton remove = new JButton("Remove Item");
		JButton dks = new JButton("Start");
		
		JLabel maxWeight = new JLabel("Maximum Weight");
		JLabel knap = new JLabel("Knapsack solution");
		JTextField w = new JTextField(5); 
		w.setMaximumSize( w.getPreferredSize() );
		w.setText("0");
		
		
		t1.setPreferredSize(new Dimension(200,100));
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p3.add(maxWeight);p3.add(w);
		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		p4.add(add);p4.add(remove);
		p1.add(p4);p1.add(p3);p1.add(knap);p1.add(dks);
		t1.add(p1,"Items");
		
		win.add(p2);win.add(t1);
		win.pack();
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
			      myPanel.add(new JLabel("Item Name"));
			      myPanel.add(name);
			      myPanel.add(new JLabel("Weight (kg)"));
			      myPanel.add(weight);
			      myPanel.add(new JLabel("Value)"));
			      myPanel.add(price);
			      
			      int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Item information", JOptionPane.OK_CANCEL_OPTION);
			      if(result == 0 ) {
			    	  
			    	  String na = name.getText(); 
			    	  int v = Integer.parseInt(price.getText());
			    	  
			    	  //check if int
			    	  if(weight.getText().contains(".")){
			    		  onlyint = false;
			    	  }
			    	  double w =  Double.parseDouble(weight.getText());
			    	  System.out.println(onlyint);
			    
			    	  addData(na,v,w);
			      }
			}
		});
		
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if(e.getColumn() == 0) {
					items.get(e.getFirstRow()).setName((String) tableModel.getValueAt(e.getFirstRow(),0 ));
				}else if (e.getColumn() == 1) {
					items.get(e.getFirstRow()).setWeight( Double.parseDouble((String) tableModel.getValueAt( e.getFirstRow(),1)));
				}else if (e.getColumn() == 2) {
					items.get(e.getFirstRow()).setValue((int) tableModel.getValueAt(e.getFirstRow(),2));
				}
			}
			
		});
		
		dks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
				
			}
		});
		
		imp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
				jc.setFileFilter(filter);
				int i = jc.showOpenDialog(jc);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = jc.getSelectedFile();
					try {
						Scanner s = new Scanner(f);
						s.nextLine();
				        while(s.hasNext()){
				        	String[] str = s.nextLine().split(",");
				        	addData(str[0],Integer.parseInt(str[1]),Double.parseDouble(str[2]));
				        
				        }
				        s.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});

	}
	public void addData(String n,int v,double w) {
		items.add(new Item(n,v,w));
  	  	Object[] data = {n,w,v};
  	  	tableModel.addRow(data);
	}

	public static void main(String args[]) {
		GraphicUI test = new GraphicUI();
	}
	
	

}

