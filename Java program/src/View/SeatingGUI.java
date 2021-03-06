package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Model.Seat;

public class SeatingGUI extends JFrame implements ActionListener{
	int seat = 0;
	int row = 0;
	int colum = 0;
	private String seatId;
	private int capacity = 18;
	private List<Seat> seats;
	private JButton[][] btn_seat = null;
	private JButton backBtn,continueBtn;
	
	public SeatingGUI() {
		this(18,null);
	}
	
	public SeatingGUI(int seatNums, List<Seat> seats) {
		this.capacity = seatNums;
		this.seats = seats;
		row = capacity / 6;
		colum = capacity % 6; 
		if (colum != 0) {
			row++;
		}
		this.setVisible(true);
		this.setTitle("Select Seat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 850, 550);

		JPanel jp_show = new JPanel(new GridLayout(row, 6, 6, 5));
		initButton();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < btn_seat[i].length; j++) {
				if (i == btn_seat[i].length - 1 && colum != 0) {
					if (j == colum) {
						break;
					}
				}
				jp_show.add(btn_seat[i][j]);
			}
		}
		JPanel jp_up = new JPanel(new FlowLayout());
		JPanel jp_down = new JPanel();
		jp_down.setLayout(new BorderLayout());
		JLabel lb_up = new JLabel("Screen");
		JLabel aroadLbl = new JLabel("Alley",JLabel.CENTER);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jp_show, BorderLayout.CENTER);
		jp_up.add(lb_up);
		jp_down.add(aroadLbl, BorderLayout.CENTER);
		JPanel btnPanel = new JPanel();
		backBtn = new JButton("Back"); 
		btnPanel.add(backBtn);
		continueBtn = new JButton("Continue"); 
		btnPanel.add(continueBtn);
		 
		this.getContentPane().add(jp_up, BorderLayout.NORTH);
		this.getContentPane().add(jp_down, BorderLayout.SOUTH);
		jp_down.add(btnPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void initButton() {
		btn_seat = new JButton[row][6];
		int count=0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < btn_seat[i].length; j++) {
				if (i == btn_seat[i].length - 1 && colum != 0) {
					if (j == colum) {
						break;
					}
				}
				btn_seat[i][j] = new JButton();
				++count;
				if(seats!=null && seats.size()!=0) {
					btn_seat[i][j].setToolTipText(seats.get(count-1).getSeatId()+"");
				}else {
					btn_seat[i][j].setToolTipText(count+"");
				} 
				btn_seat[i][j].setIcon(new ImageIcon("image/seat.png"));
				btn_seat[i][j].addActionListener(this);
				getDisableButton(btn_seat[i][j],btn_seat[i][j].getToolTipText());
			}
		}
	}
	
	private void getDisableButton(JButton btn,String seatid) {
		int id = 0;
		try {
			id = Integer.parseInt(seatid);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
		if (id!=0) {
			boolean isOpen = true;
			if(id<8001) {
				isOpen = seats.get(id-1).isOpen();
			}else {
				for (Seat s : seats) {
					if(s.getSeatId()==id) {
						isOpen = s.isOpen();
						break;
					}
				} 
			} 
			btn.setEnabled(isOpen);
		}
	}
	
	public void displayMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton  btn = (JButton) e.getSource();
		displayMessage(btn.getToolTipText());
	} 
	
	public void addCalcListener (ActionListener listenForCalcButton) {
		backBtn.addActionListener(listenForCalcButton);
		continueBtn.addActionListener(listenForCalcButton);
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getSeatId() {
		return seatId;
	}
	
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	
	public static void main(String[] args) {
		new SeatingGUI(18,null);
	}
}
