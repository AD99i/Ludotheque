package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dto.FactureDTO;
import fr.eni.ludotheque.dto.LocationDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationRestController {

    @NonNull
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<ApiResponse<Location>> enregistrerLocation(@RequestBody LocationDTO locationDTO) {
        try {
            Location location = locationService.ajouterLocation(locationDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Location enregistrée avec succès", location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Erreur lors de l'enregistrement : " + e.getMessage(), null));
        }
    }

    @PostMapping("/retour")
    public ResponseEntity<ApiResponse<Facture>> enregistrerRetourEtGenererFacture(@RequestBody FactureDTO factureDTO) {
        try {
            Facture facture = locationService.retourExemplaires(factureDTO.getCodebarres());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Retour enregistré et facture générée", facture));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Erreur : " + e.getMessage(), null));
        }
    }

}
