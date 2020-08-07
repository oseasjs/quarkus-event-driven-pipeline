package com.quarkus.event.driven.pipeline.core.random.user.client;

import com.quarkus.event.driven.pipeline.commons.ru.response.Results;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
public interface RandomUserClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    Results getUsers(@QueryParam("results") int results, @QueryParam("gender") String gender,
                     @QueryParam("nat") String nat);

}
