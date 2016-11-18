package com.WHTest.Webdriver.utilityfunctions;

        
        import java.io.*;
        
        import static com.WHTest.Webdriver.utilityfunctions.CommonFunctionTest.com_RemoveChar;
        import static com.WHTest.Webdriver.utilityfunctions.ConstantTest.*;
        import static com.WHTest.Webdriver.utilityfunctions.ReportFunctionTest.*;
        
        
        public class ErrorFunctionTest {

            //  ##############################################################
            //  err_Handling
            //
            //  Description:
            //      Reports a test fail, called as a results of an unexpected
            //      error. Then cleans up.
            //
            //  Parameters:
            //       e - exception stack trace
            //
            //  Returns:
            //      n/a
            //
            //  Created by: Dave Lees
            //  Date created: 16th November 2016
            //
            //
            //  ##############################################################
            public static void err_Handling(Exception e) throws Exception{
                getintTestStep = 9999;
                ReportFunctionTest.rep_Report(3, "Error handler entered");
                ReportFunctionTest.rep_Report(1, "The Test has failed");
                try{
                    ReportFunctionTest.rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "Fail.jpg");
                }catch (Exception e1){
                    e.printStackTrace();
                }
                err_PrintStackTrace(e);
                //Calling the enhanced reporting function -- Mathi
                ReportFunctionTest.rep_WriteResults(strTemplatePath + "results_Template.html", strTestResultsHomeFolder);
                driver = null;
                err_Cleanup();
        
            }
        
        
        
            //  ##############################################################
            //  err_Cleanup
            //
            //  Description:
            //      Cleans up after test fails
            //
            //  Parameters:
            //      n/a
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
            public static void err_Cleanup() throws IOException {
                if (driver != null) {
                    driver.quit();
                }
                System.exit(1);
            }
        
        
        
            //  ##############################################################
            //  err_PrintStackTrace
            //
            //  Description:
            //      Prints the stack trace to file
            //
            //  Parameters:
            //      e - exception stack trace
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
            public static void err_PrintStackTrace(Exception e) throws FileNotFoundException {
                FileOutputStream strFileWrite = new FileOutputStream(new File(strTestResultsHomeFolder + "FullFailStackTrace.txt"),true);
                PrintStream strPrintStream = new PrintStream(strFileWrite);
                e.printStackTrace(strPrintStream);
                rep_Report(2, "StackTrace : " + strTestResultsHomeFolder + "FailStackTrace.txt");
        
                err_CleanupStackTrace(e);
                ReportFunctionTest.rep_WriteStack(strTemplatePath + "stack_Template.html", strTestResultsHomeFolder);
        
            }
        
        
        
            //  ##############################################################
            //  err_CleanupStackTrace
            //
            //  Description:
            //      Cleans up stack trace to include only user defined funcs
            //
            //  Parameters:
            //      e - exception stack trace
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
            public static void err_CleanupStackTrace(Exception e){
                String strTmpVar;
                int intLength = e.getStackTrace().length;
                String strPassedVars;
                StackTraceElement[] errorStack = e.getStackTrace();
        
                for (int i = 0; i < intLength; i++) {
                    strPassedVars = "";
                    StackTraceElement t = errorStack[i];
                    String strClass = t.getClassName();
                    strClass = com_RemoveChar(strClass, "$");
        
                    String strMethod = t.getMethodName();
                    strMethod = com_RemoveChar(strMethod, "$");
        
                    String strFileNme = t.getFileName();
                    strFileNme = com_RemoveChar(strFileNme, "$");
        
                    int strLineNo = t.getLineNumber();
        
        
                    for (int intCnt= 0 ; intCnt < listOfPassedVariables.size(); intCnt++) {
                        strTmpVar = (String) listOfPassedVariables.get(intCnt);
        
                        String strName_tmp[] = strTmpVar.split("-");
        
                        if ((strName_tmp[0]).equals(strMethod)){

                            if (intCnt == 0){
                                strPassedVars = ("" + strName_tmp[strName_tmp.length - 1] + ", ");
                            }else {
                                strPassedVars = strPassedVars + ("" + strName_tmp[strName_tmp.length-1] + ", ");
                            }
                        }
                    }

                    if (strFileNme.contains(strProjectName)){
                        ReportFunctionTest.rep_Stack(strClass, strMethod, strFileNme, strLineNo, strPassedVars);
                        break;
                    }

                    //remove stack trace elements which arent required
                    if (!strClass.contains("java.lang")) {
                        if(!strClass.contains("io.appium")){
                            if (!strClass.contains("org.openqa")){
                                if(!strClass.contains("sun.reflect")){
                                    if(!strClass.contains("java.util")) {
                                        if (!strClass.contains("com.intellij")){
                                            if(!strClass.contains("org.junit")) {
                                                ReportFunctionTest.rep_Stack(strClass, strMethod, strFileNme, strLineNo, strPassedVars);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        

