package website.bloop.server;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import website.bloop.server.jdbi.FlagDAO;
import website.bloop.server.resources.FlagResource;

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
        
        environment.jersey().register(new FlagResource(flagDAO));
    }
}
