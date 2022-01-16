package com.georgeisaev.springbank.user.cmd.api.dto;

import com.georgeisaev.springbank.usercore.dto.BaseResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserResponse extends BaseResponse {

    String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }

}
