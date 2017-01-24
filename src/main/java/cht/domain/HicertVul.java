/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.domain;

import java.util.List;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：USCertPost.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author su
 *@version 1.0
 *@since 1.0
 */
public class HicertVul {
    
    String vendor;
    String product;
    int size;
    String description;
    List<String> cves;
    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }
    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }
    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the cves
     */
    public List<String> getCves() {
        return cves;
    }
    /**
     * @param cves the cves to set
     */
    public void setCves(List<String> cves) {
        this.cves = cves;
    }
    
    
}
