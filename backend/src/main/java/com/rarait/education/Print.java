package com.rarait.education;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class Print {  public static void main(String args[]) throws PrinterException, IOException
{
    String filename = "C:\\\\Users\\\\user\\\\Downloads\\\\staff.pdf";
    try (PDDocument document = PDDocument.load(new File(filename)))
    {
        printWithDialog(document);
    }
}
    /**
     * Prints the document at its actual size. This is the recommended way to print.
     */
    private static void print(PDDocument document) throws IOException, PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.print();
    }    /**
     * Prints with a print preview dialog.
     */
    private static void printWithDialog(PDDocument document) throws IOException, PrinterException
    {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        if (job.printDialog())
        {
            job.print();
        }
    }

}
