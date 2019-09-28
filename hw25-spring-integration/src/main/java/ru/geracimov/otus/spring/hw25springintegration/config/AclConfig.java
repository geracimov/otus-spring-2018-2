package ru.geracimov.otus.spring.hw25springintegration.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionCacheOptimizer;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class AclConfig {
    private final DataSource dataSource;

    @Bean
    public EhCacheManagerFactoryBean aclCacheManager() {
        return new EhCacheManagerFactoryBean();
    }

    @Bean
    public AuditLogger auditLogger() {
        return new ConsoleAuditLogger();
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(auditLogger());
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ADMIN"));
    }

    @Bean
    public LookupStrategy lookupStrategy(AclCache aclCache,
                                         AclAuthorizationStrategy aclAuthorizationStrategy) {
        BasicLookupStrategy basicLookupStrategy = new BasicLookupStrategy(dataSource,
                                                                          aclCache,
                                                                          aclAuthorizationStrategy,
                                                                          auditLogger());
        basicLookupStrategy.setAclClassIdSupported(true);

        //AclClassIdUtils is package private. Cannot overriding
        //used by https://github.com/spring-projects/spring-security/issues/4814#issuecomment-480475528
        //        try {
        //            Field aclClassIdUtilsField = basicLookupStrategy.getClass()
        //                                                            .getDeclaredField("aclClassIdUtils");
        //            aclClassIdUtilsField.setAccessible(true);
        //            Object aclClassIdUtilsObject = aclClassIdUtilsField.get(basicLookupStrategy);
        //            Field conversionServiceField = aclClassIdUtilsObject.getClass()
        //                                                                .getDeclaredField("conversionService");
        //            conversionServiceField.setAccessible(true);
        //            GenericConversionService gcs = new GenericConversionService();
        //            gcs.addConverter(String.class, Long.class, Long::parseLong);
        //            conversionServiceField.set(aclClassIdUtilsObject, gcs);
        //        } catch (NoSuchFieldException | IllegalAccessException e) {
        //            e.printStackTrace();
        //        }
        return basicLookupStrategy;
    }

    @Bean
    public MutableAclService aclService(LookupStrategy lookupStrategy, AclCache aclCache) {
        JdbcMutableAclService aclService = new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
        aclService.setAclClassIdSupported(true);
        return aclService;
    }

    @Bean
    public EhCacheBasedAclCache aclCache(AclAuthorizationStrategy aclAuthorizationStrategy,
                                         PermissionGrantingStrategy permissionGrantingStrategy,
                                         EhCacheFactoryBean ehCacheFactoryBean) {
        return new EhCacheBasedAclCache(
                Objects.requireNonNull(ehCacheFactoryBean.getObject()),
                permissionGrantingStrategy,
                aclAuthorizationStrategy
        );
    }

    @Bean
    public EhCacheFactoryBean aclEhCacheFactoryBean(EhCacheManagerFactoryBean aclCacheManager) {
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(Objects.requireNonNull(aclCacheManager.getObject()));
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }

    @Bean
    public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler(AclService aclService) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService);
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        expressionHandler.setPermissionCacheOptimizer(new AclPermissionCacheOptimizer(aclService));
        return expressionHandler;
    }

}
