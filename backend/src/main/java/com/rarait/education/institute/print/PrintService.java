package com.rarait.education.institute.print;

import javax.print.PrintException;
import java.io.FileNotFoundException;

public interface PrintService {
public void printFile() throws PrintException, FileNotFoundException;

    void getTitle();
}
