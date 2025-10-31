package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JeuRestController {

    @NonNull
    private final JeuService jeuService;

    @GetMapping("/jeux")
    public ResponseEntity<ApiResponse<List<Jeu>>> listeJeu() {
        List<Jeu> listeJeux = jeuService.listeJeuxCatalogue("");
        ApiResponse<List<Jeu>> response = new ApiResponse<>(true, "Liste des jeux récupérée", listeJeux);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jeux/{codeBarre}")
    public ResponseEntity<ApiResponse<Jeu>> getJeu(@PathVariable("codeBarre") String codeBarre) {
        Jeu jeu = jeuService.findJeuByCodeBarre(codeBarre);

        if (jeu != null) {
            ApiResponse<Jeu> response = new ApiResponse<>(true, "Jeu trouvé", jeu);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Jeu> response = new ApiResponse<>(false, "Jeu non trouvé", null);
            return ResponseEntity.status(404).body(response);
        }
    }
}
