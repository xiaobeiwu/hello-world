package com.testjavastudy;

import static org.testng.Assert.*;

import javax.security.auth.Destroyable;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Alert;


import com.javastudy.Calculate;



public class CalculateTest {
	
	private Calculate cal;

	@BeforeClass
	public void setup() {
		cal=new Calculate();
		System.out.println("CalculateTest--beforeclass");
		
	}
	
	@AfterClass
	public void destroy() {
		cal=null;
		System.out.println("CalculateTest--afterclass");
	}
	

// 使用priority指定执行顺序(默认值为0)，数值越小，越靠前执行
	@Test(priority=4)
	public void testadd2() {
		int x=cal.add(2, 3);
		System.out.println("2+3="+x);
	}

	@Test(priority=3)
	public void testsub() {
		int a=5-2;
		System.out.println("5-2="+a);
	}
	
	@Test(priority=2)
	public void testmult1() {
		int y=3*4;
		System.out.println("3*4="+y);
	}
	@Test(priority=1)
	public void testdiv() {
		int b=10/2;
		System.out.println("10/2="+b);
	}
	
//  传递参数给@Test可以用@parameters和@DataProvider
	 @DataProvider(name="data")
	  public Object[][] myData(){
	      return new Object[][]{{1,2},{3,4},{5,6}};

	  }
	 
	 @Test(dataProvider = "data")
	 public void testProvide(int x, int y) {
//	      System.out.println("this is TestNG test case1, and param1 is:"+param1+"; param2 is:"+param2);
//	      Assert.assertFalse(false);
	      int a= cal.add(x, y);
	      int b= cal.sub(x,y);
	      int c= cal.mult(x, y);
	      int d= cal.div(x, y);
	      System.out.println("x="+x+",y="+y+" add="+a+" sub="+b+" mult="+c+" div="+d);
	    }
	 

//	        依赖测试--方法依赖
		@Test(dependsOnMethods="testBy",alwaysRun=true)
		public void testDpend() {
			
			assertEquals(cal.add(1, 2), 3, "加法计算错误");
			System.out.println("1+2="+cal.add(1, 2));
		} 
		
		@Test(priority=5)
		public void testBy() {
			
			assertEquals(cal.add(1, 0), 1, "加法计算错误");
			System.out.println("1+0="+cal.add(1, 0));
		} 

}
