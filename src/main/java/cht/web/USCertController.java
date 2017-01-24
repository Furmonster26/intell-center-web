/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cht.domain.HicertVul;
import cht.form.USCertForm;
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
    
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        System.out.println("init");
        Collection<SimpleGrantedAuthority> authorities = 
                (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        
        JSONObject json = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        
        authorities.forEach(a -> jsonArr.put(a.getAuthority()));
        
        return json.put("roles", jsonArr).toString();
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
    
    @RequestMapping(value="/update",method = RequestMethod.POST)    
    public void update(@RequestBody USCertForm form) {        
        System.out.println(form.getPostId()+ " " + form.getChi());
        usCertService.update(form.getPostId(), form.getChi());        
    }
    
    @RequestMapping(value="/submit",method = RequestMethod.POST)    
    public void submit(@RequestBody USCertForm form) {        
        System.out.println(form.getPostId() + " " + form.getChi());
        usCertService.submit(form.getPostId(), form.getChi());        
    }
    
    @RequestMapping(value="/hicert",method = RequestMethod.GET)    
    public ModelAndView hicert(ModelAndView mav) {        
        
        mav.setViewName("hicert_weekly");
        mav.addObject("year", 2016);
        return mav;
    }
    
    @RequestMapping(value="/hicert/list",method = RequestMethod.GET)    
    public String hicertList() {        
        
        List<String> posts = usCertService.getList();
        
        Map<String, HicertVul> vuls = new HashMap();
        posts.forEach(p -> {
            System.out.println(p);
            JSONObject json = new JSONObject(p);
            String vendor = (String) json.get("vendor");
            String product = (String) json.get("product");
            
            if (vuls.containsKey(vendor + " -- " + product)) {
                HicertVul oldVul = vuls.get(vendor + " -- " + product);
                oldVul.setSize(oldVul.getSize() +1);
                List oldCVEs = oldVul.getCves();
                System.out.println((String) json.get("published"));
                oldCVEs.add((String) json.get("published"));
                oldVul.setCves(oldCVEs);
            } else {
                HicertVul newV = new HicertVul();
                newV.setVendor(vendor);
                newV.setProduct(product);
                newV.setSize(1);
                
                try {
                    newV.setDescription((String) json.get("chi"));
                } catch (Exception e) {
                    newV.setDescription("");
                }
                List<String> newCves = new ArrayList<String>();
                newCves.add((String) json.get("published"));
                newV.setCves(newCves);
                
                vuls.put(vendor + " -- " + product, newV);
            }
        });
        
        String result = new JSONObject(vuls).toString();
        return result;
    }
}
