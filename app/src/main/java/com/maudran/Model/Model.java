package com.maudran.Model;

/**
 * Created by maudran on 03/07/2017.
 */

public class Model
{
    private int id;

    public Model(int id) throws Exception
    {
        if(id <= 0){
            throw new Exception("Id non valide");
        }
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}

/* Créer l'attribut id qui correspond au marchand, private pour l'encapsulation. Constructeur + getter */


