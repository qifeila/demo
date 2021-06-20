package goct.query.demo.service;

import goct.query.demo.model.Pdf;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GetDpetService {

    public static Set<String> getDept(List<Pdf> pdfList){
        Set<String> set = new HashSet<String>();
        for (Pdf pdf : pdfList){
            set.add(pdf.getPdfDept());
        }
        return set;

    }
}
