package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Address;
import br.com.innovix.domain.repository.AddressRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAddressRepository extends JpaRepository<Address, Long>, AddressRepository {
}
