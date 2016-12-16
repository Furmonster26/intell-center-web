/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cht.connection.MongoDB;
import cht.domain.USCertPost;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：USCertServiceImpl.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author su
 *@version 1.0
 *@since 1.0
 */

@Service
@Validated
public class USCertServiceImpl implements USCertService {

    /* (non-Javadoc)
     * @see javabeat.net.springboot.service.USCertService#save(javabeat.net.springboot.domain.USCertPost)
     */
    public USCertPost save(USCertPost post) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see javabeat.net.springboot.service.USCertService#getList()
     */
    public List<String> getList() {
        // 
        
        MongoDB db = new MongoDB();
        try {
            return db.getList();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    /* (non-Javadoc)
     * @see cht.service.USCertService#update(java.lang.String, java.lang.String)
     */
    @Override
    public void update(String id, String chi) {
        MongoDB db = new MongoDB();
        try {
            db.update(id, chi);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see cht.service.USCertService#submit(java.lang.String, java.lang.String)
     */
    @Override
    public void submit(String id, String chi) {
        MongoDB db = new MongoDB();
        try {
            db.update(id, chi, true);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
