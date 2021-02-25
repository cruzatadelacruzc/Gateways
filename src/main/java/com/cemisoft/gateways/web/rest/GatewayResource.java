package com.cemisoft.gateways.web.rest;


import com.cemisoft.gateways.service.GatewayService;
import com.cemisoft.gateways.service.dto.GatewayDTO;
import com.cemisoft.gateways.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link com.cemisoft.gateways.domain.Gateway}.
 */
@RestController
@RequestMapping("/api/gateway")
public class GatewayResource {

    private static final String ENTITY_NAME = "gateway";

    private final Logger log = LoggerFactory.getLogger(GatewayResource.class);

    private final GatewayService gatewayService;

    /**
     * Instantiates a new Gateway resource.
     *
     * @param gatewayService the gateway service
     */
    public GatewayResource(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    /**
     * Create gateway response entity.
     *
     * @param gatewayDTO the gateway dto
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new GatewayDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<GatewayDTO> createGateway(@Valid @RequestBody GatewayDTO gatewayDTO) throws URISyntaxException {
        log.debug("REST request to save Gateway : {}", gatewayDTO);
        if (gatewayDTO.getId() != null) {
            throw new BadRequestAlertException("A new gateway cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GatewayDTO result = gatewayService.save(gatewayDTO);
        return ResponseEntity.created(new URI("/api/gateway/" + result.getId()))
                .body(result);
    }

    /**
     * Update gateway response entity.
     *
     * @param gatewayDTO the gateway dto
     * @return the response entity
     */
    @PutMapping("/")
    public ResponseEntity<GatewayDTO> updateGateway(@Valid @RequestBody GatewayDTO gatewayDTO) {
        log.debug("REST request to update Gateway : {}", gatewayDTO);
        if (gatewayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GatewayDTO result = gatewayService.save(gatewayDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * Gets all gateways.
     *
     * @param pageable the pageable
     * @return the all gateways
     */
    @GetMapping("/")
    public ResponseEntity<List<GatewayDTO>> getAllGateways(Pageable pageable) {
        log.debug("REST request to get a page of Gateways");
        Page<GatewayDTO> page = gatewayService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GatewayDTO> getGateway(@PathVariable Long id) {
        log.debug("REST request to get a Gateway : {}", id);/*
        Optional<GatewayDTO> gatewayDTO = gatewayService.findOne(id);*/
        return ResponseEntity.ok(null);
    }
}
