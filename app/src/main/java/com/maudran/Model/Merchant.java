package com.maudran.Model;


/**
 * Created by maudran on 03/07/2017.
 */

public class Merchant extends Model {

    private String name;
    private String logo;

    public Merchant(int id,String name,String logo) throws Exception
    {
        super(id);

        if(name == null || name.equals("")){
            throw new Exception("Name non valide");
        }

        if(logo == null || logo.equals("")){
            throw new Exception("Logo non valide");
        }

        this.name = name;
        this.logo = logo;
    }


    public String getName()
    {
        return name;
    }


    public String getLogo()
    {
        return logo;
    }

}
