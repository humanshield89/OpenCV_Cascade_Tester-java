package humanshield85.cascadeTester.ui;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import humanshield85.cascadeTester.Main;
import humanshield85.cascadeTester.Obj.StateChangedEvent;
import humanshield85.cascadeTester.Obj.Subscriber;

public class MainUi implements Subscriber, ActionListener {
	static final int MAIN_UI_WIDTH = 600;
	static final int MAIN_UI_HEIGHT = 610;

	protected JFrame mainFrame = new JFrame();
	protected JPanel mainPane = new JPanel();
	protected JPanel effectPane = new JPanel();
	protected JTextField txtFieldBrowse = new JTextField("Click browse and select the images folder");
	protected JButton btnBrowse = new JButton("Browse");

	// radio
	protected JRadioButton radioOriginal = new JRadioButton("No Effects");
	protected JRadioButton radioGrayScale = new JRadioButton("GrayScale");
	protected ButtonGroup groupEffect = new ButtonGroup();

	protected JTextField txtFieldImageNumber = new JTextField("00");
	protected JLabel labSeparator = new JLabel("/");
	protected JTextField txtFieldMaxImageNumber = new JTextField("00");
	protected JButton btnPrevImage = new JButton("Prev");
	protected JButton btnNextImage = new JButton("Next");
	protected JPanel footerPane = new JPanel();

	protected ImagePanel imagePane = new ImagePanel();

	protected JPanel cascadePanel = new JPanel();
	protected JButton btnSlectCascade = new JButton("Select cascade  file");
	protected JLabel labCascadeFile = new JLabel("No Files Selected");
	protected JButton btnStartDetection = new JButton("Start Detection");

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

	MenuItem menuItemSettings = new MenuItem("Settings");
	MenuItem menuItemAboutTheSoftware = new MenuItem("About this software");

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
		setActions();
		Main.state.addSubscriber(this);
	}

	private void setActions() {
		// TODO Auto-generated method stub
		this.btnBrowse.addActionListener(this);
		this.btnNextImage.addActionListener(this);
		this.btnPrevImage.addActionListener(this);
		menuItemOpenFolder.addActionListener(this);
		btnSlectCascade.addActionListener(this);
		menuItemOpenCascadeFile.addActionListener(this);
		this.menuItemExit.addActionListener(this);
	}

	/**
	 * called when constructed just for clear code
	 */
	private void setUiPropreties() {
		mainFrame.setSize(MainUi.MAIN_UI_WIDTH, MainUi.MAIN_UI_HEIGHT);
		mainFrame.setContentPane(mainPane);
		mainFrame.setTitle("OpenCV cascade tester");
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		effectPane.setBounds(10, 50, 200, 90);
		effectPane.setBorder(BorderFactory.createTitledBorder("Effects before detection"));
		groupEffect.add(radioOriginal);
		groupEffect.add(radioGrayScale);
		effectPane.add(radioOriginal);
		radioOriginal.setSelected(true);
		effectPane.add(radioGrayScale);

		cascadePanel.setBounds(220, 50, 200, 90);
		cascadePanel.setBorder(BorderFactory.createTitledBorder("Cascade files"));
		cascadePanel.add(labCascadeFile);
		cascadePanel.add(btnSlectCascade);

		btnStartDetection.setBounds(430, 64, 155, 72);
		btnStartDetection.setEnabled(false);

		imagePane.setBounds(10, 140, 580, 370);
		imagePane.setBorder(BorderFactory.createLineBorder(Color.black));

		footerPane.setBounds(10, 520, 580, 70);
		txtFieldImageNumber.setSize(50, 60);
		btnPrevImage.setFocusPainted(false);
		btnNextImage.setFocusPainted(false);
		footerPane.add(btnPrevImage);
		footerPane.add(txtFieldImageNumber);
		txtFieldImageNumber.setEditable(false);
		txtFieldMaxImageNumber.setEditable(false);
		txtFieldImageNumber.setEnabled(false);
		txtFieldMaxImageNumber.setEnabled(false);
		footerPane.add(labSeparator);
		footerPane.add(txtFieldMaxImageNumber);
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
		mainPane.add(btnStartDetection);
	}

	@Override
	public void getSubscriptionUpdate(StateChangedEvent stateChangedEvent) {
		// TODO Auto-generated method stub
		switch (stateChangedEvent.getType()) {
		case StateChangedEvent.ACTIVE_IMAGE_CHNAGED:
			this.txtFieldImageNumber.setText("0" + (Main.state.getActiveImageIndex() + 1));
			if ((Main.state.getActiveImageIndex() + 1) == Main.state.getMaxImageCount()) {
				this.btnNextImage.setEnabled(false);
			} else {
				this.btnNextImage.setEnabled(true);
			}
			if ((Main.state.getActiveImageIndex() + 1) == 1) {
				this.btnPrevImage.setEnabled(false);
			} else {
				this.btnPrevImage.setEnabled(true);
			}
			break;
		case StateChangedEvent.CASCADE_FILE_CHANGED:
			this.labCascadeFile.setText(Main.state.getCascadeFile().getName());
			this.btnStartDetection.setEnabled(Main.state.HasValidImages() && Main.state.isCascadeSet());
			break;
		case StateChangedEvent.IMAGE_LIST_CHNAGED:
			this.txtFieldBrowse.setText(Main.state.getImagesPath().getAbsolutePath());
			this.txtFieldMaxImageNumber.setText("" + Main.state.getMaxImageCount());
			this.btnStartDetection.setEnabled(Main.state.HasValidImages() && Main.state.isCascadeSet());
			break;
		case StateChangedEvent.EFFECT_CHNAGED:
			if (Main.state.getEffect() == 0) {
				radioOriginal.setSelected(true);
			} else {
				radioGrayScale.setSelected(true);
			}
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source.equals(btnBrowse) || source.equals(menuItemOpenFolder)) {
			JFileChooser fs = new JFileChooser();
			fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fs.showOpenDialog(mainFrame);
			if (result != JFileChooser.CANCEL_OPTION)
				Main.state.setImagesPath(fs.getSelectedFile());
		} else if (source.equals(btnNextImage)) {

			Main.state.nextImage();

		} else if (source.equals(btnPrevImage)) {

			Main.state.prevImage();

		} else if (source.equals(radioOriginal)) {

			Main.state.setEffect(0);

		} else if (source.equals(radioGrayScale)) {

			Main.state.setEffect(1);

		} else if (source.equals(btnSlectCascade) || source.equals(menuItemOpenCascadeFile)) {
			JFileChooser fs = new JFileChooser();
			fs.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fs.setFileFilter(new FileNameExtensionFilter("xml", "xml"));
			int result = fs.showOpenDialog(mainFrame);
			if (result != JFileChooser.CANCEL_OPTION)
				Main.state.setCascadeFile(fs.getSelectedFile());
		} else if (source.equals(menuItemExit)) {
			System.exit(0);
		}

	}

}
