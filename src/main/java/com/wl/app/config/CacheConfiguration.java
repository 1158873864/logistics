package com.wl.app.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.wl.app.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.wl.app.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.wl.app.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.wl.app.domain.LogisticsDdn.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.LogisticsDdnPic.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.LogisticsDdnWWW.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.GoodsSource.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.UserInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.Member.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.MemberTwo.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.IntegralRule.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.IntegralChangeRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.Goods.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.GoodsExchange.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.SysRecruitmentInformation.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.PreferentialActivities.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.Topic.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.TopicFabulous.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.TopicComment.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.TopicForward.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.TopicViewed.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.UserDdnFavorites.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.Area.class.getName(), jcacheConfiguration);
            cm.createCache(com.wl.app.domain.Version.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
