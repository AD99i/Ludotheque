package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@Controller + @ResponseBody
public class ClientRestController {

    @NonNull
    private ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<ApiResponse<Client>> ajouterClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Validation échouée", null)
            );
        }

        Client client = clientService.ajouterClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "Client créé avec succès", client)
        );
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable Integer id) {
        clientService.supprimerClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(true, "Client supprimé", null)
        );
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<ApiResponse<ClientDTO>> updateClient(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        clientService.modifierClient(id, clientDTO);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client mis à jour", clientDTO)
        );
    }

    @PatchMapping("clients/{id}")
    public ResponseEntity<ApiResponse<AdresseDTO>> updateClient(@RequestBody AdresseDTO adresseDTO, @PathVariable Integer id) {
        clientService.modifierAdresse(id, adresseDTO);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Adresse mise à jour", adresseDTO)
        );
    }

    @GetMapping("clients/nom/{nom}")
    public ResponseEntity<ApiResponse<List<Client>>> findClientByNom(@PathVariable String nom) {
        List<Client> listeClients = clientService.trouverClientsParNom(nom);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Clients trouvés", listeClients)
        );
    }

    @GetMapping("clients/id/{id}")
    public ResponseEntity<ApiResponse<Client>> findClientById(@PathVariable Integer id) {
        Client client = clientService.trouverClientParId(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client trouvé", client)
        );
    }


}
