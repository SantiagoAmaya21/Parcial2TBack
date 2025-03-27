package edu.eci.cvds.parcial2t.repository;


import edu.eci.cvds.parcial2t.model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase,String> {
    List<Purchase> findByUserId(String userId);
}
