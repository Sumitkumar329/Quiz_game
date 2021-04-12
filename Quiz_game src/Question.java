import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Question implements ActionListener
{
	JFrame fr;
	String level;
	JLabel levelLabel,timerLabel,questionLabel;
	ButtonGroup bg;
	JRadioButton option1,option2,option3,option4;
	JButton nextButton; 
	
	int count=1;
	boolean isSubmitted;
	ResultSet rs;
	String[] option;
	String[] answer = new String[5];
	
	public Question(String level)
	{
		this.level = level;
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame("Question");
		fr.setSize(dim.width, dim.height);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLayout(null);
		
		levelLabel = new JLabel("Your Level: " + level);
		levelLabel.setBounds(10,10,200,25);
		levelLabel.setFont(new Font("verdana",Font.PLAIN,18));
		fr.add(levelLabel);
		
		timerLabel = new JLabel("Time left: 30sec");
		timerLabel.setBounds(dim.width-180,10,180,25);
		timerLabel.setFont(new Font("verdana",Font.PLAIN,18));
		fr.add(timerLabel);
		
		questionLabel = new JLabel();
		questionLabel.setBounds(300,200,600,25);
		questionLabel.setFont(new Font("verdana",Font.PLAIN,18));
		fr.add(questionLabel);
		
		bg = new ButtonGroup();
		
		option1 = new JRadioButton();
		option1.setBounds(350,240,500,25);
		option1.setFont(new Font("verdana",Font.PLAIN,18));
		option1.addActionListener(this);
		bg.add(option1);
		fr.add(option1);
		
		option2 = new JRadioButton();
		option2.setBounds(350,270,500,25);
		option2.setFont(new Font("verdana",Font.PLAIN,18));
		option2.addActionListener(this);
		bg.add(option2);
		fr.add(option2);
		
		option3 = new JRadioButton();
		option3.setBounds(350,300,500,25);
		option3.setFont(new Font("verdana",Font.PLAIN,18));
		option3.addActionListener(this);
		bg.add(option3);
		fr.add(option3);
		
		option4 = new JRadioButton();
		option4.setBounds(350,330,500,25);
		option4.setFont(new Font("verdana",Font.PLAIN,18));
		option4.addActionListener(this);
		bg.add(option4);
		fr.add(option4);	
		
		nextButton = new JButton("Next");
		nextButton.setBounds(500,380,120,35);
		nextButton.setFont(new Font("verdana",Font.BOLD,18));
		nextButton.addActionListener(this);
		fr.add(nextButton);
		
		loadQuestion();
		
		fr.setVisible(true);

		startTimer();
	}
	
	public void startTimer()
	{
		new Thread(()->{
			for(int i=30; i>=0; i--)
			{	
				timerLabel.setText("Time left: "+i+"sec");
				try{ Thread.sleep(1000); }
				catch(InterruptedException ie){}
			}
			if(!isSubmitted)
			{
				submit();
			}
		}).start();
	}
	
	
	public void loadQuestion()
	{
		try
		{
			Connection con = DAO.getConnection();
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM questions WHERE level=?");
			ps.setString(1,level.toLowerCase());
			
			rs = ps.executeQuery();
			rs.next();
			questionLabel.setText("Q"+count+". "+rs.getString("question"));
			
			option = rs.getString("options").split(",");
			
			option1.setText("A. "+option[0]);
			option2.setText("B. "+option[1]);
			option3.setText("C. "+option[2]);
			option4.setText("D. "+option[3]);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadNextQuestion()
	{
		try
		{
			rs.next();
			questionLabel.setText("Q"+count+". "+rs.getString("question"));
			
			option = rs.getString("options").split(",");
			
			option1.setText("A. "+option[0]);
			option2.setText("B. "+option[1]);
			option3.setText("C. "+option[2]);
			option4.setText("D. "+option[3]);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//event listener
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == nextButton)
		{
			
			bg.clearSelection();
			
			if(count < 5)
			{
				
				count++;
				loadNextQuestion();
			}
			if(count == 5)
			{
				nextButton.setText("Submit");
			}
			
		}
		
		if(ae.getActionCommand().equals("Submit"))
		{
			submit();
			isSubmitted = true;
		}
		
		if(ae.getSource() instanceof JRadioButton)
		{
			answer[count-1] = ae.getActionCommand().substring(3);
		}
	}
	
	public void submit()
	{
		fr.dispose();
		new Result(answer,level);
	}
}