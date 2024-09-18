package org.eduaid;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Trust {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trustName;
    private String governmentId;
    private String address;
    private String city;
    private String district;
    private int startedYear;
    private int donorCount;
    private int volunteerCount;
    private int totalMembersCount;

    @ElementCollection
    private List<String> members;

    private String trustType;
    private String email;
    private String password;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrustName() {
        return trustName;
    }

    public void setTrustName(String trustName) {
        this.trustName = trustName;
    }

    public String getGovernmentId() {
        return governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getStartedYear() {
        return startedYear;
    }

    public void setStartedYear(int startedYear) {
        this.startedYear = startedYear;
    }

    public int getDonorCount() {
        return donorCount;
    }

    public void setDonorCount(int donorCount) {
        this.donorCount = donorCount;
    }

    public int getVolunteerCount() {
        return volunteerCount;
    }

    public void setVolunteerCount(int volunteerCount) {
        this.volunteerCount = volunteerCount;
    }

    public int getTotalMembersCount() {
        return totalMembersCount;
    }

    public void setTotalMembersCount(int totalMembersCount) {
        this.totalMembersCount = totalMembersCount;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getTrustType() {
        return trustType;
    }

    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
