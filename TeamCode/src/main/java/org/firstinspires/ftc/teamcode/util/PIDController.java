package org.firstinspires.ftc.teamcode.util;

public class PIDController {
    private double kP;

    private double setpoint;
    private double error = 0.0;

    public PIDController(double kP) {
        this.kP = kP;
    }

    public PIDController() {
        this(0.0);
    }

    public void setkP(double kP) {
        this.kP = kP;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double getError() {
        return error;
    }

    public double calculate(double processVariable) {
        return calculate(processVariable, setpoint);
    }

    public double calculate(double processVariable, double setpoint) {
        error = setpoint - processVariable;

        double pOut = error * kP;

        return pOut;
    }
}
