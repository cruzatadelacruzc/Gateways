package com.cemisoft.gateways.service.mapper;


import com.cemisoft.gateways.domain.Gateway;
import com.cemisoft.gateways.service.dto.GatewayDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Gateway} and its DTO {@link GatewayDTO}.
 */
@Mapper(componentModel = "spring")
public interface GatewayMapper extends EntityMapper<GatewayDTO, Gateway> {

    @Mapping(target = "peripherals", ignore = true)
    @Mapping(target = "removePeripheral", ignore = true)
    Gateway toEntity(GatewayDTO gatewayDTO);

    default Gateway fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gateway gateway = new Gateway();
        gateway.setId(id);
        return gateway;
    }
}
