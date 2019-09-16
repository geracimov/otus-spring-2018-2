package ru.geracimov.otus.spring.hw22springsecurityacl.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AclCreationService {
    private final static GrantedAuthoritySid ADMIN_AUTHORITY_SID = new GrantedAuthoritySid("ADMIN");
    private final static GrantedAuthoritySid USER_AUTHORITY_SID = new GrantedAuthoritySid("USER");
    private final static GrantedAuthoritySid EDITOR_AUTHORITY_SID = new GrantedAuthoritySid("EDITOR");

    private final MutableAclService aclService;

    public void addDefaultPrivilege(ObjectIdentity objectIdentity) {
        MutableAcl acl;
        Sid owner = new PrincipalSid(SecurityContextHolder.getContext()
                                                          .getAuthentication());
        try {
            acl = aclService.createAcl(objectIdentity);
        } catch (AlreadyExistsException e) {
            log.warn(e.getMessage());
            return;
        }

        acl.setOwner(owner);
        //USER
        acl.insertAce(acl.getEntries()
                         .size(), BasePermission.READ, USER_AUTHORITY_SID, true);
        //ADMIN
        acl.insertAce(acl.getEntries()
                         .size(), MyPermission.ALL, ADMIN_AUTHORITY_SID, true);
        //EDITOR
        acl.insertAce(acl.getEntries()
                         .size(), MyPermission.RW, EDITOR_AUTHORITY_SID, true);
        //OWNER
        acl.insertAce(acl.getEntries()
                         .size(), MyPermission.ALL, owner, true);

        aclService.updateAcl(acl);
    }

    public void dropAcl(ObjectIdentity objectIdentity) {
        try {
            aclService.deleteAcl(objectIdentity, true);
        } catch (AlreadyExistsException e) {
            log.warn(e.getMessage());
        }
    }

}
