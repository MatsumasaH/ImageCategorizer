
// Necessary Library
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.Random;

public class ImageViewer {

	public static void main(String[] args) {
		// Initiate Main Window
		JFrame frame = new ImageViewerFrame();

		// Set Window Title
		frame.setTitle("イメージビュワー");

		// Property
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Property
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

	JMenuItem open; // Button
	JMenuItem exit; // Button

	JMenuItem next;// Next
	JMenuItem back;// Back
	JMenuItem random;// Back
	
	String filename;
	String currentdirectry;
	String absolutepath;

	// Constructor
	public ImageViewerFrame() {

		// Window Size
		setSize(500, 1000);

		// Use a label to display the image
		label = new JLabel();
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
	}

}