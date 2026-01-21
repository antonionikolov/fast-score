package com.tgroup.fastscore.model;

import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        boolean isAccountNonLocked
) {}
