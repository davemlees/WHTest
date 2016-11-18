package com.WHTest.Webdriver.utilityfunctions;

        import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        
        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.nio.file.StandardOpenOption;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        
        import static com.WHTest.Webdriver.utilityfunctions.ConstantTest.*;

        
        public class ReportFunctionTest {

            private static List<ResultTest> details;
            private static List<ResultStackTest> details_Stack;
            private static final String resultPlaceholder = "<!-- INSERT_RESULTS -->";

            public static void initialize_Stack() {
                details_Stack = new ArrayList<ResultStackTest>();
            }

            public static void initialize() {
                details = new ArrayList<ResultTest>();
            }

            //    CommonFunctionTest comFunc = new CommonFunctionTest();


            //  ##############################################################
            //  rep_GetScreenshot
            //
            //  Description:
            //     Saves a screen shot
            //
            //  Parameters:
            //      outputlocation - Location to save screenshot
            //      strFileName - Name to save the screenshot as
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
            public static void rep_GetScreenshot(String outputlocation, String strFileName) throws IOException {
                File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFiler, new File(outputlocation + strFileName));
                rep_Report(2, "Screen shot saved");
            }


            //  ##############################################################
            //  rep_Report
            //
            //  Description:
            //     Report information to the result file
            //
            //  Parameters:
            //      strStatus - Report status
            //      strDetails - Report information
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
            public static void rep_Report(int intStatus, String strDetails) {
                String strStatus = null;

                switch (intStatus) {
                    case 0:
                        strStatus = "Pass";
                        break;

                    case 1:
                        strStatus = "Fail";
                        break;

                    case 2:
                        strStatus = "Info";
                        break;

                    case 3:
                        strStatus = "Step";
                        break;
                }

                strFailureSnapshotFileName = "";
                ResultTest r = new ResultTest(getintTestStep, strStatus, strDetails);
                details.add(r);
            }

            
            //  ##############################################################
            //  rep_Stack
            //
            //  Description:
            //     Report stack trace information to the stack trace report
            //
            //  Parameters:
            //      classNme - Name of class
            //      methodNme - Name of method
            //      fileNme - Name of file
            //      lineNm - Line number of fail
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
            public static void rep_Stack(String classNme, String methodNme, String fileNme, int lineNm, String PassedVars) {
                ResultStackTest rs = new ResultStackTest(classNme, methodNme, fileNme, lineNm, PassedVars);
                details_Stack.add(rs);
            }


            //  ##############################################################
            //  rep_WriteStack
            //
            //  Description:
            //     Write stack trace to stacktrace file
            //
            //  Parameters:
            //      strTemplatePath - Path of stacktrace file
            //      strOutputPath - Name of stacktrace file
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
            public static void rep_WriteStack(String strTemplatePath, String strOutputPath) {
                try {
                    String reportIn = new String(Files.readAllBytes(Paths.get(strTemplatePath)));
                    for (int i = details_Stack.size() - 1; i >= 0; i--) {
                        if (i == details_Stack.size() - 1) {
                            reportIn = reportIn.replaceFirst(resultPlaceholder, "<tr><td>" + details_Stack.get(i).getMethodNme() + "</td><td>" + details_Stack.get(i).getIntlineNm() + "</td><td>" + details_Stack.get(i).getFileNme() + "</td><td>" + details_Stack.get(i).getClassNme() + "</td></tr>" + resultPlaceholder);
                        } else {
                            //if statement to prevent duplication of test step numbers in the report
                            reportIn = reportIn.replaceFirst(resultPlaceholder, "<tr><td>" + details_Stack.get(i).getMethodNme() + "</td><td>" + details_Stack.get(i).getIntlineNm() + "</td><td>" + details_Stack.get(i).getFileNme() + "</td><td>" + details_Stack.get(i).getClassNme() + "</td><td>" + details_Stack.get(i).getPassedVars() + "</td></tr>" + resultPlaceholder);
                        }
                    }

                    String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());


                    String currentTime = CommonFunctionTest.com_CurrentTime();
                    String reportPath = strOutputPath + "\\Appium_stackTrace_" + currentDate + "_" + currentTime + ".html";

                    Files.write(Paths.get(reportPath), reportIn.getBytes(), StandardOpenOption.CREATE);

                } catch (Exception e) {
                    System.out.println("Error when writing report file:\n" + e.toString());
                }
            }


            //  ##############################################################
            //  rep_WriteResults
            //
            //  Description:
            //     Write results file
            //
            //  Parameters:
            //      strTemplatePath - Path of results file
            //      strOutputPath - Name of results file
            //
            //  Returns:
            //      n/a
            //
            //  Created by: 16th November 2016
            //  Date created: 9th October 2015
            //
            //
            //  ##############################################################


            public static void rep_WriteResults(String strTemplatePath, String strOutputPath) {
                String strStatus;
                strStatus = "Pass";
                intPassCount = 0;
                intFailCount = 0;
                intInfoCount = 0;
                try {
                    String reportIn = new String(Files.readAllBytes(Paths.get(strTemplatePath)));
                    for (int i = 0; i < details.size(); i++) {
                        //if statement to prevent duplication of test step numbers in the report
                        if (i == 0) {


                            if (details.get(i).getResult().equals("Pass")) {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=center width=100><font color=\"green\">" + details.get(i).getResult() + "</font></td><td align=left width=1040>" +
                                        details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intPassCount++;
                            } else if (details.get(i).getResult().equals("Fail")) {
                                strStatus = "Fail";
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=center width=100><font color=\"red\">" + details.get(i).getResult() + "</font></td><td>" +
                                        details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intFailCount++;
                            } else if (details.get(i).getResult().equals("Step")) {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=left width=100><font color=\"blue\">" + details.get(i).getResult() + "</font></td><td><font color=\"blue\">" +
                                        details.get(i).getResultText() + "</font></td></tr>" + resultPlaceholder);

                            } else {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=center width=100><font color=\"black\">" + details.get(i).getResult() + "</font></td><td>" + details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intInfoCount++;
                            }


                        } else if (details.get(i).getintTestStep() == details.get(i - 1).getintTestStep()) {
                            if (details.get(i).getResult().equals("Pass")) {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        "" + "</td><td align=center width=100><font color=\"green\">" + details.get(i).getResult() + "</font></td><td align=left width=1040>" +
                                        details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intPassCount++;
                            } else if (details.get(i).getResult().equals("Fail")) {
                                strStatus = "Fail";
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        "" + "</td><td align=center width=100><font color=\"red\">" + details.get(i).getResult() + "</font></td><td>" +
                                        details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intFailCount++;
                            } else {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        "" + "</td><td align=center width=100><font color=\"black\">" + details.get(i).getResult() + "</font></td><td>" + details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intInfoCount++;
                            }
                        } else {
                            if (details.get(i).getResult().equals("Pass")) {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=center width=100><font color=\"green\">" + details.get(i).getResult() + "</font></td><td align=left width=1040>" +
                                        details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intPassCount++;
                            } else if (details.get(i).getResult().equals("Fail")) {
                                strStatus = "Fail";
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=center width=100><font color=\"red\">" + details.get(i).getResult() + "</font></td><td>" +
                                        details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intFailCount++;
                            } else if (details.get(i).getResult().equals("Step")) {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=left width=100><font color=\"blue\">" + details.get(i).getResult() + "</font></td><td><font color=\"blue\">" +
                                        details.get(i).getResultText() + "</font></td></tr>" + resultPlaceholder);
                            } else {
                                reportIn = reportIn.replaceFirst(resultPlaceholder, "</td><td align=center width=60>" +
                                        details.get(i).getintTestStep() + "</td><td align=center width=100><font color=\"black\">" + details.get(i).getResult() + "</font></td><td>" + details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
                                intInfoCount++;
                            }
                        }

                    }
                    reportIn = reportIn.replaceFirst(resultPlaceholder, "</table>");
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());


                    String currentTime = CommonFunctionTest.com_CurrentTime();
                    String reportPath = strOutputPath + "\\report_" + currentDate + "_" + currentTime + "_" + strStatus + ".html";

                    Files.write(Paths.get(reportPath), reportIn.getBytes(), StandardOpenOption.CREATE);

                } catch (Exception e) {
                    System.out.println("Error when writing report file:\n" + e.toString());
                }
            }

        }