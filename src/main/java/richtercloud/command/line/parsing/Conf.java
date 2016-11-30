/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package richtercloud.command.line.parsing;

import com.beust.jcommander.Parameter;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author richter
 */
public class Conf {
    private final static int A_DEFAULT = 1;
    private final static String B_DEFAULT = "b";
    private final static List<String> C_DEFAULT = new LinkedList<>(Arrays.asList("c1", "c2"));
    private final static File CONFIG_FILE_DEFAULT = new File("/inexisting");
    @Parameter(names = {"-a"})
    private int a = A_DEFAULT;
    @Parameter(names = {"-b"})
    private String b = B_DEFAULT;
    @Parameter(names = {"-c"})
    private List<String> c = C_DEFAULT;
    @Parameter(names = {"--config-file"})
    private final File configFile = CONFIG_FILE_DEFAULT;

    public Conf() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public List<String> getC() {
        return c;
    }

    public void setC(List<String> c) {
        this.c = c;
    }

    public File getConfigFile() {
        return configFile;
    }
}
