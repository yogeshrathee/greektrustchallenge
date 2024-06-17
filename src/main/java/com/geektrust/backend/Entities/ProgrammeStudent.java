package com.geektrust.backend.Entities;

import com.geektrust.backend.Util.ProgrammeGeekDmyConstant;

public class ProgrammeStudent {

    private Boolean isProMember;
    private Double proMembershipFee;
    private Double enrollmentFee;

    public ProgrammeStudent() {
        isProMember = false;
        proMembershipFee = 0.0;
        enrollmentFee = 0.0;
    }

    public void addProMembershipPlan() {
        isProMember = true;
        proMembershipFee = ProgrammeGeekDmyConstant.PRO_MEMBERSHIP_FEE;
    }

    public void addEnrollmentFee() {
        enrollmentFee = ProgrammeGeekDmyConstant.ENROLLMENT_FEE;
    }

    public Boolean getProMembershipStatus() {
        return isProMember;
    }

    public Double getProMembershipFee() {
        return proMembershipFee;
    }

    public Double getEnrollmentFee() {
        return enrollmentFee;
    }
    
}
