package ru.itis.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.itis.models.Requisite;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

////@Lazy
//@Component
//public class RequisiteRepositoryHibernateImpl implements RequisiteRepository {
//
//    @Autowired
////    @Lazy
//    SessionFactory sessionFactory;
//
//    @Autowired
////    @Lazy
//    EntityManager em;
//
//    @Autowired
////    @Lazy
//    public RequisiteRepositoryHibernateImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
//        em = emf.createEntityManager();
//    }
//
//    @Override
//    @Transactional
//    public void save(Requisite requisite) {
//        Query query = em.createNativeQuery("INSERT INTO requisite(id,company_id,full_company_name,legal_address,actual_address,inn,ogrn,kpp,okpo,current_bank_account,full_name_of_the_bank,correspondent_account_of_the_bank,bic)" +
//                " VALUES (:id,:company_id,:full_company_name,:legal_address,:actual_address,:inn,:ogrn,:kpp,:okpo,:current_bank_account,:full_name_of_the_bank,:correspondent_account_of_the_bank,:bic);");
//        query.setParameter("id", requisite.getId());
//        query.setParameter("company_id", requisite.getCompany().getId());
//        query.setParameter("full_company_name", requisite.getFullCompanyName());
//        query.setParameter("legal_address", requisite.getLegalAddress());
//        query.setParameter("actual_address", requisite.getActualAddress());
//        query.setParameter("inn", requisite.getInn());
//        query.setParameter("ogrn", requisite.getOgrn());
//        query.setParameter("kpp", requisite.getKpp());
//        query.setParameter("okpo", requisite.getOkpo());
//        query.setParameter("current_bank_account", requisite.getCurrentBankAccount());
//        query.setParameter("full_name_of_the_bank", requisite.getFullNameOfTheBank());
//        query.setParameter("correspondent_account_of_the_bank", requisite.getCorrespondentAccountOfTheBank());
//        query.setParameter("bic", requisite.getBic());
//        query.executeUpdate();
//    }
//
//    @Override
//    public void update(Requisite model) {
//        Session session = this.sessionFactory.openSession();
//        session.beginTransaction();
//        session.update(model);
//        session.getTransaction().commit();
//        if (session.isOpen()) {
//            session.close();
//        }
//    }
//
//    @Override
//    public void delete(Long id) {
//        Session session = this.sessionFactory.openSession();
//        session.beginTransaction();
//        Requisite del = (Requisite) session.get(Requisite.class, id);
//        session.delete(del);
//        session.getTransaction().commit();
//        if (session.isOpen()) {
//            session.close();
//        }
//    }
//
//    @Override
//    public Optional<Requisite> findOneByFullCompanyName(String fullCompanyName) {
//        Session session = this.sessionFactory.openSession();
//        Query q = session.createNativeQuery("SELECT * FROM requisite WHERE full_company_name = :fullCompanyName");
//        q.setParameter("fullCompanyName", fullCompanyName);
//        Requisite requisite = (Requisite) q.getSingleResult();
//        return Optional.ofNullable(requisite);
//    }
//
//    @Override
//    public Optional<Requisite> findByCompanyId(Long companyId) {
//        Session session = this.sessionFactory.openSession();
//        Query q = session.createNativeQuery("SELECT * FROM requisite WHERE company_id = :company_id");
//        q.setParameter("company_id", companyId);
//        Requisite requisite = (Requisite) q.getSingleResult();
//        return Optional.ofNullable(requisite);
//    }
//
//    @Override
//    public Optional<Requisite> findOneByID(Long id) {
//        //Entity Manager
//        Requisite u = em.find(Requisite.class, id);
//        return Optional.ofNullable(u);
//    }
//
//    @Override
//    public List<Requisite> findAll() {
//        Session session = this.sessionFactory.openSession();
//        Query q = session.createNativeQuery("SELECT * FROM requisite");
//        return (List<Requisite>) q.getResultList();
//    }
//
//}
