/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cht.service.USCertService;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：USCertController.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author su
 *@version 1.0
 *@since 1.0
 */

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/uscert")
public class USCertController {
    
    @Autowired
    private final USCertService usCertService;
    
    
    public USCertController(final USCertService s) {
        this.usCertService = s;
    }  
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public List<String> add() {
        System.out.println("add");
        return null;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<String> listPost() {
        List<String> posts = usCertService.getList();
        return posts;
    }
    
    @RequestMapping(value="/update/",method = RequestMethod.GET)
    public void addEmployee(@RequestParam(value = "employeeName", required = false) String employeeName,
            @RequestParam(value = "employeeId", required = false) String employeeId,
            @RequestParam(value = "employeeCity", required = false) String employeeCity){
        
    }
}
