package humanshield85.cascadeTester.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9170901709234892030L;
	
	BufferedImage image ;
	public ImagePanel() {
		setSize(580,370);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (image != null) {
			Rectangle rec = getRectabgle(image.getWidth(),image.getHeight());
			g2d.drawImage(image, rec.x, rec.y, rec.width, rec.height, null);
		}
	}
	
	private Rectangle getRectabgle(int imgWidth , int imgHeight) {
		int x,y,width = imgWidth,height = imgHeight;
		if (width > this.getWidth()) {
			width = this.getWidth();
			double ratio = (double)width / (double)imgWidth;
			height = (int)(imgHeight*ratio);
		}
		if (height > this.getHeight()) {
			double ratio = (double)this.getHeight() / (double)height;
			height = this.getHeight();
			width = (int)(width * ratio);
		}
		x = (this.getWidth() - width)/2;
		y = (this.getHeight() - height)/2;
		
		return new Rectangle(x,y,width,height);
	}
	
	
}
