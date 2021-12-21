package com.georgeisaev.springbank.usercore.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Role implements GrantedAuthority {

    READ_PRIVILEGE, WRITE_PRIVILEGE;

    /**
     * {@inheritDoc}
     *
     * @return a representation of the granted authority (or {@code null} if the
     * granted authority cannot be expressed as a  {@code String} with sufficient
     * precision).
     */
    @Override
    public String getAuthority() {
        return name();
    }

}
