/*
 * Copyright (c) 2023-2024 Open Shop Channel
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this
 * program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.oscwii.repositorymanager.controllers.hbb;

import java.util.Objects;

/*
 * Some of the code for HBB support was originally written by Spotlight for Open Shop Channel's "Danbo Shop Server".
 * Ported to Java by Artuto.
 *
 * In the case of the Homebrew Browser, responses _must_ end with CRLF
 * Otherwise, the browser will horribly fail parsing.
 */
public class HBBResponse
{
    private String response = "";

    public HBBResponse append(String str)
    {
        this.response += str + (str.isEmpty() ? "" : " ");
        return this;
    }

    public HBBResponse append(Object obj)
    {
        return append(Objects.toString(obj));
    }

    public void appendLine(String str)
    {
        this.response += str + "\r\n";
    }

    public void appendLine(Object obj)
    {
        appendLine(Objects.toString(obj));
    }

    @Override
    public String toString()
    {
        return response;
    }

    public static final String START_LINE =
            "Homebrew 2092896 v0.3.9e | - Updated with latest libogc which should correct network issues some users are experiencing";
}
