import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Result
{
	JFrame fr;
	Dimension dim;
	JLabel resultLabel, questionLabel[], userAnswerLabel[], correctAnswerLabel[];
	
	String level;
	String[] answer;
	int correctAnswer;
		
	public Result(String[] answer,String level)
	{
		this.answer = answer;
		this.level = level;
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame("Results");
		fr.setSize(620, 500);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLayout(null);
		
		resultLabel = new JLabel();
		resultLabel.setBounds(0,5,600,50);
		resultLabel.setHorizontalAlignment(JLabel.CENTER);
		resultLabel.setFont(new Font("verdana",Font.BOLD,30));
		fr.add(resultLabel);
		
		questionLabel = new JLabel[5];
		userAnswerLabel = new JLabel[5];
		correctAnswerLabel = new JLabel[5];
		
		//Question1
		questionLabel[0] = new JLabel();
		questionLabel[0].setBounds(25,60,600,20);
		questionLabel[0].setFont(new Font("verdana",Font.BOLD,16));
		fr.add(questionLabel[0]);
		
		userAnswerLabel[0] = new JLabel();
		userAnswerLabel[0].setBounds(40,80,600,20);
		userAnswerLabel[0].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(userAnswerLabel[0]);
		
		correctAnswerLabel[0] = new JLabel();
		correctAnswerLabel[0].setBounds(40,100,600,20);
		correctAnswerLabel[0].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(correctAnswerLabel[0]);

		//Question2
		questionLabel[1] = new JLabel();
		questionLabel[1].setBounds(25,140,600,20);
		questionLabel[1].setFont(new Font("verdana",Font.BOLD,16));
		fr.add(questionLabel[1]);
		
		userAnswerLabel[1] = new JLabel();
		userAnswerLabel[1].setBounds(40,160,600,20);
		userAnswerLabel[1].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(userAnswerLabel[1]);
		
		correctAnswerLabel[1] = new JLabel();
		correctAnswerLabel[1].setBounds(40,180,600,20);
		correctAnswerLabel[1].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(correctAnswerLabel[1]);

		//Question3
		questionLabel[2] = new JLabel();
		questionLabel[2].setBounds(25,220,600,20);
		questionLabel[2].setFont(new Font("verdana",Font.BOLD,16));
		fr.add(questionLabel[2]);
		
		userAnswerLabel[2] = new JLabel();
		userAnswerLabel[2].setBounds(40,240,600,20);
		userAnswerLabel[2].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(userAnswerLabel[2]);
		
		correctAnswerLabel[2] = new JLabel();
		correctAnswerLabel[2].setBounds(40,260,600,20);
		correctAnswerLabel[2].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(correctAnswerLabel[2]);

		//Question4
		questionLabel[3] = new JLabel();
		questionLabel[3].setBounds(25,300,600,20);
		questionLabel[3].setFont(new Font("verdana",Font.BOLD,16));
		fr.add(questionLabel[3]);
		
		userAnswerLabel[3] = new JLabel();
		userAnswerLabel[3].setBounds(40,320,600,20);
		userAnswerLabel[3].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(userAnswerLabel[3]);
		
		correctAnswerLabel[3] = new JLabel();
		correctAnswerLabel[3].setBounds(40,340,600,20);
		correctAnswerLabel[3].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(correctAnswerLabel[3]);

		//Question5
		questionLabel[4] = new JLabel();
		questionLabel[4].setBounds(25,380,600,20);
		questionLabel[4].setFont(new Font("verdana",Font.BOLD,16));
		fr.add(questionLabel[4]);
		
		userAnswerLabel[4] = new JLabel();
		userAnswerLabel[4].setBounds(40,400,600,20);
		userAnswerLabel[4].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(userAnswerLabel[4]);
		
		correctAnswerLabel[4] = new JLabel();
		correctAnswerLabel[4].setBounds(40,420,600,20);
		correctAnswerLabel[4].setFont(new Font("verdana",Font.PLAIN,16));
		fr.add(correctAnswerLabel[4]);
		
		
		correctAnswer = checkCorrect();
		
		switch(correctAnswer)
		{
			case 0: resultLabel.setText("Result: Very Poor(0)");
				break;
			case 1: resultLabel.setText("Result: Poor(1)");
				break;
			case 2: resultLabel.setText("Result: Bad(2)");
				break;
			case 3: resultLabel.setText("Result: Good(3)");
				break;
			case 4: resultLabel.setText("Result: Strong(4)");
				break;
			case 5: resultLabel.setText("Result: Very Strong(5)");
		}
		
		fr.setVisible(true);
	}
	
	public int checkCorrect()
	{
		try
		{
			Connection con = DAO.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select * from questions where level=?");
			ps.setString(1,level);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			for(int i=0; i<5; i++)
			{
				questionLabel[i].setText("Question "+(i+1)+". "+rs.getString("question"));
				
				if(answer[i]==null)
				{
					userAnswerLabel[i].setText("You have not answered this question!");
				}
				else if(rs.getString("answer").equals(answer[i]))
				{
					correctAnswer++;
					userAnswerLabel[i].setText("Your Answer: "+answer[i]);
				}
				else
				{
					userAnswerLabel[i].setText("Your Answer: "+answer[i]);
				}
				
				correctAnswerLabel[i].setText("Correct Answer: "+rs.getString("answer"));
				
				rs.next();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return correctAnswer;
	}
}