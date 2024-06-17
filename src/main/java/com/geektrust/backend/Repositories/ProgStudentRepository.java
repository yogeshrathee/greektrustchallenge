package com.geektrust.backend.Repositories;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.Entities.CertProg;
import com.geektrust.backend.Entities.DegProg;
import com.geektrust.backend.Entities.DipProg;
import com.geektrust.backend.Entities.ProgrammeDisCountCoupons;
import com.geektrust.backend.Entities.Programme;
import com.geektrust.backend.Entities.ProgrammeCat;
import com.geektrust.backend.Entities.ProgrammeStudent;


public class ProgStudentRepository implements IProgStudentRepository {
    private ProgrammeStudent programmeStudent;
    private Programme certificationProgramme;
    private Programme degreeProgramme;
    private Programme diplomaProgramme;
    private List<ProgrammeDisCountCoupons> programmeDisCountCoupons;
    public ProgStudentRepository(ProgrammeStudent programmeStudent){
        this.programmeStudent = programmeStudent;
        certificationProgramme = new CertProg();
        degreeProgramme = new DegProg();
        diplomaProgramme = new DipProg();
        programmeDisCountCoupons = new ArrayList<>();
    }
    public ProgStudentRepository(ProgrammeStudent programmeStudent, CertProg certProg, DegProg degProg, DipProg dipProg){
        this(programmeStudent);
        this.certificationProgramme = certProg;
        this.degreeProgramme = degProg;
        this.diplomaProgramme = dipProg;
        programmeDisCountCoupons = new ArrayList<>();
    }
    public ProgrammeStudent getStudent() {
        return programmeStudent;
    }

    public Integer getTotalProgrammeCount() {
        return certificationProgramme.getProgramCount() + degreeProgramme.getProgramCount() + diplomaProgramme.getProgramCount();
    }
    @Override
    public void addProgramsToCart(ProgrammeCat programmeCat, Integer quantity) {
        if(programmeCat == ProgrammeCat.CERTIFICATION) {
            certificationProgramme.addProgram(quantity);
        }
        else if(programmeCat == ProgrammeCat.DEGREE) {
            degreeProgramme.addProgram(quantity);
        }
        else {
            diplomaProgramme.addProgram(quantity);
        }
    }
    @Override
    public void addProMembershipPlan() {
        programmeStudent.addProMembershipPlan();
        certificationProgramme.addProMembershipDiscountCoupon();
        degreeProgramme.addProMembershipDiscountCoupon();
        diplomaProgramme.addProMembershipDiscountCoupon();
    }
    @Override
    public Integer getDegreeProgrammeCount() {
        return degreeProgramme.getProgramCount();
    }
    @Override
    public Integer getCertificationProgrammeCount() {
        return certificationProgramme.getProgramCount();
    }
    @Override
    public Integer getDiplomaProgrammeCount() {
        return diplomaProgramme.getProgramCount();
    }
    @Override
    public void addDiscountCoupons(ProgrammeDisCountCoupons discountCoupon) {
        programmeDisCountCoupons.add(discountCoupon);
    }
    @Override
    public Double getDegreeProgrammeCost() {
        return degreeProgramme.getProgramFee();
    }
    @Override
    public Double getCertificationProgrammeCost() {
        return certificationProgramme.getProgramFee();
    }
    @Override
    public Double getDiplomaProgrammeCost() {
        return diplomaProgramme.getProgramFee();
    }
    @Override
    public Double getDegreeProgrammeDiscount() {
        return degreeProgramme.getProgramDiscount();
    }
    @Override
    public Double getCertificationProgrammeDiscount() {
        return certificationProgramme.getProgramDiscount();
    }
    @Override
    public Double getDiplomaProgrammeDiscount() {
        return diplomaProgramme.getProgramDiscount();
    }
    @Override
    public Boolean containsDiscountCoupon(ProgrammeDisCountCoupons discountCoupon) {
        return programmeDisCountCoupons.contains(discountCoupon);
    }
    @Override
    public Double getCertificationProgrammeDiscountAmount() {
        return certificationProgramme.getProgramDiscountAmount();
    }
    @Override
    public Double getDegreeProgrammeDiscountAmount() {
        return degreeProgramme.getProgramDiscountAmount();
    }
    @Override
    public Double getDiplomaProgrammeDiscountAmount() {
        return diplomaProgramme.getProgramDiscountAmount();
    }
}
