package ru.itis.services;

import ru.itis.dto.RequisiteDto;
import ru.itis.forms.RequisiteAddForm;
import ru.itis.forms.RequisiteEditForm;
import ru.itis.models.Requisite;

import java.util.Optional;

public interface RequisiteService {
    Requisite addRequisite(RequisiteAddForm requisiteCreateForm, Long companyId);

    Optional<Requisite> getRequisiteByCompanyId(Long companyId);

    RequisiteDto showRequisite(Long requisiteId);

    Requisite editRequisite(RequisiteEditForm requisiteEditForm);

    void deleteRequisite(Long requisiteId);
}
