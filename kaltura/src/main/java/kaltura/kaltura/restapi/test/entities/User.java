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
    "firstName",
    "id",
    "lastName",
    "username",
    "address",
    "affiliateCode",
    "city",
    "countryId",
    "createDate",
    "dynamicData",
    "email",
    "externalId",
    "facebookId",
    "facebookImage",
    "facebookToken",
    "householdId",
    "isHouseholdMaster",
    "phone",
    "roleIds",
    "suspensionState",
    "updateDate",
    "userState",
    "userType",
    "zip"
})
public class User {

    @JsonProperty("objectType")
    private String objectType;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("username")
    private String username;
    @JsonProperty("address")
    private String address;
    @JsonProperty("affiliateCode")
    private String affiliateCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("countryId")
    private Integer countryId;
    @JsonProperty("createDate")
    private Integer createDate;
    @JsonProperty("dynamicData")
    private DynamicData dynamicData;
    @JsonProperty("email")
    private String email;
    @JsonProperty("externalId")
    private String externalId;
    @JsonProperty("facebookId")
    private String facebookId;
    @JsonProperty("facebookImage")
    private String facebookImage;
    @JsonProperty("facebookToken")
    private String facebookToken;
    @JsonProperty("householdId")
    private Integer householdId;
    @JsonProperty("isHouseholdMaster")
    private Boolean isHouseholdMaster;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("roleIds")
    private String roleIds;
    @JsonProperty("suspensionState")
    private String suspensionState;
    @JsonProperty("updateDate")
    private Integer updateDate;
    @JsonProperty("userState")
    private String userState;
    @JsonProperty("userType")
    private UserType userType;
    @JsonProperty("zip")
    private String zip;
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

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("affiliateCode")
    public String getAffiliateCode() {
        return affiliateCode;
    }

    @JsonProperty("affiliateCode")
    public void setAffiliateCode(String affiliateCode) {
        this.affiliateCode = affiliateCode;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("countryId")
    public Integer getCountryId() {
        return countryId;
    }

    @JsonProperty("countryId")
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @JsonProperty("createDate")
    public Integer getCreateDate() {
        return createDate;
    }

    @JsonProperty("createDate")
    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    @JsonProperty("dynamicData")
    public DynamicData getDynamicData() {
        return dynamicData;
    }

    @JsonProperty("dynamicData")
    public void setDynamicData(DynamicData dynamicData) {
        this.dynamicData = dynamicData;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("externalId")
    public String getExternalId() {
        return externalId;
    }

    @JsonProperty("externalId")
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @JsonProperty("facebookId")
    public String getFacebookId() {
        return facebookId;
    }

    @JsonProperty("facebookId")
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    @JsonProperty("facebookImage")
    public String getFacebookImage() {
        return facebookImage;
    }

    @JsonProperty("facebookImage")
    public void setFacebookImage(String facebookImage) {
        this.facebookImage = facebookImage;
    }

    @JsonProperty("facebookToken")
    public String getFacebookToken() {
        return facebookToken;
    }

    @JsonProperty("facebookToken")
    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    @JsonProperty("householdId")
    public Integer getHouseholdId() {
        return householdId;
    }

    @JsonProperty("householdId")
    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    @JsonProperty("isHouseholdMaster")
    public Boolean getIsHouseholdMaster() {
        return isHouseholdMaster;
    }

    @JsonProperty("isHouseholdMaster")
    public void setIsHouseholdMaster(Boolean isHouseholdMaster) {
        this.isHouseholdMaster = isHouseholdMaster;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("roleIds")
    public String getRoleIds() {
        return roleIds;
    }

    @JsonProperty("roleIds")
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @JsonProperty("suspensionState")
    public String getSuspensionState() {
        return suspensionState;
    }

    @JsonProperty("suspensionState")
    public void setSuspensionState(String suspensionState) {
        this.suspensionState = suspensionState;
    }

    @JsonProperty("updateDate")
    public Integer getUpdateDate() {
        return updateDate;
    }

    @JsonProperty("updateDate")
    public void setUpdateDate(Integer updateDate) {
        this.updateDate = updateDate;
    }

    @JsonProperty("userState")
    public String getUserState() {
        return userState;
    }

    @JsonProperty("userState")
    public void setUserState(String userState) {
        this.userState = userState;
    }

    @JsonProperty("userType")
    public UserType getUserType() {
        return userType;
    }

    @JsonProperty("userType")
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
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
