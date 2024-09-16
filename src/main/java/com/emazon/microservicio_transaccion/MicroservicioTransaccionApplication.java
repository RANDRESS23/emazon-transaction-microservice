package com.emazon.microservicio_transaccion;

import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.entity.StateEntity;
import com.emazon.microservicio_transaccion.adapters.driven.jpa.mysql.repository.IStateRepository;
import com.emazon.microservicio_transaccion.domain.enums.StateEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MicroservicioTransaccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioTransaccionApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IStateRepository stateRepository) {
		return args -> {
			/* Create STATES */
			StateEntity statePending = StateEntity.builder()
					.name(StateEnum.PENDIENTE)
					.build();

			StateEntity stateApproved = StateEntity.builder()
					.name(StateEnum.APROBADO)
					.build();

			StateEntity stateReject = StateEntity.builder()
					.name(StateEnum.RECHAZADO)
					.build();

			if (!stateRepository.findByName(statePending.getName()).isPresent()) {
				stateRepository.save(statePending);
			}

			if (!stateRepository.findByName(stateApproved.getName()).isPresent()) {
				stateRepository.save(stateApproved);
			}

			if (!stateRepository.findByName(stateReject.getName()).isPresent()) {
				stateRepository.save(stateReject);
			}
		};
	}
}
