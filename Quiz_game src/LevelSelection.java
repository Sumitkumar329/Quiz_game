import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelSelection implements ActionListener
{
	JFrame fr;
	JLabel l1;
	ButtonGroup rbg; 
	JRadioButton rb1,rb2,rb3;
	JButton b1;
	
	public LevelSelection() 
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame("Select your Level");
		fr.setSize(dim.width, dim.height);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLayout(null);
		
		l1 = new JLabel("Select your Level:");
		l1.setBounds(350,200,200,25);
		l1.setFont(new Font("verdana",Font.PLAIN,18));
		fr.add(l1);
		
		rbg = new ButtonGroup();
		
		rb1 = new JRadioButton("Easy");
		rb1.setSelected(true);
		rb1.setBounds(350,240,200,25);
		rb1.setFont(new Font("verdana",Font.PLAIN,18));
		rbg.add(rb1);
		fr.add(rb1);
		
		rb2 = new JRadioButton("Medium");
		rb2.setBounds(350,280,200,25);
		rb2.setFont(new Font("verdana",Font.PLAIN,18));
		rbg.add(rb2);
		fr.add(rb2);
		
		rb3 = new JRadioButton("Hard");
		rb3.setBounds(350,320,200,25);
		rb3.setFont(new Font("verdana",Font.PLAIN,18));
		rbg.add(rb3);
		fr.add(rb3);
		
		
		b1 = new JButton("Start Quiz");
		b1.setBounds(350,360,200,35);
		b1.setFont(new Font("verdana",Font.BOLD,18));
		b1.addActionListener(this);
		fr.add(b1);
		
		fr.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(rb1.isSelected())
		{
			new Question("Easy");
			fr.dispose();
		}
		else if(rb2.isSelected())
		{
			new Question("Medium");
			fr.dispose();
		}
		else if(rb3.isSelected())
		{
			new Question("Hard");
			fr.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(fr,"Please Select your level");
		}
	}
	
	public static void main(String[] args)
	{
		new LevelSelection();
	}
}