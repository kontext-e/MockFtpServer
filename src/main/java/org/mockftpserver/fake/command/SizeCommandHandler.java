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
package org.mockftpserver.fake.command;

import org.mockftpserver.core.command.Command;
import org.mockftpserver.core.command.ReplyCodes;
import org.mockftpserver.core.session.Session;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystemEntry;

/**
 * CommandHandler for the LIST command. Handler logic:
 * <ol>
 * <li>If the user has not logged in, then reply with 530 and terminate</li>
 * <li>If the current user does not have read access to the file or directory to be listed, then reply with 550 and terminate</li>
 * <li>If an error occurs during processing, then send a reply of 451 and terminate</li>
 * <li>Send an initial reply of 150</li>
 * <li>If the optional pathname parameter is missing, then send a directory listing for
 * the current directory across the data connection</li>
 * <li>Otherwise, if the optional pathname parameter specifies a directory or group of files,
 * then send a directory listing for the specified directory across the data connection</li>
 * <li>Otherwise, if the optional pathname parameter specifies a filename, then send information
 * for the specified file across the data connection</li>
 * <li>Send a final reply with 226</li>
 * </ol>
 *
 * @author Chris Mair
 * @version $Revision: 290 $ - $Date: 2016-12-04 10:13:44 -0500 (Sun, 04 Dec 2016) $
 */
public class SizeCommandHandler extends AbstractFakeCommandHandler {

    protected void handle(Command command, Session session) {
        verifyLoggedIn(session);
        this.replyCodeForFileSystemException = ReplyCodes.READ_FILE_ERROR;

        String path = getRealPath(session, command.getRequiredParameter(0));
        FileSystemEntry entry = getFileSystem().getEntry(path);
        verifyFileSystemCondition(entry != null, path, "filesystem.doesNotExist");
        verifyFileSystemCondition(!entry.isDirectory(), path, "filesystem.isNotAFile");
        FileEntry fileEntry = (FileEntry) entry;

        // User must have read permission to the file
        verifyReadPermission(session, path);

        // User must have execute permission to the parent directory
        verifyExecutePermission(session, getFileSystem().getParent(path));

        final Long size = fileEntry.getSize();
        sendReply(session, ReplyCodes.SIZE_OK, "size", list(size));
    }

}
