/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.service;

import java.util.List;

import cht.domain.USCertPost;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：USCertService.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author su
 *@version 1.0
 *@since 1.0
 */
public interface USCertService {
    USCertPost save(USCertPost post);

    List<String> getList();
}