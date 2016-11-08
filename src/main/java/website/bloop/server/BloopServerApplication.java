package website.bloop.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: implement application
    }

}
