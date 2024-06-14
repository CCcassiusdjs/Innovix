package br.com.innovix.frameworks.repository.jpa;

import br.com.innovix.domain.entity.Store;
import br.com.innovix.domain.repository.StoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStoreRepository extends JpaRepository<Store, Long>, StoreRepository {
}
