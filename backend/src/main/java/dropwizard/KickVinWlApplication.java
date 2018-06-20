package dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import persistence.AchievementsChecker;
import resources.*;
import util.DBInitializer;

public class KickVinWlApplication extends Application<KickVinWlConfiguration> {


    public static void main(String[] args) throws Exception {
        new KickVinWlApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<KickVinWlConfiguration> bootstrap) {
        super.initialize(bootstrap);
        // HTTP Proxy Settings
        System.setProperty("http.proxyHost", "172.28.2.5");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");

        // HTTPS Proxy Settings
        System.setProperty("https.proxyHost", "172.28.2.5");
        System.setProperty("https.proxyPort", "9090");
    }

    @Override
    public void run(KickVinWlConfiguration configuration, Environment environment) throws Exception {
//        DBInitializer.dropDatabase();
        DBInitializer.init();
        
        AchievementsChecker ac = new AchievementsChecker();
        ac.check();

        final TipResource tipResource = new TipResourceImpl();
        environment.jersey().register(tipResource);

        final Resource resource = new Resource();
        environment.jersey().register(resource);

        final Login login = new Login();
        environment.jersey().register(login);

        final UserResource userResource = new UserResourceImpl();
        environment.jersey().register(userResource);

        final SearchResource searchResource = new SearchResourceImpl();
        environment.jersey().register(searchResource);
    }
}
