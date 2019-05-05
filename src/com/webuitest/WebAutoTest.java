package com.webuitest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import com.google.common.io.Files;

/**
 * selenium+java 页面元素定位与操作
 * @author 30371
 *
 */

public class WebAutoTest {
	
	/**
	 * 用例：启动火狐浏览器，打开“https://www.baidu.com/”，在搜索框，输入“taobao”，点击搜索，打印出新页面的标题；
	 *     然后在搜索框输入"qq"，点击搜索，打印新页面的标题
	 * 元素定位：通过元素id定位元素     
	 * @throws InterruptedException  
	 */
	
	@Test
	public void testByid() throws InterruptedException {
		
		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		
		//启动浏览器
		WebDriver driver=new FirefoxDriver();
		//打开页面
		driver.get("https://www.baidu.com/");
		
		WebElement element=driver.findElement(By.id("kw"));
		element.sendKeys("taobao");
		WebElement element2=driver.findElement(By.id("su"));
		element2.click();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		
		element.clear();		
		Thread.sleep(3000);
		
		//可以合并为一句
		driver.findElement(By.id("kw")).sendKeys("qq");	
		driver.findElement(By.id("su")).click();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		
		//关闭浏览器
//		driver.close(); //关闭当前浏览器
		driver.quit();	//关闭所有浏览器
			
	}
	
	
	/**
	 * 用例：启动火狐浏览器，打开“https://www.163.com/”，打印页面的标题；
	 * 元素定位：通过元素cssSelector之类选择器定位元素     		
	 */
	@Test
	public void testBycssClass() {
		
		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");

//		启动浏览器
		FirefoxDriver driver=new FirefoxDriver();
		
		driver.get("https://www.163.com/");
		
		String titleString=driver.findElement(By.cssSelector("a.ntes-nav-login-title")).getText();
		System.out.println(titleString);
		
		driver.quit();	//关闭所有浏览器				
		
	}
	

	/**
	 * 用例：启动火狐浏览器，打开“https://www.baidu.com/”，在搜索框输入"qq"，点击搜索，打印出新页面的标题
	 * 元素定位：通过元素CSS之属性选择器定位元素     
	 */
	@Test
	public void testCssAttribute() {
		
		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		
		//启动浏览器
		WebDriver driver=new FirefoxDriver();
		//打开页面
		driver.get("https://www.baidu.com/");
		
		driver.findElement(By.id("kw")).sendKeys("qq");
		driver.findElement(By.cssSelector("input[id=\"su\"]")).click(); //cssSelector之属性选择器
//		driver.findElement(By.id("su")).click();
		
		String title = driver.getTitle();
		System.out.println(title);
		
		//关闭浏览器
		driver.quit();	//关闭所有浏览器
		
	}
	
	/**
	 * 
	 * 用例：启动火狐浏览器，打开“http://cn.bing.com/”，在搜索框输入"AI"，点击搜索，打印出新页面的标题
	 * 元素定位：通过xpath定位元素   
	 *  //tag[@attr=attr]  
	 *  driver.findElement(By.xpath("//input[@id='sb_form_go']"))
	 */	
//	 	Xpath定位元素
		@Test
		public void testXpath(){
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//打开页面
			driver.get("http://cn.bing.com/");
			//在搜索框输入“AI”
			driver.findElement(By.xpath("//input[@id=\"sb_form_q\"]")).sendKeys("AI");
			//点击“搜索”
//			driver.findElement(By.xpath("//input[@id=\"sb_form_go\"]")).click();
			driver.findElement(By.xpath("//input[@id='sb_form_go']")).click();
			//打印页面标题
			System.out.println(driver.getTitle());
			
			driver.quit();	//关闭所有浏览器	
		}

		
		/**
		 * 下拉选择框测试
		 * 1、通过索引值选择  
		 * 2、通过值选择
		 * 3、通过可见文本选择
		 *     
		 */	
		@Test
		public void testSelect() throws InterruptedException {
			
			System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			WebDriver driver = new FirefoxDriver();		
			driver.get("http://ww.hao123.com/edu");
			//浏览器最大化
			driver.manage().window().maximize();
			Select selectObject = new Select(driver.findElement(By.id("score-college")));
			
			//索引值从0开始
			selectObject.selectByIndex(2);
			List<WebElement> allSelectOptions = selectObject.getAllSelectedOptions();
			for(WebElement e:allSelectOptions) {
				System.out.println(e.getText());
			}
			Thread.sleep(3000);
			
			
			selectObject.selectByValue("北京邮电大学");
			
			List<WebElement> allSelectOptions1 = selectObject.getAllSelectedOptions();
			for(WebElement e:allSelectOptions1) {
				System.out.println(e.getText());
			}
			
			Thread.sleep(3000);
			
			
			selectObject.selectByVisibleText("北京大学");
			List<WebElement> allSelectOptions2 = selectObject.getAllSelectedOptions();
			for(WebElement e:allSelectOptions2) {
				System.out.println(e.getText());
			}
			
			driver.quit();	//关闭所有浏览器	
			
		}
		
		/**
		 * Windows 和 Frames之间的切换	
		 * driver.switchTo().frame("f1");//切换到frame f1
		 * @throws InterruptedException
		 * iframe:是文档中的文档（页面中的页面），或者浮动的框架.iframe测试
		 */

		@Test
		public void testFrame() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			//窗口最大化
			driver.manage().window().maximize();
			//切换页面
			driver.switchTo().frame("f1");
			driver.findElement(By.id("btn")).click();			
			Thread.sleep(3000);
			driver.quit();	//关闭所有浏览器	
		}
		
		
		/**
		 * Windows 和 Windows窗口之间的切换	
		 * Set<String> handles = driver.getWindowHandles();
		 * ArrayList<String> tabs =new ArrayList<>(handles);
		 * driver.switchTo().window(tabs.get(1));
		 * 
		 * @throws InterruptedException
		 */
		
		@Test
		public void testSwitchWindows() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			//窗口最大化
			driver.manage().window().maximize();
			driver.findElement(By.linkText("百度")).click();
			Thread.sleep(2000);

			
//			String [] handles = new String [driver.getWindowHandles().size()];
//			//获取窗口句柄
//			for(String title:driver.getWindowHandles()) {
//				System.out.println(" ----title----:" + title);
//			}
//			
//			handles = driver.getWindowHandles().toArray(handles);
//			driver.switchTo().window(handles[1]);
//			Thread.sleep(1000);
//			System.out.println("新窗口标题："+ driver.getTitle());
//		
//   		获得句柄
			for(String s : driver.getWindowHandles()) {
				System.out.println(s);
			}
			
			//切换窗口
			Set<String> handles = driver.getWindowHandles();
			ArrayList<String> tabs =new ArrayList<>(handles);
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
			System.out.println("新窗口标题："+ driver.getTitle());
			Thread.sleep(3000);
//   		获得句柄
			for(String s : driver.getWindowHandles()) {
				System.out.println(s);
			}			

//			
//			Thread.sleep(3000);
//			driver.quit();	//关闭所有浏览器	
		}
		
		
		/**
		 * 弹出对话框操作
		 * 
		 * 切换到对话框
		 * Alert alert=driver.switchTo().alert();
		 * 
		 * alert.getText();// 获取弹出框文本
		 * alert.accept();//确定
		 * alert.dismiss();//取消	
		 * 
		 * @throws InterruptedException
		 */		
		@Test
		public void testAlert() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			//窗口最大化
			driver.manage().window().maximize();
			//切换页面
			driver.switchTo().frame("f2");
			driver.findElement(By.id("btn")).click();	
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
		
			alert.accept();//对话框确定
//			alert.dismiss();//对话框取消	
			
			driver.switchTo().defaultContent();//切换到上一层表单
			boolean result=driver.getPageSource().contains("try");
			System.out.println(result);
			Thread.sleep(3000);
//			driver.quit();	//关闭所有浏览器	
		}

		
		/**
		 * 获取控件状态
		 * 
		 * WebElement element1 = driver.findElement(By.name("username"));
		 * System.out.println(element1.isEnabled()); //检查控件是否enable
		 * WebElement element2 = driver.findElement(By.id("rd-1"));
		 * System.out.println(element2.isSelected());  //检查控件是否选择
		 * element2.isDisplayed();  //检查控件是否显示
		 * 
		 * @throws InterruptedException
		 */	
		@Test
		public void testStatus() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//窗口最大化
			driver.manage().window().maximize();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			WebElement element1 = driver.findElement(By.name("username"));
			System.out.println(element1.isEnabled());

			WebElement element2 = driver.findElement(By.id("rd-1"));
			System.out.println(element2.isSelected());
			Thread.sleep(3000);
			driver.quit();	//关闭所有浏览器	
		}
		
		
		/**
		 * 单选项（radio）和多选项(checkbox)操作
		 * 
		 * WebElement radio=driver.findElement(By.id("rd-1")); //单选项按钮定位
		 * radio.click();　　　　   //选择某个单选项
		 * radio.clear();　　　　  //清空某个单选项
		 * radio.isSelected();　//判断某个单选项是否已经被选择
		 * 
		 * WebElement checkBox1 =driver.findElement(By.id("cb-1")); //多选项按钮定位
		 * checkbox.click();   
		 * checkbox.clear();
		 * checkbox.isSelected();
		 * checkbox.isEnabled();
		 * 
		 */
		@Test
		public void radioCheckbox() {
			System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			WebDriver driver = new FirefoxDriver();		
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			
			WebElement radio1=driver.findElement(By.id("rd-1"));
			System.out.println("radio1 is selected:"+radio1.isSelected());	
			
			WebElement radio2=driver.findElement(By.id("rd-2"));
			radio2.click();
			System.out.println("radio2 is selected:"+radio2.isSelected());
			System.out.println("radio1 is selected:"+radio1.isSelected());	
			
			WebElement checkBox1 =driver.findElement(By.id("cb-1"));
			checkBox1.click();
			System.out.println("checkbox1 is selected:"+checkBox1.isSelected());
			
			WebElement checkBox2 =driver.findElement(By.id("cb-2"));
			checkBox2.click();		
			System.out.println("checkbox1 is selected:"+checkBox1.isSelected());
			System.out.println("checkbox2 is selected:"+checkBox2.isSelected());	
			
			
		}
		
		
		/**
		 * 浏览器导航navigation按钮的操作
		 * driver.navigate().back();	 //页面返回导航
		 * driver.navigate().forward();  //页面前进导航
		 * driver.navigate().refresh();  //页面导航刷新功能
		 * 
		 * @throws InterruptedException
		 */
		@Test
		public void navigateTest() throws InterruptedException {
			System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");
			ChromeDriver driver = new ChromeDriver();


//			System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
//			WebDriver driver = new FirefoxDriver();

			Thread.sleep(5 * 1000);
			driver.get("https://cn.bing.com/");
			System.out.println("首页面标题是："+driver.getTitle());
			
			WebElement element = driver.findElement(By.id("sb_form_q"));
			element.sendKeys("海贼王");
			
			WebElement goElement = driver.findElement(By.id("sb_form_go"));
			goElement.click();
			Thread.sleep(5 * 1000);
			System.out.println("搜索页面标题是："+driver.getTitle());
			
			driver.navigate().back();		//页面返回导航
			Thread.sleep(5 * 1000);
			System.out.println("返回页面标题是："+driver.getTitle());
			
			driver.navigate().forward();    //页面前进导航
			Thread.sleep(5 * 1000);		
			System.out.println("前进页面标题是："+driver.getTitle());
			
			driver.navigate().refresh();   //页面导航刷新功能
			
			Thread.sleep(5 * 1000);	
			
			driver.quit();
			

		}
		
		/**
		 * 上传文件的元素操作和执行JS脚本
		 * 上传文件：
		 * driver.findElement(By.xpath("//input[@type='file']")).sendKeys("E:\\images\\spring.png");
		 * 
		 * 执行JS脚本：
		 * Web driver对Java Script的调用是通过JavascriptExecutor来实现的，例如：
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("JS脚本");
		 * @throws InterruptedException
		 */
		//
		@Test
		public void testUpload() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//窗口最大化
			driver.manage().window().maximize();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			Thread.sleep(3000);
//			driver.findElement(By.cssSelector("body > input:nth-child(22)")).sendKeys("E:\\\\images\\\\spring.png");
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys("E:\\images\\spring.png");
			
			//执行JS脚本  
			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("document.write(\"hello\")");
					
			long num = (long)js.executeScript("var links=document.getElementsByTagName('a');"
					+ "return links.length");
			System.out.println(num);
			Thread.sleep(3000);
			driver.quit();	//关闭所有浏览器	
		}
		
		
		/**
		 * 获取Tag标签
		 * List<WebElement> list = driver.findElements(By.tagName("a"));
		 * System.out.println(list.size()); //个数
		 * for (WebElement e : list) {
		 * System.out.println(e.getAttribute("href")); //依次打印出属性为href的值
		 * }
		 * @throws InterruptedException
		 */
		@Test
		public void testFindTag() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//窗口最大化
			driver.manage().window().maximize();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			List<WebElement> list = driver.findElements(By.tagName("a"));
			System.out.println(list.size());
			for (WebElement e : list) {
				System.out.println(e.getAttribute("href"));
				
			}
			Thread.sleep(3000);
			driver.quit();	//关闭所有浏览器	
		}
		
		
		/**
		 * 截屏
		 * @throws InterruptedException
		 */
		
		@Test
		public void testScreenshort() throws InterruptedException{
			
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");			
			//启动浏览器
			WebDriver driver=new FirefoxDriver();
			//窗口最大化
			driver.manage().window().maximize();
			//打开页面
			driver.get("file:///E:/%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91%E8%AF%AD%E8%A8%80/testngjava.html");
			
			File source_file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//置于temp路径

			Thread.sleep(3000);
         	driver.quit();	//关闭所有浏览器	
		}
		
		
		/**
		 * 获取一类相似元素的集合
		 * List<WebElement> eList = driver.findElements(By.cssSelector("#tab-news-01>ul:nth-child(2)>li>a"));
		 */
		
		@Test
		public void testList() {
			
			System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			WebDriver driver = new FirefoxDriver();		
			driver.get("https://www.qq.com/");
			
//			WebElement headlines = driver.findElement(By.id("tab-news-01"));
			List<WebElement> eList = driver.findElements(By.cssSelector("#tab-news-01>ul:nth-child(2)>li>a"));
			for (WebElement e : eList) {
				System.out.println(e.getText()+"URL:"+e.getAttribute("href"));
				
			}

		}

	
}
