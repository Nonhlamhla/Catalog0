package com.example.admin.catalog0;

/**
 * Created by Admin on 8/28/2017.
 */

public class Catalog {

    String catalogId;
    String catalogtitle;
    String catalogimageurl;

    public Catalog(){

    }


    public Catalog(String catalogId,  String catalogtitle, String catalogimageurl) {

        this.catalogId = catalogId;
        this.catalogtitle = catalogtitle;
        this.catalogimageurl = catalogimageurl;
    }


    public String getCatalogtitle (){
        return catalogtitle;
    }
    public String getCatalogId(){
        return catalogId;
    }

    public String getCatalogimageurl(){
        return catalogimageurl;
    }
}




