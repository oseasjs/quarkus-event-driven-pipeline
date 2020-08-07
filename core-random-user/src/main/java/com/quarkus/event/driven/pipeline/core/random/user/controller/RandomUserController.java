package com.quarkus.event.driven.pipeline.core.random.user.controller;

import com.quarkus.event.driven.pipeline.commons.ru.request.RandomUserDto;
import com.quarkus.event.driven.pipeline.commons.ru.response.Results;
import com.quarkus.event.driven.pipeline.core.random.user.service.RandomUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ru")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RandomUserController {

    @Inject
    RandomUserService randomUserService;

    @GET
    public Object getSingleUser() throws Exception {
        Results results = randomUserService.findSingleUser();
        return Response.ok(results).status(200).build();
    }

    @POST
    public Response prepare(RandomUserDto dto) throws Exception {
        randomUserService.generateRandomUser(dto);
        return Response.ok("Criação de users iniciada com sucesso").status(201).build();
    }

}
