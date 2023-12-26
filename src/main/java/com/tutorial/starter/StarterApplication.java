package com.tutorial.starter;

import com.tutorial.starter.resources.PaymentCollectionResource;
import com.tutorial.starter.resources.PaymentResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StarterApplication extends Application<StarterConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(StarterApplication.class);

    public static void main(String[] args) throws Exception {
        System.out.println("Inside main, before run.");
        logger.debug("Inside main, before run.");
        new StarterApplication().run(args);
    }

    @Override
    public void run(StarterConfiguration starterConfiguration, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, starterConfiguration.getDataSourceFactory(), "mariadb:demo1");
        environment.jersey().register(new PaymentCollectionResource(jdbi));
        environment.jersey().register(new PaymentResource(jdbi));
    }

    @Override
    public String getName() {
        return "dropwizard-starter";
    }
}
