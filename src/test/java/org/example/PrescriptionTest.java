package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

// Unit tests for the Prescription class are contained in this class.
// It examines several scenarios for adding prescription and remarks.

public class PrescriptionTest {

    private Prescription prescription;

    //set up a valid prescription before the test start.
    @BeforeEach
    void setUp() {
        prescription = new Prescription(
                101,
                "Reza",
                "Pratama",
                "169 Spencer Street, CBD, 3000, Australia",
                -2.0f,
                90f,
                -1.5f,
                new Date(),
                "Dr. Octopus"
        );
    }

     //This test is to check a valid prescription
    @Test
    void testAddPrescription_ValidInput() {
        assertTrue(prescription.addPrescription(), "Valid prescription should be added successfully");
    }

    //this test is to add prescription with an invalid first name (less than 2 words)
    @Test
    void testAddPrescription_InvalidFirstName() {
        prescription = new Prescription(
                102, "Re", "Pratama", "169 Spencer Street, CBD, 3000, Australia",
                -2.0f, 90f, -1.5f, new Date(), "Dr. Octopus"
        );
        assertFalse(prescription.addPrescription(), "Prescription with invalid first name should not be added");
    }
    //this test is to add prescription with an invalid address (less than 20 words)
    @Test
    void testAddPrescription_InvalidAddress() {
        prescription = new Prescription(
                103, "Reza", "Pratama", "Address only",
                -2.0f, 90f, -1.5f, new Date(), "Dr. Octopus"
        );
        assertFalse(prescription.addPrescription(), "Prescription with invalid address should not be added");
    }

    //this test is to add prescription with an invalid Sphere (ranges should be between -20.00 and +20.00)
    @Test
    void testAddPrescription_InvalidSphere() {
        prescription = new Prescription(
                105, "Reza", "Pratama", "169 Spencer Street, CBD, 3000, Australia",
                -21.0f, 90f, -1.5f, new Date(), "Dr. Octopus"
        );
        assertFalse(prescription.addPrescription(), "Prescription with invalid sphere should not be added");
    }


    //this test is to add prescription with an invalid Axis (ranges should be between 0 and 180)
    @Test
    void testAddPrescription_InvalidAxis() {
        prescription = new Prescription(
                106, "Reza", "Pratama", "169 Spencer Street, CBD, 3000, Australia",
                -2.0f, 181f, -1.5f, new Date(), "Dr. Octopus"
        );
        assertFalse(prescription.addPrescription(), "Prescription with invalid axis should not be added");
    }

    //this test is to add prescription with an invalid Cylinder (ranges should be between -4.00 to +4.00)
    @Test
    void testAddPrescription_InvalidCylinder() {
        prescription = new Prescription(
                107, "Reza", "Pratama", "169 Spencer Street, CBD, 3000, Australia",
                -2.0f, 90f, -4.5f, new Date(), "Dr. Octopus"
        );
        assertFalse(prescription.addPrescription(), "Prescription with invalid cylinder should not be added");
    }

    //this test is to add a valid remark to the prescription.
    @Test
    void testAddRemark_ValidRemark() {
        assertTrue(prescription.addRemark("This is a valid remark for the prescription.", "client"),
                "Valid remark should be added successfully");
    }

    //this test is to add remark with an invalid category
    @Test
    void testAddRemark_InvalidCategory() {
        assertFalse(prescription.addRemark("This is a valid remark but with invalid category.", "Doctor"),
                "Remark with invalid category should not be added");
    }

    //this test is to add remark with a short word (less than 6 words)
    @Test
    void testAddRemark_WordsTooShort() {
        assertFalse(prescription.addRemark("Fewer", "client"),
                "Remark that is too short should not be added");
    }

    //this test is to add remark with no capital letter
    @Test
    void testAddRemark_LowercaseOnly() {
        assertFalse(prescription.addRemark("only lower case letter.", "client"),
                "Remark not starting with an uppercase letter should not be added");
    }

    //this test is to add remark with a long word (more than 20 words)
    @Test
    void testAddRemark_WordsTooLong() {
        assertFalse(prescription.addRemark("I create this so it will not fit and will have more than twenty word and it will exceed the word limit", "optometrist"));
    }

    //this test is to add remark more that the maximum number allowed (only 2 remarks)
    @Test
    void testAddRemark_MaximumReached() {
        assertTrue(prescription.addRemark("First valid remark for the prescription.", "client"));
        assertTrue(prescription.addRemark("Second valid remark for the prescription.", "optometrist"));
        assertFalse(prescription.addRemark("Third remark should not be added.", "client"));
    }
}
