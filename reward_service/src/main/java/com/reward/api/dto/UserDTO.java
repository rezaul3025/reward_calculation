package com.reward.api.dto;

public class UserDTO {

    private String name;

    private String countryISOCode;

    private String currencyISOCode;

    public UserDTO(String name, String countryISOCode, String currencyISOCode){
        this.name = name;
        this.countryISOCode = countryISOCode;
        this.currencyISOCode = currencyISOCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public String getCurrencyISOCode() {
        return currencyISOCode;
    }

    public void setCurrencyISOCode(String currencyISOCode) {
        this.currencyISOCode = currencyISOCode;
    }
}
