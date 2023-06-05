package solution;



import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JLabel;
import javax.swing.JTextField; 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton; 

public class Calculator2 {
	
	private JFrame frame; 
	JLabel resultLabel; 
	JTextField infixExpression;
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JTextField getInfixExpression() {
		return infixExpression;
	}
	public void setInfixExpression(JTextField infixExpression) {
		this.infixExpression = infixExpression;
	}
	public JLabel getResultLabel() {
		return resultLabel;
	}
	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}
	
	public Calculator2()
	{
		frame = new JFrame(); 
		frame.setLocation(100,100); 
		frame.setSize(400,400); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setTitle("Simple Calculator"); 
		
		initializeComponents(); 
		frame.pack();
		frame.setVisible(true); 
	}
	
	//**********************************************************
	public void initializeComponents()
	{
		JPanel buttonPanel = new JPanel(); 
		

		// new clear and calculate buttons
		JButton calculateButton = new JButton("CALCULATE"); 
		JButton clearButton = new JButton("CLEAR"); 
		
		calculateButton.setName("calculateButton");
		clearButton.setName("clearButton");

		
	
		buttonPanel.add(calculateButton);
		buttonPanel.add(clearButton);
		
		//Result panel and its labels
		JPanel resultPanel = new JPanel();
		resultLabel = new JLabel("Result = "); 
		resultLabel.setName("resultLabel");

		resultPanel.add(resultLabel);

		// text panel and labels 
		JPanel txtPanel = new JPanel();
		infixExpression = new JTextField(10); 
		
		infixExpression.setName("infixExpression");
		
		
		txtPanel.add(infixExpression);
	
		
		
		// MAKE THEBUTTONS WORK : ADDITION BUTTON
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				resultLabel.setText("Result = "); 
				infixExpression.setText("");
			}
		});
		
		calculateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ExpressionEvaluator eval = new ExpressionEvaluator();
				String result = eval.toPostfix(infixExpression.getText());
				if(result.equals("Error"))
				{
					resultLabel.setText("Result = Error"); 
				}
				else
				{
					double answer = eval.evaluate(result);
					double finResult = (answer);
					if(finResult == Double.NaN)
					{
						resultLabel.setText("Result = Error"); 
					}
					else 
					{
						resultLabel.setText("Result = " + answer); 

					}
				}
			
					
				
				
			}
		});
		
		
		
		frame.add(buttonPanel, BorderLayout.PAGE_END);
		frame.add(txtPanel, BorderLayout.PAGE_START);
		frame.add(resultPanel, BorderLayout.WEST);

		

		}
	
	
	
	public static void main(String[] args)
	{
		Calculator2 calc = new Calculator2(); 
	}

	
	
	
	
}
