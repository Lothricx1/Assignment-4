package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;

  // In an optometry system, a prescription is represented by this class.
  // Prescriptions and any related remarks are created and validated by it.


// Prescription Class
public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // This validates and adds a new prescription to the system.
    public boolean addPrescription() {
        // This section is to validate the name length.
        if (firstName.length() < 3 || firstName.length() > 15 || lastName.length() < 3 || lastName.length() > 15) {
            System.out.println("Error: Name length condition failed");
            return false;
        }
        //This section is to validate the address length.
        if (address.length() < 20) {
            System.out.println("Error: Address length condition failed");
            return false;
        }
        //This section is to validate the sphere, cylinder, and axis ranges.
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            System.out.println("Error: Sphere, Cylinder, or Axis range condition failed");
            return false;
        }
        //This section is to validate the optometrist name length.
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Error: Optometrist name length condition failed");
            return false;
        }

        // If all conditions are met, write the prescription to a file.
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + examinationDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // This validates and adds a remarks to the prescription.
    public boolean addRemark(String remark, String category) {
        //This section is to check if the maximum remarks has been reached.
        if (postRemarks.size() >= 2) {
            System.out.println("Error: Maximum number of remarks reached");
            return false;
        }
        //This section is to validate remark length and starting letter (first letter must be Upper case).
        String[] words = remark.trim().split("\\s+");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(remark.charAt(0))) {
            System.out.println("Error: Remark must be 6-20 words and start with an uppercase letter");
            return false;
        }
        //This section is to validate remark category.
        if (!category.equals("client") && !category.equals("optometrist")) {
            System.out.println("Error: Category must be 'client' or 'optometrist'");
            return false;
        }

        // If all conditions are met, write the remark to a file.
        try (FileWriter writer = new FileWriter("review.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Category: " + category + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // This section is to add remark to the lists of remarks for this prescription.
        postRemarks.add(remark);
        return true;
    }
}


