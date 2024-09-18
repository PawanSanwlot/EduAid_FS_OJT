package org.eduaid.Controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.eduaid.Donor;
import org.eduaid.DonorRepository;
import org.eduaid.Media;
import org.eduaid.MediaRepository;
import org.eduaid.MediaService;
import org.eduaid.Trust;
import org.eduaid.TrustRepository;
import org.eduaid.Volunteer;
import org.eduaid.VolunteerRepository;
import org.eduaid.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class LoginController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private TrustRepository trustRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaService mediaService;

    @PostMapping("/volunteer/submit")
    public RedirectView submitVolunteerForm(
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("governmentProfessionalIdNumber") String governmentProfessionalIdNumber,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("district") String district,
            @RequestParam(value = "trustJoined", required = false) String trustJoined,
            @RequestParam("age") int age,
            @RequestParam("gender") String gender,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("professionOfVolunteer") String professionOfVolunteer) {

        Volunteer volunteer = new Volunteer();
        volunteer.setFullName(fullName);
        volunteer.setEmail(email);
        volunteer.setPassword(password);
        volunteer.setMobileNumber(mobileNumber);
        volunteer.setGovernmentProfessionalIdNumber(governmentProfessionalIdNumber);
        volunteer.setAddress(address);
        volunteer.setCity(city);
        volunteer.setDistrict(district);
        volunteer.setTrustJoined(trustJoined);
        volunteer.setAge(age);
        volunteer.setGender(gender);
        volunteer.setDateOfBirth(LocalDate.parse(dateOfBirth));
        volunteer.setProfessionOfVolunteer(professionOfVolunteer);

        volunteerRepository.save(volunteer);

        return new RedirectView("/login");
    }
    
    
    @GetMapping("/profile")
    public String viewProfile(HttpServletRequest request, Model model) {
        // Retrieve the email from session
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("currentUserEmail");

        // Fetch volunteer details based on the email
        Volunteer volunteer = volunteerRepository.findByEmail(userEmail);
        if (volunteer != null) {
            model.addAttribute("volunteer", volunteer);
            return "profile";
        }

        // Handle case where volunteer is not found
        model.addAttribute("error", "Volunteer not found");
        return "error";
    }

    @PostMapping("/trust/submit")
    public RedirectView registerTrust(
            @RequestParam("trust-name") String trustName,
            @RequestParam("govt-id") String governmentId,
            @RequestParam("trust-address") String address,
            @RequestParam("trust-city") String city,
            @RequestParam("trust-district") String district,
            @RequestParam("trust-year") int startedYear,
            @RequestParam("donor-count") int donorCount,
            @RequestParam("volunteer-count") int volunteerCount,
            @RequestParam("total-members") int totalMembersCount,
            @RequestParam("member-name[]") String[] members,
            @RequestParam("trust-type") String trustType,
            @RequestParam("trust-email") String email,
            @RequestParam("trust-password") String password,
            RedirectAttributes attributes) {

        Trust trust = new Trust();
        trust.setTrustName(trustName);
        trust.setGovernmentId(governmentId);
        trust.setAddress(address);
        trust.setCity(city);
        trust.setDistrict(district);
        trust.setStartedYear(startedYear);
        trust.setDonorCount(donorCount);
        trust.setVolunteerCount(volunteerCount);
        trust.setTotalMembersCount(totalMembersCount);
        trust.setMembers(Arrays.asList(members));
        trust.setTrustType(trustType);
        trust.setEmail(email);
        trust.setPassword(password);

        trustRepository.save(trust);

        attributes.addFlashAttribute("success", "Trust registered successfully!");
        return new RedirectView("/login");
    }

    @PostMapping("/donate")
    public RedirectView registerDonor(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("bank-details") String bankDetails,
            @RequestParam("ifsc-code") String ifscCode,
            @RequestParam("holder-name") String holderName,
            RedirectAttributes attributes) {

        Donor donor = new Donor();
        donor.setFirstName(firstName);
        donor.setLastName(lastName);
        donor.setEmail(email);
        donor.setpassword(password); // Correct method name here
        donor.setPhone(phone);
        donor.setAddress(address);
        donor.setBankDetails(bankDetails);
        donor.setIfscCode(ifscCode);
        donor.setHolderName(holderName);

        donorRepository.save(donor);

        attributes.addFlashAttribute("success", "Donor registered successfully!");
        return new RedirectView("/login");
    }

    @PostMapping("/login")
    public RedirectView loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request) {

        // Check in Volunteer table
        Volunteer volunteer = volunteerRepository.findByEmail(email);
        if (volunteer != null && volunteer.getPassword().equals(password)) {
            request.getSession().setAttribute("currentUserEmail", email);
            return new RedirectView("/vol_dashbord");
        }

        // Check in Donor table
        Donor donor = donorRepository.findByEmail(email);
        if (donor != null && donor.getPassword().equals(password)) {
            request.getSession().setAttribute("currentUserEmail", email);
            return new RedirectView("/donor_dashboard");
        }

        // Check in Trust table
        Trust trust = trustRepository.findByEmail(email);
        if (trust != null && trust.getPassword().equals(password)) {
            request.getSession().setAttribute("currentUserEmail", email);
            return new RedirectView("/trust_dashboard");
        }

        return new RedirectView("/login?error=true");
    }

    @PostMapping("/media/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "coverPhoto", required = false) MultipartFile coverPhoto,
            @RequestParam("description") String description,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        try {
            // Get the current user's email from the session
            HttpSession session = request.getSession();
            String userEmail = (String) session.getAttribute("currentUserEmail");

            // Check if user email is available
            if (userEmail == null || userEmail.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "User is not logged in!");
                return "redirect:/login"; // Redirect to login page if not logged in
            }

            // Ensure the file is not empty
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Please select a file to upload!");
                return "redirect:/media/view"; // Redirect to media view if no file selected
            }

            // Create a new Media object and set its attributes
            Media media = new Media();
            media.setFileName(file.getOriginalFilename());
            media.setFileType(file.getContentType());
            media.setData(file.getBytes());
            media.setDescription(description);
            media.setUserEmail(userEmail); // Set the user's email

            // If a cover photo is provided, set the cover photo
            if (coverPhoto != null && !coverPhoto.isEmpty()) {
                media.setCoverPhoto(coverPhoto.getBytes());
            }

            // Save the media object using the service
            mediaService.saveFile(media);

            // Add a success message and redirect
            redirectAttributes.addFlashAttribute("message", "File uploaded successfully!");
            return "redirect:/media_post"; // Redirect to media post view

        } catch (IOException e) {
            // Log the error and return an error message
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "File upload failed! " + e.getMessage());
            return "redirect:/media/view"; // Redirect back to the media view on failure
        }
    }

   
    
    
    
    // Fetch all media files and display them
    @GetMapping("/vol_dashbord")
    public String getAllMediaFiles(Model model) {
        List<Media> mediaList = mediaService.getAllFiles();
        model.addAttribute("mediaList", mediaList);
        return "Volunteer_dashboard"; // Return the view page for displaying media
    }

    
    @GetMapping("/trust_dashboard")
    public String getAllMediaFiles1(Model model) {
        List<Media> mediaList = mediaService.getAllFiles();
        model.addAttribute("mediaList", mediaList);
        return "trust_dashboard"; // Return the view page for displaying media
    }
    
    @GetMapping("/donor_dashboard")
    public String getAllMediaFiles2(Model model) {
        List<Media> mediaList = mediaService.getAllFiles();
        model.addAttribute("mediaList", mediaList);
        return "donor_dashboard"; // Return the view page for displaying media
    }
    
    
    // Endpoint to stream the video content
    @GetMapping("/media/video/{id}")
    public ResponseEntity<ByteArrayResource> streamVideo(@PathVariable Long id) {
        Media media = mediaService.getFileById(id);
        if (media != null && media.getFileType().startsWith("video")) {
            ByteArrayResource resource = new ByteArrayResource(media.getData());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + media.getFileName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(media.getData().length)
                    .contentType(org.springframework.http.MediaType.parseMediaType(media.getFileType()))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    @GetMapping("/user_media")
    public String userMedia(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("currentUserEmail");
        
        System.out.println("Current User Email: " + userEmail); // Debugging line

        // Fetch user details based on the email
        Volunteer user = volunteerRepository.findByEmail(userEmail);
        if (user != null) {
            model.addAttribute("user", user);
            // Fetch media for this user
            List<Media> mediaList = mediaService.getMediaByUserEmail(userEmail);
            model.addAttribute("mediaList", mediaList);
            return "user_media";
        }

        // Handle case where user is not found
        model.addAttribute("errorMessage", "User not found");
        return "error";
    }
    
    

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/trust_volunteer-list")
    public String showVolunteers(Model model) {
        // Fetch all volunteers from the service
        model.addAttribute("volunteers", volunteerService.getAllVolunteers());

        return "trust_volunteer-list";
    }
    

    
    
    @GetMapping("/fetch-details")
    public String fetchUserDetails(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String currentUserEmail = (String) session.getAttribute("currentUserEmail");

        if (currentUserEmail == null) {
            model.addAttribute("message", "No user is logged in.");
            return "error";
        }

        // Try to find a volunteer with this email
        Volunteer volunteer = volunteerRepository.findByEmail(currentUserEmail);
        if (volunteer != null) {
            model.addAttribute("user", volunteer);
            model.addAttribute("role", "Volunteer");
            return "User Details";  // Return a view to display the user details
        }

        // Try to find a donor with this email
        Donor donor = donorRepository.findByEmail(currentUserEmail);
        if (donor != null) {
            model.addAttribute("user", donor);
            model.addAttribute("role", "Donor");
            return "User Details";  // Return a view to display the user details
        }

        // Try to find a trust with this email
        Trust trust = trustRepository.findByEmail(currentUserEmail);
        if (trust != null) {
            model.addAttribute("user", trust);
            model.addAttribute("role", "Trust");
            return "User Details";  // Return a view to display the user details
        }

        model.addAttribute("message", "User not found in any role.");
        return "error";
    }

    



    
    
    
    
    
    @PostMapping("/logout")
    public RedirectView logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get the session if it exists, don't create a new one
        if (session != null) {
            session.invalidate(); // Invalidate the session, logging out the user
        }
        return new RedirectView("/login?logout=true"); // Redirect to login page with a logout success message
    }

}


