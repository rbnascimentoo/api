package br.com.rnascimento.api.resources;

import br.com.rnascimento.api.dtos.ClientDTO;
import br.com.rnascimento.api.models.PageModel;
import br.com.rnascimento.api.models.PageRequestModel;
import br.com.rnascimento.api.response.Response;
import br.com.rnascimento.api.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Api(value = "client", tags = "clients", description = "Endpoints of clients")
@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Find all clients.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<ClientDTO>>> findAll() {
        Response<List<ClientDTO>> response = new Response<List<ClientDTO>>();
        response.setData(this.clientService.findAll());
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new user.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ClientDTO>> create(@RequestBody ClientDTO clientDTO, BindingResult result){
        Response<ClientDTO> response = new Response<ClientDTO>();
        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.clientService.save(clientDTO));
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update an existing user.")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ClientDTO>> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ClientDTO clientDTO, BindingResult result){
        Response<ClientDTO> response = new Response<ClientDTO>();
        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.clientService.update(id, clientDTO));
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Find user by id.")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ClientDTO>> findById(@PathVariable(value = "id") Long id) {
        Response<ClientDTO> response = new Response<ClientDTO>();
        response.setData(this.clientService.findById(id));
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Delete an existing user.")
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable(value = "id") Long id) {
        this.clientService.deleteById(id);
    }

    @ApiOperation(value = "Find all users paginator.")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PageModel<ClientDTO>>> findAllPaginator(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pageRequest = new PageRequestModel(page, size);
        Response<PageModel<ClientDTO>> response = new Response<PageModel<ClientDTO>>();
        response.setData(this.clientService.listAllUserPaginator(pageRequest));
        return ResponseEntity.ok(response);
    }

}
