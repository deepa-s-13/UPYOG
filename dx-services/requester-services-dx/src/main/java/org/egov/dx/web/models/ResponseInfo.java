package org.egov.dx.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ResponseInfo should be used to carry metadata information about the response from the server. apiId, ver and msgId in ResponseInfo should always correspond to the same values in respective request&#39;s RequestInfo.
 */
@Validated
@javax.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2018-06-05T12:58:12.679+05:30")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseInfo {

    @JsonProperty("apiId")
    @NotNull
    @Size(max = 128)
    private String apiId = null;

    @JsonProperty("ver")
    @NotNull
    @Size(max = 32)
    private String ver = null;

    @JsonProperty("ts")
    @NotNull
    private Long ts = null;

    @JsonProperty("resMsgId")
    @Size(max = 256)
    private String resMsgId = null;

    @JsonProperty("msgId")
    @Size(max = 256)
    private String msgId = null;

}
