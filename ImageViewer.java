
// Necessary Library
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.*;

public class ImageViewer {

	public static void main(String[] args) {
		// Initiate Main Window
		JFrame frame = new ImageViewerFrame();

		// Set Window Title
		frame.setTitle("Image Categorizer");

		// Property
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setBounds(200, 200, 100, 80);

		frame.setLocationRelativeTo(null);

		// Make it Visible
		frame.setVisible(true);
	}
}

// No Idea
@SuppressWarnings("serial")

// A frame with a label to show an image
class ImageViewerFrame extends JFrame{

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
	JMenuItem csv;// Back

	JButton button;

	String filename;
	String currentdirectry;
	String absolutepath;

	JSplitPane splitpane;

	DefaultListModel model;
	
	JButton btn1;
	JButton btn2;
	JButton btn3;
	
	int flag = 0;
	int total = 0;
	// Constructor
	public ImageViewerFrame() {

		currentdirectry = "C:\\\\Program Files (x86)\\\\ImageGeter\\\\Data\\\\Main";

		// Set Icon of the application
		// ImageIcon icon = new ImageIcon("./icon.png");
		// setIconImage(icon.getImage());

		// Window Size
		setSize(1000, 1000);

		// Use a label to display the image
		label = new JLabel(null, null, JLabel.CENTER);
		add(label);
		// label.setForeground(Color.black);
		// label.setOpaque(true);
		// label.setPreferredSize(new Dimension(50, 50));
		// label.setHorizontalAlignment(JLabel.CENTER);
		// label.setVerticalAlignment(JLabel.CENTER);

		// Set up the file chooser
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(currentdirectry));

		// set up the menubar
		menubar = new JMenuBar();
		setJMenuBar(menubar);

		menu = new JMenu("ファイル");
		menubar.add(menu);

		open = new JMenuItem("開く");
		menu.add(open);

		menu2 = new JMenu("アクション");
		menubar.add(menu2);

		// next = new JMenuItem("次へ");
		// menu2.add(next);
		// back = new JMenuItem("戻る");
		// menu2.add(back);

		random = new JMenuItem("ランダム");
		menu2.add(random);

		csv = new JMenuItem("CSV書き込み");
		menu2.add(csv);

		// debug = new JMenuItem("デバック");
		// menu2.add(debug);
		// menu3 = new JMenu("デバッグ");
		// menubar.add(menu3);
		// button = new JButton("デバッグ");
		// add(button,BorderLayout.NORTH);

		JPanel p = new JPanel();
		btn1 = new JButton("Not Valid");
		btn2 = new JButton("Back");
		btn3 = new JButton("Random");
		p.add(btn1);
		p.add(btn2);
		p.add(btn3);

		add(p, BorderLayout.SOUTH);
		p.setBackground(Color.GRAY);

		JPanel pl = new JPanel();
		JButton btn11 = new JButton("NA");
		// JButton btn12 = new JButton("South");

		JPanel pr = new JPanel();
		// JButton btn13 = new JButton("West");
		// JButton btn14 = new JButton("East");

		pl.add(btn11, BorderLayout.WEST);
		// pl.add(btn12,BorderLayout.WEST);
		// pr.add(btn13,BorderLayout.WEST);
		// pr.setBorder(new BevelBorder(BevelBorder.RAISED));
		pr.add(new JLabel("History"));

		add(pl, BorderLayout.WEST);
		pl.setBackground(Color.ORANGE);
		// pl.setPreferredSize(new Dimension(50, 50));

		add(pr, BorderLayout.EAST);
		pr.setBackground(Color.ORANGE);

		model = new DefaultListModel();
		/* 初期データをモデルに追加する */
		// StringBuffer sb;
		// for (int i = 0 ; i < 15 ; i++){
		// sb = new StringBuffer();
		// sb.append("項目");
		// sb.append(i);
		// model.addElement(new String(sb));
		// }
		JList list = new JList(model);
		JScrollPane sp = new JScrollPane();
		sp.getViewport().setView(list);
		sp.setPreferredSize(new Dimension(200, 400));
		pr.setPreferredSize(new Dimension(210, 200));
		pr.add(sp);

		// JLabel label2 = new JLabel("入場者数");
		// JTextField text = new JTextField(5);
		// pr.add(label2);
		// pr.add(text);
		// pr.setLayout(new BorderLayout());
		// pr.setLayout(new FlowLayout());
		// pr.setPreferredSize(new Dimension(200, 100));
		// splitpane = new JSplitPane();
		// splitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		// add(splitpane);
		// JTree tree = new JTree();
		// JScrollPane scrollPane = new JScrollPane();
		// scrollPane.getViewport().setView(tree);
		// scrollPane.setPreferredSize(new Dimension(180, 120));
		// pl.add(scrollPane);
		// pl.setPreferredSize(new Dimension(500, 0));
		// pl.add(new JFileChooser());

		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// show file chooser dialog
				int result = chooser.showOpenDialog(null);

				// if file selected, set it as icon of label
				if (result == JFileChooser.APPROVE_OPTION) {
					String name = chooser.getSelectedFile().getPath();
					label.setIcon(new ImageIcon(name));

					currentdirectry = new File(name).getParent();
					filename = new File(name).getName();
					absolutepath = name;
				}
			}
		});

		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Just Copy
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				nextImage(0);
			}
		});
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				nextImage(1);
			}
		});
		csv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
	}
	public void nextImage(int flag) {
		File dir = new File(currentdirectry);
		File[] list = dir.listFiles();
		// System.out.println(list.length);
		int rand = (new Random()).nextInt(list.length - 0 + 1) + 0;
		String selected = list[rand].getPath();
		label.setIcon(new ImageIcon(selected));

		currentdirectry = new File(selected).getParent();
		filename = new File(selected).getName();
		absolutepath = selected;

		model.add(0, filename);
		
		
		int width = 0;
		int height = 0;
		try {
			BufferedImage bimg = ImageIO.read(new File(selected));
			width          = bimg.getWidth();
			height         = bimg.getHeight();
		}catch(Exception e) {
			
		}
		//System.out.println(width);
		//System.out.println(height);
		if(flag == 1) {
			System.out.println(++total);
		}
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		try {
			File csv = new File("C:\\Users\\Hijiri\\Desktop\\test.csv"); // CSVデータファイル
			// 追記モード
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			// 新たなデータ行の追加
			bw.write(filename + "," + width + "," + height + "," + flag);
			bw.newLine();
			bw.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedWriterオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////
		//System.out.println(Arrays.toString(model.toArray()));
		// System.out.println(list2.length);
		// System.out.println(list2[0].getPath());
	}
}