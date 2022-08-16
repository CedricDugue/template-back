package br.com.trackertemplateback.util;

import br.com.trackertemplateback.error.ResourceNotFoundException;
import br.com.trackertemplateback.persistence.model.generic.AbstractEntity;
import br.com.trackertemplateback.persistence.repository.generic.CustomPagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Valida endpoint´s.
 *
 * @author Cedric Christian on 30/04/2021
 */
@Service
public class EndpointUtil implements Serializable {

    public ResponseEntity<?> returnObjectOrNotFound(Optional object) {
        return object.isPresent() ? new ResponseEntity<>(object, OK) : new ResponseEntity<>(NOT_FOUND);
    }

    public ResponseEntity<?> returnObjectOrNotFound(Page object) {
        return object.isEmpty() ? new ResponseEntity<>(NOT_FOUND) : new ResponseEntity<>(object, OK);
    }

    public <T extends AbstractEntity, ID extends Long> void verifyIfRecordExists(long t, CustomPagingAndSortingRepository<T, ID> repository, String msg) {
        if (!repository.existsById(t))
            throw new ResourceNotFoundException(msg);
    }

    public <T extends AbstractEntity, ID extends Long> void verifyIfRecordExists(long t, CustomPagingAndSortingRepository<T, ID> repository) {
        verifyIfRecordExists(t, repository, "Record not found");
    }
}
