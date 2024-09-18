package org.eduaid.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
    @GetMapping("/")
    public String home() {
        return "index";  // This returns the "index.html" from the templates folder
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";  // This returns the "login.html" from the templates folder
    }

    
    @GetMapping("/volunteer")
    public String volunteer() {
        return "volunteer";  // This returns the "register.html" from the templates folder
    }
    
    @GetMapping("/trust")
    public String trust() {
        return "trust";  // This returns the "register.html" from the templates folder
    }
    
    @GetMapping("/donater")
    public String donater() {
        return "donater";  // This returns the "register.html" from the templates folder
    }
    
    @GetMapping("/payment")
    public String payment() {
        return "payment";  // This returns the "register.html" from the templates folder
    }
    
    //VOLUNTEER
//    @GetMapping("/vol_dashbord")
//    public String volunteer_dashboard() {
//    	return "volunteer_dashboard";
//    }


    @GetMapping("/volunteer_donarlist")
    public String volunteerdonarlist() {
        return "volunteer_donarlist"; // Adjust path if necessary
    }
    
    @GetMapping("/volunteer_addcases")
    public String volunteerAddCases() {
        return "volunteer_addcases"; // Adjust path if necessary
    }

    @GetMapping("/volunteer_profile")
    public String volunteerProfile() {
        return "volunteer_profile"; // Adjust path if necessary
    }

    @GetMapping("/volunteer_Trustlist")
    public String volunteerTrustList() {
        return "volunteer_Trustlist"; // Adjust path if necessary
    }

    @GetMapping("/volunteer_favorites")
    public String volunteerFavorites() {
        return "volunteer_favorites"; // Adjust path if necessary
    }

    @GetMapping("/volunteer_cases_status")
    public String volunteerCasesStatus() {
        return "volunteer_cases_status"; // Adjust path if necessary
    }

    @GetMapping("/volunteer_report_problem")
    public String volunteerReportProblem() {
        return "volunteer_report-problem"; // Adjust path if necessary
    }

    
    //DONOR
    
    
    @GetMapping("/donor_profile")
    public String donorProfile() {
        return "donor_profile"; // Adjust path if necessary
    }

    @GetMapping("/donor_donor-list")
    public String donorDonorList() {
        return "donor_donor-list"; // Adjust path if necessary
    }

    @GetMapping("/donor_trust-list")
    public String donorTrustList() {
        return "donor_trust-list"; // Adjust path if necessary
    }

    @GetMapping("/donor_volunteer-list")
    public String donorVolunteerList() {
        return "donor_volunteer-list"; // Adjust path if necessary
    }

    @GetMapping("/donor_favorites")
    public String donorFavorites() {
        return "donor_favorites"; // Adjust path if necessary
    }

    @GetMapping("/donor_completed-cases")
    public String donorCompletedCases() {
        return "donor_completed-cases"; // Adjust path if necessary
    }

    @GetMapping("/donor_report-problem")
    public String donorReportProblem() {
        return "donor_report-problem"; // Adjust path if necessary
    }

    
    
    //TRUST
//    @GetMapping("/trust_dashboard")
//    public String trust_dashboard() {
//    	return "trust_dashboard";
//    }

    @GetMapping("/trust_profile")
    public String trustProfile() {
        return "trust_profile"; // Adjust path if necessary
    }

    @GetMapping("/trust_donor-list")
    public String trustDonorList() {
        return "trust_report-problem"; // Adjust path if necessary
    }

    @GetMapping("/trust_trust-list")
    public String trustTrustList() {
        return "trust_trust-list"; // Adjust path if necessary
    }

//    @GetMapping("/trust_volunteer-list")
//    public String trustVolunteerList() {
//        return "trust_volunteer-list"; // Adjust path if necessary
//    }

    @GetMapping("/trust_favorites")
    public String trustFavorites() {
        return "trust_favorites"; // Adjust path if necessary
    }

    @GetMapping("/trust_Cases Status")
    public String trustCasesStatus() {
        return "trust_Cases Status"; // Adjust path if necessary
    }

    @GetMapping("/trust_report-problem")
    public String trustReportProblem() {
        return "trust_report-problem"; // Adjust path if necessary
    }
    

    @GetMapping("profilee")
    public String profilee() {
    	return "profilee";
    }
    
    

}
