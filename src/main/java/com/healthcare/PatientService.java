package com.healthcare;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PatientService {

    private List<Patient> patients = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(4);

    // Sample data loaded on startup
    public PatientService() {
        patients.add(new Patient(1, "John Smith", 45,
                "Hypertension", "Male", "555-0101"));
        patients.add(new Patient(2, "Sarah Jones", 32,
                "Diabetes", "Female", "555-0102"));
        patients.add(new Patient(3, "Mike Brown", 60,
                "Arthritis", "Male", "555-0103"));
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patients;
    }

    // Get patient by ID
    public Patient getPatientById(int id) {
        return patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Add new patient
    public void addPatient(Patient patient) {
        patient.setId(idCounter.getAndIncrement());
        patients.add(patient);
    }

    // Update patient
    public void updatePatient(Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == updatedPatient.getId()) {
                patients.set(i, updatedPatient);
                return;
            }
        }
    }

    // Delete patient
    public void deletePatient(int id) {
        patients.removeIf(p -> p.getId() == id);
    }

    // Count patients
    public int countPatients() {
        return patients.size();
    }
}