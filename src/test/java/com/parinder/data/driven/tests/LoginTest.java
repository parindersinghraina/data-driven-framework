package com.parinder.data.driven.tests;

import com.parinder.data.driven.pages.login.LoginPage;
import com.parinder.data.driven.utils.excelutils.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class LoginTest extends BaseTest {

    @BeforeTest
    public void setupTestData() {
        ExcelUtil.setExcelFileSheet("LoginData");
    }

    @Test(description = "Verify that the user login to linkedin ")
    public void testLogin()  {
        setup();
        LoginPage loginPage = new LoginPage(this.driver, this.config);
        loginPage.get();
        loginPage.enterUserName(this.config.getSecret("TEST_USERNAME"));
        loginPage.enterPassword(this.config.getSecret("TEST_PASSWORD"));
    }

    @Test(description = "Verify that the user login to linkedin with excel data")
    public void testLoginWithExcelData()  {
        this.loginToLinkedin(ExcelUtil.getRowData(1));
    }


    public void loginToLinkedin(XSSFRow row)  {
        setup();
        LoginPage loginPage = new LoginPage(this.driver, this.config);
        loginPage.get();
        loginPage.enterUserName(row.getCell(1).toString());
        loginPage.enterPassword(row.getCell(2).toString());
    }
}
