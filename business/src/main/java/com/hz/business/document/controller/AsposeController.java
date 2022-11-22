package com.hz.business.document.controller;

import com.hz.business.document.service.AsposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.MessageFormat;

/**
 * @author： pt
 * @date： 2022/11/21 16:06
 * @discription
 */
@RestController
@RequestMapping("aspose")
public class AsposeController {

    @Value("${custom.report.path}")
    private String reportPath;

    @Autowired
    private AsposeService asposeService;

    @GetMapping(value = "export")
    public void export(HttpServletResponse response) throws Exception {
        String filePath = MessageFormat.format("{0}.pdf", reportPath + File.separator + "aspose");
        if (new File(filePath).exists()) {
            asposeService.exportAspose(filePath, response);
        } else {
            asposeService.handleAsposeReport(filePath, response);
        }
    }

}
