package apap.tutorial.shapee.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreDetail {
    private String status;

    @JsonProperty("store-license")
    private Integer storeLicense;

    @JsonProperty("valid-until")
    private Date validUntil;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStoreLicense() {
        return storeLicense;
    }

    public void setStoreLicense(Integer storeLicense) {
        this.storeLicense = storeLicense;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
