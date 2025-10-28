package fr.eni.ludotheque.bo;

import lombok.Data;

@Data
public class Adresse {

    private int no_adresse;
    private String rue;
    private int code_postale;
    private String ville;
    private Client client;
}
