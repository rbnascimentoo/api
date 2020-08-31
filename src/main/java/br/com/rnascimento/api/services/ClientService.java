package br.com.rnascimento.api.services;

import br.com.rnascimento.api.dtos.ClientDTO;
import br.com.rnascimento.api.entities.Client;
import br.com.rnascimento.api.models.PageModel;
import br.com.rnascimento.api.models.PageRequestModel;
import br.com.rnascimento.api.repositories.ClientRepository;
import br.com.rnascimento.api.utils.ModelMapperUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private static final Logger LOG = LogManager.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Find all Client.
     *
     * @return List<ClientDTO>
     */
    @Cacheable("findAllClient")
    public List<ClientDTO> findAll() {
        LOG.info("### Find all client... ###");
        List<Client> listClient = this.clientRepository.findAll();
        if(listClient.isEmpty() || listClient == null) {
            return new ArrayList<ClientDTO>();
        }
        return ModelMapperUtil.converter(listClient, ClientDTO.class);
    }

    /**
     * Save a new Client.
     *
     * @param
     * @return ClientDTO
     */
    @CacheEvict(allEntries = true, value = {"findAllClient", "findByIdClient"}, beforeInvocation = true)
    public ClientDTO save(ClientDTO clientDTO) {
        LOG.info("### Saving a new Client or Updating an existing Client... ###");
        Client client = ModelMapperUtil.converter(clientDTO, Client.class);
        client = this.clientRepository.save(client);
        return ModelMapperUtil.converter(client, ClientDTO.class);
    }

    /**
     * Update an existing Client.
     *
     * @param
     * @return ClientDTO
     */
    @CacheEvict(allEntries = true, value = {"findAllClient", "findByIdClient"}, beforeInvocation = true)
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        LOG.info("### Saving a new client or Updating an existing client... ###");
        Client client = ModelMapperUtil.converter(clientDTO, Client.class);
        client.setId(id);
        client = this.clientRepository.save(client);
        return ModelMapperUtil.converter(client, ClientDTO.class);
    }

    /**
     * Find a Client.
     *
     * @param id
     * @return ClientDTO
     */
    @Cacheable("findByIdClient")
    public ClientDTO findById(Long id) {
        LOG.info("### Find a User... ###");
        ClientDTO clientDTO = new ClientDTO();
        Optional<Client> client = this.clientRepository.findById(id);
        if(client.isPresent()) {
            clientDTO = ModelMapperUtil.converter(client.get(), ClientDTO.class);
        }
        return clientDTO;
    }

    /**
     * Delete a client.
     *
     * @param id
     */
    @CacheEvict(allEntries = true, value = {"findAllClient", "findByIdClient"}, beforeInvocation = true)
    public void deleteById(Long id) {
        LOG.info("### Deleting a client... ###");
        this.clientRepository.deleteById(id);
    }

    /**
     *
     *
     * @param pageRequestModel
     * @return
     */
    @Cacheable("findAllClientPaginator")
    public PageModel<ClientDTO> listAllUserPaginator(PageRequestModel pageRequestModel){
        LOG.info("### Find a client Paginator... ###");
        PageRequest pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<Client> page = this.clientRepository.findAll(pageable);
        PageModel<ClientDTO> pageModel = new PageModel<>((int) page.getTotalElements(),
                page.getSize(), page.getTotalPages(), ModelMapperUtil.converter(page.getContent(), ClientDTO.class));
        return pageModel;
    }

}
