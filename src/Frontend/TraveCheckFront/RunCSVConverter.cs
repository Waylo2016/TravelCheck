using System.IO;
using System.Xml.Linq;

namespace TraveCheckFront
{
    public  class RunCSVConverter
    {
        public int CSVConverter(string Filename, int returnvalue)
        {
            returnvalue = 0;
            var lines = File.ReadAllLines(Filename);
            string[] headers = lines[0].Split(',').Select(x => x.Trim('\"')).ToArray();

            var xml = new XElement("Werknemers",
               lines.Where((line, index) => index > 0).Select(line => new XElement("Werknemer",
                  line.Split(',').Select((column, index) => new XElement(headers[index], column)))));

            xml.Save(Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.UserProfile), "TravelCheck\\src\\Frontend\\TraveCheckFront\\XmlFolder\\XmlUnformedData.xml"));
            return returnvalue = 1;
        }
    }
}
