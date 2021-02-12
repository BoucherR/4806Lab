package AppStarter.JpaMemoryDatabase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findById(long id);
    void deleteById(long id);
}
