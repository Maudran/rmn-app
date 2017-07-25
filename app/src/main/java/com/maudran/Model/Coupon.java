package com.maudran.Model;


/**
 * Created by maudran on 03/07/2017.
 */

public class Coupon extends Model {

    private String title;
    private String code;
    private String affiliationUrl;
    private Merchant merchant;

    public Coupon(int id,String title,String code,String affiliationUrl,Merchant merchant) throws Exception
    {
        super(id);

        if(title == null || title.equals("")){
            throw new Exception("Title non valide");
        }

        if(code == null || code.equals("")){
            throw new Exception("Code non valide");
        }
        if(affiliationUrl == null || affiliationUrl.equals("")){
            throw new Exception("Url non valide");
        }

        if(merchant == null){
            throw new Exception("Merchant non valide");
        }
        this.title = title;
        this.code = code;
        this.affiliationUrl = affiliationUrl;
        this.merchant = merchant;
    }

    public String getTitle()
    {
        return title;
    }

    public String getCode()
    {
        return code;
    }



    public String getAffiliationUrl()
    {
        return affiliationUrl;
    }



    public Merchant getMerchant()
    {
        return merchant;
    }



}
