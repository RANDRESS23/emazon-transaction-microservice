package com.emazon.microservicio_transaccion.domain.spi;

public interface IAuthPersistencePort {
    Long getAuthenticatedUserId();
}
