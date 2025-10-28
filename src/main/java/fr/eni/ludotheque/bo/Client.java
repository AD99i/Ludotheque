package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Client {

    private  Integer no_client;
    @NonNull private String nom;
    @NonNull private String prenom;
    @NonNull private String email;
    private int no_telephone;
    @NonNull private Adresse adresse;
}
