package com.WHTest.Webdriver.utilityfunctions;

        import org.openqa.selenium.*;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import static com.WHTest.Webdriver.utilityfunctions.CommonFunctionTest.com_Wait;
        import static com.WHTest.Webdriver.utilityfunctions.ConstantTest.*;
        import static com.WHTest.Webdriver.utilityfunctions.ErrorFunctionTest.*;
        import static com.WHTest.Webdriver.utilityfunctions.ReportFunctionTest.*;
        
        
        public class ObjectFunctionTest {
        

            //  ##############################################################
            //  obj_ClickObjectByName
            //
            //  Description:
            //     Click on object, selected by the object name
            //
            //  Parameters:
            //      strName - The object to click
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
            public static void obj_ClickObjectByLink(String strName) throws Exception {
                class Local{}
                String strFuncName = Local.class.getEnclosingMethod().getName();

                listOfPassedVariables.add (strFuncName + "-" + strName);
                try {
                    obj_WaitForObjectExistByLink(strName, 10);
                    WebElement Link = driver.findElement(By.linkText(strName));
                    Link.click();
                    ReportFunctionTest.rep_Report(2, "The object " + strName + " has been clicked.");
                    com_Wait(1);

                } catch (Exception e) {
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_"+ strName + "_NotLocated.jpg");
                    ReportFunctionTest.rep_Report(1, "The object " + strName + " has not been located.");
                    err_Handling(e);
                }
                con_RemoveFuncVar(1);
            }


            //  ##############################################################
            //  obj_ClickObjectByPartLink
            //
            //  Description:
            //     Click on object, selected by the object partial link text
            //
            //  Parameters:
            //      strName - The object to click
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
            public static void obj_ClickObjectByPartLink(String strName) throws Exception {
                class Local{}
                String strFuncName = Local.class.getEnclosingMethod().getName();

                listOfPassedVariables.add (strFuncName + "-" + strName);
                try {
                    WebElement Link = driver.findElement(By.partialLinkText(strName));
                    Link.click();
                    ReportFunctionTest.rep_Report(2, "The object " + strName + " has been clicked.");
                    com_Wait(1);
                } catch (Exception e) {
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_"+ strName + "_NotLocated.jpg");
                    ReportFunctionTest.rep_Report(1, "The object " + strName + " has not been located.");
                    err_Handling(e);
                }
                con_RemoveFuncVar(1);

            }


            //  ##############################################################
            //  obj_GetObjText_ByPartLinkText
            //
            //  Description:
            //     Gets the text of an object, selected by the partial link text
            //
            //  Parameters:
            //      strName - The object to click
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
            public static String obj_GetObjText_ByPartLinkText(String strObjName) throws Exception {
                String objValue = null;
                try{
                    WebElement objName = driver.findElement(By.partialLinkText(strObjName));
                    objValue = objName.getText();

                }catch (Exception e){
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_"+ strObjName + "_NotLocated.jpg");
                    ReportFunctionTest.rep_Report(1, "The object " + strObjName + " has not been located.");
                    err_Handling(e);
                }
                return objValue;
            }



            //  ##############################################################
            //  obj_EditBoxTypeByCSS
            //
            //  Description:
            //     inserts text in an object, selected by css
            //
            //  Parameters:
            //      strName - The object to click
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
            public static void obj_EditBoxTypeByCSS(String strSrchBox, String strSrchText)throws Exception {
                try{
                    WebElement SearchBox = driver.findElement(By.cssSelector(strSrchBox));
                    SearchBox.sendKeys(Keys.chord(strSrchText));
                    com_Wait(1);
                }catch (Exception e){
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_"+ strSrchBox + "_NotLocated.jpg");
                    ReportFunctionTest.rep_Report(1, "The object " + strSrchBox + " has not been located.");
                    err_Handling(e);
                }
            }



            //  ##############################################################
            //  obj_GetLabelByID
            //
            //  Description:
            //     gets label text of an object, selected by id
            //
            //  Parameters:
            //      strName - The object to click
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
            public static String obj_GetLabelByID(String strObjName)throws Exception {
                String objValue = null;
                try {
                    WebElement objName = driver.findElement(By.id(strObjName));
                    objValue = objName.getText();
                }catch (Exception e){
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_"+ strObjName + "_NotLocated.jpg");
                    ReportFunctionTest.rep_Report(1, "The object " + strObjName + " has not been located.");
                    err_Handling(e);
                }
                return objValue;
            }


            //  ##############################################################
            //  obj_ClickObjectById
            //
            //  Description:
            //     Clicks an object, selected by id
            //
            //  Parameters:
            //      strName - The object to click
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
            public static void obj_ClickObjectById(String strIdName)throws Exception {
                try {
                    WebElement Link = driver.findElement(By.id(strIdName));
                    Link.click();
                    com_Wait(1);
                }catch (Exception e){
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_"+ strIdName + "_NotLocated.jpg");
                    ReportFunctionTest.rep_Report(1, "The object " + strIdName + " has not been located.");
                    err_Handling(e);
                }
            }


            //  ##############################################################
            //  obj_WaitForObjectExistByLink
            //
            //  Description:
            //     Wait for an object to exist by link text
            //
            //  Parameters:
            //      strName - The object to click
            //      int - timeout time
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
            public static boolean obj_WaitForObjectExistByLink(String strName, int timeout)throws Exception{
                boolean bolObj = false;

                try{
                    WebDriverWait waitImplicit1 = new WebDriverWait(driver, timeout);
                    waitImplicit1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(strName)));
                    bolObj = true;
                    rep_Report(2, "The element with the name " + strName + " is displayed");

                }catch (Exception e){
                    e.printStackTrace();
                    rep_GetScreenshot(path_ScreenShots, strDevice + "_" + getintTestStep + "_" + "unableToLocateElement" + ".jpg");
                    rep_Report(1, "Unable to locate the element with name " + strName + "");
                }
                return bolObj;
            }

        }


