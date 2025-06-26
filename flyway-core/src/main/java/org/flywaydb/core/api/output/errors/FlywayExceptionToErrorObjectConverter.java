/*-
 * ========================LICENSE_START=================================
 * flyway-core
 * ========================================================================
 * Copyright (C) 2010 - 2025 Red Gate Software Ltd
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package org.flywaydb.core.api.output.errors;

import java.util.Optional;
import org.flywaydb.core.api.ErrorCode;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.output.errors.FlywayExceptionToErrorObjectConverter.FlywayErrorOutputItem;

public class FlywayExceptionToErrorObjectConverter extends ExceptionToErrorObjectConverter<FlywayException, FlywayErrorOutputItem> {
    public record FlywayErrorOutputItem(ErrorCode errorCode, String message, ErrorCause cause) implements
                                                                                               ErrorOutputItem {}

    @Override
    public Class<FlywayException> getSupportedExceptionType() {
        return FlywayException.class;
    }

    @Override
    public FlywayErrorOutputItem convert(final FlywayException exception) {
        return new FlywayErrorOutputItem(exception.getErrorCode(),
            Optional.ofNullable(exception.getMessage()).orElse("Error occurred"),
            getCause(exception).orElse(null));
    }
}
