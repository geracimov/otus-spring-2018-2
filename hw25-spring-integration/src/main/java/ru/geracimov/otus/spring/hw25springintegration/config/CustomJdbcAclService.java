package ru.geracimov.otus.spring.hw25springintegration.config;

import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CustomJdbcAclService extends JdbcMutableAclService {
    private final AclAuthorizationStrategy aclAuthorizationStrategy;
    private final AuditLogger auditLogger;
    private final LookupStrategy lookupStrategy;

    CustomJdbcAclService(DataSource dataSource,
                         LookupStrategy lookupStrategy, AclCache aclCache,
                         AclAuthorizationStrategy aclAuthorizationStrategy,
                         AuditLogger auditLogger) {
        super(dataSource, lookupStrategy, aclCache);
        this.aclAuthorizationStrategy = aclAuthorizationStrategy;
        this.auditLogger = auditLogger;
        this.lookupStrategy = lookupStrategy;
    }

    @Override
    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects,
                                                 List<Sid> sids) {
        return lookupStrategy.readAclsById(objects, sids);
    }

    @Override
    public Acl readAclById(ObjectIdentity object, List<Sid> sids) throws NotFoundException {
        Sid owner = new PrincipalSid(SecurityContextHolder.getContext()
                                                          .getAuthentication());
        Map<ObjectIdentity, Acl> map = readAclsById(Collections.singletonList(object), sids);
        if (map.containsKey(object)) {
            return map.get(object);
        } else {
            return new AclImpl(object,
                               object.getIdentifier()
                                     .toString(),
                               aclAuthorizationStrategy, auditLogger);
        }

    }


}
