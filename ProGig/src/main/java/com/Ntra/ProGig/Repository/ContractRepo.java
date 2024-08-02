package com.Ntra.ProGig.Repository;

import com.Ntra.ProGig.Entity.Contract;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends JpaRepository<Contract,Integer> {

}
