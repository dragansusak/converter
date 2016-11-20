package de.zooplus.converter.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by dragan on 19-Nov-16.
 */
@Entity
@Table(name = "conversions")
public class Conversion extends AbstractEntity{

    private String sourceCurrency;

    private String targetCurrency;

    private Double rate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
