package com.georgeisaev.springbank.user.query.api.security;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
public interface BearerAuthSecuredRestController {

}
