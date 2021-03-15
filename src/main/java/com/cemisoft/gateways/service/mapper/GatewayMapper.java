package com.cemisoft.gateways.service.mapper;


import com.cemisoft.gateways.domain.Gateway;
import com.cemisoft.gateways.service.dto.GatewayDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Gateway} and its DTO {@link GatewayDTO}.
 */
@Mapper(componentModel = "spring")
public interface GatewayMapper extends EntityMapper<GatewayDTO, Gateway> {

    Gateway toEntity(GatewayDTO gatewayDTO);

    GatewayDTO toDto(Gateway gateway);

    default Gateway fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gateway gateway = new Gateway();
        gateway.setId(id);
        return gateway;
    }
}
