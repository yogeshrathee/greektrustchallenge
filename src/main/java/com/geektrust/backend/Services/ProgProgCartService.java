package com.geektrust.backend.Services;

import com.geektrust.backend.Repositories.ProgIBillingRepository;
import com.geektrust.backend.Repositories.IProgStudentRepository;

public class ProgProgCartService implements IProgCartService {

    private IProgStudentRepository studentRepository;
    private ProgIBillingRepository billingRepository;

    public ProgProgCartService(IProgStudentRepository studentRepository,
                               ProgIBillingRepository billingRepository) {
        this.billingRepository = billingRepository;
        this.studentRepository = studentRepository;
        
    }

    public void calculateTotalCost() {
        Double totalCost = 0.0;
        totalCost += studentRepository.getCertificationProgrammeCount() * studentRepository.getCertificationProgrammeCost();
        totalCost += studentRepository.getDegreeProgrammeCount() * studentRepository.getDegreeProgrammeCost();
        totalCost += studentRepository.getDiplomaProgrammeCount() * studentRepository.getDiplomaProgrammeCost();
        totalCost += billingRepository.getProMembershipFee();
        billingRepository.setTotalProgramFees(totalCost);
        billingRepository.setTotalAmount(totalCost);
    }

    public void calculateTotalProMembershipDiscount() {
        Double proMembershipDiscount = 0.0;
        proMembershipDiscount += studentRepository.getCertificationProgrammeCount() * studentRepository.getCertificationProgrammeDiscountAmount();
        proMembershipDiscount += studentRepository.getDegreeProgrammeCount() * studentRepository.getDegreeProgrammeDiscountAmount();
        proMembershipDiscount += studentRepository.getDiplomaProgrammeCount() * studentRepository.getDiplomaProgrammeDiscountAmount();
        billingRepository.setProMembershipDiscount(proMembershipDiscount);
    }

}