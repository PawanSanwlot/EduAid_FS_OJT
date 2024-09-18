package org.eduaid;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String governmentProfessionalIdNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Column(nullable = true)
    private String trustJoined;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String professionOfVolunteer;
    
    @ManyToMany
    @JoinTable(
        name = "volunteer_saved_media",
        joinColumns = @JoinColumn(name = "volunteer_id"),
        inverseJoinColumns = @JoinColumn(name = "media_id"))
    private List<Media> savedMedia = new ArrayList<>();
    

    // Default constructor
    public Volunteer() {
    }

    // Parameterized constructor
    public Volunteer(String fullName, String email, String mobileNumber, String governmentProfessionalIdNumber,
                     String address, String city, String district, String trustJoined, int age, String gender,
                     LocalDate dateOfBirth, String professionOfVolunteer) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.governmentProfessionalIdNumber = governmentProfessionalIdNumber;
        this.address = address;
        this.city = city;
        this.district = district;
        this.trustJoined = trustJoined;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.professionOfVolunteer = professionOfVolunteer;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getpassword() {
        return password;
    }

    public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGovernmentProfessionalIdNumber() {
        return governmentProfessionalIdNumber;
    }

    public void setGovernmentProfessionalIdNumber(String governmentProfessionalIdNumber) {
        this.governmentProfessionalIdNumber = governmentProfessionalIdNumber;
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

    public String getTrustJoined() {
        return trustJoined;
    }

    public void setTrustJoined(String trustJoined) {
        this.trustJoined = trustJoined;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfessionOfVolunteer() {
        return professionOfVolunteer;
    }

    public void setProfessionOfVolunteer(String professionOfVolunteer) {
        this.professionOfVolunteer = professionOfVolunteer;
    }

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	
	  public List<Media> getSavedMedia() {
	        return savedMedia;
	    }

	    public void setSavedMedia(List<Media> savedMedia) {
	        this.savedMedia = savedMedia;
	    }
}
