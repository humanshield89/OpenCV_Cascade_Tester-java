package humanshield85.cascadeTester.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;

import humanshield85.cascadeTester.CascadeObjectDetector;
import humanshield85.cascadeTester.Main;
import humanshield85.cascadeTester.Obj.StateChangedEvent;
import humanshield85.cascadeTester.Obj.Subscriber;

public class ImagePanel extends JPanel implements Subscriber {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9170901709234892030L;

	BufferedImage image;

	public ImagePanel() {
		setSize(580, 370);
		Main.state.addSubscriber(this);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (image != null) {
			Rectangle rec = getRectangle(image.getWidth(), image.getHeight());
			g2d.drawImage(image, rec.x, rec.y, rec.width, rec.height, null);
		}
	}

	private Rectangle getRectangle(int imgWidth, int imgHeight) {
		int x, y, width = imgWidth, height = imgHeight;
		if (width > this.getWidth()) {
			width = this.getWidth();
			double ratio = (double) width / (double) imgWidth;
			height = (int) (imgHeight * ratio);
		}
		if (height > this.getHeight()) {
			double ratio = (double) this.getHeight() / (double) height;
			height = this.getHeight();
			width = (int) (width * ratio);
		}
		x = (this.getWidth() - width) / 2;
		y = (this.getHeight() - height) / 2;

		return new Rectangle(x, y, width, height);
	}

	@Override
	public void getSubscriptionUpdate(StateChangedEvent stateChangedEvent) {
		if (stateChangedEvent.getType() == StateChangedEvent.ACTIVE_IMAGE_CHNAGED && Main.state.HasValidImages()) {
			try {
				image = ImageIO.read(Main.state.getCurrentImage());
				if (Main.state.isCascadeSet())
					newDetection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (stateChangedEvent.getType() == StateChangedEvent.CASCADE_FILE_CHANGED) {

			if (Main.state.HasValidImages() && Main.state.isCascadeSet()) {
				try {
					image = ImageIO.read(Main.state.getCurrentImage());
					newDetection();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private void newDetection() {
		if (image == null)
			return;
		MatOfRect rects = CascadeObjectDetector.detectObjects(Main.state.getCurrentImage(), Main.state.getCascadeFile(),
				Main.state.getEffect());
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		for (Rect rect : rects.toArray()) {
			g2d.setColor(Color.GREEN);
			g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
			g2d.drawRect(rect.x - 1, rect.y - 1, rect.width + 2, rect.height + 2);
			g2d.drawRect(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
		}
		repaint();
	}

}
