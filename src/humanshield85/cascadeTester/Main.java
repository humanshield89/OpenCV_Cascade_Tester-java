package humanshield85.cascadeTester;

import humanshield85.cascadeTester.ui.MainUi;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        // if anything happend we load default look and feel so no harm no feel
        } catch (ClassNotFoundException ex) {
           //unlikly 
        } catch (InstantiationException ex) {
            //unlikly 

        } catch (IllegalAccessException ex) {
            //unlikly 

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //unlikly 
        }
        
		MainUi window = new MainUi();
		
	}

}
