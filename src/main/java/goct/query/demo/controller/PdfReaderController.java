package goct.query.demo.controller;


import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.model.Pdf;
import goct.query.demo.service.GetDpetService;
import goct.query.demo.service.Word2PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class PdfReaderController {

    @Autowired
    Word2PdfService word2PdfService;
    @Autowired
    ActionMapper actionMapper;

    Set<String > deptSet = new HashSet<>();



    @RequestMapping(value = "/pdf")
    public ModelAndView getPdfList( ModelMap modelMap) {
        List<Pdf>  pdfList = new ArrayList<>();
        File file = new File("D:\\PDF\\作业指导书");
        word2PdfService.getAllFilePath(file).clear(); //为了清空 算法问题
        pdfList = word2PdfService.getAllFilePath(file);
        deptSet = GetDpetService.getDept(pdfList);
        modelMap.addAttribute("pdfList",pdfList);
        modelMap.addAttribute("deptSet",deptSet);

     //   modelMap.addAttribute("pdfList",pdfList);
        return new ModelAndView("pdf/pdfList",modelMap);
    }
    @RequestMapping(value = "/deptPdf")
    public ModelAndView getDeptPdfList(@RequestParam(value = "dept")String dept, ModelMap modelMap) {
        List<Pdf>  pdfList = new ArrayList<>();
        String pathName = "D:\\PDF\\作业指导书\\"+dept;
        System.out.println("pathNme0000000000"+pathName);
        File file = new File(pathName);

        word2PdfService.getAllFilePath(file).clear(); //为了清空 算法问题
        pdfList = word2PdfService.getAllFilePath(file);
        modelMap.addAttribute("pdfList",pdfList);
        modelMap.addAttribute("dept",dept);
        modelMap.addAttribute("deptSet",deptSet);

        return new ModelAndView("pdf/pdfList",modelMap);
    }

    @RequestMapping(value = "/pdfView")
    public ModelAndView getPdfTest( ModelMap modelMap) {
        return new ModelAndView("pdf/viewer.html",modelMap);
    }
   /* @RequestMapping(value = "/pdf1")
    public ModelAndView getPdf1( ModelMap modelMap) {
        List<Pdf>  pdfList = new ArrayList<>();
        File file = new File("C:\\Users\\wangjun\\Downloads\\PDF");
        word2PdfService.getAllFilePath(file).clear();
        pdfList =word2PdfService.getAllFilePath(file);

        //   File file1 = new File("C:\\Users\\wangjun\\Downloads\\PDF");
        //  Word2PdfService.getAllFilePath1(file1);

        modelMap.addAttribute("pdfList",pdfList);
        return new ModelAndView("pdf/pdf",modelMap);
    }*/
    /**
     * 预览pdf文件
     * @author Mike
     * @param request
     * @param response
     * @param fileName
     */
    @RequestMapping(value = "/downloadPdf", method = RequestMethod.GET)
    public void pdfStreamHandeler(HttpServletRequest request, HttpServletResponse response,String fileName) {
        System.out.println("path"+fileName);
        // fileName= "信息类\\TEST0.pdf";
        String filePath = "D:\\PDF\\作业指导书\\"+fileName.split(",")[0]+"\\"+fileName.split(",")[1];
        System.out.println("path"+filePath);
        File file = new File(filePath);
        byte[] data = null;
        try {
            FileInputStream input = new FileInputStream(file);
            data = new byte[input.available()];
            input.read(data);
            response.getOutputStream().write(data);
            input.close();
        } catch (Exception e) {
            System.out.print("pdf文件处理异常：" + e.getMessage());
        }

    }


}

