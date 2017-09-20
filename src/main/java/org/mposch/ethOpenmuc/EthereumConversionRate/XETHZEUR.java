
package org.mposch.ethOpenmuc.EthereumConversionRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "a",
    "b",
    "c",
    "v",
    "p",
    "t",
    "l",
    "h",
    "o"
})
public class XETHZEUR {

    @JsonProperty("a")
    public List<String> a = null;
    @JsonProperty("b")
    public List<String> b = null;
    @JsonProperty("c")
    public List<String> c = null;
    @JsonProperty("v")
    public List<String> v = null;
    @JsonProperty("p")
    public List<String> p = null;
    @JsonProperty("t")
    public List<Integer> t = null;
    @JsonProperty("l")
    public List<String> l = null;
    @JsonProperty("h")
    public List<String> h = null;
    @JsonProperty("o")
    public String o;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
