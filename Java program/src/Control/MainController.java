package Control;

import java.io.IOException;

public class MainController {
	private LogisticsController logisticConrol;
	private PaymentController paymentControl;
	private DBController dbControl;
	private UserController userControl;
	private ViewController viewControl;
	
	public MainController() {
		viewControl = new ViewController();
	}
	
	public static void DatabaseImport() throws IOException {
		
		
	}
	
	
	public void DatabaseExport() {
		
	}
    
	public static void main(String[] args) throws IOException {
		MainController mc = new MainController();
		mc.viewControl.showSeatingGUI();
	}


	
	
	
}

