package backend_group_5.we_lead_bootcamp.repository;


import backend_group_5.we_lead_bootcamp.model.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    @Transactional
    List<Address> findAddressesByUserId(Long userId);


}
