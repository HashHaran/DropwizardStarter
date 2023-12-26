package com.tutorial.starter.cache.aerospike;

import com.aerospike.client.AerospikeClient;
import com.aerospike.mapper.tools.AeroMapper;
import com.tutorial.starter.api.Payment;
import com.tutorial.starter.cache.CacheInitializer;
import com.tutorial.starter.dao.PaymentDao;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AerospikeCacheInitializer implements CacheInitializer {

    private static final Logger logger = LoggerFactory.getLogger(AerospikeCacheInitializer.class);

    @Override
    public void initCache(Jdbi jdbi) {
        logger.info("Started querying for payments from DB");
        List<Payment> payments = jdbi.withExtension(PaymentDao.class, PaymentDao::listPayments);
        AerospikeClient client = new AerospikeClient("localhost", 3000);
        AeroMapper mapper = new AeroMapper.Builder(client).build();
        payments.forEach(mapper::save);
        logger.info("Completed initializing cache with payments");
    }
}
