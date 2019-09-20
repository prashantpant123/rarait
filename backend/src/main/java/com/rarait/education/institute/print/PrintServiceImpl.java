package com.rarait.education.institute.print;

import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class PrintServiceImpl implements PrintService{
    @Override
    public void printFile() throws PrintException, FileNotFoundException {
        javax.print.PrintService[] printServices = PrintServiceLookup
                .lookupPrintServices(null, null);
        javax.print.PrintService ps = PrintServiceLookup
                .lookupDefaultPrintService();
        DocPrintJob job = ps.createPrintJob();

        FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\Capture.PNG");
        Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.PNG, null);
        // Doc doc=new SimpleDoc(fis, DocFlavor.INPUT_STREAM.JPEG, null);

        PrintRequestAttributeSet attrib =
                new HashPrintRequestAttributeSet();
        javax.print.PrintService selectedPrintService =
                ServiceUI.printDialog(null,150, 150,
                        printServices, ps, null, attrib);
        if (selectedPrintService != null)
            System.out.println("selected printer:"
                    + selectedPrintService.getName());
        else
            System.out.println("selection cancelled");

        job.addPrintJobListener(new PrintJobAdapter() {
            public void printDataTransferCompleted(PrintJobEvent event) {
                System.out.println("data transfer complete");
            }

            public void printJobNoMoreEvents(PrintJobEvent event) {
                System.out.println("received no more events");
            }
        });
        attrib.add(new Copies(1));
        job.print(doc, attrib);
    }

    @Override
    public void getTitle(){
        String title= "Hello World";
        System.out.println(title);

    }
}
