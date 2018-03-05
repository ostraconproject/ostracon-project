package ostracon.ostracon_project.account;

import org.springframework.stereotype.Repository;

import ostracon.ostracon_project.lib.HibernateJPABase;

@Repository
public class AccountDAOImpl extends HibernateJPABase<Account, Long> implements AccountDAO{

}
