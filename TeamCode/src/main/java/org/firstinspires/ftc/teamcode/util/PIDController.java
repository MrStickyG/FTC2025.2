package org.firstinspires.ftc.teamcode.util;

public class PIDController {
    private double kP;

    private double setpoint;

    public PIDController(double kP) {
        this.kP = kP;
    }

    public void setkP(double kP) {
        this.kP = kP;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double calculate(double processVariable) {
        return calculate(processVariable, setpoint);
    }

    public double calculate(double processVariable, double setpoint) {
        double error = setpoint - processVariable;

        double pOut = error * kP;

        return pOut;
    }
}
