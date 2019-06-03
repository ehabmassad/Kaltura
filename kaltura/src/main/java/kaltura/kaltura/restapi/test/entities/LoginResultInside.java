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
    "loginSession",
    "user"
})
public class LoginResultInside {

    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("loginSession")
    private LoginSession loginSession;
    @JsonProperty("user")
    private User user;
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

    @JsonProperty("loginSession")
    public LoginSession getLoginSession() {
        return loginSession;
    }

    @JsonProperty("loginSession")
    public void setLoginSession(LoginSession loginSession) {
        this.loginSession = loginSession;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
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
