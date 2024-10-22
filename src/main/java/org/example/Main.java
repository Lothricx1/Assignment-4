package org.example;
import java.util.Date;


// It is demonstrated how to use the Prescription class by the use of this class.
 // It begins by generating a prescription, then adds it, and then makes an attempt to add remarks.


// Main Class
public class Main {
    public static void main(String[] args) {
        // Example values for creating a Prescription object
        Prescription prescription = new Prescription(
            101, // Prescription ID
            "Reza", // First Name
            "Pratama", // Last Name
            "169 Spencer Street, CBD, 3000, Australia", // Address
            -2.0f, // Sphere
            90f, // Axis
            -1.5f, // Cylinder
            new Date(), // Examination Date
            "Dr. Octopus" // Optometrist Name
        );

        // Attempt to add the prescription and print the result
        boolean result = prescription.addPrescription();
        if (result) {
            System.out.println("Prescription added successfully.");
        } else {
            System.out.println("Failed to add the prescription. Check the input conditions.");
        }

        // Attempt to add a remark from the client.
        result = prescription.addRemark("This is a remark for the prescription from the client.", "client");
        if (result) {
            System.out.println("Remark added successfully.");
        } else {
            System.out.println("Failed to add the remark.");
        }

        // Attempt to add a second remark from the optometrist.
        result = prescription.addRemark("The optometrist suggests a follow-up in six months.", "optometrist");
        if (result) {
            System.out.println("Second remark added successfully.");
        } else {
            System.out.println("Failed to add the second remark.");
        }

        // Attempt to add a third remark to the prescription and print the result (should fail because maximum is 2).
        result = prescription.addRemark("This is an extra remark that should not be added.", "client");
        if (result) {
            System.out.println("Third remark added successfully.");
        } else {
            System.out.println("Failed to add the third remark, as expected.");
        }
    }
}
