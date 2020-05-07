/**
 * Calculator to calulate stuff
 * @author Jeffrey C. Slocum jcs307
 * @author Arthur Hatgis
 */

package HW8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
/** Homework 8 due April 29 2020 at 2p.m.
 * Add javadoc to the file */


public class Calculator {

	//declare all variables needed to create GUI
	static JFrame f;
	static JTextField t;
	static JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdiv,bmul,bsub,badd,bdec,beq,bdel,bclr, bSqrd,bHalf,bRoot;

	static double a=0,b=0,result=0;
	static int operator=0;

	//constructor for calculator
	Calculator(){
		f=new JFrame("Calculatorulator");
		GridLayout grid = new GridLayout(8,3,2,4);

		//declare all buttons
		t=new JTextField();
		b1=new JButton("1");
		b2=new JButton("2");
		b3=new JButton("3");
		b4=new JButton("4");
		b5=new JButton("5");
		b6=new JButton("6");
		b7=new JButton("7");
		b8=new JButton("8");
		b9=new JButton("9");
		b0=new JButton("0");
		bdiv=new JButton("/");
		bmul=new JButton("*");
		bsub=new JButton("-");
		badd=new JButton("+");
		bdec=new JButton(".");
		beq=new JButton("=");
		bdel=new JButton("Delete");
		bclr=new JButton("Clear");

		bSqrd =new JButton("x^2");
		bHalf=new JButton("1/x");
		bRoot=new JButton("Sqrt(x)");


		//add all buttons to panel / frame
		f.add(t);
		f.add(bdel);
		f.add(bclr);
		f.add(bHalf);
		f.add(bSqrd);
		f.add(bRoot);
		f.add(b7);
		f.add(b8);
		f.add(b9);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(bsub);
		f.add(b0);
		f.add(badd);
		f.add(bdiv);
		f.add(bmul);
		f.add(bdec);
		f.add(beq);


		//options for alignment
		//set to GridLayout for alignment consistency
		f.setLayout(grid);
		f.setVisible(true);
		f.setSize(350,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(true);
	}

	/**
	 * Button listener class to 'listen' for when a button is clicked
	 *
	 */
	private static class ButtonListener implements ActionListener {

		/**
		 *
		 * @param e action event when a button with actionListener attached is clicked
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == b1)
				t.setText(t.getText().concat("1"));

			if (e.getSource() == b2)
				t.setText(t.getText().concat("2"));

			if (e.getSource() == b3)
				t.setText(t.getText().concat("3"));

			if (e.getSource() == b4)
				t.setText(t.getText().concat("4"));

			if (e.getSource() == b5)
				t.setText(t.getText().concat("5"));

			if (e.getSource() == b6)
				t.setText(t.getText().concat("6"));

			if (e.getSource() == b7)
				t.setText(t.getText().concat("7"));

			if (e.getSource() == b8)
				t.setText(t.getText().concat("8"));

			if (e.getSource() == b9)
				t.setText(t.getText().concat("9"));

			if (e.getSource() == b0)
				t.setText(t.getText().concat("0"));

			if (e.getSource() == bdec)
				t.setText(t.getText().concat("."));

			if (e.getSource() == badd) {
				a = Double.parseDouble(t.getText());
				operator = 1;
				t.setText("");
			}

			if (e.getSource() == bsub) {
				a = Double.parseDouble(t.getText());
				operator = 2;
				t.setText("");
			}

			if (e.getSource() == bmul) {
				a = Double.parseDouble(t.getText());
				operator = 3;
				t.setText("");
			}

			if (e.getSource() == bdiv) {
				a = Double.parseDouble(t.getText());
				operator = 4;
				t.setText("");
			}

			if (e.getSource() == beq) {
				b = Double.parseDouble(t.getText());

				switch (operator) {
					case 1:
						result = a + b;
						break;

					case 2:
						result = a - b;
						break;

					case 3:
						result = a * b;
						break;

					case 4:
						result = a / b;
						break;

					default:
						result = 0;
				}

				t.setText("" + result);
			}

			if (e.getSource() == bclr)
				t.setText("");

			if (e.getSource() == bdel) {
				String s = t.getText();
				t.setText("");
				for (int i = 0; i < s.length() - 1; i++)
					t.setText(t.getText() + s.charAt(i));
			}

			//our added buttons
			if (e.getSource() == bHalf) {
				double var = Double.parseDouble(t.getText());
				t.setText("" + (1/var));
			}

			if (e.getSource() == bSqrd) {
				double var = Double.parseDouble(t.getText());
				t.setText("" + (var*var));
			}

			if (e.getSource() == bRoot) {
				double var = Double.parseDouble(t.getText());
				t.setText("" + Math.sqrt(var));
			}

		}
	}

	public static void main(String[] args){
		new Calculator();
		ButtonListener listen = new ButtonListener();
		//added listeners to each button
		b1.addActionListener(listen);
		b2.addActionListener(listen);
		b3.addActionListener(listen);
		b4.addActionListener(listen);
		b5.addActionListener(listen);
		b6.addActionListener(listen);
		b7.addActionListener(listen);
		b8.addActionListener(listen);
		b9.addActionListener(listen);
		b0.addActionListener(listen);
		badd.addActionListener(listen);
		bdiv.addActionListener(listen);
		bmul.addActionListener(listen);
		bsub.addActionListener(listen);
		bdec.addActionListener(listen);
		beq.addActionListener(listen);
		bdel.addActionListener(listen);
		bclr.addActionListener(listen);
		bHalf.addActionListener(listen);
		bSqrd.addActionListener(listen);
		bRoot.addActionListener(listen);

	}
}
