package com.WHTest.Webdriver.utilityfunctions;
        
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.WHTest.Webdriver.utilityfunctions.ConstantTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ErrorFunctionTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ObjectFunctionTest.*;
import static com.WHTest.Webdriver.utilityfunctions.ReportFunctionTest.*;


        public class CommonFunctionTest {
        
            //  ##############################################################
            //  com_CreateFolder
            //
            //  Description:
            //     Creates a folder
            //
            //  Parameters:
            //      strPath ? Path to folder
            //      strFolder - Folder to check
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_CreateFolder(String strPath, String strFolder) {
                class Local {};
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + strPath);
                listOfPassedVariables.add(strFolder);
        
                //Check if the folder exists
                Boolean bolExists = com_FolderExists(strPath, strFolder);
        
                if (bolExists == false) {
        
                    boolean booFileCreated = (new File(strPath + strFolder).mkdirs());
                    if (booFileCreated != true) {
                        ReportFunctionTest.rep_Report(1, "Failed to create the folder : " + strFolder);
                    } else{
                        ReportFunctionTest.rep_Report(2, "Folder created : " + strFolder);
                    }
                } else {
                    ReportFunctionTest.rep_Report(2, "Folder, " + strFolder + " already exists.");
                }
                con_RemoveFuncVar(2);
            }
        
        

            //  ##############################################################
            //  com_DeleteFolder
            //
            //  Description:
            //     Deletes a folder
            //
            //  Parameters:
            //      strPath ? Path to folder
            //      strFolder - Folder to check
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_DeleteFolder(String strPath, String strFolder) {
                class Local {};
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + strPath);
                listOfPassedVariables.add(strFuncName + "-" + strFolder);
        
                Boolean bolExists = com_FolderExists(strPath, strFolder);
                String strDirPath = strPath + strFolder;
                if (bolExists == true) {
                    try {
                        File directory = new File(strDirPath);
                        FileUtils.deleteDirectory(directory);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ReportFunctionTest.rep_Report(2, "Folder, " + strFolder + " doesnt exists.");
                }
                con_RemoveFuncVar(2);
            }
        
        
            //  ##############################################################
            //  com_FolderExists
            //
            //  Description:
            //     Checks a folder exists
            //
            //  Parameters:
            //      strPath ? Path to folder
            //      strFolder - Folder to check
            //
            //  Returns:
            //      true or false
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static Boolean com_FolderExists(String strPath, String strFolder) {
                class Local {};
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + strPath);
                listOfPassedVariables.add(strFuncName + "-" + strFolder);
                Boolean booFolderEx;
        
                File strFile = new File(strPath + strFolder);
                if (strFile.isDirectory()) {
                    con_RemoveFuncVar(2);
                    return booFolderEx = true;
                } else {
                    con_RemoveFuncVar(2);
                    return booFolderEx = false;
                }
            }
        
        
            //  ##############################################################
            //  com_DateTimeStamp
            //
            //  Description:
            //     Creates a date time stamp string
            //
            //  Parameters:
            //      n/a
            //
            //  Returns:
            //      date time stamp
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static String com_DateTimeStamp() {
                Date myDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                String myDateString = sdf.format(myDate);
        
                return myDateString;
            }
        
        

        

            //  ##############################################################
            //  com_CompareStringsAndReport
            //
            //  Description:
            //     Compares two strings then reports status
            //
            //  Parameters:
            //      strText1 - First string to compare
            //      strText2 - Second string to compare
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_CompareStringsAndReport(String strTextAct, String strTextExp) {
                class Local {}
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + strTextAct);
                listOfPassedVariables.add(strFuncName + "-" + strTextExp);
                try{
                    if (strTextExp.equals(strTextAct)){
                        ReportFunctionTest.rep_Report(0, "Strings match :" + strTextAct);
                    }
                    else{
                        rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_CompareString.jpg");
                        ReportFunctionTest.rep_Report(1, "Strings do not match. Actual :" + strTextAct + ", Expected :" + strTextExp);
                    }
                }catch (Exception e){
                    ReportFunctionTest.rep_Report(1, "Unable to compare the strings. Actual :" + strTextAct + ", Expected :" + strTextExp);
                }
        
                con_RemoveFuncVar(2);
            }


            //  ##############################################################
            //  com_CalculateWinnings
            //
            //  Description:
            //     Calculates winnings based on odds and stake
            //
            //  Parameters:
            //      strOdds - Odds of the bet
            //      strBetStake - stake of the bet
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static String com_CalculateWinnings (String strOdds, String strBetStake)throws Exception{
                String strWinnings = null;
                double douWinnings;
                try{
                    String[] arrOdds = strOdds.split("/");
                    double douOdd1 = Double.parseDouble(arrOdds[0]);
                    double douOdd2 = Double.parseDouble(arrOdds[1]);
                    double dobStake = Double.parseDouble(strBetStake);
                    douWinnings = ((douOdd1/douOdd2)* dobStake)+ dobStake;
                    strWinnings = Double.toString(douWinnings);

                } catch (Exception e){
                    err_Handling(e);
                }
                return strWinnings;
            }
        
            //  ##############################################################
            //  com_CurrentTime
            //
            //  Description:
            //     get the current time and date
            //
            //  Parameters:
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static String com_CurrentTime(){
                Calendar cal = Calendar.getInstance();
                cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
                return sdf.format(cal.getTime());
            }
        
        
        
            //  ##############################################################
            //  com_Wait
            //
            //  Description:
            //     Wait for a period of time; defined or default
            //
            //  Parameters:
            //      intWait - Time to wait in seconds
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_Wait(int intWait){
                class Local {}
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + intWait);
        
                if (!(intWait > 0 )){
                    intWait = int_DefaultWaitTime;
                }
                else{
                    intWait = intWait * 1000;
                }
        
                int intMillie = (int) System.currentTimeMillis();
                int intNewTime = intMillie + intWait;
        
                do{
                    intMillie = (int) System.currentTimeMillis();
                }while (intMillie < intNewTime);
        
                con_RemoveFuncVar(1);
            }
        
        
        
            //  ##############################################################
            //  com_RemoveChar
            //
            //  Description:
            //     Removes specified character from a string
            //
            //  Parameters:
            //      strValue - String value containing character
            //      strChar - Character to remove
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static String com_RemoveChar(String strValue, String strChar){
                class Local {}
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + strValue);
                listOfPassedVariables.add(strFuncName + "-" + strChar);
        
                String strValue_New;
                if (strValue.contains(strChar)){
                    strValue_New = strValue.replace(strChar,"");
                    con_RemoveFuncVar(2);
                    return strValue_New;
                }else{
                    con_RemoveFuncVar(2);
                    return strValue;
                }
            }
        
        
            //  ##############################################################
            //  com_CheckFileExistAndAppend
            //
            //  Description:
            //     Append test run file with results folder path
            //
            //  Parameters:
            //      strFilePath - Path to append file with
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_CheckFileExistAndAppend(String strFilePath) throws IOException {
                class Local {}
                String strFuncName = Local.class.getEnclosingMethod().getName();
        
                listOfPassedVariables.add(strFuncName + "-" + strFilePath);
        
                File f = new File(strFilePath);
                File fileRunnerExists = new File ("C:\\Environments\\testrunfile.txt");
                if(f.exists() && !f.isFile()) {
                   if (fileRunnerExists.exists()) {
                       FileWriter fw = new FileWriter("C:\\Environments\\testrunfile.txt", true); //the true will append the new data
                       fw.write(strFilePath);//appends the string to the file
                       fw.close();
                   }
                }
                con_RemoveFuncVar(1);
            }
        

            //  ##############################################################
            //  com_PrintValue_Text
            //
            //  Description:
            //     print out text
            //
            //  Parameters:
            //      strName - text to print out
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_PrintValue_Text(String strName) {
                System.out.println(strName);
            }
        
        
        
            //  ##############################################################
            //  com_Navigate
            //
            //  Description:
            //     Navigate to url in web browser
            //
            //  Parameters:
            //      strUrl - strURL to navigate to.
            //      strAssertVal - name of page
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_Navigate(String strURL, String strAssertVal) {
                driver.navigate().to(strURL);
        
                com_MaximiseBrowser();
        
                String strTitle = driver.getTitle();
                com_PrintValue_Text(strTitle);

                com_CompareStringsAndReport(strTitle, strAssertVal);
            }
        
        
        
            //  ##############################################################
            //  com_MaximiseBrowser
            //
            //  Description:
            //     Maximise the browser
            //
            //  Parameters:
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //  Updates:
            //
            //  ##############################################################
            public static void com_MaximiseBrowser() {
                driver.manage().window().maximize();
            }

        }
        

