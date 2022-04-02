package br.com.integracaodatapoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.integracaodatapoa.models.LinhaOnibusModel;

public interface LinhaOnibusRepository extends JpaRepository<LinhaOnibusModel, String> {

	Optional<LinhaOnibusModel> findByNomeLike(String nome);
}
