package com.falco.user_api.dto;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private int points;
    private String referralCode;

    public UserResponse(Long id, String name, String email, int points, String referralCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.points = points;
        this.referralCode = referralCode;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getReferralCode() { return referralCode; }
    public void setReferralCode(String referralCode) { this.referralCode = referralCode; }
}
