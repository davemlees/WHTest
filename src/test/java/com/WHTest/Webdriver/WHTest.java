package com.WHTest.Webdriver;

import com.WHTest.Webdriver.utilityfunctions.CommonFunctionTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


import static com.WHTest.Webdriver.utilityfunctions.CommonFunctionTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ConstantTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ErrorFunctionTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ObjectFunctionTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ReportFunctionTest.*;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


public class WHTest {

    @Before
    public void setUp() throws Exception {
        exeStartTime = System.currentTimeMillis();
        testType = "Regression Test";
        strTestScriptName = this.getClass().getSimpleName();
        dir_Tmp = System.getProperty("user.dir");
        con_Setup();
    }
            
    @Test
    public void main() throws Exception {
        try {
            //Test step 1
            getintTestStep++;
            rep_Report(3, "Open Browser and navigate");

                CommonFunctionTest.com_Navigate(strUrl, "Online Betting from William Hill - The Home of Betting");
                com_Wait(1);


            //Test step 2
            getintTestStep++;
            rep_Report(3, "Navigate to Premiership football event");

                obj_ClickObjectByLink("Football");
                obj_ClickObjectByLink("Competitions");
                com_Wait(2);


            //Test step 3
            getintTestStep++;
            rep_Report(3, "Select event and place a £0.05 bet for the home team to ‘Win’");

                String strTeams = obj_GetObjText_ByPartLinkText(strBet);

                String[] arrTeams = strTeams.split("\n");

                rep_Report(3, "You have chosen the match: " + arrTeams[0] + " Verses " + arrTeams[1]);

                obj_ClickObjectByPartLink(strBet);
                obj_ClickObjectByLink("Popular");
                String strOdds = obj_GetLabelByID("OB_OU1381515604");
                String strWin = com_CalculateWinnings(strOdds, strStake);
                obj_ClickObjectById("OB_OU1381515604");
                obj_ClickObjectByLink("Bet Slip");
                obj_EditBoxTypeByCSS("input[name=\"English Premier League\"]", strStake);


            //Test step 4
            getintTestStep++;
            rep_Report(3, "Place bet and assert the odds and returns offered");

                obj_ClickObjectById("place-bet-button");
                String strReturns = obj_GetLabelByID("estimated-returns_1381515604L");
                com_CompareStringsAndReport(strReturns, strWin);

                String strTakenOdds = obj_GetLabelByID("bet-price_1381515604L");
                com_CompareStringsAndReport(strTakenOdds, strOdds);

            //Test step 5
            getintTestStep++;
            rep_Report(3, "Test step to clean up");
                rep_WriteResults(strTemplatePath + "results_Template.html", strTestResultsHomeFolder);


        }catch(Exception e){
            err_Handling(e);
        }
            
    }
            
    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}