/*
 * Copyright 2008 the original author or authors.
 *
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
 */
package org.mockftpserver.core;

/**
 * Represents an error indicating that the current user has not yet logged in, but
 * is required to in order to invoke the requested command.
 *
 * @author Chris Mair
 * @version $Revision: 288 $ - $Date: 2016-10-13 21:28:23 -0400 (Thu, 13 Oct 2016) $
 */
public class NotLoggedInException extends MockFtpServerException {

    /**
     * @param message - the exception message
     */
    public NotLoggedInException(String message) {
        super(message);
    }

    /**
     * @param cause - the cause exception
     */
    public NotLoggedInException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message - the exception message
     * @param cause - the cause exception
     */
    public NotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }

}
