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

import com.beust.jcommander.JCommander;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;

/**
 *
 * @author richter
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //prepare configuration file
        File configFile = File.createTempFile("prefix", "suffix");
        Conf conf = new Conf();
        int a = 7;
        String b = "kdkd";
        List<String> c = new LinkedList<>(Arrays.asList("d1", "d2"));
        conf.setA(a);
        conf.setB(b);
        conf.setC(c);
        try {
            XStream xStream = new XStream();
            xStream.registerConverter(new Converter() {
                @Override
                public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
                }

                @Override
                public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                    return null;
                }

                @Override
                public boolean canConvert(Class type) {
                    return false;
                }
            });
            xStream.toXML(conf, new FileOutputStream(configFile));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        String[] args0 = new String[] {"--config-file", configFile.getAbsolutePath()};
        new JCommander(conf, args0);
        Assert.assertEquals(a, conf.getA());
        Assert.assertEquals(b, conf.getB());
        Assert.assertEquals(c, conf.getC());
        a = 8;
        args0 = new String[] {"--config-file", configFile.getAbsolutePath(), "-a", String.valueOf(a)};
        new JCommander(conf, args0);
        Assert.assertEquals(a, conf.getA());
        Assert.assertEquals(b, conf.getB());
        Assert.assertEquals(c, conf.getC());
    }
}
