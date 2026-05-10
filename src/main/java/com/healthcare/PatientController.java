package com.healthcare;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientService patientService;
    private final SecurityLogger securityLogger;

    public PatientController(PatientService patientService,
                             SecurityLogger securityLogger) {
        this.patientService = patientService;
        this.securityLogger = securityLogger;
    }

    // Home redirect
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalPatients", patientService.countPatients());
        model.addAttribute("patients", patientService.getAllPatients());
        securityLogger.logApiRequest("GET", "/dashboard");
        return "dashboard";
    }

    // View all patients
    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        securityLogger.logApiRequest("GET", "/patients");
        return "patients";
    }

    // Show add patient form
    @GetMapping("/patients/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    // Submit add patient form
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        securityLogger.logApiRequest("POST", "/patients/add");
        return "redirect:/patients";
    }

    // Show edit patient form
    @GetMapping("/patients/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "edit-patient";
    }

    // Submit edit patient form
    @PostMapping("/patients/edit/{id}")
    public String updatePatient(@PathVariable int id,
                                @ModelAttribute Patient patient) {
        patient.setId(id);
        patientService.updatePatient(patient);
        securityLogger.logApiRequest("POST", "/patients/edit/" + id);
        return "redirect:/patients";
    }

    // Delete patient
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        securityLogger.logApiRequest("DELETE", "/patients/delete/" + id);
        return "redirect:/patients";
    }
}