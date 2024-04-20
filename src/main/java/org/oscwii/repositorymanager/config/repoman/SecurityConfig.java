package org.oscwii.repositorymanager.config.repoman;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = "repository-manager.security")
public record SecurityConfig(
        boolean allowRegistration,
        Set<Integer> protectedUsers,
        String secretKey)
{
}
