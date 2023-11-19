package com.portalSMB.portalSMB.model.DTO;

public record RegisterDTO(String login, String password, String email, UserRole role) {
}
