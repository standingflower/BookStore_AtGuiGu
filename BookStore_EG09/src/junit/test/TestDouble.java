package junit.test;

import java.math.BigDecimal;

import org.junit.Test;

public class TestDouble {
	
	@Test
	public void testDouble2(){
		//BigDecimal的double的构造器会返回一个不可预知的结果，所以我们不使用double型的构造器
		//而是使用Sting型的构造器
		BigDecimal a = new BigDecimal("0.09");
		BigDecimal b = new BigDecimal("0.01");
		
		BigDecimal add = a.add(b);
		
		double c = add.doubleValue();
		
		System.out.println(c);
		
	}
	
	@Test
	public void testInt2(){
		//BigDecimal专门用来做数字的计算'
		//可以通过使用BigDecimal对象，将要计算的数值进行包装，直接调用对象中的方法进行计算，就可以解决计算精度问题
		BigDecimal a = new BigDecimal(1);
		
		for(int i=1 ; i<=100 ; i++){
			
			//将i包装为一个BigDecimal类型
			BigDecimal b = new BigDecimal(i);
			
			a = a.multiply(b);
		}
		
		System.out.println(a);
		
	}
	
	@Test
	public void testDouble(){
		double a=0.09;
		double b=0.01;
		
		System.out.println(a+b);
	}

	@Test
	public void testInt() {
		
		//求一个1-10的阶乘
		int a = 1;
		for(int i=1 ; i<=20 ; i++){
			a *= i;
		}
		
		System.out.println(a);
		
	}

}
