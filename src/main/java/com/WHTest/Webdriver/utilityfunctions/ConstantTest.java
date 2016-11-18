package com.WHTest.Webdriver.utilityfunctions;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.w3c.dom.Document;
        
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import java.io.File;
        import java.util.ArrayList;

        import static com.WHTest.Webdriver.utilityfunctions.CommonFunctionTest.*;


        // contains all the global variables
        public class ConstantTest {
            public static int getintTestStep = 0;

            public static WebDriver driver;
        
            //define paths
            public static String strTestScriptName;
            public static String dir_Tmp;
            public static String strUser = System.getProperty("user.name");
            public static String path_UserPath = "C:/Users/" + strUser + "/";
            public static String path_Project = path_UserPath + "IdeaProjects/WHTest/Documents/";
            public static String path_ResFolder = path_Project + "Results";
            public static String Path_ResFolder_CurrentUser = path_ResFolder + "\\" + strUser + "\\";
            public static String path_envFile = path_Project + "environments.xml";
        
            //the location of the report and stacktrace templates
            public static String strTemplatePath;
            public static String strTestResultsHomeFolder;
            public static String path_ScreenShots;
            public static String strDevice;
            public static String strProjectName;
            public static String strFailureSnapshotFileName;
            public static int intPassCount;
            public static int intFailCount;
            public static int intInfoCount;
            public static long exeStartTime;
            public static String testType;

            public static String strUrl;
            public static String strBet;
            public static String strStake;
        
            public static Document environments;
            public static File file;
            public static File classpathRoot;
        
            public static int int_DefaultWaitTime = 100;
        
            public static ArrayList<Object> listOfPassedVariables = new ArrayList<Object>();
        
        
            public static void con_Setup() throws Exception {
                String dir_Tmp1 = dir_Tmp.replace("\\", "/");
                String[] arr_Tmp = dir_Tmp1.split("/");

                driver = new FirefoxDriver();

                classpathRoot = new File(path_UserPath);
        
                //to use with stack trace
                strProjectName = arr_Tmp[arr_Tmp.length-1];
        
                //initialize reporting and stacktrace reports
                ReportFunctionTest.initialize();
                ReportFunctionTest.initialize_Stack();
        
                file = new File(path_envFile);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
                environments = (Document) documentBuilder.parse(file);
                strUrl = environments.getElementsByTagName("url").item(0).getTextContent();
                strBet = environments.getElementsByTagName("bet").item(0).getTextContent();
                strStake = environments.getElementsByTagName("stake").item(0).getTextContent();

        
                ReportFunctionTest.rep_Report(3, "Setup of paths and associations");
        
                String strTDStamp = com_DateTimeStamp();
                ReportFunctionTest.rep_Report(2, "Time date stamp : " + strTDStamp);
        
                String strUsrDirPath = System.getProperty("user.dir");
                String[] arrTestCasePath = strUsrDirPath.split("\\\\", -1);
        
                int arrLength = arrTestCasePath.length;
                String strTestName = arrTestCasePath[arrLength - 1];

                com_CreateFolder(path_ResFolder, strUser);

                String strTestResultsFolder = strTestName + "_" + strTDStamp;
                strTestResultsHomeFolder = Path_ResFolder_CurrentUser + strTestResultsFolder + "\\" + strTestScriptName + "\\";
                ReportFunctionTest.rep_Report(2, "Results Folder : " + strTestResultsFolder);
                com_CreateFolder(Path_ResFolder_CurrentUser, strTestResultsFolder);
                com_CreateFolder(strTestResultsHomeFolder, "Screenshots");
                path_ScreenShots = strTestResultsHomeFolder + "Screenshots/";
                com_CheckFileExistAndAppend(strTestResultsHomeFolder);

                strTemplatePath = dir_Tmp1 + "/utils/";
                com_Wait(20);
            }
        
            public static void con_RemoveFuncVar(int intPassedCnt){
        
                listOfPassedVariables.size();
        
                for(int intCnt = 0; intCnt < intPassedCnt; intCnt++){
                    listOfPassedVariables.remove(listOfPassedVariables.size()-1);
                }
            }
        
        
        }
