package com.cemisoft.gateways.web.rest;

import com.cemisoft.gateways.GatewaysApplication;
import com.cemisoft.gateways.domain.Gateway;
import com.cemisoft.gateways.repository.GatewayRepository;
import com.cemisoft.gateways.service.GatewayService;
import com.cemisoft.gateways.service.dto.GatewayDTO;
import com.cemisoft.gateways.service.mapper.GatewayMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GatewayResource} REST controller.
 */
@AutoConfigureMockMvc
@WithMockUser
@SpringBootTest(classes = GatewaysApplication.class)
public class GatewayResourceIT {

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "12.12.12.12";
    private static final String UPDATED_ADDRESS = "13.13.13.13";
    private static final String WRONG_ADDRESS_LETTERS = "ERROR.8.8.9";
    private static final String WRONG_ADDRESS_RANGE = "256.256.256.256";
    private static final String WRONG_ADDRESS_LENGTH = "256.256.256";
    private static final String WRONG_ADDRESS_CHARACTERS = "25,25,25;60";

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private GatewayMapper gatewayMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGatewayMockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Gateway gateway;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gateway createEntity() {
        return new Gateway()
                .name(DEFAULT_NAME)
                .serialNumber(DEFAULT_SERIAL_NUMBER)
                .address(DEFAULT_ADDRESS);
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gateway createUpdatedEntity() {
        return new Gateway()
                .name(UPDATED_NAME)
                .serialNumber(UPDATED_SERIAL_NUMBER)
                .address(UPDATED_ADDRESS);
    }

    @BeforeEach
    public void initTest() {
        gateway = createEntity();
    }

    @Test
    @Transactional
    public void createGateway() throws Exception {
        int databaseSizeBeforeCreate = gatewayRepository.findAll().size();
        // Create the Gateway
        GatewayDTO gatewayDTO = gatewayMapper.toDto(gateway);
        restGatewayMockMvc.perform(post("/api/gateways").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(gatewayDTO)))
                .andExpect(status().isCreated());

        // Validate the Gateway in the database
        List<Gateway> gatewayList = gatewayRepository.findAll();
        assertThat(gatewayList).hasSize(databaseSizeBeforeCreate + 1);
        Gateway testGateway = gatewayList.get(gatewayList.size() - 1);
        assertThat(testGateway.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGateway.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testGateway.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void createGatewayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gatewayRepository.findAll().size();

        // Create the Gateway with an existing ID
        gateway.setId(1L);
        GatewayDTO gatewayDTO = gatewayMapper.toDto(gateway);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGatewayMockMvc.perform(post("/api/gateways").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(gatewayDTO)))
                .andExpect(status().isBadRequest());

        // Validate the Gateway in the database
        List<Gateway> gatewayList = gatewayRepository.findAll();
        assertThat(gatewayList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGateways() throws Exception {
        // Initialize the database
        gatewayRepository.saveAndFlush(gateway);

        // Get all the gatewayList
        restGatewayMockMvc.perform(get("/api/gateways?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(gateway.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))/*
                .andExpect(jsonPath("$.[*].regularOrigin").value(hasItem(DEFAULT_REGULAR_ORIGIN)))
                .andExpect(jsonPath("$.[*].regularDestination").value(hasItem(DEFAULT_REGULAR_DESTINATION)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))*/;
    }

    @Test
    @Transactional
    public void getGateway() throws Exception {
        // Initialize the database
        gatewayRepository.saveAndFlush(gateway);

        // Get the gateway
        restGatewayMockMvc.perform(get("/api/gateways/{id}", gateway.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(gateway.getId().intValue()))
                .andExpect(jsonPath("$.name").value(DEFAULT_NAME))/*
                .andExpect(jsonPath("$.regularOrigin").value(DEFAULT_REGULAR_ORIGIN))
                .andExpect(jsonPath("$.regularDestination").value(DEFAULT_REGULAR_DESTINATION))
                .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
                .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))*/;
    }

    @Test
    @Transactional
    public void getNonExistingGateway() throws Exception {
        // Get the gateway
        restGatewayMockMvc.perform(get("/api/gateways/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGateway() throws Exception {
        // Initialize the database
        gatewayRepository.saveAndFlush(gateway);

        int databaseSizeBeforeUpdate = gatewayRepository.findAll().size();

        // Update the gateway
        Gateway updatedGateway = gatewayRepository.findById(gateway.getId()).orElse(gateway);
        // Disconnect from session so that the updates on updatedGateway are not directly saved in db
        em.detach(updatedGateway);
        updatedGateway
                .name(UPDATED_NAME)/*
                .regularOrigin(UPDATED_REGULAR_ORIGIN)
                .regularDestination(UPDATED_REGULAR_DESTINATION)
                .phoneNumber(UPDATED_PHONE_NUMBER)
                .status(UPDATED_STATUS)*/;
        GatewayDTO gatewayDTO = gatewayMapper.toDto(updatedGateway);

        restGatewayMockMvc.perform(put("/api/gateways").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(gatewayDTO)))
                .andExpect(status().isOk());

        // Validate the Gateway in the database
        List<Gateway> gatewayList = gatewayRepository.findAll();
        assertThat(gatewayList).hasSize(databaseSizeBeforeUpdate);
        Gateway testGateway = gatewayList.get(gatewayList.size() - 1);
        assertThat(testGateway.getName()).isEqualTo(UPDATED_NAME);/*
        assertThat(testGateway.getRegularOrigin()).isEqualTo(UPDATED_REGULAR_ORIGIN);
        assertThat(testGateway.getRegularDestination()).isEqualTo(UPDATED_REGULAR_DESTINATION);
        assertThat(testGateway.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testGateway.getStatus()).isEqualTo(UPDATED_STATUS);*/
    }

    @Test
    @Transactional
    public void updateNonExistingGateway() throws Exception {
        int databaseSizeBeforeUpdate = gatewayRepository.findAll().size();

        // Create the Gateway
        GatewayDTO gatewayDTO = gatewayMapper.toDto(gateway);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGatewayMockMvc.perform(put("/api/gateways").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(gatewayDTO)))
                .andExpect(status().isBadRequest());

        // Validate the Gateway in the database
        List<Gateway> gatewayList = gatewayRepository.findAll();
        assertThat(gatewayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGateway() throws Exception {
        // Initialize the database
        gatewayRepository.saveAndFlush(gateway);

        int databaseSizeBeforeDelete = gatewayRepository.findAll().size();

        // Delete the gateway
        restGatewayMockMvc.perform(delete("/api/gateways/{id}", gateway.getId()).with(csrf())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gateway> gatewayList = gatewayRepository.findAll();
        assertThat(gatewayList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
