package humanshield85.cascadeTester.ui;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MainUi {
	static final int MAIN_UI_WIDTH = 600;
	static final int MAIN_UI_HEIGHT = 610;

	protected JFrame mainFrame = new JFrame();
	protected JPanel mainPane = new JPanel();
	protected JPanel effectPane = new JPanel();
	protected JTextField txtFieldBrowse = new JTextField("Click browse and select the images folder");
	protected JButton btnBrowse = new JButton("Browse");
	
	//radio 
	protected JRadioButton radioOriginal = new JRadioButton("No Effects");
	protected JRadioButton radioGrayScale = new JRadioButton("GrayScale");
	protected ButtonGroup groupEffect = new ButtonGroup();
	
	protected JTextField txtFieldImageNumber = new JTextField("00");
	protected JButton btnPrevImage = new JButton("Prev");
	protected JButton btnNextImage = new JButton("Next");
	protected JPanel footerPane = new JPanel();
	
	protected ImagePanel imagePane = new ImagePanel();
	
	protected JPanel cascadePanel = new JPanel();
	protected JButton btnSlectCascade = new JButton("Select cascade  file");
	protected JLabel labCascadeFile = new JLabel("No Files Selected");
	
	// labels 
	protected final JLabel labBrowse = new JLabel("Images path: ");
	
	
	// JMenu 
	MenuBar mainMenu = new MenuBar();
	Menu menuFile = new Menu("File");
	Menu menuEdition = new Menu("Edition");
	Menu menuAbout = new Menu("About");
	MenuItem menuItemOpenFolder = new MenuItem("Open images folder");
	MenuItem menuItemOpenCascadeFile = new MenuItem("Open Cascade file");
	MenuItem menuItemExit = new MenuItem("Exit");

	MenuItem menuItemSettings =  new MenuItem("Settings");
	MenuItem menuItemAboutTheSoftware =  new MenuItem("About this software");




	
	/**
	 * main constructor
	 */
	public MainUi() {
		setUiPropreties();
		mainFrame.setMenuBar(mainMenu);
		mainMenu.add(menuFile);
		mainMenu.add(menuEdition);
		mainMenu.add(menuAbout);
		menuFile.add(menuItemOpenFolder);
		menuFile.add(menuItemOpenCascadeFile);
		menuFile.add(menuItemExit);
		menuEdition.add(menuItemSettings);
		menuAbout.add(menuItemAboutTheSoftware);
	}

	/**
	 * called when constructed just for clear code  
	 */
	private void setUiPropreties() {
		// frame
		mainFrame.setSize(MainUi.MAIN_UI_WIDTH, MainUi.MAIN_UI_HEIGHT);
		mainFrame.setContentPane(mainPane);
		mainFrame.setTitle("OpenCV cascade tester");
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		effectPane.setBounds(10, 50, 285, 80);
		effectPane.setBorder(BorderFactory.createTitledBorder("Effects before detection"));
		groupEffect.add(radioOriginal);
		groupEffect.add(radioGrayScale);
		effectPane.add(radioOriginal);
		radioOriginal.setSelected(true);
		effectPane.add(radioGrayScale);
		
		cascadePanel.setBounds(300, 50, 285, 80);
		cascadePanel.setBorder(BorderFactory.createTitledBorder("Cascade files"));
		cascadePanel.add(labCascadeFile);
		cascadePanel.add(btnSlectCascade);
		
		imagePane.setBounds(10, 140, 580, 370);
		imagePane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		footerPane.setBounds(10, 520, 580, 70);
		txtFieldImageNumber.setSize(50, 60);
		btnPrevImage.setFocusPainted(false);
		btnNextImage.setFocusPainted(false);
		footerPane.add(btnPrevImage);
		footerPane.add(txtFieldImageNumber);
		footerPane.add(btnNextImage);
		
		
		// labs 
		labBrowse.setBounds(70, 10, 80, 30);
		
		// textField
		txtFieldBrowse.setBounds(160, 10, 300, 30);
		txtFieldBrowse.setEditable(false);
		
		
		// button
		btnBrowse.setBounds(460, 10, 80, 30);
		btnBrowse.setFocusPainted(false);
		
		// mainPannel
		mainPane.setLayout(null);
		mainPane.add(labBrowse);
		mainPane.add(txtFieldBrowse);
		mainPane.add(btnBrowse);
		mainPane.add(effectPane);
		mainPane.add(imagePane);
		mainPane.add(footerPane);
		mainPane.add(cascadePanel);
	}
	
}
