package fr.eni.ludotheque.dto;

import fr.eni.ludotheque.bo.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureDTO {
    //private LocalDateTime datePaiement;
    //private List<Location> locations=new ArrayList<Location>();
    //private float prix;
    private List<String> codebarres = new ArrayList<>();
}
