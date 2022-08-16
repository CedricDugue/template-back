package br.com.trackertemplateback.persistence.repository.generic;

import br.com.trackertemplateback.persistence.model.generic.AbstractEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Altera assinatura basica da classe PagingAndSortingRepository atendendo especificamente
 * as necessidades do sistema.</br>
 * Inicialmente utilizada no metodo verifyIfRecordExists da classe EndpointUtil como exemplo.
 *
 * @author Cedric Christian on 30/04/2021
 */
@NoRepositoryBean
public interface CustomPagingAndSortingRepository<T extends AbstractEntity, ID extends Long>
        extends PagingAndSortingRepository<T, ID> {

    @Override
    boolean existsById(Long id);
}
