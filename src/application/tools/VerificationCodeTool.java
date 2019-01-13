package application.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**   
* @title: RegistTool.java 
* @package application.tools 
* @description: (生成随机算术运算验证码) 
* @author 夏靖雯  
* @date 2018年12月19日 下午4:21:22 
* @version V1.0   
*/
public class VerificationCodeTool {
	
	/**
	* 	生成四则算术验证码
	* 	
	* 	@return Map<String,Integer> 生成的四则运算验证码字符串和正确的结果	
	*/
	public static Map<String,Integer> generateArithmeticVerification() {
		//生成三个随机操作数
		Random random = new Random();			
		int a = random.nextInt(10)+1;		
		int b = random.nextInt(10)+1;
		int c = random.nextInt(10)+1;
		
		//从'+', '-', '*', '/'随机选择两个运算符
		char[] OperatorArray  = new char[] {'+', '-', '*', '/'};
		int opIndex1 = random.nextInt(4);
		int opIindx2 = random.nextInt(4);
		char op1 = OperatorArray[opIndex1];
		char op2 = OperatorArray[opIindx2];
		
		int result = 0; 		//算术运算的真实结果
		if(comparisonOpPriority(op1, op2)) {
			result = calculate(a, b, op1);
			result = calculate(result, c, op2);
		}else {
			result = calculate(b, c, op2);
			result = calculate(a, result, op1);
		}
		//System.out.print("请输入"+ a + op1+ b + op2 + c + "=？的答案:" );	//提示用户输入验证码的答案
		String opExp = ""+a + op1+ b + op2 + c ;
		Map<String,Integer> resExp = new HashMap<>();
		resExp.put(opExp, result);
		return resExp;			
	}
	
	/**
	* 	二元一则运算
	* 	@param a 第一个操作数
	* 	@param b 第二个操作数
	* 	@param op 操作符
	* 	@return int 运算结果
	*/
	public static int calculate(int a, int b, char op) {
		int calResult = 0;
		switch(op)
		{
		case '+':
			calResult = a+b;
			break;
		case '-':
			calResult = a-b;
			break;
		case '*':
			calResult = a*b;
			break;
		case '/':
			calResult = a/b;
			break;
		}
		return calResult;
	}
	
	/**
	* 	比较操作符的优先级
	* 	@param op1 第一个操作符
	* 	@param op2 第二个操作符
	* 	@return boolean 
	*/
	public static boolean comparisonOpPriority(char op1, char op2)
	{
		boolean op1HasHighPriority = true;
		switch(op1)						//若是op1和op2为/或者*时，默认op1的优先级高
		{
		case '-':	
		case '+':
			if(op2=='*' || op2=='/')
				op1HasHighPriority = false;
			break;
		}
		return op1HasHighPriority;
	}
}
