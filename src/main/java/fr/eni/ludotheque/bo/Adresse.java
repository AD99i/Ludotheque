package fr.eni.ludotheque.bo;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Adresse {

    @EqualsAndHashCode.Exclude
    private Integer no_adresse;

    @NonNull private String rue;

    @NonNull private int code_postale;

    @NonNull private String ville;

}
