package website.bloop.server;

import javax.ws.rs.client.Client;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import website.bloop.server.jdbi.FlagDAO;
import website.bloop.server.jdbi.NearbyFlagDAO;
import website.bloop.server.jdbi.PlayerDAO;
import website.bloop.server.resources.FlagResource;
import website.bloop.server.resources.PlayerResource;

public class BloopServerApplication extends Application<BloopServerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BloopServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "BloopServer";
    }

    @Override
    public void initialize(final Bootstrap<BloopServerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final BloopServerConfiguration configuration,
                    final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final FlagDAO flagDAO = jdbi.onDemand(FlagDAO.class);
        final NearbyFlagDAO nearbyFlagDAO = jdbi.onDemand(NearbyFlagDAO.class);
        final PlayerDAO playerDAO = jdbi.onDemand(PlayerDAO.class);
        
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration())
                .build(getName());
        final String firebaseKey = configuration.getFirebaseKey();
        
        environment.jersey().register(new FlagResource(flagDAO, nearbyFlagDAO, playerDAO, client, firebaseKey));
        environment.jersey().register(new PlayerResource(playerDAO, flagDAO));
    }
}
