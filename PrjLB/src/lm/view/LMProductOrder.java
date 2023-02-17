package lm.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class LMProductOrder extends JFrame {
	
	// Fields 
			JLabel        lblaccount, orderDate, stockDate	// 거래처명, 주문일, 입고일
						 ,lblyear, lblmonth, lblday;		// 년, 월, 일 
			JTextField    txtaccount;						// 거래처명
			JComboBox     cmbYear, cmbMonth, cmbDay;		// year콤보박스, month콤보박스, day콤보박스
			JButton       btnOddsave, btnOrder, btnToExcel;	// 주문일자저장, 주문, 인쇄(엑셀)
			JPanel        topPane;
			JTable        jTable;
			JScrollPane   pane; 
	
	
	public LMProductOrder() {
		initComponent();
	}

	private void initComponent() {
		setTitle("상품주문 화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		setLocation(200, 200);
		setVisible(true);
		//ㅁㄴㅇㄹㄴㅁㄹㅇㅁㄴㄹㅇㄴㅇ
			
	}




	
	public static void main(String[] args) {
		new LMProductOrder();

		

	}

}
