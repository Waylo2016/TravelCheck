import pandas as pd

def csvreaderxmlwriter(filename):
    file_path = r"C:\Users\Stijn\source\repos\Waylo2016\TravelCheck\src\XmlUnformedData.xml"  # Windows
    df = pd.read_csv(filename)
    with open(file_path, "x") as file:
    
        file.write(df.to_xml(index = False, root_name='Werknemers', row_name='Werknemer'))
        file.close
  



