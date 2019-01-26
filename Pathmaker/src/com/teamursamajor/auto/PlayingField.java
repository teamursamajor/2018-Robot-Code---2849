package com.teamursamajor.auto;

interface PlayingField extends FieldMeasurements {

    /* This is contains the field
     * measurements in feet.
     * 
     * Location of field objects
     */


     //TODO - Make a field object class
     //TODO - cargo Intake Buttons
     //class FieldObject {};
     FieldObject startPosition();
     FieldObject rocket();
     FieldObject cargoBay();
     FieldObject hatchIntake();
     FieldObject cargoIntake();
     //FieldObject 
}