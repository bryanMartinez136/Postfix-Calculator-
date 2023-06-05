package solution;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Stack; 
/**
 * 
 * @author ???
 * @version ???
 * 
 */
public class ExpressionEvaluator
{

    public static final Pattern UNSIGNED_DOUBLE =
            Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");

    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public String toPostfix(String expression)
    {
        // ... other local variables
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        String postfixExpression = "";
        
        Stack<Character> operators = new Stack<Character>(); 
        
        char peeked; 
        
        int nums = 0; 
        int ops = 0; 
        
        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                postfixExpression += next + " "; 
                nums++; 
            }
            else
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);
                
                // TODO: do what you want to with a symbol
                // such as (, ), *, /, +, -
                if(symbol == '(')
                {
                	
                	operators.push(symbol); 
                	
                }
                else if(symbol == '+' || symbol == '-' || symbol == '/' || symbol == '*' )
                {
                	
                	boolean breakloop = false; 
                	while(!operators.isEmpty() && operators.peek() != '(')
                	{
                		peeked = operators.peek();
                		                		 
                		switch(symbol)
                		{
                		case '*':
                		case '/':
                			if(peeked == '/' || peeked == '*' )
                			{
                				postfixExpression += operators.pop() + " "; // write to ouput!!
                			}
                			else 
                			{
                				breakloop = true; 
                			}
                			break; 
                		case '+':
                		case '-':
                			
                			postfixExpression += operators.pop() + " "; // write to ouput!!
                		
                		}
                		if(breakloop)
                		{
                			break; 
                		}
                		
                	}
                	operators.push(symbol); 
                	ops++; 
                }
                else if(symbol == ')' )
                {
                	while(!operators.isEmpty() && operators.peek() != '(' )
                	{
                		postfixExpression += operators.pop() + " ";// write to ouput!!
                		
                	}
                	operators.pop(); 
                }
                else
                {
                	return "Error";
                }

            }
            
        }
        while(!operators.isEmpty())
        {
        	
    		postfixExpression += operators.pop() + " ";// write to ouput!!

        }
        
// When a number was pushed, the "number count" was incremented. When an operation was pushed,
// the "operations" count was incremented. It is impossible to perform operations if the number
// of operators is greater than or equal to numbers. Ex: 5 *- is impossible (1 number, 2 operators), 
// so is -5 / 6 and (5+6)(4-1) (we are not reading the minus sign as negative 
// nor are we treating () as multiplication. So, if there are more operators or the same amount
// there is an error. Furthermore, the difference in operators count and the numbers count has 
// to be equal to one. Ex: (3/4)(1-2) (4 numbers, 2 operators) this would yield an error since
// adjacent parenthesis are not considered the same as multiplication.
//      System.out.println("nums = " + nums);
//   	System.out.println("ops = " + ops);
    	int tot = nums - ops; 
        if(tot != 1)
        {
        	

        	return "Error"; 
        }
        
        return postfixExpression;
    }

    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     */
    public double evaluate(String postfixExpression)
    {
        // other local variables you may need
        Scanner input = new Scanner(postfixExpression);
        String next;
        char operator;
        double answer = Double.NaN;
        Stack<Double> numbers = new Stack<Double>(); 

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                double num = Double.parseDouble(next);
                numbers.push(num); 
                   
            }
            else
            {
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);

                // TODO: do what you want to with an operator
                // such as *, /, +, -
                try
                {
                	double rightOp = numbers.pop(); 
                	double leftOp = numbers.pop(); 
                	switch (operator)
                	{
                	case '*':
                		numbers.push(leftOp * rightOp);
                		break; 
                	case '/':
                		numbers.push(leftOp / rightOp);
                		break;
                	case '-':
                		numbers.push(leftOp - rightOp);
                		break;
                	case '+':
                		numbers.push(leftOp + rightOp);
                		break;
                	}
                }
                catch(EmptyStackException e)
                {
                	return answer; 
                }
                   
                
            }
            
            answer = numbers.peek(); 
            
        }
        return answer;

    }

}
