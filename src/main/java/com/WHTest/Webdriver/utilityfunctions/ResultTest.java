package com.WHTest.Webdriver.utilityfunctions;

public class ResultTest {
    
                private int intTestStep;
                private String result;
                private String resultText;
                private String filePath;
            
                public ResultTest(int intTestStep, String result, String resultText) {
                    this.result = result;
                    this.resultText = resultText;
                    this.intTestStep = intTestStep;
                }
                       
                public void setResult(String result) {
                    this.result = result;
                }
            
                public String getResult() {
                    return this.result;
                }
            
                public void setResultText(String resultText) {
                    this.resultText = resultText;
                }
            
                public String getResultText() {
                    return this.resultText;
                }
            
                public void setintTestStep(int intTestStep) {
                    this.intTestStep = intTestStep;
                }
            
                public int getintTestStep() {
                    return this.intTestStep;
                }
            
                public String getFilePath() {
                    return this.filePath;
                }
            }

