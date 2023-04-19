package com.online.library.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode()
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {


}
