package kaltura.kaltura.restapi.test.entities;




import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "objectType",
    "ks",
    "refreshToken"
})
public class LoginSession {

    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("ks")
    private String ks;
    @JsonProperty("refreshToken")
    private String refreshToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("objectType")
    public String getObjectType() {
        return objectType;
    }

    @JsonProperty("objectType")
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @JsonProperty("ks")
    public String getKs() {
        return ks;
    }

    @JsonProperty("ks")
    public void setKs(String ks) {
        this.ks = ks;
    }

    @JsonProperty("refreshToken")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("refreshToken")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
