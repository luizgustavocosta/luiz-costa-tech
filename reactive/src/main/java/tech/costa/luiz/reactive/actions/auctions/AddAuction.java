package tech.costa.luiz.reactive.actions.auctions;

import io.smallrye.mutiny.Multi;
import io.vertx.axle.core.Vertx;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import tech.costa.luiz.reactive.rxjava.AuctionRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auction")
public class AddAuction {

    @Inject
    Vertx vertx;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("{name}/streaming")
    public Multi<String> greeting(@PathParam String name) {
        AuctionRepository auctionRepository = new AuctionRepository();
        // Receiving a stream from rxjava
        return Multi.createFrom().publisher(auctionRepository.findTop10Auctions())
                .map(auction -> String.format("New auction for  %s is %d %n", name, auction));

    }

}
