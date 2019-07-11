import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
		
		JFrame win = new JFrame();
		
		JTabbedPane t1 = new JTabbedPane();
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		JPanel p2 =new JPanel(new BorderLayout());
		p2.setBackground(Color.white);
		p2.add(new JScrollPane(table));
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem imp = new JMenuItem("Import");

		
		menu.add(imp);bar.add(menu);
		win.setJMenuBar(bar);
		win.setLayout(new BoxLayout(win.getContentPane(),BoxLayout.X_AXIS));
		
		JButton add = new JButton("Add Item");
		JButton remove = new JButton("Remove Item");
		JButton dks = new JButton("Dynamic Programming Solver");
		JButton bnb = new JButton("Branch and Bound Solver");
		JButton weight = new JButton("Set");
		
		JLabel maxWeight = new JLabel("W: ");
		
		JLabel knap = new JLabel("Knapsack Solver");
		knap.setHorizontalAlignment(SwingConstants.LEFT);
		knap.setAlignmentX(Component.LEFT_ALIGNMENT);
		JTextField w = new JTextField(5); 
		w.setMaximumSize( w.getPreferredSize() );
		w.setText("0");
		
		t1.setPreferredSize(new Dimension(300,450));
		
		JPanel p3 = new JPanel();
		JLabel p3label = new JLabel("Maximum Weight");
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p3.add(maxWeight);p3.add(w);p3.add(weight);
		p3.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel p4 = new JPanel();
		JLabel p4label = new JLabel("Items");
		p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		p4.setAlignmentX(Component.LEFT_ALIGNMENT);
		p4.add(add);p4.add(remove);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
		p5.setAlignmentX(Component.LEFT_ALIGNMENT);
		p5.add(dks);p5.add(bnb);
		
		p1.add(p4label);p1.add(Box.createRigidArea(new Dimension(0, 10)));p1.add(p4);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(p3label);p1.add(Box.createRigidArea(new Dimension(0, 10)));p1.add(p3);
		p1.add(Box.createRigidArea(new Dimension(0, 20)));
		p1.add(knap);p1.add(Box.createRigidArea(new Dimension(0, 10)));p1.add(p5);
		
		t1.add(p1,"Tools");
		
		win.add(p2);win.add(t1);
		win.pack();
		win.setBounds(50, 50, 750, 450);
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
			    	  double w =  Double.parseDouble(weight.getText());
			    	  addData(na,v,w);
			    	  dks.setEnabled(checkforDouble());
			    	  
			    	  
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
					items.get(e.getFirstRow()).setValue(Integer.parseInt((String) tableModel.getValueAt(e.getFirstRow(),2)));
				}
				dks.setEnabled(checkforDouble());
			}
			
		});
		
		dks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DPSolver d = new DPSolver(items);	
				d.setmaxWeight(W);
				d.Solver();
				showSolution(d.getResult());
			}
		});
		
		bnb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BNBSolver d = new BNBSolver();	
				d.setItems(items);
				d.setMaxWeight(W);
				BNBSolver.Node n = d.solve();
				System.out.print(n.taken.size());
				for(int i = 0;i < n.taken.size();i++) {
					System.out.print(n.taken.get(i).getName());
				}
				showSolution(n.taken);
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
				        dks.setEnabled(checkforDouble());
				        s.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        if (table.getSelectedRow() != -1) {
		        	int i = table.getSelectedRow();
		            ((DefaultTableModel) table.getModel()).removeRow(i);
		            items.remove(i);
		            dks.setEnabled(checkforDouble());
		            
		        }
			}
			
		});
		
		weight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				W = Integer.parseInt(w.getText());
			}
			
		});

	}
	public void addData(String n,int v,double w) {
		items.add(new Item(n,v,w));
  	  	Object[] data = {n,w,v};
  	  	tableModel.addRow(data);
	}
	
	public boolean checkforDouble() {
		onlyint = true;
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getWeight()%1 != 0) {
				onlyint = false;
			}
		}
		return onlyint;
	}
	
	public void showSolution(List<Item> items) {
		JMenuBar bar2 = new JMenuBar();
		JMenu menu2 = new JMenu("File");
		JMenuItem exp = new JMenuItem("Export");
		bar2.add(menu2.add(exp));
		JFrame f = new JFrame();
		f.setJMenuBar(bar2);
		DefaultTableModel tableModel2 = new DefaultTableModel(col, 0);
		JTable table2 = new JTable(tableModel2);
		for ( int i =0 ; i < items.size();i++) {
			String name = items.get(i).getName();
			double weight  = items.get(i).getWeight();
			int value = items.get(i).getValue();
			
			Object[] obj = {name,weight,value};
			tableModel2.addRow(obj);
		}
		
		table2.setVisible(true);
		JPanel panel =new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table2));
		f.add(panel);
		f.pack();
		f.setBounds(50, 50, 300, 450);
		f.setLayout(new BoxLayout(f.getContentPane(),BoxLayout.X_AXIS));
		f.setVisible(true);
		
		exp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jc2 = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
				jc2.setFileFilter(filter);
				int i = jc2.showSaveDialog(jc2);
				if (i == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(jc2.getSelectedFile(), true);
						fw.write("Item Name,"+"Weight,"+"Value\n");
						for(int i1 =0 ; i1<items.size();i1++) {
							fw.write(items.get(i1).getName()+","+items.get(i1).getWeight()+","+items.get(i1).getValue()+"\n");
						}
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
	}
	public static void main(String args[]) {
		GraphicUI test = new GraphicUI();
	}
	
	

}

