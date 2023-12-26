package com.tutorial.starter.cache;

import org.jdbi.v3.core.Jdbi;

public interface CacheInitializer {
     void initCache(Jdbi jdbi);
}
