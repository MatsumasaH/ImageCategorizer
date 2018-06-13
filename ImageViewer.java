
// Necessary Library
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.io.*;
import java.awt.event.*;
import java.util.Random;

import java.awt.*;

public class ImageViewer {

	public static void main(String[] args) {
		// Initiate Main Window
		JFrame frame = new ImageViewerFrame();

		// Set Window Title
		frame.setTitle("イメージビュワー");

		// Property
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//frame.setBounds(200, 200, 100, 80);
		
		frame.setLocationRelativeTo(null);

		// Make it Visible
		frame.setVisible(true);
	}
}

// No Idea
@SuppressWarnings("serial")

// A frame with a label to show an image
class ImageViewerFrame extends JFrame {

	JLabel label; // label
	JFileChooser chooser; // File Exploder
	JMenuBar menubar; // Menu Bar
	JMenu menu; // Menu
	JMenu menu2;// Action
	JMenu menu3;// Action

	JMenuItem open; // Button
	JMenuItem exit; // Button

	JMenuItem next;// Next
	JMenuItem back;// Back
	JMenuItem random;// Back
	JMenuItem debug;// Back
	
	JButton button;
	
	String filename;
	String currentdirectry;
	String absolutepath;

	// Constructor
	public ImageViewerFrame() {
		
		//Set Icon of the application
		ImageIcon icon = new ImageIcon("./icon.png");
	    setIconImage(icon.getImage());

		// Window Size
		setSize(500, 500);

		// Use a label to display the image
		label = new JLabel(null,null,JLabel.CENTER);
		label.setForeground(Color.black);
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(50, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		add(label);

		// Set up the file chooser
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(
				new File("C:\\Users\\Keiichi Matsumoto\\ml\\models\\tutorials\\image\\imagenet\\flower_photos\\daisy"));

		// set up the menubar
		menubar = new JMenuBar();
		setJMenuBar(menubar);

		menu = new JMenu("ファイル");
		menubar.add(menu);

		open = new JMenuItem("開く");
		menu.add(open);

		menu2 = new JMenu("アクション");
		menubar.add(menu2);

		next = new JMenuItem("次へ");
		menu2.add(next);

		back = new JMenuItem("戻る");
		menu2.add(back);
		
		random = new JMenuItem("ランダム");
		menu2.add(random);
		
		debug = new JMenuItem("デバック");
		menu2.add(debug);
		
		menu3 = new JMenu("デバッグ");
		menubar.add(menu3);
		
		button = new JButton("デバッグ");
		add(button,BorderLayout.NORTH);
		
		JPanel p = new JPanel();
		JButton btn1 = new JButton("A");
	    JButton btn2 = new JButton("B");
	    JButton btn3 = new JButton("C");
	    p.add(btn1);
	    p.add(btn2);
	    p.add(btn3);
	    
	    add(p, BorderLayout.SOUTH);
		p.setBackground(Color.GRAY);
	    
	    JPanel pl = new JPanel();
	    JButton btn11 = new JButton("North");
	    JButton btn12 = new JButton("South");
	    
	    JPanel pr = new JPanel();
	    JButton btn13 = new JButton("West");
	    JButton btn14 = new JButton("East");

		pl.add(btn11,BorderLayout.WEST);
		pl.add(btn12,BorderLayout.WEST);
		pr.add(btn13,BorderLayout.WEST);
		pr.add(btn14,BorderLayout.WEST);
		pr.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		add(pl, BorderLayout.WEST);
		pl.setBackground(Color.ORANGE);
		pl.setPreferredSize(new Dimension(50, 50));
		
		add(pr, BorderLayout.EAST);
		pr.setBackground(Color.ORANGE);
		
		JLabel label2 = new JLabel("入場者数");
	    JTextField text = new JTextField(5);
	    pr.add(label2);
	    pr.add(text);
	    //pr.setLayout(new BorderLayout());
	    pr.setLayout(new FlowLayout());
	    pr.setPreferredSize(new Dimension(200, 100));


		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// show file chooser dialog
				int result = chooser.showOpenDialog(null);

				// if file selected, set it as icon of label
				if (result == JFileChooser.APPROVE_OPTION) {
					String name = chooser.getSelectedFile().getPath();
					label.setIcon(new ImageIcon(name));
					
					currentdirectry = new File(name).getParent();
					filename=new File(name).getName();
					absolutepath=name;
				}
			}
		});
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				File dir = new File(currentdirectry);
		        File[] list = dir.listFiles();
		        System.out.println(list.length);
		        int rand = (new Random()).nextInt(list.length - 0 + 1) + 0;
		        String selected = list[rand].getPath();
				label.setIcon(new ImageIcon(selected));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				currentdirectry = "C:\\Users\\Keiichi Matsumoto\\ml\\models\\tutorials\\image\\imagenet\\flower_photos\\daisy";
				File dir = new File(currentdirectry);
		        File[] list = dir.listFiles();
		        System.out.println(list.length);
		        int rand = (new Random()).nextInt(list.length - 0 + 1) + 0;
		        String selected = list[rand].getPath();
				label.setIcon(new ImageIcon(selected));
			}
		});
	}

}