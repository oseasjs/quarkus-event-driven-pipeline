package com.quarkus.event.driven.pipeline.core.customer.mapper;

import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.customer.domain.Customer;
import com.quarkus.event.driven.pipeline.core.customer.domain.CustomerAddress;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "cdi")
public abstract class CustomerMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(result.getUserId()))")
    @Mapping(target = "proposalId", expression = "java(java.util.UUID.fromString(result.getProposalId()))")
    @Mapping(target = "name", source = "name.fullName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "birthDate", expression = "java(java.time.LocalDate.parse(result.getDob().getDate().split(\"T\")[0], java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd\")))")
    @Mapping(target = "phone", source = "cell")
    @Mapping(target = "eventStatus", expression = "java(com.quarkus.event.driven.pipeline.core.customer.enums.CustomerEventStatus.CUSTOMER_NOTIFICATED)")
    @Mapping(target = "mothersName", expression = "java(result.getName().getFullName() + \" Mom \")")
    @Mapping(target = "maritalStatus", expression = "java(com.quarkus.event.driven.pipeline.core.customer.enums.MaritalStatusEnum.SINGLE)")
    @Mapping(target = "partnersName", expression = "java(result.getName().getFullName() + \" Patterns \")")
    @Mapping(target = "schooling", expression = "java(com.quarkus.event.driven.pipeline.core.customer.enums.SchoolingEnum.COMPLETE_HIGH_SCHOOL)")
    @Mapping(target = "declaredIncome", constant = "1500.00")
    @Mapping(target = "declaredPatrimony", constant = "15000.00")

    @Mapping(target = "document.documentType", constant = "ID")
    @Mapping(target = "document.documentNumber", source = "id.value")
    @Mapping(target = "document.issuingStateAbbreviation", constant = "ES")
    @Mapping(target = "document.issuingDate", expression = "java(java.time.LocalDate.now())")

    public abstract Customer toDomain(Result result);

    @AfterMapping
    protected void afterMapping(@MappingTarget Customer customer, Result result) {
        buildAddressList(customer, result);
        customer.getDocument().setCustomer(customer);
    }

    private void buildAddressList(Customer customer, Result result) {

        List<CustomerAddress> addressList = new ArrayList<>();
        CustomerAddress address = new CustomerAddress();

        address.setCustomer(customer);
        address.setZipCode(result.getLocation().getPostcode());
        address.setStateAbbreviation("ES");
        address.setLocality(result.getLocation().getCity());
        address.setPublicPlace(result.getLocation().getStreet());
        address.setNeighborhood("Neighborhood");
        address.setNumber("1");
        address.setComplement("Address complement");
        address.setReference("Reference point!");
        address.setDomicile(true);
        address.setResidenceType("Residential");

        address.setLongitude(result.getLocation().getCoordinates().getLongitude());
        address.setLatitude(result.getLocation().getCoordinates().getLatitude());

        addressList.add(address);
        customer.setAddressList(addressList);

    }

}
