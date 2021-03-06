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
package org.mockftpserver.fake.command

import org.mockftpserver.core.command.Command
import org.mockftpserver.core.command.CommandHandler
import org.mockftpserver.core.command.CommandNames
import org.mockftpserver.core.command.ReplyCodes

/**
 * Tests for SystCommandHandler
 *
 * @version $Revision: 243 $ - $Date: 2010-03-21 08:57:54 -0400 (Sun, 21 Mar 2010) $
 *
 * @author Chris Mair
 */
class SizeCommandHandlerTest extends AbstractFakeCommandHandlerTestCase {

    def DIR = "/"
    def FILENAME = "file.txt"
    def FILE = p(DIR, FILENAME)
    def CONTENTS = "abc\ndef\nghi"

    boolean testNotLoggedIn = false

    void testHandleCommand() {
        handleCommand([FILE])
        assertSessionReply(ReplyCodes.SIZE_OK, ['size', ''+CONTENTS.length()])
    }

    //-------------------------------------------------------------------------
    // Helper Methods
    //-------------------------------------------------------------------------

    void setUp() {
        super.setUp()
        createDirectory(DIR)
        createFile(FILE, CONTENTS)
    }

    CommandHandler createCommandHandler() {
        new SizeCommandHandler()
    }

    Command createValidCommand() {
        return new Command(CommandNames.SIZE, [FILE])
    }

}
