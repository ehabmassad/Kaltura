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
    "executionTime",
    "result"
})
public class ErrorMessage {

    @JsonProperty("executionTime")
    private Double executionTime;
    @JsonProperty("result")
    private ErrResult result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("executionTime")
    public Double getExecutionTime() {
        return executionTime;
    }

    @JsonProperty("executionTime")
    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    @JsonProperty("result")
    public ErrResult getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(ErrResult result) {
        this.result = result;
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
