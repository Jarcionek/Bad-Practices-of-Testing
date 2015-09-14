package presentation._09_fake_not_meeting_the_contract_of_the_interface;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JsonLoggingExceptionMapper implements ExceptionMapper<RuntimeException> {

    private final UuidProvider uuidProvider;
    private final ExceptionLogger exceptionLogger;

    public JsonLoggingExceptionMapper(UuidProvider uuidProvider, ExceptionLogger exceptionLogger) {
        this.uuidProvider = uuidProvider;
        this.exceptionLogger = exceptionLogger;
    }

    @Override
    public Response toResponse(RuntimeException exception) {
        String uuid = uuidProvider.nextUuid();

        for (Throwable suppressedException : exception.getSuppressed()) {
            exceptionLogger.log(uuid, suppressedException);
        }
        exceptionLogger.log(uuid, exception);

        return Response.status(500).build();
    }

}
