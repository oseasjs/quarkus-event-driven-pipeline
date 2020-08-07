package com.quarkus.event.driven.pipeline.core.user.mapper;

import com.quarkus.event.driven.pipeline.commons.ru.response.Dob;
import com.quarkus.event.driven.pipeline.commons.ru.response.Result;
import com.quarkus.event.driven.pipeline.core.user.domain.User;
import com.quarkus.event.driven.pipeline.core.user.domain.UserParameter;
import com.quarkus.event.driven.pipeline.core.user.enums.RoleEnum;
import com.quarkus.event.driven.pipeline.core.user.enums.UserParameterTypeEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "cdi")
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name.fullName")
    @Mapping(target = "username", source = "login.username")
    @Mapping(target = "password", source = "login.password")
    @Mapping(target = "phone", source = "cell")
    @Mapping(target = "active", constant = "true")
    public abstract User toDomain(Result result);

    @AfterMapping
    protected void afterMapping(@MappingTarget User user, Result result) {
        user.addAllRoles(Arrays.asList(RoleEnum.CLIENT_APPROVED));
        user.setUserParameterSet(mockUserParameterSet(user, result.getDob()));
    }

    public Set<UserParameter> mockUserParameterSet(User user, Dob dob) {

        Set<UserParameter> userParameterSet = new HashSet<>();
        userParameterSet.add(buildUserParameter(user, UserParameterTypeEnum.CUSTOMER, "dob", dob.getDate().toString()));
        userParameterSet.add(buildUserParameter(user, UserParameterTypeEnum.CUSTOMER, "age", dob.getAge().toString()));
        return userParameterSet;

    }

    private UserParameter buildUserParameter(User user, UserParameterTypeEnum type, String name, String value) {
        return UserParameter.builder()
                .user(user)
                .type(type)
                .name(name)
                .value(value)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
