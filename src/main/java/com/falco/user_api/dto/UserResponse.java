package com.falco.user_api.dto;

public class UserResponse {
    private String name;
    private int points;
    private String referralCode;

    public UserResponse(String name, int points, String referralCode) {
        this.name = name;
        this.points = points;
        this.referralCode = referralCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
}