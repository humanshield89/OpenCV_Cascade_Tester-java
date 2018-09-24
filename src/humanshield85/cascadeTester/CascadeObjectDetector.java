package humanshield85.cascadeTester;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class CascadeObjectDetector {

	public static MatOfRect detectObjects(File image , File cascade ,int effect) {
        CascadeClassifier detector = new CascadeClassifier(cascade.getAbsolutePath());
        Mat img = Highgui.imread(image.getAbsolutePath());
        if (effect == 1) {
        	Mat gray = new Mat(img.size(), 1);
        	Imgproc.cvtColor(img, gray, Imgproc.COLOR_RGB2GRAY);
        	// equilize histogram 
        	Imgproc.equalizeHist(gray, gray);
        	img = gray;
        }
        MatOfRect detections = new MatOfRect();
        detector.detectMultiScale(img, detections);
        
		return detections;
		
	}
}
