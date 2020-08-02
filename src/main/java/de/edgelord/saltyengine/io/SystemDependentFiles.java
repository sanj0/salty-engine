/*
 * Copyright 2020 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.io;

import java.io.File;

/**
 * This class contains a couple of methods for getting Files or paths depending
 * on the current user and the Operating System.
 * <p>
 * Tested and working on: <code>macOS</code>
 * <p>
 */
public class SystemDependentFiles {

    private static final String macOSSearchPhrase = "mac";
    private static final String linuxSearchPhrase = "linux";
    private static final String windowsSearchPhrase = "windows";
    private static final String macOSUserDirPrefix = "/Users/";
    private static final String linuxUserDirPrefix = "/home/";
    private static final String windowsUserDir = System.getProperty("user.home");
    private static final String windowsSystemPath = "C:/";
    private static OS os = null;
    private static String user = null;

    private SystemDependentFiles() {
    }

    /**
     * This method returns the current user's home directory as a File object
     * depending on the Operating System. For example, on linux, with "joe" as
     * the current user, this method would return a
     * <code>File</code> with the absolute path
     * <code>/home/joe/</code>.
     *
     * @return the home directory of the current user as a <code>File</code>.
     */
    public static File getUserDir() {
        return new File(getUserDirPath());
    }

    /**
     * This method returns the path to the absolute path of the home directory
     * of the current user depending on the Operating System. For example, on
     * macOS, with the current user being "joe", this method would return
     * <code>"/Users/joe/"</code>.
     *
     * @return the absolute path of the home directory of the current user.
     */
    public static String getUserDirPath() {

        checkOS();
        checkUser();

        switch (os) {
            case MAC_OS:
                return macOSUserDirPrefix + user + "/";
            case LINUX:
                return linuxUserDirPrefix + user + "/";
            case WINDOWS:
                return windowsUserDir + "/";
        }

        return "lorem/ipsum";
    }

    /**
     * This method returns the <code>File</code> that belongs to the given
     * relative path from the current user's home directory. For example, if the
     * current user is "joe", the OS is linux and the relative path is
     * "lorem/ipsum.foo", this would return a
     * <code>File</code> with the absolute path
     * "/home/joe/lorem/ipsum.foo".
     *
     * @param relativePath the path of the <code>File</code> relative to the
     *                     user directory
     *
     * @return the <code>File</code> from the user's directory that belongs to
     * the given relative path.
     */
    public static File getUserFile(final String relativePath) {
        return new File(getUserDirPath() + relativePath);
    }

    /**
     * This method return the <code>File</code> from the directory below the
     * user's directory that belongs to the given relative path. For example: If
     * the OS is linux or macOS and the relative path is "usr/share", this
     * method would return a file with the absolute path
     * <code>"/usr/share"</code> (notice the "/" in front which wouldn't be
     * correct on a windows system).
     *
     * @param relativePath the path relative to the directory below the user's
     *                     directory.
     *
     * @return the file from the directory below the user's directory that
     * belongs to the given relative path
     */
    public static File getSystemFile(final String relativePath) {

        checkOS();

        switch (os) {

            case MAC_OS:
            case LINUX:
                return new File("/" + relativePath);
            case WINDOWS:
                return new File(windowsSystemPath + relativePath);
        }

        return new File("/lorem/ipsum");
    }

    /**
     * Checks for the os and then returns its type. On a Windows computer for
     * example, this method would check the os and would most likely return
     * <code>OS.windows</code>.
     *
     * @return the operating system of the computer that is running this
     */
    public static OS getOS() {
        checkOS();

        return os;
    }

    private static void checkOS() {
        if (os == null) {

            if (System.getProperty("os.name").toLowerCase().contains(macOSSearchPhrase)) {
                os = OS.MAC_OS;
            } else if (System.getProperty("os.name").toLowerCase().contains(linuxSearchPhrase)) {
                os = OS.LINUX;
            } else if (System.getProperty("os.name").toLowerCase().contains(windowsSearchPhrase)) {
                os = OS.WINDOWS;
            } else {
                // If the os is none of the three, it is most likely something like openSuse or anything like that
                // for which linux is probably the most similar
                os = OS.LINUX;
            }
        }
    }

    private static void checkUser() {
        if (user == null) {
            user = System.getProperty("user.name");
        }
    }

    public enum OS {
        MAC_OS,
        LINUX,
        WINDOWS
    }
}
