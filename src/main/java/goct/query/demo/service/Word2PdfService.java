package goct.query.demo.service;

import goct.query.demo.model.Pdf;
import goct.query.demo.util.Word2PdfJacobUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * pdf 使用
 */
@Service
public class Word2PdfService {
    List<Pdf> pdfList = new ArrayList<>();

    public List<Pdf> getAllFilePath(File dir) {

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            Pdf pdf = new Pdf();


            //这里面用了递归的算法
            // getAllFilePath(files[i]);
            if (files[i].isDirectory()) {
                System.out.println(files[i].getPath());
                getAllFilePath(files[i]);
            } else {
                System.out.println("pdf目录:" + files[i].getPath());
                String str = files[i].getPath();
                if (str.length() > 18 && str.substring(str.length() - 4).equals(".pdf")){
                    // str.split("\\\\");根据具体目录更改

                    pdf.setPdfDept(str.split("\\\\")[3]);
                    System.out.println("pdf类别:" + pdf.getPdfDept());
                    pdf.setPdfName(str.split("\\\\")[4]);
                    pdfList.add(pdf);
                    pdf.setPdfUrl(pdf.getPdfDept()+","+pdf.getPdfName());
                    System.out.println("pdfURL:" + pdf.getPdfUrl());
                }
            }
        }
        return pdfList;

    }


}