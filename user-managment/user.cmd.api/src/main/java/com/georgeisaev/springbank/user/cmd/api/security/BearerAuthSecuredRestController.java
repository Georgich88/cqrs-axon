package com.georgeisaev.springbank.user.cmd.api.security;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
public interface BearerAuthSecuredRestController {

}
