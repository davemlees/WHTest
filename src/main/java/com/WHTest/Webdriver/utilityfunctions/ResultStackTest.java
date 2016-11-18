package com.WHTest.Webdriver.utilityfunctions;

        public class ResultStackTest {
            private int intlineNm;
            private String classNme;
            private String methodNme;
            private String fileNme;
            private String PassedVars;
        
        
            public ResultStackTest(String classNme, String methodNme, String fileNme, int intlineNm, String PassedVars) {
                this.classNme = classNme;
                this.methodNme = methodNme;
                this.fileNme = fileNme;
                this.intlineNm = intlineNm;
                this.PassedVars = PassedVars;
            }
        
            public void setClassNme(String classNme) {
                this.classNme = classNme;
            }
        
            public String getClassNme() {
                return this.classNme;
            }
        
            public void setMethodNme(String methodNme) {
                this.methodNme = methodNme;
            }
        
            public String getMethodNme() {
                return this.methodNme;
            }
        
            public void setFileNme(String fileNme) {
                this.fileNme = fileNme;
            }
        
            public String getFileNme() {
                return this.fileNme;
            }
        
            public void setIntlineNm(int intlineNm) {
                this.intlineNm = intlineNm;
            }
        
            public int getIntlineNm() {
                return this.intlineNm;
            }
        
            public void setPassedVars(String PassedVars) {
                this.PassedVars = PassedVars;
            }
        
            public String getPassedVars() {
                return this.PassedVars;
            }
        
        }

