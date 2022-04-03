package br.com.integracaodatapoa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.integracaodatapoa.models.LinhaOnibusModel;

public interface LinhaOnibusRepository extends JpaRepository<LinhaOnibusModel, String> {

	Optional<List<LinhaOnibusModel>> findByNomeLike(String nome);
	
	Optional<LinhaOnibusModel> findByCodigo(String codigo);

	Optional<List<LinhaOnibusModel>> findByNome(String nome);
}
