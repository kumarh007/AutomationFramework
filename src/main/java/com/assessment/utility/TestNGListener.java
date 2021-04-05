package com.assessment.utility;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.assessment.core.DriverFactory;
import com.assessment.core.TestBase;

public class TestNGListener extends TestBase  implements ITestListener{
	
	Logger logger = Logger.getLogger("AutomationFramework");

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info(result.getMethod().getConstructorOrMethod().getName()+": Test Execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logger.info(result.getMethod().getConstructorOrMethod().getName()+": Test Execution Failed");
		try {
			captureScreen(DriverFactory.getInstance().getWebDriver(),result.getMethod().getConstructorOrMethod().getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info("Test Suite Execution started"+context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		logger.info("Test Suite Execution Finished"+context.getName());
	}

}
