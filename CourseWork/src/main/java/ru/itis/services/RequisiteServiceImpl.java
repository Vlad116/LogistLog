package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.itis.dto.RequisiteDto;
import ru.itis.forms.RequisiteAddForm;
import ru.itis.forms.RequisiteEditForm;
import ru.itis.models.Requisite;
import ru.itis.repository.CompaniesRepository;
import ru.itis.repository.RequisiteRepository;

import java.util.Optional;

//@Lazy
//@Component
//public class RequisiteServiceImpl implements RequisiteService {
//
//    RequisiteRepository requisiteRepository;
//    CompanyService companyService;
//
////    @Lazy
//    @Autowired
//    public RequisiteServiceImpl(RequisiteRepository requisiteRepository, CompanyService companyService) {
//        this.requisiteRepository = requisiteRepository;
//        this.companyService = companyService;
//    }
//
//    @Override
//    public Requisite addRequisite(RequisiteAddForm requisiteAddForm, Long companyId) {
//        Requisite requisite = Requisite.builder()
//                .company(requisiteAddForm.getCompany())
//                .fullCompanyName(requisiteAddForm.getFullCompanyName())
//                .legalAddress(requisiteAddForm.getLegalAddress())
//                .actualAddress(requisiteAddForm.getActualAddress())
//                .inn(requisiteAddForm.getInn())
//                .ogrn(requisiteAddForm.getOgrn())
//                .kpp(requisiteAddForm.getKpp())
//                .okpo(requisiteAddForm.getOkpo())
//                .currentBankAccount(requisiteAddForm.getCurrentBankAccount())
//                .fullNameOfTheBank(requisiteAddForm.getFullNameOfTheBank())
//                .correspondentAccountOfTheBank(requisiteAddForm.getCorrespondentAccountOfTheBank())
//                .bic(requisiteAddForm.getBic())
//                .build();
//        requisiteRepository.save(requisite);
//        companyService.setRequisite(getRequisiteByCompanyId(companyId).get().getId(),companyId);
//        return requisite;
//    }
//
//    @Override
//    public Optional<Requisite> getRequisiteByCompanyId(Long companyId) {
//        return requisiteRepository.findByCompanyId(companyId);
//    }
//
//    @Override
//    public RequisiteDto showRequisite(Long requisiteId) {
//        return RequisiteDto.from(requisiteRepository.findOneByID(requisiteId));
//    }
//
//    @Override
//    public Requisite editRequisite(RequisiteEditForm requisiteEditForm) {
//        Requisite requisite = Requisite.builder()
//                .id(requisiteEditForm.getId())
//                .fullCompanyName(requisiteEditForm.getFullCompanyName())
//                .legalAddress(requisiteEditForm.getLegalAddress())
//                .actualAddress(requisiteEditForm.getActualAddress())
//                .inn(requisiteEditForm.getInn())
//                .ogrn(requisiteEditForm.getOgrn())
//                .kpp(requisiteEditForm.getKpp())
//                .okpo(requisiteEditForm.getOkpo())
//                .currentBankAccount(requisiteEditForm.getCurrentBankAccount())
//                .fullNameOfTheBank(requisiteEditForm.getFullNameOfTheBank())
//                .correspondentAccountOfTheBank(requisiteEditForm.getCorrespondentAccountOfTheBank())
//                .bic(requisiteEditForm.getBic())
//                .build();
//        requisiteRepository.update(requisite);
//        return requisite;
//    }
//
//    @Override
//    public void deleteRequisite(Long requisiteId) {
//        requisiteRepository.delete(requisiteId);
//    }
//}
